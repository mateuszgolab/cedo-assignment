package uk.ac.cranfield.cedo.assignment.controller;

import java.util.Comparator;
import java.util.PriorityQueue;

import uk.ac.cranfield.cedo.assignment.model.DesignVector;


public class BestSolutionsArchive
{
    
    private PriorityQueue<DesignVector> bestSolutions;
    private int size;
    private double worstSolution;
    
    public BestSolutionsArchive(int size)
    {
        this.size = size;
        bestSolutions = new PriorityQueue<DesignVector>(size, new DesignVectorComparator());
        for (int i = 0; i < size; i++)
            bestSolutions.add(new DesignVector());
        
        worstSolution = 0.0;
    }
    
    private class DesignVectorComparator implements Comparator<DesignVector>
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
    
    public double getWorstSolution()
    {
        return worstSolution;
    }
    
    public void addSolution(DesignVector vector)
    {
        
        if (worstSolution < vector.getObjFunctionValue())
        {
            bestSolutions.poll();
            bestSolutions.add(vector);
            worstSolution = bestSolutions.peek().getObjFunctionValue();
            
            
        }
        
        // for (DesignVector d : bestSolutions)
        // System.out.print(d.getObjFunctionValue() + " ");
        // System.out.println();
    }
    
    
    /**
     * @return the bestSolutions
     */
    public final PriorityQueue<DesignVector> getBestSolutions()
    {
        return bestSolutions;
    }
}
