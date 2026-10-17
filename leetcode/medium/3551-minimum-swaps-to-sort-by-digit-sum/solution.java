class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        int[] ds = new int[n];
        for (int i = 0; i < n; i++) ds[i] = digitSum(nums[i]);

        Integer[] target = new Integer[n];
        for (int i = 0; i < n; i++) target[i] = i;

        Comparator<Integer> cmp = (a, b) -> {
            if (ds[a] != ds[b]) return Integer.compare(ds[a], ds[b]);
            return Integer.compare(nums[a], nums[b]);
        };
        Arrays.sort(target, cmp);

        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int len = 0, j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = target[j];
                len++;
            }
            if (len > 0) swaps += len - 1;
        }
        return swaps;
    }

    private int digitSum(int x) {
        int s = 0;
        while (x > 0) { s += x % 10; x /= 10; }
        return s;
    }
}