package rollin;
import java.util.*;
public class RollinApp{

    private static final Random rand = new Random();
    private static final int handSize = 6;
    private static final int diceSize = 6;
    private static final int NO_REPLACE = -1; 
    
    public static void main (String[] args){
        /** Code for running and Testing here*/
        int numberOfTests = 1000;
        int sumOfTurns = 0;
        
        /*Test Algorithm*/
        for(int i = 0;i<numberOfTests;i++){
            //int[] myDice = {3,5,0,2,0,5};
            int[] myDice = createStartDice();
            MyRollin myRoll = new MyRollin(myDice);
            int numberOfTurns = 0;
            System.out.println(Arrays.toString(myRoll.getDice()));
            System.out.println(myRoll.isComplete());
            int index=0;
                          
            //while(!myRoll.isComplete() && index != -1){
            while(!myRoll.isComplete()){
                int newRoll = rollDice();
                System.out.println(newRoll);
                //index = myRoll.handleRollRandom(newRoll);
                index = myRoll.handleRoll(newRoll);
                if(index != NO_REPLACE){
                    myRoll.dice[index] = newRoll;
                }
                System.out.println(Arrays.toString(myRoll.getDice()));
                numberOfTurns++;
            }
            sumOfTurns += numberOfTurns;
        //System.out.println(Arrays.toString(myRoll.getDice()));
            System.out.println(myRoll.isComplete());
            System.out.println("Number of Turns : " +numberOfTurns);
        }
        double averageTurns = (double) sumOfTurns/ (double) numberOfTests;

        for(int i = 0;i<numberOfTests;i++){
            int[] myDice = createStartDice();
            MyRollin myRoll = new MyRollin(myDice);
            int numberOfTurns = 0;
            //System.out.println(Arrays.toString(myRoll.getDice()));
            //System.out.println(myRoll.isComplete());
            int index=0;
                          
            //while(!myRoll.isComplete() && index != -1){
            while(!myRoll.isComplete()){
                int newRoll = rollDice();
                System.out.println(newRoll);
                //index = myRoll.handleRollRandom(newRoll);
                index = myRoll.handleRollRandom(newRoll);
                if(index != NO_REPLACE){
                    myRoll.dice[index] = newRoll;
                }
                System.out.println(Arrays.toString(myRoll.getDice()));
                numberOfTurns++;
            }
            sumOfTurns += numberOfTurns;
            //System.out.println(Arrays.toString(myRoll.getDice()));
            System.out.println(myRoll.isComplete());
            System.out.println("Number of Turns : " + numberOfTurns);
        }
        double averageRandomTurns = (double) sumOfTurns/ (double) numberOfTests;


        
        System.out.println("Average Turns: " + averageTurns);
        System.out.println("Average Random Turns: " + averageRandomTurns);
        

        /* Calculates average of multiple runs*/
        
        /*
        for(int i = 0;i<numberOfTests;i++){
            myRoll= new MyRollin(createStartDice());
            numberOfTurns = 0;
            while(!myRoll.isComplete()){
                int newRoll = rollDice();
                //System.out.println(newRoll);
                myRoll.handleRoll(newRoll);
                //System.out.println(Arrays.toString(myRoll.getDice()));
                numberOfTurns++;
            }
            sumOfTurns +=numberOfTurns;
        }
        double averageTurns = (double) sumOfTurns/ (double) numberOfTests;
        System.out.println("Average Turns: " + averageTurns);
        */
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


