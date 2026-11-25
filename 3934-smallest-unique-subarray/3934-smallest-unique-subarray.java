class Solution {
    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;
    static final long BASE = 100003L;

    public int smallestUniqueSubarray(int[] nums) {
        int low = 1;
        int high = nums.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (isUnique(nums, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean isUnique(int[] nums, int k) {
        HashMap<Long, Integer> map = new HashMap<>();

        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        // Calculate the required powers
        for (int i = 1; i < k; i++) {
            p1 = (p1 * BASE) % MOD1;
            p2 = (p2 * BASE) % MOD2;
        }

        // Hash of the first window
        for (int i = 0; i < k; i++) {
            h1 = (h1 * BASE + nums[i]) % MOD1;
            h2 = (h2 * BASE + nums[i]) % MOD2;
        }

        long key = h1 * MOD2 + h2;
        map.put(key, 1);

        // Slide the window
        for (int i = k; i < nums.length; i++) {
            h1 = (h1 - nums[i-k] * p1 % MOD1
                  + MOD1) % MOD1;

            h2 = (h2 - nums[i-k] * p2 % MOD2
                  + MOD2) % MOD2;

            h1 = (h1 * BASE + nums[i]) % MOD1;
            h2 = (h2 * BASE + nums[i]) % MOD2;

            key = h1 * MOD2 + h2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}