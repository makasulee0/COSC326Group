package rollin;

import java.util.*;

/**
 * Brute force implementation of the abstract class Rollin.
 * @see Rollin
 */
public class MyRollinBrute extends Rollin {
    /** Return value for handleRoll() if no dice are replaced. */
    public static final int NO_REPLACE = -1; 
    public static Random r = new Random();

    public MyRollinBrute(int[] dice){
        super(dice);
    }

    /**
     * Replaces the die at the given index with the given value.
     * @param i The index of the die to replace.
     * @param value The new value of the die. 
     */
    public void replace(int i, int value) {
        dice[i] = value;
    }

    /**
     * Given a die roll, determine whether or not to replace a dice and which
     * one to replace.
     *
     * @param roll The value of the die roll.
     * @return The index of the die whose value will be replaced by the roll, 
     * or any int outside of 0 to 5 if no replacement is made.
     */
    public int handleRoll(int roll){
        // If we do not have two sets already...
        if (!isComplete()) {
            // Just replace a random die.
            return r.nextInt(6);
        }

        // Return value indicating that no dice were replaced.
        return NO_REPLACE;
    }  

} 


