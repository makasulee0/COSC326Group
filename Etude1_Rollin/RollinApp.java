package rollin;
import java.util.*;
public class RollinApp{

    public static void main (String[] args){
        /** Code for running and Testing here*/
        int[] myDice ={1,2,3,4,5,6};
        MyRollin myRoll = new MyRollin(myDice);
        System.out.println(Arrays.toString(myRoll.getDice()));
    }
}


