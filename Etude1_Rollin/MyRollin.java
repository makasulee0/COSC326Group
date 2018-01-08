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
            int[] setIndicies = new int[3];
            int[] nonSetIndicies = new int[3];
            
            // Try to find a set of 3 identical values and their indicies.
            setIndicies = getIdenticalSetIndicies();
            
            // If a set of indentical die values was found...
            if (setIndicies != NOT_FOUND) {
                printDebug("Set of identical values found.");
            } 
            else {
                // If not, try to find 3 consecutive values and their indicies.      
                setIndicies = findConsecutiveSetIndicies();

                // If a set of consecutive die values was found...
                if (setIndicies != NOT_FOUND) {
                    printDebug("Set of consecutive values found.");
                }
                // Otherwise if neither a set of identical die values or a set 
                // of consecutive die values were found...
                else {
                    printDebug("No suitable candidates found. " + 
                                "Replacing die at index " + replaceIndex);
                    // Return the randomly generated die index.
                    return replaceIndex;
                }
            } 

            // Get the non-set indicies.
            nonSetIndicies = getNonSetIndicies(setIndicies);

            printDebug("Found set indicies: " + Arrays.toString(setIndicies));
            printDebug("Found non-set indicies: " + 
                        Arrays.toString(nonSetIndicies));
            
            // We can ignore the dice reffered to by setIndicies as they 
            // already form a set.
            // We should now try to make a set with the other 3 dice.
            int suggestedReplaceIndex = suggestReplaceIndex(nonSetIndicies);

            printDebug(""); // Formatting.

            // Return index of the die to replace.
            return replaceIndex;
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
     * Count how many of each die value we have.
     * @return The array of die value counts.
     */
    private final int[] getValueCounts() {
        int[] valueCounts = { 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < dice.length; i++) {
            valueCounts[dice[i] - 1]++;
        }  

        printDebug("Counts of values 1-6: " + Arrays.toString(valueCounts));

        return valueCounts;
    }

    /**
     * Given a set of results from getValueCounts(), find the die value that
     * occurs 3+ times.
     * @return The die value that occurs 3+ times, -1 if no die value occurs
     * 3+ times. */
    private final int getValueWithThree(int[] valueCounts) {
        for (int i = 0; i < valueCounts.length; i++) {
            if (valueCounts[i] >= 3) {
                return i + 1;
            }
        }

        return -1;
    }

    /**
     * Finds the indicies of 3 consecutive die values.
     * @return Array of the indicies of the dice that have consecutive values.
     * NOT_FOUND if no set of consecutive values is found.
     */
     private final int[] findConsecutiveSetIndicies() {
        for (int i = 0; i < dice.length - 2; i++) {
            for (int j = i + 1; j < dice.length - 1; j++) {
                for (int k = j + 1; k < dice.length; k++) {
                    // If all three are different and largest minus smallest is 2 then it
                    // is a set, otherwise not.
                    int max = Math.max(dice[i], Math.max(dice[j], dice[k]));
                    int min = Math.min(dice[i], Math.min(dice[j], dice[k]));

                    if (max - min == 2) {
                        return new int[] { i, j , k };
                    }
                }
            }
        }

        return NOT_FOUND;
     }

    /**
     * Finds the indicies of 3 indentical die values.
     * @return Array of the indicies of the dice that have indentical values.
     * NOT_FOUND if no set of indentical values is found.
     */
    private final int[] getIdenticalSetIndicies() {
        // Start by trying to find a set of 3 identical values.
        // Get a count of how many of each die value we have.
        int[] valueCounts = getValueCounts();

        // Check for 3+ of the same value.
        int valueWithThree = getValueWithThree(valueCounts);

        // If we have a set of 3 identical values within our dice...
        if (valueWithThree != -1) {
            printDebug("Set of indentical values found.");

            // Find the index of the dice that match the value of the die 
            // value that occurs 3+ times.
            int j = 0; // Index of the value in indenticalSetIndicies to set.
            int[] indenticalSetIndicies = new int[3];

            for (int i = 0; i < dice.length; i++) {
                // Find up to 3 matches and put them in indenticalSetIndicies.
                if (dice[i] == valueWithThree && j < indenticalSetIndicies.length) {
                    indenticalSetIndicies[j++] = i;
                }
            }

            return indenticalSetIndicies;
        }

        return NOT_FOUND; 
    }

    /**
     * Gets the indicies of the dice that do not belong the set that was found
     * based on the indicies of the dice that are not part of a set.
     * @param setIndicies The indicies of the set that was found.
     * @return The indicies of the dice that are not in setIndicies */
    private final int[] getNonSetIndicies(int[] setIndicies) {
        int[] nonSetIndicies = new int[3];

        // Construct the nonSetIndicies array by adding the 
        // indices that are not present in setIndicies
        int k = 0; // Index of the value in nonSetIndicies to set.
        boolean found;

        for (int i = 0; i < dice.length; i++) {
            found = false;

            for (int j = 0; j < setIndicies.length; j++) {
                if (i == setIndicies[j]) {
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


