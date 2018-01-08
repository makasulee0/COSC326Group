package rollin;
import java.util.*;

public class MyRollin extends Rollin {

    private static final Random rand = new Random();
    private static final int HAND_SIZE = 6;
    private static final int DICE_SIZE = 6;
    private static final int NO_REPLACE = -1; 
    
    public MyRollin(int[] dice){
        super(dice);
    }

    
    public int handleRoll(int roll){
        int[] tempDice = Arrays.copyOf(dice,dice.length);

        /*Check if complete*/
        if (this.isComplete()){
            System.out.println("Complete Hand");
            return NO_REPLACE;
        }
               
        /*Check if half complete*/
        if (this.isHalfComplete()){
            
            System.out.println("Half Complete");
            
            /* Checks to see if substitution of the roll with  any one of the values in the hand
             * will result in the hand containing two sets */
            for(int i=0; i < HAND_SIZE; i++){
                dice[i] = roll;
                if(this.isComplete()){
                    dice = Arrays.copyOf(tempDice,tempDice.length);
                    return i;
                }
                dice = Arrays.copyOf(tempDice,tempDice.length);
            }


            /*Check to see if a swap will result in higher probability*/
            /* TODO Anthony*/
            /*Place holder no replace*/
            
            return NO_REPLACE;
            
        }else{
            /*Check for three incomplete sets*/
            /* If three incomplete sets*/
            /* Do random swap*/
            int index = rand.nextInt(DICE_SIZE);

            /* Check not swapping with the same number */
            while (dice[index] == roll){
                index = rand.nextInt(DICE_SIZE);
            }
            
            return index;
        }
            
        //return NO_REPLACE;
    }
          
            /*Trash code starts here  */ 
        /* Check for three pairs?*/
        /* Random change if see one*/
        /* Could also check if one of the same number already there*/
            /*
            int[] diceSorted = Arrays.copyOf(dice,dice.length);
        Arrays.sort(diceSorted);
        //System.out.println(Arrays.toString(diceSorted));
        if ((diceSorted[0] == diceSorted[1])
            && (diceSorted[2] == diceSorted[3])
            && (diceSorted[4] == diceSorted[5])){
            System.out.println("Three pair!");

            //Can be improved here
            int index = rand.nextInt(DICE_SIZE);
            dice[index] = roll;
            return index;
        }
            */
        /* Check for two pairs*/
            /*
        if ((diceSorted[0] == diceSorted[1] && diceSorted[2] == diceSorted[3])
            || (diceSorted[0] == diceSorted[1] && diceSorted[4] == diceSorted[5])
            || (diceSorted[2] == diceSorted[3] && diceSorted[4] == diceSorted[5])
            ){
            //Can be improved here
            System.out.println("Two pair!");
            int index = rand.nextInt(DICE_SIZE);
            dice[index] = roll;
            return index;
        }
            
        System.out.println("Cannot be resolved in one swap");    
            */
               
        /* Randomly change one*/
        
    

    /**
     * Checks the hand of dice to see if can make at least one set.
     * @return true if one set can be made, false if not.
     */
    public final boolean isHalfComplete(){
        for (int[][] si : setIndices) {
            if (isSet(si[0]) || isSet(si[1])) {
                return true;
            }
        }
        return false;
    }

           

    /**
     * Randomly selects a dice to replace with the rolled one.
     * @param the value of the die roll.
     * @return index of die replaced.
     */
    public int handleRollRandom(int roll){
        int index = rand.nextInt(HAND_SIZE);
        return index;
    }

    

} 

    
