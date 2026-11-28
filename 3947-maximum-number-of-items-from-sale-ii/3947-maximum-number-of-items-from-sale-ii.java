
import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int[] freq = new int[n + 1];
        int[] gain = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        // Step 1: Count frequency and minimum price
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Calculate eligible free items
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                gain[i] += freq[j];
            }

            gain[i]--;
        }

        // Step 3: Sort by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1]));

        int ans = 0;

        // Step 4: Purchase items with beneficial offers
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if (price >= 2L * minPrice || price > budget) {
                break;
            }

            int count = Math.min(
                gain[factor],
                budget / price
            );

            ans += 2 * count;
            budget -= count * price;
        }

        // Step 5: Buy cheapest item with remaining budget
        ans += budget / minPrice;

        return ans;
    }
}
