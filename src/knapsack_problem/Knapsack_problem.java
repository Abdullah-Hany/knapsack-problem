/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack_problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import java.util.Scanner;

/**
 *
 * @author Beido
 */
public class Knapsack_problem {

    /**
     * @param args the command line arguments
     */
   

    public static void main(String[] args) {
        
       
        Scanner s = new Scanner(System.in);
         
        System.out.println("enter the number of items ");
        int number_of_genes = s.nextInt();
        
        System.out.println("enter the max size of the knapsack : ");
        int knapsack_size = s.nextInt();
        
        System.out.println("enter the number of iterations: ");
        int number_of_iterations = s.nextInt();
        
        System.out.println("enter the number of chromosomes    : ");
        int chromosome_number = s.nextInt();
        
        System.out.println("enter the probability of crossover : ");
        float crossover_prob = s.nextFloat();
        
        if (crossover_prob > 1)
        {
            crossover_prob = crossover_prob/100;
        }
        
        
        System.out.println("enter the probability of mutation  : ");
        float mutation_prob = s.nextFloat();
        
        if (mutation_prob > 1)
        {
            mutation_prob = mutation_prob/100;
        }
        
        Pair pairs[] = new Pair[number_of_genes];
              
        for (int i = 0; i < number_of_genes; i++) {
            
            System.out.println("enter the value of item number  : " + (i + 1));
            int val = s.nextInt();
               
            

            System.out.println("enter the weight of item number : " + (i + 1));
            int weight2 = s.nextInt();
            pairs[i] = new Pair (val , weight2);
                    

        }
        
        
        
        ArrayList<Chromosome> chromosomes = new ArrayList<>();
 
        // random initialization
        for (int i = 0 ; i < chromosome_number ; i++)
        {
            ArrayList <Integer> chromosome_generation = new ArrayList(); 
            //chromosome_generation.add(0);
            Chromosome c1 = new Chromosome() ;
            for (int j = 1 ; j< number_of_genes+1 ; j++)
            {
                float rand = (float) Math.random();
                if (rand >= 0.5)
                {
                    chromosome_generation.add(1);
                    
                }
                else 
                {
                    chromosome_generation.add(0);
                }

            }
            c1.setChromosome(chromosome_generation);
            
            
            chromosomes.add(c1);
               
        }
       
        //printing Arraylist of Arraylists
        
        for(int i=0;i<chromosomes.size();i++) {
	
                System.out.print(chromosomes.get(i).getChromosome());
                        System.out.println();
                
                
		}
        
        
        
       
        float max_fitness = 0;
        int final_weight = 0;
        
       for ( int i = 0 ; i< number_of_iterations ; i++)
       {
           if (i !=0)
           {
               //selection part after the first iteration 
        
       chromosomes = calculate_fitness(chromosomes, pairs);
       chromosomes = roulette_wheel_selection(chromosomes , number_of_genes);
           }
   
           //crossover part 
        
       chromosomes = crossover (chromosomes ,crossover_prob);
       
       // mutation part 
       
        chromosomes = mutation(chromosomes, mutation_prob);
        
       //weight calculations and removing extra weight chromosomes
       
        chromosomes = calculate_weight(chromosomes, pairs , knapsack_size);
       
        
        chromosomes = calculate_fitness(chromosomes, pairs);
        
        for (int j = 0 ; j< chromosomes.size() ; j++)
        {
            if (max_fitness < chromosomes.get(j).getFitness())
            {
                max_fitness = chromosomes.get(j).getFitness();
                final_weight = chromosomes.get(j).getWeight();
            }
        }
        
        
      
       }
       
       System.out.println("max fitness is : " + max_fitness);
       System.out.println("and the weight is : " + final_weight);
       
        
       
      
       
       
       
     
       
        
    }
 
    
    public static ArrayList<Chromosome> roulette_wheel_selection (ArrayList<Chromosome> chromosomes ,int number_of_chromosomes )
    {
        
        ArrayList<Chromosome> new_chromosomes = new ArrayList();
        float total_fitness = 0;
        for (int i = 0 ; i< chromosomes.size();i++)
        {
            total_fitness += chromosomes.get(i).getFitness();
        }
        
        
        // set boundries for the selection process
        
        for (int i = 0 ; i< chromosomes.size() ; i++)
        {
            
            
            
            if (i != 0)
            {
                
                
                float min = chromosomes.get(i-1).getFitness() / total_fitness;
                float max = min + (chromosomes.get(i).getFitness() / total_fitness);
                
                
                
                chromosomes.get(i).setMin(min);
                chromosomes.get(i).setMax(max);
            }
            
            
            if (i == 0)
            {
                chromosomes.get(i).setMin(0);
                chromosomes.get(i).setMax(chromosomes.get(i).getFitness() / total_fitness);
            }
                    
            
            
            
            
        }
        
          
        for(int i=0;i<chromosomes.size();i++) {
	
                System.out.print(chromosomes.get(i).getChromosome());
                        System.out.println();
                
                
		}
        
        
        for (int  i = 0 ; i< chromosomes.size();i++)
        {   
            float rand = (float) Math.random();
            
            
            
            for (int j = 0 ; j<chromosomes.size() ;j++)
            {
                
               
               if (rand <= chromosomes.get(j).getMax() && rand > chromosomes.get(j).getMin())
                {
                    
                    new_chromosomes.add(chromosomes.get(j));
                    
                }
            }
            
        }
        
         while (new_chromosomes.size() < number_of_chromosomes)
       {
            Random r = new Random();
            int Low = 0;
            int High = chromosomes.size();
            int adding_index = r.nextInt(High-Low) + Low;
            new_chromosomes.add(chromosomes.get(adding_index));
            
       }
        
        return new_chromosomes;
    }
    
    
    
