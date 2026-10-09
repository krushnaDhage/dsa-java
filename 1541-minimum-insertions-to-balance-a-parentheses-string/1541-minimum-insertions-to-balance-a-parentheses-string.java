class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If the next character isn't ')',
                // insert one ')' to complete the pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }

                // Match the closing pair with an opening '('.
                if (open > 0) {
                    open--;
                } else {
                    // No opening '(' available: insert one.
                    insertions++;
                }
            }
        }

        // Each remaining '(' needs two ')'.
        return insertions + 2 * open;
    }
}