import java.util.*;

class Solution {

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Required variable
        int[] maritovexi = nums;

        // Step 1: Calculate the cost of every window of size x.
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < n; i++) {

            window.add(maritovexi[i]);

            if (i >= x) {
                window.remove(maritovexi[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        // Step 2: Dynamic Programming.
        // prev[i] = minimum cost using exactly t-1 windows
        // within the first i elements.

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];

        for (int t = 1; t <= k; t++) {

            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current position.
                curr[i] = curr[i - 1];

                // Option 2: Select window ending at i-1.
                if (i >= x && prev[i - x] != INF) {

                    long take = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            prev = curr;
        }

        return prev[n];
    }

    // Maintains the median and sum of a sliding window.
    static class MedianWindow {

        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();

        int lowerSize = 0;
        int upperSize = 0;

        long lowerSum = 0;
        long upperSum = 0;

        void add(int num) {

            if (lower.isEmpty() || num <= lower.lastKey()) {
                lower.merge(num, 1, Integer::sum);
                lowerSize++;
                lowerSum += num;
            } else {
                upper.merge(num, 1, Integer::sum);
                upperSize++;
                upperSum += num;
            }

            balance();
        }

        void remove(int num) {

            if (lower.containsKey(num)) {

                decrease(lower, num);
                lowerSize--;
                lowerSum -= num;

            } else {

                decrease(upper, num);
                upperSize--;
                upperSum -= num;
            }

            balance();
        }

        void balance() {

            // Lower half must contain the extra element
            // when the window size is odd.

            while (lowerSize > upperSize + 1) {

                int val = lower.lastKey();

                decrease(lower, val);
                lowerSize--;
                lowerSum -= val;

                upper.merge(val, 1, Integer::sum);
                upperSize++;
                upperSum += val;
            }

            while (lowerSize < upperSize) {

                int val = upper.firstKey();

                decrease(upper, val);
                upperSize--;
                upperSum -= val;

                lower.merge(val, 1, Integer::sum);
                lowerSize++;
                lowerSum += val;
            }
        }

        void decrease(TreeMap<Integer, Integer> map, int val) {

            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        long getCost() {

            // Median is the largest element in lower half.
            long median = lower.lastKey();

            long leftCost =
                median * lowerSize - lowerSum;

            long rightCost =
                upperSum - median * upperSize;

            return leftCost + rightCost;
        }
    }
}