package rollin;

import java.util.*;

/**
 * Implementation of the abstract class Rollin.
 * @see Rollin
 */
public class MyRollin extends Rollin {
    /** Return value for handleRoll() if no dice are replaced. */
    public static final int NO_REPLACE = -1; 
    /** Random number generator for choosing which dice to replace.
     * Can be replaced if you want to use a seeded instance. */
    public static Random r = new Random();
    /** Controls whether or not to print debug text. */
    public static boolean DEBUG = false;

    /** Return value for a few methods when a set of indicies could
     * not be found. */
    private static final int[] NOT_FOUND = { -1, -1, -1 };
    
    public MyRollin(int[] dice){
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
        printDebug("Current dice: " + Arrays.toString(dice));

        // If we do not have two sets already...
        if (!isComplete()) {
            // Initially just replace a random die.
            int replaceIndex = r.nextInt(6);

            // Try to find a set and the indicies of that set and the indicies
            // that do not belong to that set.
            int[] set = new int[3];
            int[] nonSet = new int[3];
            
            // Try to find a set and their indicies.
            set = findSet();
            
            // If a set of indentical die values was found...
            if (set != NOT_FOUND) {
                printDebug("Set of identical values found.");
            } 
            else {
                // Otherwise if neither a set of identical die values or a set 
                // of consecutive die values were found...
                printDebug("No suitable candidates found. " + 
                           "Replacing die at index " + replaceIndex);
                // Return the randomly generated die index.
                return replaceIndex;
            } 

            // Get the non-set indicies.
            nonSet = getNonSetIndicies(set);

            printDebug("Found set indicies: " + Arrays.toString(set));
            printDebug("Found non-set indicies: " + 
                        Arrays.toString(nonSet));
            
            // We can ignore the dice reffered to by setIndicies as they 
            // already form a set.
            // We should now try to make a set with the other 3 dice.
            int suggestedReplaceIndex = suggestReplaceIndex(nonSet);

            printDebug(""); // Formatting.

            // Return index of the die to replace.
            return (suggestedReplaceIndex != -1) ? suggestedReplaceIndex : replaceIndex;
        }

        // Return value indicating that no dice were replaced.
        return NO_REPLACE;
    }

    //////////////////////////// Helper functions ////////////////////////////

    /** 
     * Helper function that handles 'debug' logging to the console. 
     */
    private void printDebug(String msg) {
        if (DEBUG) {
            System.out.println(msg);
        }
    }

    /**
     * Determine whether the current dice form a set and return the indicies
     * for that set.
     * @return set of indicies if the dice form a set, NOT_FOUND otherwise
     */
    public final int[] findSet() {
        for (int[][] si : setIndices) {
            if (isSet(si[0])) {
                return si[0];
            } else if (isSet(si[1])) {
                return si[1];
            }
        }
        
        return NOT_FOUND;
    }


    /**
     * Gets the indicies of the dice that do not belong to the given set.
     * @param set The indicies of the set that was found.
     * @return The indicies of the dice that are not in set */
    private final int[] getNonSetIndicies(int[] set) {
        int[] nonSetIndicies = new int[3];

        // Construct the nonSetIndicies array by adding the 
        // indices that are not present in set
        int k = 0; // Index of the value in nonSetIndicies to set.
        boolean found;

        for (int i = 0; i < dice.length; i++) {
            found = false;

            for (int j = 0; j < set.length; j++) {
                if (i == set[j]) {
                    found = true;
                }
            }

            if (!found) {
                nonSetIndicies[k++] = i;
            }
        }

        return nonSetIndicies;
    }

    /**
     * Tries to make a set of 2 identical or consecutive die values, and then
     * suggests the remaining die value as the die to replace.
     * @param x The resulting array from getNonSetIndicies().
     * @return The index of the die that is suggested to be replaced.
     */
    private final int suggestReplaceIndex(int[] x) {
        // Check if there is 2 of a kind and return the index of the 
        // die that is not part of the pair.
        boolean foundPair = false;
        int replaceIndex = -1;

        if (dice[x[0]] == dice[x[1]]) {
            replaceIndex = x[2];
            foundPair = true;
        } 
        else if (dice[x[0]] == dice[x[2]]) {
            replaceIndex = x[1];   
            foundPair = true;
        } 
        else if (dice[x[1]] == dice[x[2]]) {
            replaceIndex = x[0];
            foundPair = true;
        }
        // DEBUG
        if (foundPair) {
            printDebug("Found pair in non-set dice," + 
                        " replacing die at index " + replaceIndex);
        } else {
            // Check if there are 2 consecutive numbers and return the 
            // index of the die that is not consecutive.
            boolean foundConsec = false;

            if (Math.abs(dice[x[0]] - dice[x[1]]) == 1) {
                replaceIndex = x[2];
                foundConsec = true;
            } 
            else if (Math.abs(dice[x[0]] - dice[x[2]]) == 1) {
                replaceIndex = x[1];
                foundConsec = true;
            } 
            else if (Math.abs(dice[x[1]] - dice[x[2]]) == 1) {
                replaceIndex = x[0];
                foundConsec = true;
            }
            // DEBUG
            if (foundConsec) {
                printDebug("Found consecutive dice, replacing die at index " + 
                            replaceIndex);
            } else {
                printDebug("No suitable candidates found. Replacing die at " +
                            "index " + replaceIndex);
            }
        }

        return replaceIndex;
    }   

} 


