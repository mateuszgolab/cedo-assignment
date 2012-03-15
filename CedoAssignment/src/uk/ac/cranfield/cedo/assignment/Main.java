package uk.ac.cranfield.cedo.assignment;

import uk.ac.cranfield.cedo.assignment.controller.BiasedMonteCarloSolution;
import uk.ac.cranfield.cedo.assignment.controller.archive.BestDissimilarSolutionsArchive;


public class Main
{
    
    public static void main(String args[])
    {
        double best = 0.0;
        double avg = 0.0;
        double n = 100;
        long time = System.currentTimeMillis();
        
        for (int i = 0; i < n; i++)
        {
            // BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new BestSolutionsArchive(10));
            BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(20000, new BestDissimilarSolutionsArchive(10,
                    1.0, 0.05));
            bmcs.initialize();
            bmcs.assignRanks();
            bmcs.assignProbabilities();
            double tmp = bmcs.performMonteCarlo();
            if (tmp > best)
                best = tmp;
            avg += tmp;
        }
        
        System.out.println(best);
        System.out.println(avg / n);
        System.out.println((System.currentTimeMillis() - time) / n);
    }
}
