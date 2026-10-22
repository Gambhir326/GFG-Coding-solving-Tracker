class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int asc = 0;
        int dsc = 0;
        
        for(int i = 1 ; i < n; i++) {
            //{condition} ? {expressionIfTrue}:{expressionIfFalse}
            asc += (nums[i] == (nums[i-1]+1)%n) ? 1 : 0;
            dsc += (nums[i-1] == (nums[i]+1)%n) ? 1 : 0;
        }
        

        // since it is in ascending there is no need for solving the array if and only if the starting index value is 0, because it will go through 0,1,2,3,4,........,n-1
        if(asc == n - 1 && nums[0] == 0) {
            return 0;
        }


        if(asc == n-1) {
            int leftRot  = n - nums[0];
            int rightRot = 1 + (nums[n-1] + 1) + 1;
            return Math.min(leftRot, rightRot);
        }

        if(dsc == n-1) {
            //Mandatory 1 Reverse
            int leftRot = n - nums[n-1] + 1;

            //x Left Rot + 1 Reverse
            int rightRot = (nums[0]+1)%n + 1;

            return Math.min(leftRot, rightRot);
        }

        return -1;
        
    }
}