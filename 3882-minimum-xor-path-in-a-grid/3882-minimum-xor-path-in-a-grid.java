import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[j] stores all possible XOR values
        // reaching the current row's cell at column j.
        Set<Integer>[] dp = new Set[n];

        for (int j = 0; j < n; j++) {
            dp[j] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> current = new HashSet<>();

                if (i == 0 && j == 0) {
                    current.add(grid[i][j]);
                } else {
                    // Reach from above
                    if (i > 0) {
                        for (int x : dp[j]) {
                            current.add(x ^ grid[i][j]);
                        }
                    }

                    // Reach from left
                    if (j > 0) {
                        for (int x : dp[j - 1]) {
                            current.add(x ^ grid[i][j]);
                        }
                    }
                }

                dp[j] = current;
            }
        }

        return Collections.min(dp[n - 1]);
    }
}