package test;

import java.util.ArrayList;
import java.util.Arrays;

public class SKT_1 {
    public static void main(String[] args) {

    }

    static int[] dp;

    public int solution(int money, int[] costs) {
        int[] ans = new int[money + 1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        int[] cache = new int[money + 1];
        cache[0] = 1;
        ans[0] = 0;
        int[] coins = new int[]{1, 5, 10, 50, 100, 500};
        for (int i = 0; i < 6; i++) {
            int coin = coins[i];
            for (int j = coin; j <= money; j++) {
                cache[j] += cache[j - coin];
                ans[j] = Math.min(ans[j], ans[j - coin] + costs[i]);
            }
        }
        return ans[money];
    }
}

class Product {
    int V, C;

    Product(int V, int C) {
        this.C = C;
        this.V = V;
    }

}