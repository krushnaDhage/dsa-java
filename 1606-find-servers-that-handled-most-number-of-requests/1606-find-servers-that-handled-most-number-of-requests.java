import java.util.*;

class Solution {
    public List<Integer> busiestServers(
            int k, int[] arrival, int[] load) {

        int[] count = new int[k];

        // Available servers in sorted order.
        TreeSet<Integer> available = new TreeSet<>();

        // Busy servers: {finishTime, serverId}.
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Initially all servers are available.
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            int currentTime = arrival[i];

            // Release servers whose tasks are completed.
            while (!busy.isEmpty()
                    && busy.peek()[0] <= currentTime) {

                long[] server = busy.poll();

                int serverId = (int) server[1];

                available.add(serverId);
            }

            // If every server is busy, drop the request.
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server.
            int preferred = i % k;

            // Find the next available server.
            Integer serverId = available.ceiling(preferred);

            // Wrap around if no server is found.
            if (serverId == null) {
                serverId = available.first();
            }

            // Remove selected server from available set.
            available.remove(serverId);

            // Calculate when the server becomes free.
            long finishTime = (long) currentTime + load[i];

            busy.offer(new long[]{
                finishTime,
                serverId
            });

            // Increase handled request count.
            count[serverId]++;
        }

        // Find maximum number of handled requests.
        int maxCount = 0;

        for (int c : count) {
            maxCount = Math.max(maxCount, c);
        }

        // Collect all busiest servers.
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                answer.add(i);
            }
        }

        return answer;
    }
}