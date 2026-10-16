class Solution {
    public int countAlwaysFound(int[] arr) {
        int n = arr.length;

        int[] suffixMin = new int[n];
        suffixMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(arr[i], suffixMin[i + 1]);
        }

        int count = 0;
        int prefixMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            boolean biggerThanLeft   = arr[i] > prefixMax;
            boolean smallerThanRight = (i == n - 1) || arr[i] < suffixMin[i + 1];
            if (biggerThanLeft && smallerThanRight) count++;
            prefixMax = Math.max(prefixMax, arr[i]);
        }
        return count;
    }
}