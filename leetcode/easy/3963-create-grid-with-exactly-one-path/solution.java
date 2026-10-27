class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || i == m - 1) {
                    grid[i][j] = '.';
                } else {
                    grid[i][j] = '#';
                }
            }
        }

        // Convert the 2D char array to String[]
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}