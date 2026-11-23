import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
int n = classroom[0].length();
        int startR = 0;
        int startC = 0;

        // Give every litter cell an ID
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        if (allCollected == 0) {
            return 0;
        }

        /*
         * visited[r][c][mask][energy]
         */
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(startR, startC, energy, 0));

        visited[startR][startC][0][energy] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                State current = queue.poll();

                int r = current.r;
                int c = current.c;
                int e = current.energy;
                int mask = current.mask;

                if (mask == allCollected) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Cannot move with zero energy
                    if (e == 0) {
                        continue;
                    }

                    int newEnergy = e - 1;
                    int newMask = mask;

                    // Collect litter
                    if (classroom[nr].charAt(nc) == 'L') {

                        int id = litterId[nr][nc];

                        newMask |= (1 << id);
                    }

                    // Reset energy
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {

                        visited[nr][nc][newMask][newEnergy] = true;

                        queue.offer(
                            new State(
                                nr,
                                nc,
                                newEnergy,
                                newMask
                            )
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    static class State {

        int r;
        int c;
        int energy;
        int mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }
}