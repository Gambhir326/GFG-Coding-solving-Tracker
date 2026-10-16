class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        lazy = new long[4 * n];
        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long cur = 0, ans = 0;
        for (int r = 0; r < n; r++) {
            int prev = last[nums[r]];
            long s = query(1, 0, n - 1, prev + 1, r);
            cur = (cur + 2 * (s % MOD) + (r - prev)) % MOD;
            update(1, 0, n - 1, prev + 1, r);
            ans = (ans + cur) % MOD;
            last[nums[r]] = r;
        }
        return (int) ans;
    }

    // add 1 to every position in [l, r]
    void update(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return;
        if (l <= lo && hi <= r) {
            sum[node] += hi - lo + 1;
            lazy[node] += 1;
            return;
        }
        push(node, lo, hi);
        int mid = (lo + hi) / 2;
        update(2 * node, lo, mid, l, r);
        update(2 * node + 1, mid + 1, hi, l, r);
        sum[node] = sum[2 * node] + sum[2 * node + 1];
    }

    // sum of values in [l, r]
    long query(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return sum[node];
        push(node, lo, hi);
        int mid = (lo + hi) / 2;
        return query(2 * node, lo, mid, l, r) + query(2 * node + 1, mid + 1, hi, l, r);
    }

    // pass pending "+x" down to the children
    void push(int node, int lo, int hi) {
        if (lazy[node] == 0) return;
        int mid = (lo + hi) / 2;
        long x = lazy[node];
        sum[2 * node] += x * (mid - lo + 1);
        lazy[2 * node] += x;
        sum[2 * node + 1] += x * (hi - mid);
        lazy[2 * node + 1] += x;
        lazy[node] = 0;
    }
}