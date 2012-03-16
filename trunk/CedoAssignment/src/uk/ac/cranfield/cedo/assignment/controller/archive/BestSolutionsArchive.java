package uk.ac.cranfield.cedo.assignment.controller.archive;

import uk.ac.cranfield.cedo.assignment.model.DesignVector;


public class BestSolutionsArchive extends Archive
{
    
    private double worstSolution;
    
    public BestSolutionsArchive(int size)
    {
        super(size);
        worstSolution = 0.0;
    }
    
    @Override
    public double getWorstSolution()
    {
        return worstSolution;
    }
    
    
    @Override
    public void addSolution(DesignVector vector)
    {
        
        if (worstSolution < vector.getObjFunctionValue())
        {
            bestSolutions.poll();
            bestSolutions.add(vector);
            worstSolution = bestSolutions.peek().getObjFunctionValue();
            
        }
    }
    
    
    @Override
    public void checkData(double value, double x1, double x2)
    {
        if (worstSolution < value)
        {
            addSolution(new DesignVector(x1, x2, value));
        }
    }
    
    @Override
    public double getBestSolution()
    {
        double result = 0.0;
        
        while (!bestSolutions.isEmpty())
        {
            result = bestSolutions.poll().getObjFunctionValue();
        }
        
        return result;
    }
    
    
}