    public static ArrayList<Chromosome> calculate_weight (ArrayList<Chromosome> chromosomes , Pair [] pairs , int knapsack_size)
    {
        int number_of_genes = chromosomes.get(0).getChromosome().size();
        
        for (int i = 0 ; i< chromosomes.size() ; i++)
        {
            int weight = 0;
            for (int j = 0 ; j<number_of_genes;j++ )
            {
                int value = chromosomes.get(i).getChromosome().get(j);
                
                if (value == 1)
                {
                weight += pairs[j].weight;
                
                }
                
            }
            
            
            chromosomes.get(i).setWeight(weight);
            
            
            if (weight > knapsack_size)
            {
             
                chromosomes.remove(i);
            }
            
            
        }
        return chromosomes;
    }
    
    public static ArrayList<Chromosome> calculate_fitness (ArrayList<Chromosome> chromosomes , Pair [] pairs )
    {
        int number_of_genes = chromosomes.get(0).getChromosome().size();
        
        for (int i = 0 ; i< chromosomes.size() ; i++)
        {
            float fitness = 0;
            for (int j = 0 ; j<number_of_genes;j++ )
            {
                int value = chromosomes.get(i).getChromosome().get(j);
                
                if (value == 1)
                {
                
                fitness+=pairs[j].value;    
                }
                
            }
            
            chromosomes.get(i).setFitness(fitness);
            
            
            
            
        }
        return chromosomes;
    }
    
    
    
    
    public static ArrayList<Chromosome> mutation (ArrayList<Chromosome> chromosomes , float mutation_prob)
    {
        int number_of_genes = chromosomes.get(0).getChromosome().size();
        try {
        for (int i = 0 ; i< chromosomes.size() ;i++)
        {
            
            
            for (int j = 0 ; j<number_of_genes;j++ )
            {
                float rand = (float) Math.random();
                if (rand >=mutation_prob)
                {   
                    System.out.println("rand = " + rand);
                    System.out.println("J = : " + j );
                    System.out.println("\n before mutation !!! ");
                    System.out.println(chromosomes.get(i).getChromosome());
            
                    if (chromosomes.get(i).getChromosome().get(j) == 1)
                    {
                        chromosomes.get(i).getChromosome().set(j, 0);
                                   
                    }
                    else if (chromosomes.get(i).getChromosome().get(j) == 0)
                    {
                       
                        chromosomes.get(i).getChromosome().set(j, 1);
                                   
                    }
                    
                    System.out.println("\n after mutation !!! ");
                    System.out.println(chromosomes.get(i).getChromosome());
            
                }
            }
            
            
            
            
        }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return chromosomes;
    }
 
    
    
    
    
    
    
    
    public static ArrayList<Chromosome> crossover (ArrayList<Chromosome> chromosomes , float crossover_prob)  
    {
        
        int number_of_genes ;
        try {
        ArrayList<Chromosome> to_crossover = new ArrayList()  ;
        number_of_genes = chromosomes.get(0).getChromosome().size();
        
        for (int i = 0 ; i< chromosomes.size();i++)
        {
            
            
            float rand = (float) Math.random();
            
            
            if (rand >= crossover_prob)
            {
                to_crossover.add(chromosomes.get(i)) ;
            }
            if(to_crossover.size() == 2)
            {
                //call function crossover2
                ArrayList<Integer> child =new ArrayList();
  
                Chromosome c1 = new Chromosome() ;
                
                child = crossover2(to_crossover , number_of_genes);
               
                c1.setChromosome(child);
                 System.out.println("father in the first crossover function" + to_crossover.get(0).getChromosome());
                System.out.println();
                 System.out.println("mother in the first crossover function" + to_crossover.get(1).getChromosome());
               System.out.println();
                System.out.println("child in the first crossover function" + child);
                System.out.println();
                
                chromosomes.remove(to_crossover.get(0));
                chromosomes.remove(to_crossover.get(1));
                chromosomes.add(c1);
                to_crossover=new ArrayList();
                
            }
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
     return chromosomes;
 }
    
  public static ArrayList<Integer> crossover2 ( ArrayList<Chromosome> chromosomes , int number_of_genes) {
  ArrayList<Integer> child =new ArrayList();
  
    Random r = new Random();
    int Low = 2;
    int High = number_of_genes;
    int crosspoint = r.nextInt(High-Low) + Low;
  System.out.println("\ncross point " + crosspoint);
    System.out.println(); 
  

  for(int i = 0 ; i< number_of_genes ; i++)
  {
      if (i < crosspoint)
          child.add(chromosomes.get(0).getChromosome().get(i));
      else
          child.add(chromosomes.get(1).getChromosome().get(i));
  }
  
  
    
  return child;
}

    
}
