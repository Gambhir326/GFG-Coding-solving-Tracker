class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 2) {                       // any order of 0..n-1 is a rotation here
            return nums[0] == 0 ? 0 : 1;
        }

        boolean asc = true, dsc = true;
        for (int i = 1; i < n && (asc || dsc); i++) {
            int prev = nums[i - 1], cur = nums[i];
            if (asc && cur != (prev == n - 1 ? 0 : prev + 1)) asc = false;
            if (dsc && prev != (cur == n - 1 ? 0 : cur + 1)) dsc = false;
        }

        if (asc) {
            int s = nums[0];
            if (s == 0) return 0;
            return Math.min(n - s, s + 2);  // rotate left vs reverse-rotate-reverse
        }
        if (dsc) {
            int fromEnd = (n - nums[n - 1]) % n + 1;  // reverse, then rotate
            int fromFront = (nums[0] + 1) % n + 1;    // rotate, then reverse
            return Math.min(fromEnd, fromFront);
        }
        return -1;
    }
}