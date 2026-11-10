class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        // Starting position
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            dp[i] = Integer.MAX_VALUE;

            // We can jump from the previous 1, 2, or 3 steps.
            for (int j = Math.max(0, i - 3); j < i; j++) {

                int jump = i - j;

                int currentCost =
                    dp[j] + costs[i - 1] + jump * jump;

                dp[i] = Math.min(dp[i], currentCost);
            }
        }

        return dp[n];
    }
}