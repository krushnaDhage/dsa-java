class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        // Count characters of s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String answer = "";

        for (int i = 0; i < n; i++) {

            int targetChar = target.charAt(i) - 'a';

            // Try making the current character larger
            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    StringBuilder temp = new StringBuilder();

                    // Prefix same as target
                    temp.append(target.substring(0, i));

                    // Make current character slightly larger
                    temp.append((char) ('a' + c));

                    // Copy remaining frequencies
                    int[] remaining = freq.clone();
                    remaining[c]--;

                    // Add remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (remaining[j] > 0) {
                            temp.append((char) ('a' + j));
                            remaining[j]--;
                        }
                    }

                    // Keep the smallest candidate
                    if (answer.equals("") || temp.toString().compareTo(answer) < 0) {
                        answer = temp.toString();
                    }
                }
            }

            // Use target[i] for the prefix if available
            if (freq[targetChar] > 0) {
                freq[targetChar]--;
            } else {
                break;
            }
        }

        return answer;
    }
}