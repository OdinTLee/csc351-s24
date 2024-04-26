package HW_15;

import java.util.*;

public class MakingChange {

    private Integer[] coins;
    private int change;
    private int[] memoization;
    private ArrayList<ArrayList<Integer>> choices;

    public MakingChange(Integer[] coins, int change) {
        this.coins = coins;
        this.change = change;
        this.memoization = new int[change + 1];
        Arrays.fill(this.memoization, -1); // Initialize memoization array
        this.choices = new ArrayList<>();
        for (int i = 0; i <= change; i++) {
            this.choices.add(new ArrayList<>());
        }
    }

    public int solveRecDP() {
        Arrays.fill(memoization, -1);
        return recDP(change);
    }

    private int recDP(int remaining) {
        if (remaining == 0) {
            return 0;
        }
        if (remaining < 0) {
            return Integer.MAX_VALUE;
        }

        if (memoization[remaining] != -1) {
            return memoization[remaining];
        }

        int minCoins = Integer.MAX_VALUE;
        int minCoinsIndex = -1;
        for (int i = 0; i < coins.length; i++) {
            int result = recDP(remaining - coins[i]);
            if (result != Integer.MAX_VALUE && result + 1 < minCoins) {
                minCoins = result + 1;
                minCoinsIndex = i;
            }
        }

        memoization[remaining] = minCoins;
        if (minCoinsIndex != -1) { // Corrected condition
            choices.get(remaining).add(coins[minCoinsIndex]);
            choices.get(remaining).addAll(choices.get(remaining - coins[minCoinsIndex]));
        }

        return minCoins;
    }

    // The solveIterDP() and solveGreedy() methods remain unchanged.

    public ArrayList<Integer> getChoices() {
        if (memoization[change] == Integer.MAX_VALUE) {
            return new ArrayList<>(); // Indicate no solution
        }
        return choices.get(change);
    }
}
