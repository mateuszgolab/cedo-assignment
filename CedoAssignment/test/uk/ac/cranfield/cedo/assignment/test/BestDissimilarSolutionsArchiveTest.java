package uk.ac.cranfield.cedo.assignment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import uk.ac.cranfield.cedo.assignment.controller.archive.BestDissimilarSolutionsArchive;


public class BestDissimilarSolutionsArchiveTest
{
    
    @Test
    public void emptyArchiveTest()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(10, 1.0, 0.05);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
    }
    
    @Test
    public void equalsValues()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        
        archive.checkData(2.0, 0.0, 1, 1);
        assertNotSame(archive.getWorstSolution(), 2.0);
        
        archive.checkData(2.0, 0.0, 2, 2);
        assertNotSame(archive.getWorstSolution(), 2.0);
        
        archive.checkData(2.0, 0.0, 3, 3);
        assertEquals(archive.getWorstSolution(), 2.0, 0.0);
        
    }
    
    @Test
    public void minValue()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        archive.checkData(0.0, 0.0, 1, 1);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        archive.checkData(2.0, 0.0, 2, 2);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        archive.checkData(3.0, 0.0, 3, 3);
        assertEquals(archive.getWorstSolution(), 0.0, 0.0);
        
        archive.checkData(4.0, 0.0, 4, 4);
        assertEquals(archive.getWorstSolution(), 2.0, 0.0);
        
    }
    
    @Test
    public void minValueOrder()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        archive.checkData(0.0, 0.0, 1, 1);
        archive.checkData(1.0, 0.0, 2, 2);
        archive.checkData(2.0, 0.0, 3, 3);
        assertEquals(archive.getWorstSolution(), 0, 0.0);
        
        BestDissimilarSolutionsArchive archive2 = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        archive2.checkData(2.0, 0.0, 4, 4);
        archive2.checkData(1.0, 0.0, 5, 5);
        archive2.checkData(0.0, 0.0, 6, 6);
        assertEquals(archive2.getWorstSolution(), 0, 0.0);
        
        archive2.checkData(4.0, 0.0, 7, 7);
        assertEquals(archive2.getWorstSolution(), 1.0, 0.0);
        
    }
    
    @Test
    public void greaterThanDminTest()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        archive.checkData(1.0, 0.0, 1, 1);
        archive.checkData(2.0, 0.0, 2, 2);
        archive.checkData(3.0, 0.0, 3, 3);
        
        archive.checkData(0.5, 0.0, 4, 4);
        assertEquals(archive.getWorstSolution(), 1.0, 0.0);
        
        
        archive.checkData(1.5, 0.0, 4, 4);
        assertEquals(archive.getWorstSolution(), 1.5, 0.0);
        
    }
    
    @Test
    public void lessThanDminGreaterThanDsimTest()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        archive.checkData(1.0, 0.0, 1, 1);
        archive.checkData(2.0, 0.0, 2, 2);
        archive.checkData(3.0, 0.0, 3, 3);
        
        archive.checkData(1.5, 0.0, 1.5, 1.5);
        assertEquals(archive.getWorstSolution(), 1.0, 0.0);
        
        
        archive.checkData(3.5, 0.0, 1.5, 1.5);
        assertEquals(archive.getWorstSolution(), 2.0, 0.0);
        
    }
    
    @Test
    public void lessThanDsimTest()
    {
        BestDissimilarSolutionsArchive archive = new BestDissimilarSolutionsArchive(3, 1.0, 0.05);
        archive.checkData(1.0, 0.0, 1, 1);
        archive.checkData(2.0, 0.0, 2, 2);
        archive.checkData(3.0, 0.0, 3, 3);
        
        archive.checkData(0.5, 0.0, 1.1, 1.1);
        assertEquals(archive.getWorstSolution(), 1.0, 0.0);
        
        
        archive.checkData(3.5, 0.0, 1.1, 1.1);
        assertEquals(archive.getWorstSolution(), 2.0, 0.0);
        
    }
    
}
