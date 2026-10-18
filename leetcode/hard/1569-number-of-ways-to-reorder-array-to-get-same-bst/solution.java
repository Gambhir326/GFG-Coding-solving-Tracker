class Solution {
    static final long MOD = 1_000_000_007L;
    private long[][] PT;

    private long solve(List<Integer> nums){
        int m = nums.size();
        if(m < 3)  {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = nums.get(0);

        for(int i = 1; i < m; i++) {
            if(root > nums.get(i)){
                right.add(nums.get(i));
            }
            else {
                left.add(nums.get(i));
            }
        }

        long x = solve(left) % MOD;
        long y = solve(right) % MOD;
        long z = PT[m - 1][left.size()];
        return(((x * y) % MOD) * z) % MOD;
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        PT = new long[n + 1][];

        for(int i = 0; i <= n ; i++) {

            PT[i] = new long[i + 1];

            Arrays.fill(PT[i], 1);

            for(int j = 1; j < i; j++) {
                PT[i][j] = (PT[i - 1][j - 1] + PT[i - 1][j] )% MOD;
            }
        }
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            list.add(nums[i]);
        }
        return(int) ((solve(list) - 1 + MOD) % MOD);
    }
}