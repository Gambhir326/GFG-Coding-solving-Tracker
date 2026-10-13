class Solution {
    public long maxAlternatingSum(int[] nums) {
         int n = nums.length;
        long[] sq = new long[n];
        for (int i = 0; i < n; i++) {
            sq[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(sq);

        int neg = n / 2;   
        long score = 0;
        for (int i = 0; i < n; i++) {
            score += (i < neg) ? -sq[i] : sq[i];
        }
        return score;
    }
}