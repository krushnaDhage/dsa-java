class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        int[] ones = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }

        if (count < k) {
            return "";
        }

        String answer = "";

        for (int i = 0; i <= count - k; i++) {

            int left = ones[i];
            int right = ones[i + k - 1];

            String current = s.substring(left, right + 1);

            if (answer.equals("")
                    || current.length() < answer.length()
                    || (current.length() == answer.length()
                        && current.compareTo(answer) < 0)) {

                answer = current;
            }
        }

        return answer;
    }
}