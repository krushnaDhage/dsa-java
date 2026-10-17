import java.util.ArrayList;

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }

            list.add(count);
        }

        int[] arr = list.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();

        return arr;
    }
}