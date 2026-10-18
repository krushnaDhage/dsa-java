
class Solution {
    public String firstPalindrome(String[] words) {
        int n = words.length;

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = words[i].length() - 1;

            while (left < right) {
                if (words[i].charAt(left) == words[i].charAt(right)) {
                    left++;
                    right--;
                } else {
                    break;
                }
            }

            if (left >= right) {
                return words[i];
            }
        }

        return "";
    }
}
