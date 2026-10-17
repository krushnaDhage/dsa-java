class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int n = word1.length;
        int n2 = word2.length;

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for (int i = 0; i < n; i++) {
            s1.append(word1[i]);
        }

        for (int j = 0; j < n2; j++) {
            s2.append(word2[j]);
        }

        return s1.toString().equals(s2.toString());
    }
}