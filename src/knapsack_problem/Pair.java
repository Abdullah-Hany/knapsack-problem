/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack_problem;

/**
 *
 * @author Beido
 */
public class Pair {
    public int value, weight;

        public Pair ()
        {
            
        }
       public Pair(int val, int weight2) {
            value = val;
            weight = weight2;
        }

       public void set_value(int val) {
            value = val;
        }

        public void set_weight(int weight2) {
            weight = weight2;
        }

        public int get_value() {
            return value;
        }

        public int get_weight() {
            return weight;
        }
    
}
