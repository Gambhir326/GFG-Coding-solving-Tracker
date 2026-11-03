class Solution {
    public int minimumOperations(int[] nums) {
        // int n = nums.length;
        long count = 0;

        for(int num : nums){
            if (num % 3 != 0) count++;
        }
        return(int) count;
        
    }
}