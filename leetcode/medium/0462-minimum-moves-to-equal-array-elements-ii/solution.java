class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        long moves = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            moves += (long) nums[j] - nums[i];   // cost of each outer pair
            i++;
            j--;
        }
        return (int) moves;
    }
}