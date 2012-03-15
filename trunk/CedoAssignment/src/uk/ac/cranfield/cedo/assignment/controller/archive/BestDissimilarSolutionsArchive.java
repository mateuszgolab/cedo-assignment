package uk.ac.cranfield.cedo.assignment.controller.archive;

import uk.ac.cranfield.cedo.assignment.model.DesignVector;


public class BestDissimilarSolutionsArchive extends Archive
{
    
    private double Dmin;
    private double Dsim;
    private DesignVector worstSolution;
    private DesignVector bestSolution;
    
    public BestDissimilarSolutionsArchive(int size, double Dmin, double Dsim)
    {
        super(size);
        this.Dmin = Dmin;
        this.Dsim = Dsim;
        
        worstSolution = new DesignVector();
        bestSolution = new DesignVector();
        
    }
    
    @Override
    public void addSolution(DesignVector vector)
    {
        if (vector.getObjFunctionValue() > bestSolution.getObjFunctionValue())
        {
            bestSolution = vector;
        }
        
        bestSolutions.poll();
        bestSolutions.add(vector);
        worstSolution = bestSolutions.peek();
        
    }
    
    @Override
    public void checkData(double value, double pen, double x1, double x2)
    {
        if (worstSolution.getObjFunctionValue() < value)
        {
            double distance = 10.0;
            DesignVector xk = null;
            
            for (DesignVector v : bestSolutions)
            {
                double tmp = Math.sqrt(Math.pow(v.getX1() - x1, 2) + Math.pow(v.getX2() - x2, 2));
                if (tmp < distance)
                {
                    distance = tmp;
                    xk = v;
                }
                
            }
            
            if (distance > Dmin)
            {
                addSolution(new DesignVector(x1, x2, value, pen));
            }
            else
            {
                if (distance < Dsim)
                {
                    if (value > xk.getObjFunctionValue())
                    {
                        xk.setObjFunction(value);
                        xk.setX1(x1);
                        xk.setX2(x2);
                        xk.setPenalty(pen);
                        
                        if (value > bestSolution.getObjFunctionValue())
                        {
                            bestSolution = xk;
                        }
                    }
                }
                else
                {
                    if (value > bestSolution.getObjFunctionValue())
                    {
                        DesignVector xn = new DesignVector(x1, x2, value, pen);
                        bestSolution = xn;
                        bestSolutions.remove(xk);
                        bestSolutions.add(xn);
                        worstSolution = bestSolutions.peek();
                        
                    }
                }
            }
            
        }
        
    }
    
    @Override
    public double getBestSolution()
    {
        return bestSolution.getObjFunctionValue();
    }
    
    
    @Override
    public double getWorstSolution()
    {
        return worstSolution.getObjFunctionValue();
    }
}
