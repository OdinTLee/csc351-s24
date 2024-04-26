package HW_15;

import java.util.ArrayList;

public class Main { 
    public static void main(String[] args) {
        Integer[] coins = {5, 2, 3, 1, 7}; // Removed 0 for clarity
        int change = 10;
        MakingChange changer = new MakingChange(coins, change);
        
        int count = changer.solveRecDP();
        System.out.println("Minimum number of coins required: " + count);

        ArrayList<Integer> coinChoices = changer.getChoices();
        if (coinChoices.isEmpty()) {
            System.out.println("No solution found with the given coin denominations.");
        } else {
            System.out.println("Coins used to make the change: " + coinChoices);
        }
    }
}
