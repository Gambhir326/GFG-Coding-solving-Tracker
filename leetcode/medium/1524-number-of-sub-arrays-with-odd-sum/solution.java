class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long count = 0;
        int prefixSum = 0;
        
        // An empty prefix has a sum of 0, which is even
        int evenCount = 1;
        int oddCount = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 1) {
                // Current prefix sum is odd: add all previous even prefix sums
                count = (count + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even: add all previous odd prefix sums
                count = (count + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) count;
    }
}