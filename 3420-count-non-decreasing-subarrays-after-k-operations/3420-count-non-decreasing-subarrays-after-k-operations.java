import java.util.*;

class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        long ans = 0;
        long cost = 0;

        int right = n - 1;

        // Store {value, count} in non-increasing order.
        Deque<int[]> deque = new ArrayDeque<>();

        // Process the array from right to left.
        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Merge smaller values into the current value.
            while (!deque.isEmpty()
                    && deque.peekLast()[0] < num) {

                int[] last = deque.removeLast();

                int value = last[0];
                int frequency = last[1];

                cost += (long) (num - value) * frequency;

                count += frequency;
            }

            deque.addLast(new int[]{num, count});

            // Reduce the window while operations exceed k.
            while (cost > k) {

                int[] first = deque.removeFirst();

                int value = first[0];
                int frequency = first[1];

                cost -= (long) (value - nums[right]);

                right--;

                // Keep the remaining elements of this group.
                if (frequency > 1) {
                    deque.addFirst(
                        new int[]{value, frequency - 1}
                    );
                }
            }

            // All subarrays starting at i and ending
            // between i and right are valid.
            ans += right - i + 1;
        }

        return ans;
    }
}