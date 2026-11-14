class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check whether palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        // Build frequency for first half
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        /*
         * We try to make the first half as small as possible
         * while making the complete palindrome > target.
         */
        char[] first = new char[halfLen];

        // First try to construct a half equal to target's first half
        int[] remaining = half.clone();

        boolean possibleEqual = true;

        for (int i = 0; i < halfLen; i++) {

            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possibleEqual = false;
                break;
            }

            first[i] = target.charAt(i);
            remaining[c]--;
        }

        // If we can match the entire first half,
        // check whether the resulting palindrome is > target.
        if (possibleEqual) {

            String palindrome = buildPalindrome(first, middle, n);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        /*
         * Find the rightmost position where we can choose
         * a character greater than target[i].
         */
        for (int i = halfLen - 1; i >= 0; i--) {

            int[] count = half.clone();

            // Match target[0 ... i-1]
            boolean valid = true;

            for (int j = 0; j < i; j++) {

                int c = target.charAt(j) - 'a';

                if (count[c] == 0) {
                    valid = false;
                    break;
                }

                count[c]--;
            }

            if (!valid) {
                continue;
            }

            int targetChar = target.charAt(i) - 'a';

            // Choose the smallest character greater than target[i]
            for (int c = targetChar + 1; c < 26; c++) {

                if (count[c] > 0) {

                    char[] result = new char[halfLen];

                    // Prefix
                    for (int j = 0; j < i; j++) {
                        result[j] = target.charAt(j);
                    }

                    // Greater character
                    result[i] = (char) ('a' + c);
                    count[c]--;

                    // Fill remaining positions smallest first
                    int pos = i + 1;

                    for (int j = 0; j < 26; j++) {
                        while (count[j] > 0) {
                            result[pos++] = (char) ('a' + j);
                            count[j]--;
                        }
                    }

                    return buildPalindrome(result, middle, n);
                }
            }
        }

        return "";
    }

    private String buildPalindrome(char[] first, char middle, int n) {

        StringBuilder result = new StringBuilder();

        // First half
        for (char c : first) {
            result.append(c);
        }

        // Middle
        if (n % 2 == 1) {
            result.append(middle);
        }

        // Reverse first half
        for (int i = first.length - 1; i >= 0; i--) {
            result.append(first[i]);
        }

        return result.toString();
    }
}