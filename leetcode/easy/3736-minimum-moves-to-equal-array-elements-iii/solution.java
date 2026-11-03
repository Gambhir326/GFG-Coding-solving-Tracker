class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int count = 0;
        int max = nums[0];

        for(int i = 1; i < n ; i++) {
            if(max < nums[i]) {
                max = nums[i];
            }
        }
        for(int i = 0; i < n; i++) {
            while(nums[i] < max){
                nums[i]++;
                count++;
            }
        }
        return count;
    }
}