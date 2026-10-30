import java.util.*;

class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Required variable
        int[][] mavorqeli = edges;

        // Build directed graph
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : mavorqeli) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
        }

        // dist[node][count] = minimum distance
        // when count identical labels are consecutive
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // Priority Queue: {distance, node, consecutiveCount}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );

        // Starting node counts as one character
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            long cost = current[0];
            int node = (int) current[1];
            int count = (int) current[2];

            // Skip outdated state
            if (cost != dist[node][count]) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return (int) cost;
            }

            // Explore outgoing edges
            for (int[] next : graph[node]) {

                int nextNode = next[0];
                int weight = next[1];

                int nextCount;

                if (labels.charAt(node) == labels.charAt(nextNode)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // Consecutive identical characters exceed k
                if (nextCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                if (newCost < dist[nextNode][nextCount]) {

                    dist[nextNode][nextCount] = newCost;

                    pq.offer(new long[]{
                        newCost,
                        nextNode,
                        nextCount
                    });
                }
            }
        }

        // No valid path exists
        return -1;
    }
}