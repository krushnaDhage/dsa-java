class Solution {
    public int minMoves(int[] nums) {

        int min = Integer.MAX_VALUE;
        long sum = 0;

        // Find minimum element and calculate sum
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }

        // Minimum moves = sum - (minimum * array length)
        return (int) (sum - (long) min * nums.length);
    }
}