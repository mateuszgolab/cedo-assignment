package uk.ac.cranfield.cedo.assignment.view;

import java.util.PriorityQueue;

import uk.ac.cranfield.cedo.assignment.model.DesignVector;


public class SolutionView
{
    
    private PriorityQueue<DesignVector> results;
    
    
    public SolutionView()
    {
        results = new PriorityQueue<DesignVector>();
    }
    
    public void setResults(PriorityQueue<DesignVector> res)
    {
        results = res;
    }
    
    public void show()
    {
        int i = 10;
        
        while (!results.isEmpty())
        {
            DesignVector v = results.poll();
            
            System.out.println(i + " th solution");
            System.out.println("x1 : " + v.getX1());
            System.out.println("x2 : " + v.getX2());
            System.out.println("value : " + v.getObjFunctionValue());
            System.out.println("=================================");
            
            i--;
        }
    }
}
