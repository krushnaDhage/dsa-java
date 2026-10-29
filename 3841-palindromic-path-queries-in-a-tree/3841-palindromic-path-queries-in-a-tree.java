import java.util.*;

class Solution {
    private List<Integer>[] graph;
    private int[][] up;
    private int[] depth, tin, tout;
    private char[] chars;
    private int timer, LOG;
    private Fenwick fenwick;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        chars = s.toCharArray();
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        fenwick = new Fenwick(n);

        buildTree(n);

        // Initialize root-to-node character masks.
        // Each node contributes its character to its subtree.
        for (int u = 0; u < n; u++) {
            int mask = 1 << (chars[u] - 'a');
            fenwick.rangeXor(tin[u], tout[u], mask);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split("\\s+");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (newChar - 'a');

                int delta = oldMask ^ newMask;

                fenwick.rangeXor(tin[u], tout[u], delta);
                chars[u] = newChar;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int mask = getPrefix(u)
                         ^ getPrefix(v)
                         ^ (1 << (chars[lca] - 'a'));

                // A palindrome allows at most one odd frequency.
                boolean possible = (mask & (mask - 1)) == 0;

                answer.add(possible);
            }
        }

        return answer;
    }

    private void buildTree(int n) {
        int[] stack = new int[n];
        int[] parent = new int[n];
        int[] iter = new int[n];

        int top = 0;
        stack[top] = 0;
        parent[top] = -1;
        iter[top] = 0;
        top++;

        timer = 0;

        while (top > 0) {
            int idx = top - 1;
            int u = stack[idx];

            if (iter[idx] == 0) {
                tin[u] = timer++;

                up[0][u] = (parent[idx] == -1)
                         ? u : parent[idx];

                if (parent[idx] != -1) {
                    depth[u] = depth[parent[idx]] + 1;
                }
            }

            if (iter[idx] < graph[u].size()) {
                int v = graph[u].get(iter[idx]++);

                if (v == parent[idx]) {
                    continue;
                }

                stack[top] = v;
                parent[top] = u;
                iter[top] = 0;
                top++;

            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        // Binary lifting table for LCA queries.
        for (int j = 1; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }
    }

    private int getPrefix(int u) {
        return fenwick.query(tin[u]);
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    static class Fenwick {
        private final int[] tree;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        private void add(int index, int value) {
            index++;

            while (index <= n + 1) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        void rangeXor(int left, int right, int value) {
            add(left, value);
            add(right + 1, value);
        }

        int query(int index) {
            int result = 0;
            index++;

            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }

            return result;
        }
    }
}