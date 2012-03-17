package uk.ac.cranfield.cedo.assignment.model;

public class DesignVector
{
    
    private double x1;
    private double x2;
    private double objFunction;
    private int region;
    
    public DesignVector()
    {
        this.x1 = -10;
        this.x2 = -10;
        this.objFunction = 0.0;
    }
    
    public DesignVector(double x1, double x2, double objFunction)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.objFunction = objFunction;
    }
    
    
    public double getObjFunctionValue()
    {
        return objFunction;
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
    
    
    /**
     * @param x1 the x1 to set
     */
    public final void setX1(double x1)
    {
        this.x1 = x1;
    }
    
    
    /**
     * @param x2 the x2 to set
     */
    public final void setX2(double x2)
    {
        this.x2 = x2;
    }
    
    
    /**
     * @param objFunction the objFunction to set
     */
    public final void setObjFunction(double objFunction)
    {
        this.objFunction = objFunction;
    }
    
    
    /**
     * @param region the region to set
     */
    public final void setRegion(int region)
    {
        this.region = region;
    }
    
    
    /**
     * @return the region
     */
    public final int getRegion()
    {
        return region;
    }
    
    
}
