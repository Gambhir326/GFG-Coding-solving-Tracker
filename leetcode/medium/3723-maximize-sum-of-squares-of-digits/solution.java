class Solution {
    public String maxSumOfSquares(int num, int sum) {
       if ((long) sum > 9L * num) return "";

        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            int d = Math.min(9, sum);
            sb.append((char) ('0' + d));
            sum -= d;
        }
    return sb.toString();
    }
}