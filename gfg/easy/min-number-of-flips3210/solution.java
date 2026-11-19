class Solution {
    public static int minFlips(String s) {
        int n = s.length();
        int mismatch = 0;                         // mismatches against "0101..."
        for (int i = 0; i < n; i++) {
            int expected = i % 2;                 // 0 at even index, 1 at odd index
            if (s.charAt(i) - '0' != expected) mismatch++;
        }
        return Math.min(mismatch, n - mismatch);  // "1010..." needs the other positions
    }
}