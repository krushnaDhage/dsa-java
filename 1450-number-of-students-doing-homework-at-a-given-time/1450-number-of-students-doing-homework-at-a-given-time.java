class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int i = 0;
        int count = 0;

        while (i < n) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                count++;
            }
            i++;
        }

        return count;
    }
}