/**
 * RollinApp.java
 */

package rollin;

import java.util.*;

public class RollinApp{

    public static void main (String[] args){
        /** Code for running and Testing here*/
        // Create random instance and seed it for testing.
        Random r = new Random(); // new Random(2018);

        // Create an array of six random dice rolls.
        int[] initialDice = new int[6];
        for (int i = 0; i < 6; i++) {
            initialDice[i] = r.nextInt(6) + 1;
        }

        // Initialise MyRollin instance and print initial dice.
        MyRollin myRollin = new MyRollin(initialDice);
        System.out.println("Initial dice: " + Arrays.toString(myRollin.getDice()));

        int i = 0; // How many rolls it took to get two sets.
        int replace; // Which die to replace or value indicating no replacement.
        int roll; // The value of the die from the die roll.

        // Roll until we get two sets.
        while (!myRollin.isComplete() && i < 100) {
            roll = r.nextInt(6) + 1;
            replace = myRollin.handleRoll(roll);

            if (replace != MyRollin.NO_REPLACE) {
                myRollin.replace(replace, roll);
            }

            i++;
        }
        
        // Print out how many rolls it took and the final dice.
        System.out.println("Finished in " + i + " rolls.");
        System.out.println("Final dice: " + Arrays.toString(myRollin.getDice()));
    }
}


