import java.util.Arrays;
import java.util.stream.IntStream;
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = IntStream.concat(
                Arrays.stream(nums1),
                Arrays.stream(nums2)
        ).toArray();
        Arrays.sort(merged);
         int n = merged.length;

        if (n % 2 == 0) {
            return (merged[n / 2] + merged[n / 2 - 1]) / 2.0;
        } else {
            return merged[n / 2];
        }
    }
}