class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();

        int n = nums.length;

        for(int i = 0 ; i < n ; i++) {
            if(nums[i] == target) res.add(i);
        }
        // if(!res.isEmpty()) {
        //     return res;
        // }
        // else{
        //     return new ArrayList<>();
        // }
        return res;
    }
}