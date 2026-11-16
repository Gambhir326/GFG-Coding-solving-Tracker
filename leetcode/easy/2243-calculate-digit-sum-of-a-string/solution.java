class Solution {
    public String digitSum(String s, int k) {
       while (s.length() > k) {
            StringBuilder merged = new StringBuilder();

            for (int i = 0; i < s.length(); i += k) {
                int end = Math.min(i + k, s.length());
                int sum = 0;

                for (int j = i; j < end; j++) {
                    sum += s.charAt(j) - '0';
                }

                merged.append(sum);
            }

            s = merged.toString();
        }

        return s;
    }
}