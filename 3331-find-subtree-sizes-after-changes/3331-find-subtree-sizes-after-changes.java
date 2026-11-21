
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] newParent = parent.clone();
        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0, count = 0;
        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];
            order[count++] = node;

            for (int child : tree[node]) {
                stack[top++] = child;
            }
        }

        // Find new parents using original tree
        int[] restore = new int[n];
        int[] path = new int[n];
        int depth = 0;

        // Enter/exit DFS events
        int[] events = new int[2 * n];
        int size = 0;
        events[size++] = 0;

        while (size > 0) {
            int event = events[--size];

            if (event < 0) {
                int node = -event - 1;
                int c = s.charAt(node) - 'a';
                last[c] = restore[node];
            } else {
                int node = event;
                int c = s.charAt(node) - 'a';

                restore[node] = last[c];

                if (last[c] != -1) {
                    newParent[node] = last[c];
                }

                last[c] = node;
                events[size++] = -node - 1;

                for (int child : tree[node]) {
                    events[size++] = child;
                }
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        // Process children before parents
        for (int i = n - 1; i > 0; i--) {
            int node = order[i];
            // Original DFS order is not necessarily
            // valid for modified parent links,
            // so accumulation needs a new traversal.
        }

        List<Integer>[] finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }

        top = 0;
        count = 0;
        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];
            order[count++] = node;

            for (int child : finalTree[node]) {
                stack[top++] = child;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            int node = order[i];
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
