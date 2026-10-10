
import java.util.*;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> freq = new HashMap<>();

        String[] words = paragraph.toLowerCase().split("[^a-z]+");

        String result = "";
        int maxFreq = 0;

        for (String word : words) {
            if (word.isEmpty() || bannedSet.contains(word)) {
                continue;
            }

            int count = freq.getOrDefault(word, 0) + 1;
            freq.put(word, count);

            if (count > maxFreq) {
                maxFreq = count;
                result = word;
            }
        }

        return result;
    }
}
