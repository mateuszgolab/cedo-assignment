package uk.ac.cranfield.cedo.assignment.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.cranfield.cedo.assignment.model.Mesh;
import uk.ac.cranfield.cedo.assignment.model.Region;


public class MeshTest
{
    
    @Test
    public void sortTest()
    {
        Mesh m = new Mesh(3);
        Region r = new Region(1, 1, 1, 3.0);
        Region r1 = new Region(1, 1, 1, 2.0);
        Region r2 = new Region(1, 1, 1, 1.0);
        
        m.addRegion(r);
        m.addRegion(r1);
        m.addRegion(r2);
        
        m.sort();
        
        assertEquals(m.getRegion(0).getAvgObjFunVal(), 1.0, 0.0);
        assertEquals(m.getRegion(1).getAvgObjFunVal(), 2.0, 0.0);
        assertEquals(m.getRegion(2).getAvgObjFunVal(), 3.0, 0.0);
        
        Region r3 = new Region(1, 1, 1, 0.0);
        Region r4 = new Region(1, 1, 1, 5.0);
        Region r5 = new Region(1, 1, 1, 7.0);
        m.addRegion(r3);
        m.addRegion(r4);
        m.addRegion(r5);
        
        m.sort();
        
        assertEquals(m.getRegion(0).getAvgObjFunVal(), 0.0, 0.0);
        assertEquals(m.getRegion(1).getAvgObjFunVal(), 1.0, 0.0);
        assertEquals(m.getRegion(2).getAvgObjFunVal(), 2.0, 0.0);
        assertEquals(m.getRegion(3).getAvgObjFunVal(), 3.0, 0.0);
        assertEquals(m.getRegion(4).getAvgObjFunVal(), 5.0, 0.0);
        assertEquals(m.getRegion(5).getAvgObjFunVal(), 7.0, 0.0);
        
        
    }
}
