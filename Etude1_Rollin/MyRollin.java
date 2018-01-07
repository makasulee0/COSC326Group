package rollin;
import java.util.*;

public class MyRollin extends Rollin {

    private static final Random rand = new Random();
    
    
    public MyRollin(int[] dice){
        super(dice);
    }

    public int handleRoll(int roll){
        int index = rand.nextInt(6);
        dice[index] = roll;
        return index;
    }

} 

    
