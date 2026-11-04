import java.util.*;

/*
BIG IDEA (two parts):
Part A: For every window of size x, find the cheapest way to make all its elements equal. The best target value is the MEDIAN of the window.
    -> computeWindowCosts()
Part B: From all those windows, pick k NON-OVERLAPPING ones whose total cost is smallest. 
    -> pickKWindows()
 */

class Solution {
    // MAIN ENTRY: just runs Part A, then Part B.
    public long minOperations(int[] nums, int x, int k) {

        // cost[s] = operations needed to make nums[s .. s+x-1] all equal
        long[] cost = computeWindowCosts(nums, x);

        // choose k non-overlapping windows with the smallest total cost
        return pickKWindows(cost, nums.length, x, k);
    }
    // FUNCTION 1: SLIDING WINDOW -> cost of every window
    //
    // Why the median? Changing every element to value v costs sum(|a - v|).
    // That sum is smallest when v is the median of the window.
    //
    // How do we do it fast? Re-sorting each window would be too slow.
    // Instead we keep a Fenwick tree (BIT) over the values. As the window
    // slides one step, we add one element and remove one element, then ask
    // the BIT for the median and for the sums on each side of it.

    private long[] computeWindowCosts(int[] nums, int x) {
        int n = nums.length;

        // ---- STEP 1: coordinate compression -------------------------
        // Values can be as big as +-10^6, so we can't index a BIT by value.
        // We map every distinct value to a rank 1..m (1-based, BIT needs that).
        int[] vals = Arrays.stream(nums).distinct().sorted().toArray(); // sorted distinct values
        int m = vals.length;    // number of distinct values
        int[] id = new int[n];  // id[i] = rank of nums[i]
        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }

        // set up two BITs
        // cnt[r] : how many elements currently in the window have rank r
        // sum[r] : the sum of those elements
        // Both are stored in Fenwick form so prefix queries are O(log m).
        long[] cnt = new long[m + 1];
        long[] sum = new long[m + 1];

        long total = 0;                     // sum of all elements currently in the window
        int top = Integer.highestOneBit(m); // biggest power of two <= m (used for BIT descent)
        int kth = (x + 1) / 2;              // median position: for even x this is the LOWER median(either middle element gives the same cost)

        long[] cost = new long[n - x + 1];    // one cost per window start

        // slide the window across the array 
        for (int i = 0; i < n; i++) {

            // a) nums[i] ENTERS the window: add it to both BITs
            for (int p = id[i]; p <= m; p += p & -p) {
                cnt[p]++;
                sum[p] += nums[i];
            }
            total += nums[i];

            int s = i - x + 1;         // start index of the window ending at i
            if (s < 0) continue;       // window not full yet, keep filling

            // b) nums[s-1] LEAVES the window (it fell off the left side)
            //     For s == 0 nothing has left yet.
            if (s > 0) {
                int r = s - 1;
                for (int p = id[r]; p <= m; p += p & -p) {
                    cnt[p]--;
                    sum[p] -= nums[r];
                }
                total -= nums[r];
            }

            // c) FIND THE MEDIAN: the kth smallest element in the window.
            // BIT descent: walk down from the top power of two, jumping
            // forward whenever the jump still leaves us short of k elements.
            int pos = 0;                      // ranks 1..pos are all strictly before the median
            long rem = kth;                   // how many more elements we still need to pass
            for (int pw = top; pw > 0; pw >>= 1) {
                if (pos + pw <= m && cnt[pos + pw] < rem) {
                    pos += pw;
                    rem -= cnt[pos];
                }
            }
            int medIdx = pos + 1;             // rank of the median
            long v = vals[medIdx - 1];        // actual median value

            // d) c and sm = count and sum of window elements with rank <= medIdx
            // (i.e. elements that are <= the median)
            long c = 0, sm = 0;
            for (int p = medIdx; p > 0; p -= p & -p) {
                c += cnt[p];
                sm += sum[p];
            }

            // e) COST of this window
            //   Elements <= v need INCREMENTS up to v:
            //        v*c - sm
            //   Elements >  v need DECREMENTS down to v:
            //        (total - sm) - v*(x - c)
            //   (Elements equal to v add 0 either way, so duplicates are fine.)
            cost[s] = (v * c - sm) + ((total - sm) - v * (x - c));
        }
        return cost;
    }
    // FUNCTION 2: DP -> choose k non-overlapping windows, minimum total cost

    // dp[i][j] = minimum cost using only the FIRST i elements of the array
                // while having chosen exactly j windows.

    // For each (i, j) there are only two choices:
    //       Don't end a window at element i-1  -> dp[i-1][j]
    //       End a window at element i-1. That window covers indices

    //       [i-x, i-1], so before it we may only use the first i-x elements
    //       and j-1 windows:                    -> dp[i-x][j-1] + cost[i-x]
    // Take the smaller one.

    // This is why the state needs only "elements so far" and "windows chosen
    // so far": the only thing that matters about earlier choices is where
    // they stopped, and "first i elements" already guarantees no overlap.
    // Gaps between windows are allowed because of choice (1).

    private long pickKWindows(long[] cost, int n, int x, int k) {
        final long INF = Long.MAX_VALUE / 4;       // "impossible"; /4 avoids overflow when adding

        long[][] dp = new long[n + 1][k + 1];

        // Start everything as impossible...
        for (long[] row : dp) Arrays.fill(row, INF);

        // ...except: choosing 0 windows always costs 0, whatever the prefix length.
        for (int i = 0; i <= n; i++) dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                // Choice 1: skip element i-1 (no window ends here)
                dp[i][j] = dp[i - 1][j];

                // Choice 2: end a window at element i-1 (needs at least x elements)
                // and the earlier state must be reachable (not INF).
                if (i >= x && dp[i - x][j - 1] < INF) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + cost[i - x]);
                }
            }
        }

        // Answer: all n elements available, exactly k windows chosen.
        return dp[n][k];
    }
}