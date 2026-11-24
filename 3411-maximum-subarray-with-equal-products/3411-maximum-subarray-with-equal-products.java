
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long g = 0;
            long l = 1;

            for (int j = i; j < n; j++) {
                product *= nums[j];
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);

                if (product == g * l) {
                    max = Math.max(max, j - i + 1);
                }

                if (product > 2520)
                    break;
            }
        }

        return max;
    }

    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
