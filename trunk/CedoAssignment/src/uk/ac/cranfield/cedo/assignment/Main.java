package uk.ac.cranfield.cedo.assignment;

import uk.ac.cranfield.cedo.assignment.controller.BiasedMonteCarloSolution;
import uk.ac.cranfield.cedo.assignment.controller.archive.Archive;
import uk.ac.cranfield.cedo.assignment.controller.archive.BestDissimilarSolutionsArchive;
import uk.ac.cranfield.cedo.assignment.controller.archive.BestSolutionsArchive;


public class Main
{
    
    public static void main(String args[])
    {
        // trialsMeasurement(1, new BestSolutionsArchive(10), true);
        // trialsMeasurement(1, new BestDissimilarSolutionsArchive(10, 1.0, 0.05), true);
        // 1.0, 0.05));
        // regionsMeasurement(50, 100);
        // dminMeasurement(10, 1.1, 0.055);
        // dminMeasurement(20, 0.1);
        dsimMeasurement(10, 0.01);
    }
    
    public static void trialsMeasurement(int n, Archive a, boolean show)
    {
        double best = 0.0;
        double avg = 0.0;
        long time = System.currentTimeMillis();
        
        for (int i = 0; i < n; i++)
        {
            BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, a, 20);
            bmcs.initialize();
            bmcs.assignRanks();
            bmcs.assignProbabilities();
            double tmp = bmcs.performMonteCarlo(show);
            if (tmp > best)
                best = tmp;
            avg += tmp;
        }
        
        // System.out.println(best);
        // // System.out.println(avg / n);
        // System.out.println((System.currentTimeMillis() - time) / n);
    }
    
    public static void regionsMeasurement(int n, int iterations)
    {
        for (int i = 10; i <= n; i += 10)
        {
            long time = System.currentTimeMillis();
            double best = 0.0;
            double avg = 0.0;
            for (int j = 0; j < iterations; j++)
            {
                // BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new BestSolutionsArchive(10), i);
                BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new BestDissimilarSolutionsArchive(
                        10, 1.0, 0.05), i);
                bmcs.initialize();
                bmcs.assignRanks();
                bmcs.assignProbabilities();
                
                double tmp = bmcs.performMonteCarlo(false);
                if (tmp > best)
                    best = tmp;
                avg += tmp;
                
                
            }
            
            System.out.println("best = " + best);
            System.out.println("average = " + avg / iterations);
            System.out.println("number of regions = " + i * i);
            System.out.println((System.currentTimeMillis() - time) / iterations);
            System.out.println("----------------------------------");
        }
    }
    
    public static void sPressureMeasurement(int n, int iterations)
    {
        for (int i = 10; i <= n; i += 10)
        {
            long time = System.currentTimeMillis();
            double best = 0.0;
            double avg = 0.0;
            for (int j = 0; j < iterations; j++)
            {
                BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new BestSolutionsArchive(10), i);
                // BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new
                // BestDissimilarSolutionsArchive(
                // 10, 1.0, 0.05), i);
                bmcs.initialize();
                bmcs.assignRanks();
                bmcs.assignProbabilities();
                
                double tmp = bmcs.performMonteCarlo(false);
                if (tmp > best)
                    best = tmp;
                avg += tmp;
                
                
            }
            
            System.out.println("best = " + best);
            System.out.println("average = " + avg / iterations);
            System.out.println("number of regions = " + i * i);
            System.out.println((System.currentTimeMillis() - time) / iterations);
            System.out.println("----------------------------------");
        }
    }
    
    public static void dminMeasurement(int n, double dMinStart)
    {
        for (int i = 0; i < n; i++)
        {
            double best = 0.0;
            double avg = 0.0;
            long time = System.currentTimeMillis();
            for (int j = 0; j < 100; j++)
            {
                
                
                BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new BestDissimilarSolutionsArchive(
                        10, dMinStart, 0.05), 20);
                bmcs.initialize();
                bmcs.assignRanks();
                bmcs.assignProbabilities();
                double tmp = bmcs.performMonteCarlo(false);
                if (tmp > best)
                    best = tmp;
                avg += tmp;
            }
            
            System.out.println("Dmin = " + dMinStart + " Dsim " + 0.05);
            System.out.println(best);
            System.out.println(avg / 100);
            System.out.println((System.currentTimeMillis() - time) / 100);
            dMinStart += 0.1;
            
        }
    }
    
    public static void dsimMeasurement(int n, double dSimStart)
    {
        for (int i = 0; i < n; i++)
        {
            double best = 0.0;
            double avg = 0.0;
            long time = System.currentTimeMillis();
            for (int j = 0; j < 100; j++)
            {
                
                
                BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000, new BestDissimilarSolutionsArchive(
                        10, 1.0, dSimStart), 20);
                bmcs.initialize();
                bmcs.assignRanks();
                bmcs.assignProbabilities();
                double tmp = bmcs.performMonteCarlo(false);
                if (tmp > best)
                    best = tmp;
                avg += tmp;
            }
            
            System.out.println("Dmin = " + 1.0 + " Dsim " + dSimStart);
            System.out.println(best);
            System.out.println(avg / 100);
            System.out.println((System.currentTimeMillis() - time) / 100);
            dSimStart += 0.01;
            
        }
    }
}
