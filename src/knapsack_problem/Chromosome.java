/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack_problem;

import java.util.ArrayList;

/**
 *
 * @author Beido
 */
public class Chromosome {

    public ArrayList<Integer> chromosome;
    int weight  ;
    float min, max , fitness;

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public Chromosome() {
        weight = 0 ;
        fitness = 0 ;
    }
    
    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }

    public void setChromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
    
   
    
}
