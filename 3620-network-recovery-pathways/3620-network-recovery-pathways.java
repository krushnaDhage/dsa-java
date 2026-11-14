class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;
        int maxEdge = 0;

        for (int[] e : edges) {
            maxEdge = Math.max(maxEdge, e[2]);
        }

        int low = 0, high = maxEdge;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(edges, online, k, mid, n)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int[][] edges, boolean[] online, long k,
                             int minEdge, int n) {

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build filtered DAG
        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (w < minEdge)
                continue;

            // Intermediate nodes must be online
            if (v != n - 1 && !online[v])
                continue;

            graph[u].add(new int[]{v, w});
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        long INF = Long.MAX_VALUE / 2;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        while (!q.isEmpty()) {

            int u = q.poll();

            for (int[] next : graph[u]) {

                int v = next[0];
                int w = next[1];

                if (dist[u] != INF) {
                    dist[v] = Math.min(dist[v], dist[u] + w);
                }

                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        return dist[n - 1] <= k;
    }
}