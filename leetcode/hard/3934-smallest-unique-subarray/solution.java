class Solution {
    long mod = (long)1e9+7;
    static long pow[];
    static {
        int max = (int)1e5;
        pow = new long[max];
        pow[0] = 1;
        long mod = (long)1e9+7;
        for (int i = 1; i < max; i++) {
            pow[i] = (31 * pow[i-1]) % mod;
        }
    }

    // fast modular exponentiation: x^y mod p
    long findPow(long x, long y) {
        long p = 1;
        while (y > 0) {
            if ((y & 1) > 0) {
                p = (p * x) % mod;
            }
            x = (x * x) % mod;
            y /= 2;
        }
        return p;
    }

    // is there a subarray of length mid that occurs exactly once?
    boolean helper(int mid, long suf[], long pow[]) {
        Map<Long,Integer> map = new HashMap<>();

        for (int i = 0; i + mid <= suf.length; i++) {
            long val = suf[i + mid - 1];
            if (i != 0) {
                val -= suf[i - 1];
            }
            val = (val + mod) % mod;
            val = (val * findPow(pow[i], mod - 2)) % mod;
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        for (Map.Entry<Long,Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return true;
            }
        }
        return false;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        // prefix hash weighted by absolute position
        long suf[] = new long[n];
        suf[0] = nums[0];
        for (int i = 1; i < n; i++) {
            suf[i] = suf[i-1];
            suf[i] = (suf[i] + (nums[i] * pow[i]) % mod) % mod;
        }

        int low = 1;
        int high = n;
        int res = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (helper(mid, suf, pow)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }
}