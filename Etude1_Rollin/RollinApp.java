package rollin;

import java.util.*;

public class RollinApp{

    public static void main (String[] args){
        /** Code for running and Testing here*/
        // Create random instance and seed it for testing.
        Random r = new Random(2018);

        // Create an array of six random dice rolls.
        int[] initialDice = new int[6];
        for (int i = 0; i < 6; i++) {
            initialDice[i] = r.nextInt(6) + 1;
        }

        // Initialise MyRollin instance and print initial dice.
        MyRollin myRollin = new MyRollin(initialDice);
        System.out.println(Arrays.toString(myRollin.getDice()));

        int i = 0; // Used to keep track of how many rolls it takes to get two sets.

        // // Roll until we get two sets.
        // while (!myRollin.isComplete()) {
        //     myRollin.handleRoll(r.nextInt(6) + 1);
        //     i++;
        // }
        
        // Print out how many rolls it took and the final dice.
        System.out.println("Finished in " + i + " rolls.");
        System.out.println(Arrays.toString(myRollin.getDice()));
    }
}


