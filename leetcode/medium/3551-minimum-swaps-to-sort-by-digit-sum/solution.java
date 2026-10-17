class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] keys = new long[n];

        for (int i = 0; i < n; i++) {
            int x = nums[i], s = 0;
            while (x > 0) { s += x % 10; x /= 10; }   
            keys[i] = ((long) s << 47) | ((long) nums[i] << 17) | i;
        }

        Arrays.sort(keys);                           

        int mask = (1 << 17) - 1;                     
        int[] target = new int[n];
        for (int k = 0; k < n; k++) target[k] = (int) (keys[k] & mask);

        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int len = 0, j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = target[j];
                len++;
            }
            if (len > 0) swaps += len - 1;// a cycle of length L needs L-1 swaps
        }
        return swaps;
    }
}