class Solution {
    public String licenseKeyFormatting(String s, int k) {

        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (ch == '-') {
                continue;
            }

            if (count == k) {
                result.append('-');
                count = 0;
            }

            result.append(Character.toUpperCase(ch));
            count++;
        }

        return result.reverse().toString();
    }
}