class Solution {

    public int countCompleteComponents(int n, int[][] edges) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                int[] count = new int[2];

                dfs(i, graph, visited, count);

                int vertices = count[0];

                // Each undirected edge was counted twice.
                int edgeCount = count[1] / 2;

                if (edgeCount == vertices * (vertices - 1) / 2) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void dfs(
            int node,
            List<Integer>[] graph,
            boolean[] visited,
            int[] count) {

        visited[node] = true;

        count[0]++;                    // vertices
        count[1] += graph[node].size(); // degree sum

        for (int neighbor : graph[node]) {

            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, count);
            }
        }
    }
}