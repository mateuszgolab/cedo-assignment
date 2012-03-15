package uk.ac.cranfield.cedo.assignment;

import uk.ac.cranfield.cedo.assignment.controller.BiasedMonteCarloSolution;


public class Main
{
    
    public static void main(String args[])
    {
        BiasedMonteCarloSolution bmcs = new BiasedMonteCarloSolution(1000);
        bmcs.initialize();
        bmcs.assignRanks();
        bmcs.assignProbabilities();
        bmcs.startMonteCarlo();
    }
}
