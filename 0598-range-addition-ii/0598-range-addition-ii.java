class Solution {
    public int maxCount(int m, int n, int[][] ops) {

        int minRow = m;
        int minCol = n;

        // Find the common overlapping region.
        for (int[] op : ops) {
            minRow = Math.min(minRow, op[0]);
            minCol = Math.min(minCol, op[1]);
        }

        // Every cell in this region gets incremented
        // during every operation.
        return minRow * minCol;
    }
}