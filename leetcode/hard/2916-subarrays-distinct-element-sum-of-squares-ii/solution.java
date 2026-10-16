class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];      // last index where each value appeared
        Arrays.fill(last, -1);

        long cur = 0;                      // sum of c[l]^2 for subarrays ending at r
        long ans = 0;

        for (int r = 0; r < n; r++) {
            int prev = last[nums[r]];

            // starts l in [prev+1 .. r] each gain one distinct value
            long s = query(1, 0, n - 1, prev + 1, r);     // sum of their old counts
            cur = (cur + 2 * (s % MOD) + (r - prev)) % MOD; // (c+1)^2 = c^2 + 2c + 1
            update(1, 0, n - 1, prev + 1, r, 1);           // now actually add 1

            ans = (ans + cur) % MOD;
            last[nums[r]] = r;
        }
        return (int) ans;
    }
     void push(int node, int lo, int hi) {
        if (lazy[node] == 0) return;
        int mid = (lo + hi) / 2;
        long x = lazy[node];
        sum[2 * node] += x * (mid - lo + 1);   lazy[2 * node] += x;
        sum[2 * node + 1] += x * (hi - mid);   lazy[2 * node + 1] += x;
        lazy[node] = 0;
     }
      void update(int node, int lo, int hi, int l, int r, long val) {
        if (r < lo || hi < l) return;
        if (l <= lo && hi <= r) {
            sum[node] += val * (hi - lo + 1);
            lazy[node] += val;
            return;
        }
        push(node, lo, hi);
        int mid = (lo + hi) / 2;
        update(2 * node, lo, mid, l, r, val);
        update(2 * node + 1, mid + 1, hi, l, r, val);
        sum[node] = sum[2 * node] + sum[2 * node + 1];
    }
    long query(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return sum[node];
        push(node, lo, hi);
        int mid = (lo + hi) / 2;
        return query(2 * node, lo, mid, l, r) + query(2 * node + 1, mid + 1, hi, l, r);
    }
}