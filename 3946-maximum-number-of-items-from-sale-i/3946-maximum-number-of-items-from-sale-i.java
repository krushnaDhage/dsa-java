class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int[] dp = new int[budget + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            minPrice = Math.min(minPrice, price);

            int count = 0;

            // Calculate first-purchase benefit
            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    count++;
                }
            }

            // 0/1 Knapsack
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b],
                                 dp[b - price] + count);
            }
        }

        int ans = 0;

        // Spend remaining budget on cheapest item
        for (int b = 0; b <= budget; b++) {

            int extra = (budget - b) / minPrice;

            ans = Math.max(ans, dp[b] + extra);
        }

        return ans;
    }
}