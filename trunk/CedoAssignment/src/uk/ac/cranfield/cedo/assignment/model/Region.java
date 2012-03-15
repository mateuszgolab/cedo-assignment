package uk.ac.cranfield.cedo.assignment.model;


public class Region implements Comparable<Region>
{
    
    private double x1;
    private double x2;
    private double size;
    private double avgObjFunVal;
    private double pRangeFrom;
    private double pRangeTo;
    private int rank;
    
    
    public Region(double x1, double x2, double size, double avgObjFunVal)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.size = size;
        this.avgObjFunVal = avgObjFunVal;
    }
    
    
    @Override
    public int compareTo(Region o)
    {
        if (avgObjFunVal < o.getAvgObjFunVal())
            return -1;
        if (avgObjFunVal > o.getAvgObjFunVal())
            return 1;
        return 0;
        
    }
    
    public double getAvgObjFunVal()
    {
        return avgObjFunVal;
    }
    
    
    /**
     * @return the pRangeFrom
     */
    public final double getpRangeFrom()
    {
        return pRangeFrom;
    }
    
    
    /**
     * @param pRangeFrom the pRangeFrom to set
     */
    public final void setpRangeFrom(double pRangeFrom)
    {
        this.pRangeFrom = pRangeFrom;
    }
    
    
    /**
     * @return the pRangeTo
     */
    public final double getpRangeTo()
    {
        return pRangeTo;
    }
    
    
    /**
     * @param pRangeTo the pRangeTo to set
     */
    public final void setpRangeTo(double pRangeTo)
    {
        this.pRangeTo = pRangeTo;
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
     * @return the size
     */
    public final double getSize()
    {
        return size;
    }
    
    
    /**
     * @param avgObjFunVal the avgObjFunVal to set
     */
    public final void setAvgObjFunVal(double avgObjFunVal)
    {
        this.avgObjFunVal = avgObjFunVal;
    }
    
    
    /**
     * @return the rank
     */
    public final int getRank()
    {
        return rank;
    }
    
    
    /**
     * @param rank the rank to set
     */
    public final void setRank(int rank)
    {
        this.rank = rank;
    }
    
    
}
