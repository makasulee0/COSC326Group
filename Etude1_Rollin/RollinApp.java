package rollin;
import java.util.*;
public class RollinApp{

    private static final Random rand = new Random();
    private static final int handSize = 6;
    private static final int diceSize = 6;
    
    public static void main (String[] args){
        /** Code for running and Testing here*/
        int[] myDice = createStartDice();
        MyRollin myRoll = new MyRollin(myDice);
        int numberOfTurns = 0;
        int numberOfSwaps = 0;
        System.out.println(Arrays.toString(myRoll.getDice()));
        System.out.println(myRoll.isComplete());
        while(!myRoll.isComplete()){
            int newRoll = rollDice();
            myRoll.handleRoll(newRoll);
            System.out.println(Arrays.toString(myRoll.getDice()));
            numberOfTurns++;
        }
        System.out.println(Arrays.toString(myRoll.getDice()));
        System.out.println(myRoll.isComplete());
        System.out.println("Number of Turns : " +numberOfTurns);
        
    }

    public static int[] createStartDice(){
        int[] diceHand= new int[handSize];
        for(int i = 0;i<handSize;i++){
            diceHand[i] = rand.nextInt(diceSize);
        }
        return diceHand;
    }

    public static int rollDice(){
        int roll = rand.nextInt(diceSize);
        //System.out.println(roll);
        return roll;
    }
}


