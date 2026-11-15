class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;

        int even = 0;
        int odd = 0;
        int ans = 0;

        for (int num : arr) {

            if (num % 2 == 0) {
                even = (even + 1) % MOD;
                odd = odd;
            } else {
                int temp = even;
                even = odd;
                odd = (temp + 1) % MOD;
            }

            ans = (ans + odd) % MOD;
        }

        return ans;
    }
}