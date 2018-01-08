package rollin;
import java.util.*;

public class MyRollin extends Rollin {

    private static final Random rand = new Random();
    private static final int handSize = 6;
    private static final int diceSize = 6;
    
    public MyRollin(int[] dice){
        super(dice);
    }

    
    public int handleRoll(int roll){
        int[] tempDice = Arrays.copyOf(dice,dice.length);

        /* Checks to see if substitution of the roll with  any one of the values in the hand
         * will result in the hand containing two sets */
        for(int i=0; i < handSize; i++){
            dice[i] = roll;
            if(this.isComplete()){
                //dice = Arrays.copyOf(tempDice,tempDice.length);
                return i;
            }
            dice = Arrays.copyOf(tempDice,tempDice.length);
        }

        /* Check for three pairs?*/
        /* Random change if see one*/
        /* Could also check if one of the same number already there*/
        
        int[] diceSorted = Arrays.copyOf(dice,dice.length);
        Arrays.sort(diceSorted);
        //System.out.println(Arrays.toString(diceSorted));
        if (diceSorted[0] == diceSorted[1]
            && diceSorted[2] == diceSorted[3]
            && diceSorted[4] == diceSorted[5]){
            //System.out.println("Three pair!");

            //Can be improved here
            int index = rand.nextInt(diceSize);
            dice[index] = roll;
            return index;
        }

        /* Check for two pairs*/
        if ((diceSorted[0] == diceSorted[1] && diceSorted[2] == diceSorted[3])
            || (diceSorted[0] == diceSorted[1] && diceSorted[4] == diceSorted[5])
            || (diceSorted[2] == diceSorted[3] && diceSorted[4] == diceSorted[5])
            ){
            //Can be improved here
            int index = rand.nextInt(diceSize);
            dice[index] = roll;
            return index;
        }
            
            
           
               
        /* Randomly change one*/
        return -1;
    }

    /**
     * Randomly selects a dice to replace with the rolled one.
     * @param the value of the die roll.
     * @return index of die replaced.
     */
    public int handleRollRandom(int roll){
        int index = rand.nextInt(6);
        dice[index] = roll;
        return index;
    }

} 

    
