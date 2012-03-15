package uk.ac.cranfield.cedo.assignment.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mesh
{
    
    private int size;
    private List<Region> regions;
    
    
    public Mesh(int size)
    {
        regions = new ArrayList<Region>();
        this.size = size;
        
    }
    
    public Mesh()
    {
        regions = new ArrayList<Region>();
        this.size = -1;
    }
    
    public int getSize()
    {
        if (size == -1)
            return regions.size();
        return size;
    }
    
    public void addRegion(Region region)
    {
        regions.add(region);
    }
    
    public void sort()
    {
        Collections.sort(regions);
        for (int i = 0; i < regions.size(); i++)
            regions.get(i).setRank(i + 1);
    }
    
    public Region getRegion(int i)
    {
        return regions.get(i);
    }
    
    public void calculateProbability(double sPressure)
    {
        double p = 0.0;
        for (Region r : regions)
        {
            r.setpRangeFrom(p);
            p += (sPressure * (size + 1 - 2 * r.getRank()) + 2 * (r.getRank() - 1)) / (size * (size - 1));
            r.setpRangeTo(p);
        }
    }
    
    public Region getRegion(double p)
    {
        int begin = 0;
        int end = size;
        
        
        if (p < 0.0 || p > 1.0)
            return null;
        
        while (true)
        {
            int i = begin + (end - begin) / 2;
            Region r = regions.get(i);
            if (r.getpRangeFrom() <= p && r.getpRangeTo() >= p)
                return r;
            if (r.getpRangeFrom() > p)
                end = i;
            else
                begin = i;
        }
    }
    
    public void setRegion(int i, double val)
    {
        regions.get(i - 1).setAvgObjFunVal(val);
    }
}
