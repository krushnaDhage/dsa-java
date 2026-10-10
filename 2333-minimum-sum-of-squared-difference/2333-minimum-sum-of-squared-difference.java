class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long operations = (long) k1 + k2;
        int n = nums1.length;

        int[] diff = new int[n];
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        long total = 0;
        for (int d : diff) {
            total += d;
        }

        if (operations >= total) {
            return 0;
        }

        int low = 0, high = maxDiff;

        // Find the smallest threshold x such that
        // reducing every difference above x to x
        // requires at most 'operations' reductions.
        while (low < high) {
            int mid = low + (high - low) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= operations) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int threshold = low;
        long used = 0;
        long answer = 0;

        for (int d : diff) {
            int reduced = Math.min(d, threshold);
            used += d - reduced;
            answer += (long) reduced * reduced;
        }

        // Distribute leftover operations by reducing
        // threshold-level differences one more unit.
        long remaining = operations - used;

        answer -= remaining * (2L * threshold - 1);

        return answer;
    }
}