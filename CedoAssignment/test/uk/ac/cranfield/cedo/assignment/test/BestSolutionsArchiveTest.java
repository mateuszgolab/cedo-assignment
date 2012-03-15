package uk.ac.cranfield.cedo.assignment.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import uk.ac.cranfield.cedo.assignment.controller.BestSolutionsArchive;
import uk.ac.cranfield.cedo.assignment.model.DesignVector;


public class BestSolutionsArchiveTest
{
    
    
    @Test
    public void emptyArchiveTest()
    {
        BestSolutionsArchive archive = new BestSolutionsArchive(10);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
    }
    
    @Test
    public void equalsValues()
    {
        BestSolutionsArchive archive = new BestSolutionsArchive(3);
        
        DesignVector v1 = new DesignVector(1, 1, 2.0, 0);
        archive.addSolution(v1);
        assertNotSame(archive.getWorstSolution(), 2.0);
        
        DesignVector v2 = new DesignVector(1, 1, 2.0, 0);
        archive.addSolution(v2);
        assertNotSame(archive.getWorstSolution(), 2.0);
        
        DesignVector v3 = new DesignVector(1, 1, 2.0, 0);
        archive.addSolution(v3);
        assertEquals(archive.getWorstSolution(), 2.0, 0.0);
        
    }
    
    @Test
    public void minValue()
    {
        BestSolutionsArchive archive = new BestSolutionsArchive(3);
        
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        DesignVector v1 = new DesignVector(1, 1, 0.0, 0);
        archive.addSolution(v1);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        DesignVector v2 = new DesignVector(1, 1, 2.0, 0);
        archive.addSolution(v2);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        DesignVector v3 = new DesignVector(1, 1, 3.0, 0);
        archive.addSolution(v3);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        DesignVector v4 = new DesignVector(1, 1, 4.0, 0);
        archive.addSolution(v4);
        assertEquals(archive.getWorstSolution(), 2.0, 0.0);
        
    }
    
    @Test
    public void minValueOrder()
    {
        BestSolutionsArchive archive = new BestSolutionsArchive(3);
        DesignVector v1 = new DesignVector(1, 1, 0.0, 0);
        DesignVector v2 = new DesignVector(1, 1, 1.0, 0);
        DesignVector v3 = new DesignVector(1, 1, 2.0, 0);
        archive.addSolution(v1);
        archive.addSolution(v2);
        archive.addSolution(v3);
        assertEquals(archive.getWorstSolution(), 0, 0.0);
        
        BestSolutionsArchive archive2 = new BestSolutionsArchive(3);
        archive2.addSolution(v3);
        archive2.addSolution(v2);
        archive2.addSolution(v1);
        assertEquals(archive2.getWorstSolution(), 0, 0.0);
        
        DesignVector v4 = new DesignVector(1, 1, 4.0, 0);
        archive2.addSolution(v4);
        assertEquals(archive2.getWorstSolution(), 1.0, 0.0);
        
        
    }
    
    
}
