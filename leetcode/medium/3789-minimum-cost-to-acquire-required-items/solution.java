class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        return Math.max(bound(need1, cost1, need2, cost2, costBoth), bound(need2, cost2, need1, cost1, costBoth));
    }

    private long bound(long needA, int costA, long needB, int costB, int costBoth) {
        long pA = Math.min(costA, costBoth);  // a takes its full price
        long pB = Math.min(costB, costBoth - pA);  // b gets what's left of the bundle
        return(int) needA * pA + needB * pB;
    }
}