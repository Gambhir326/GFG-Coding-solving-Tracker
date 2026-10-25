class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;

                boolean closed = true;
                java.util.ArrayDeque<int[]> stack = new java.util.ArrayDeque<>();
                stack.push(new int[]{i, j});
                grid[i][j] = 1;

                while (!stack.isEmpty()) {
                    int[] c = stack.pop();
                    if (c[0] == 0 || c[1] == 0 || c[0] == m - 1 || c[1] == n - 1) closed = false;
                    for (int[] d : dirs) {
                        int x = c[0] + d[0], y = c[1] + d[1];
                        if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 0) {
                            grid[x][y] = 1;
                            stack.push(new int[]{x, y});
                        }
                    }
                }
                if (closed) count++;
            }
        }
        return count;
    }
}