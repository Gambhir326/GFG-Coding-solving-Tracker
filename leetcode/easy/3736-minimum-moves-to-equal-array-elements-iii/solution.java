class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];
        for (int x : nums) max = Math.max(max, x);

        int count = 0;
        for (int x : nums) count += max - x;
        return count;
    }
}