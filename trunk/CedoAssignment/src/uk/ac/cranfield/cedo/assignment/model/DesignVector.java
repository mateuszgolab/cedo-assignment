package uk.ac.cranfield.cedo.assignment.model;

public class DesignVector
{
    
    private double x1;
    private double x2;
    private double objFunction;
    private double penalty;
    
    public DesignVector()
    {
        this.x1 = -1;
        this.x2 = -1;
        this.objFunction = 0.0;
        this.penalty = 0.0;
    }
    
    public DesignVector(double x1, double x2, double objFunction, double penalty)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.objFunction = objFunction;
        this.penalty = penalty;
    }
    
    
    public double getObjFunctionValue()
    {
        return objFunction;
    }
    
    public double getObjFuncWithPenalty()
    {
        return objFunction + penalty;
    }
    
    
    /**
     * @return the x1
     */
    public final double getX1()
    {
        return x1;
    }
    
    
    /**
     * @return the x2
     */
    public final double getX2()
    {
        return x2;
    }
    
}
