class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        long count = 0;

        for(int num : nums){
            // while(num < 3) {
            //     if(num == 2){
            //         num++;
            //         count++;
            //     }
            //     else if(num == 1){
            //         n--;
            //         count++;
            //     }
            // }
            // while(num > 3 && num % 3 == 1) {
            //     num--;
            //     count++;
            // }
            // while(num > 3 && num % 3 == 2) {
            //     num++;
            //     count++;
            // }
            
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