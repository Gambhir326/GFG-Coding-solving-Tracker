class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];

        // 1. Sort logs by timestamp
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        // 2. Pair each query with its original index and sort by query time
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        // 3. Sliding window over logs
        // serverCount[id] tracks how many requests server 'id' has in the current window
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        int left = 0;
        int right = 0;
        int numLogs = logs.length;

        for (int[] q : sortedQueries) {
            int queryTime = q[0];
            int originalIndex = q[1];
            int windowStart = queryTime - x;

            // Expand right pointer: include logs with time <= queryTime
            while (right < numLogs && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                if (serverCount[serverId] == 0) {
                    activeServers++;
                }
                serverCount[serverId]++;
                right++;
            }

            // Shrink left pointer: exclude logs with time < windowStart
            while (left < numLogs && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount[serverId]--;
                if (serverCount[serverId] == 0) {
                    activeServers--;
                }
                left++;
            }

            // Servers with 0 requests = total servers - active servers
            ans[originalIndex] = n - activeServers;
        }

        return ans;
    }
}