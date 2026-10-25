class Solution {
    public int countStaircase(int[] nums) {
        int[] qal = nums;
        int n = qal.length;

        int ans = 0;

        int[] freq = new int[100001];
        int[] order = new int[100001];
        Arrays.fill(order, -1);
        int[] distinct = new int[n];

        for(int i = 0; i < n; i++) {
            int k = 0;
            int count = 0;

            for(int j = i; j < n; j++) {
                int val = qal[j];

                if(order[val] == -1) {
                    k++;
                    order[val] = k;
                    distinct[k - 1] = val;
                }

                int target = order[val];

                if(freq[val] == target) {
                    count--;
                }

                freq[val]++;

                if(freq[val] == target) {
                    count++;
                }

                int len = j - i + 1;

                int rlen = k * ( k + 1) / 2;

                if(len == rlen && count == k) {
                    ans++;
                }
            }

            for(int idx = 0; i < k; i++) {
                int val2 = distinct[idx];
                freq[val2] = 0;
                order[val2] = -1;
            }
        }
        return ans;
    }
}