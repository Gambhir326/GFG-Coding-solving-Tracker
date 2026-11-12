class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxLen = 1;
        int inc = 1; // Tracks current strictly increasing subarray length
        int dec = 1; // Tracks current strictly decreasing subarray length

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1; // Reset decreasing streak
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1; // Reset increasing streak
            } else {
                // Equal elements break both strictly increasing and decreasing streaks
                inc = 1;
                dec = 1;
            }
            
            maxLen = Math.max(maxLen, Math.max(inc, dec));
        }

        return maxLen;
    }
}