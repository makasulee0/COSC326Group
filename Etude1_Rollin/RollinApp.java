package rollin;

import java.util.*;

public class RollinApp{

    public static void main (String[] args){
        /** Code for running and Testing here*/
        // Create random instance and seed it for testing
        Random r = new Random(2018);

        // Initialise MyRollin instance with two sets, { 1, 2, 3 } and { 4, 4, 4 }
        int[] myDice = { 1, 3, 4, 4, 2, 4 };
        MyRollin myRoll = new MyRollin(myDice);
        System.out.println(Arrays.toString(myRoll.getDice()));

        int i = 0; // Keep track of how many rolls it takes to get two sets.

        // Roll until we get two sets
        while (!myRoll.isComplete()) {
            myRoll.handleRoll(r.nextInt(6) + 1);
            i++;
        }

        System.out.println("Finished in " + i + " rolls.");
        System.out.println(Arrays.toString(myRoll.getDice()));
    }
}


