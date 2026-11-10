class Solution {
    public int climbStairs(int n, int[] costs) {
        // dist[i] stores the minimum cost to reach step i
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        // Min-PriorityQueue storing {node, currentDist}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int u = top[0];
            int currentDist = top[1];

            // If we reached the destination step n, we have the guaranteed shortest distance
            if (u == n) {
                return currentDist;
            }

            // Skip outdated pairs in the priority queue
            if (currentDist > dist[u]) {
                continue;
            }

            // Allowed transitions: jump to u + 1, u + 2, or u + 3
            for (int jump = 1; jump <= 3; jump++) {
                int v = u + jump;
                if (v <= n) {
                    // costs is 1-indexed, so step v uses costs[v - 1]
                    int edgeWeight = costs[v - 1] + (jump * jump);
                    
                    if (dist[u] + edgeWeight < dist[v]) {
                        dist[v] = dist[u] + edgeWeight;
                        pq.offer(new int[]{v, dist[v]});
                    }
                }
            }
        }

        return dist[n];
    }
}