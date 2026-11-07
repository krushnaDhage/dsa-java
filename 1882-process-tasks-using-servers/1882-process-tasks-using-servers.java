import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        // Available servers: {weight, index}
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: {freeTime, weight, index}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                if (a[1] != b[1]) {
                    return Long.compare(a[1], b[1]);
                }
                return Long.compare(a[2], b[2]);
            }
        );

        // Initially, all servers are available.
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] answer = new int[m];

        // Maintain actual scheduling time.
        long time = 0;

        for (int i = 0; i < m; i++) {

            // Update time to the arrival time of this task.
            time = Math.max(time, i);

            // Release all servers that have completed their tasks.
            while (!busy.isEmpty() && busy.peek()[0] <= time) {

                long[] s = busy.poll();

                available.offer(new int[]{
                    (int) s[1],
                    (int) s[2]
                });
            }

            // If no server is available, wait.
            if (available.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers available at this time.
                while (!busy.isEmpty() && busy.peek()[0] <= time) {

                    long[] s = busy.poll();

                    available.offer(new int[]{
                        (int) s[1],
                        (int) s[2]
                    });
                }
            }

            // Select the server with minimum weight,
            // then minimum index.
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            answer[i] = index;

            // Schedule task and calculate completion time.
            long freeTime = time + tasks[i];

            busy.offer(new long[]{
                freeTime,
                weight,
                index
            });
        }

        return answer;
    }
}