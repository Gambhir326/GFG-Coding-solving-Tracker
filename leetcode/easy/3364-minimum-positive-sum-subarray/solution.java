class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Fix the starting index i of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;

            // Expand the subarray to ending index j
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int len = j - i + 1;

                // Stop expanding if the subarray exceeds maximum allowed length r
                if (len > r) {
                    break;
                }

                // Check if length is within [l, r] and sum is strictly positive
                if (len >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}