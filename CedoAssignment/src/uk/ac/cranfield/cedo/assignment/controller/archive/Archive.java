package uk.ac.cranfield.cedo.assignment.controller.archive;

import java.util.Comparator;
import java.util.PriorityQueue;

import uk.ac.cranfield.cedo.assignment.model.DesignVector;


public abstract class Archive
{
    
    protected PriorityQueue<DesignVector> bestSolutions;
    protected int size;
    
    
    public abstract void checkData(double value, double x1, double x2);
    
    public abstract void addSolution(DesignVector vector);
    
    public abstract double getBestSolution();
    
    public abstract double getWorstSolution();
    
    public Archive(int size)
    {
        this.size = size;
        bestSolutions = new PriorityQueue<DesignVector>(size, new DesignVectorComparator());
        for (int i = 0; i < size; i++)
            bestSolutions.add(new DesignVector());
        
    }
    
    
    /**
     * @return the bestSolutions
     */
    public final PriorityQueue<DesignVector> getBestSolutions()
    {
        return bestSolutions;
    }
    
    protected class DesignVectorComparator implements Comparator<DesignVector>
    {
        
        @Override
        public int compare(DesignVector o1, DesignVector o2)
        {
            if (o1.getObjFunctionValue() < o2.getObjFunctionValue())
                return -1;
            if (o1.getObjFunctionValue() > o2.getObjFunctionValue())
                return 1;
            return 0;
        }
        
    }
}
