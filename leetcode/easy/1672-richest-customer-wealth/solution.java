class Solution {
    public int maximumWealth(int[][] accounts) {
        int maxWealth = 0;
        int m = accounts.length;

        for (int i = 0; i < m; i++) {
            int currentWealth = 0;
            int n = accounts[i].length; 
            for (int j = 0; j < n; j++) {
                currentWealth += accounts[i][j];
            }

            if (currentWealth > maxWealth) {
                maxWealth = currentWealth;
            }
        }

        return maxWealth;
    }
}