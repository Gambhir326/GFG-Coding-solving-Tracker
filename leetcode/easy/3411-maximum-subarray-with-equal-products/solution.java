class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1; // Any subarray of length 1 or 2 is valid (for len 2: a*b == gcd(a,b)*lcm(a,b))

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long currentGcd = 0;
            long currentLcm = 1;
            for (int j = i; j < n; j++) {
                prod *= nums[j];
                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);
                // Max possible lcm(1..10) * gcd is 2520 * 10 = 25200.
                // Breaking early prevents long overflow.
                // if (prod > 25200) {
                //     break;
                // }

                if (prod == currentGcd * currentLcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}