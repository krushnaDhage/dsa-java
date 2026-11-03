class Solution {
    public int minMoves(int[] nums) {

        int max = Integer.MIN_VALUE;
        int sum = 0;

        // Find maximum and calculate sum
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        // Minimum operations
        return max * nums.length - sum;
    }
}