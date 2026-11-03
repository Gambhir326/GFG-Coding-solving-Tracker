class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        for (int x : nums) max = Math.max(max, x);

        long count = 0;
        for (int x : nums) count += max - x;
        return(int) count;
    }
}