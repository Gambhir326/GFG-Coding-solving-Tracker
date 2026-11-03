class Solution {
    public int minimumOperations(int[] nums) {
        // int n = nums.length;
        long count = 0;

        for(int num : nums){
            if(num % 3 == 1){
                num--;
                count++;
            }
            else if(num % 3 == 2) {
                num++;
                count++;
            }
            
        }
        return(int) count;
        
    }
}