package uk.ac.cranfield.cedo.assignment.controller;

import java.util.Random;

import uk.ac.cranfield.cedo.assignment.controller.archive.Archive;
import uk.ac.cranfield.cedo.assignment.model.Mesh;
import uk.ac.cranfield.cedo.assignment.model.Region;
import uk.ac.cranfield.cedo.assignment.view.SolutionView;

public class BiasedMonteCarloSolution
{
    
    public static double ADD_CONSTRAINT = 15;
    public static double MUL_CONSTRAINT = 0.75;
    public static int SIZE = 10;
    public static int NUMBER_OF_DISCRETIZATION = 20;
    public static int NUMBER_OF_SURVEYS = 5;
    
    private Mesh mesh;
    private double Dmin;
    private double Dsim;
    private double surveys;
    private double sPressure;
    private int archiveSize;
    private Random random;
    private Archive archive;
    private int trials;
    private SolutionView view;
    private double p1;
    private double p2;
    
    public BiasedMonteCarloSolution(int numberOfTrials, Archive archive)
    {
        mesh = new Mesh(NUMBER_OF_DISCRETIZATION * NUMBER_OF_DISCRETIZATION);
        random = new Random(System.currentTimeMillis());
        this.archive = archive;
        view = new SolutionView();
        trials = numberOfTrials;
        Dmin = 1.0;
        Dsim = 0.05;
        surveys = 5;
        archiveSize = 10;
        sPressure = 1.0;
        p1 = 10.0;
        p2 = 1.0;
        
    }
    
    public void initialize()
    {
        double step = SIZE;
        step /= NUMBER_OF_DISCRETIZATION;
        
        
        for (double i = 0.0; i < SIZE; i += step)
        {
            for (double j = 0.0; j < SIZE; j += step)
            {
                double avgObjFunVal = calculateAvgObjFunction(i, j, step, NUMBER_OF_SURVEYS);
                
                mesh.addRegion(new Region(i, j, step, avgObjFunVal));
            }
        }
    }
    
    public void assignRanks()
    {
        mesh.sort();
    }
    
    public void assignProbabilities()
    {
        mesh.calculateProbability(sPressure);
    }
    
    public double performMonteCarlo()
    {
        for (int j = 0; j < 10; j++)
        {
            
            for (int i = 0; i < trials; i++)
            {
                Region r = mesh.getRegion(random.nextDouble());
                
                double avgObjFunVal = calculateAvgObjFunction(r.getX1(), r.getX2(), r.getSize(), NUMBER_OF_SURVEYS);
                
                mesh.setRegion(r.getRank(), avgObjFunVal);
            }
            
            sPressure += 0.1;
            p1 += 5;
            assignRanks();
            assignProbabilities();
        }
        view.setResults(archive.getBestSolutions());
        view.show();
        
        return archive.getBestSolution();
        
    }
    
    private double calculatePenaltyForObjFunction(double x1, double x2)
    {
        return -(p1 * Math.pow(Math.max(0.0, MUL_CONSTRAINT - x1 * x2), 2) + p2
                * Math.pow(Math.max(0.0, x1 + x2 - ADD_CONSTRAINT), 2));
    }
    
    private double calculateObjectiveFunction(double x1, double x2)
    {
        if (x1 * x2 <= MUL_CONSTRAINT || x1 + x2 >= ADD_CONSTRAINT)
            return 0.0;
        
        return Math.abs((Math.pow(Math.cos(x1), 4) + Math.pow(Math.cos(x2), 4) - 2 * (Math.pow(Math.cos(x1), 2))
                * (Math.pow(Math.cos(x2), 2)))
                / Math.sqrt(x1 * x1 + x2 * x2));
    }
    
    private double calculateAvgObjFunction(double x1, double x2, double size, int n)
    {
        double sum = 0.0;
        
        for (int i = 0; i < n; i++)
        {
            double x1r = x1 + size * random.nextDouble();
            double x2r = x2 + size * random.nextDouble();
            
            double val = calculateObjectiveFunction(x1r, x2r);
            double pen = calculatePenaltyForObjFunction(x1r, x2r);
            
            archive.checkData(val + pen, x1r, x2r);
            
            sum += val + pen;
            
        }
        
        return sum / n;
    }
}
