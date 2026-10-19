
class Solution {
    public int secondHighest(String s) {
        int largest = -1;
        int second = -1;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                int digit = ch - '0';

                if (digit > largest) {
                    second = largest;
                    largest = digit;
                } else if (digit < largest && digit > second) {
                    second = digit;
                }
            }
        }

        return second;
    }
}
