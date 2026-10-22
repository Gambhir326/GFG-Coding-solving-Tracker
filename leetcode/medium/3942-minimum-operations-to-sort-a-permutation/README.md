# Minimum Operations to Sort a Permutation

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums` of length `n`, where `nums` is a permutation of the integers from 0 to `n - 1`.

You may perform  **only**  the following operations:

- Reverse the entire array.
- Rotate Left by One: Move the first element to the end of the array, and rest elements to left by one position.

Return an integer denoting the  **minimum**  number of operations required to sort the array in  **increasing**  order. If it is  **not possible**  to sort the array using only the given operations, return -1.

 

 **Example 1:** 

 **Input:**  nums = [0,2,1]

 **Output:**  2

 **Explanation:** 

- Rotate Left by one: [2, 1, 0]
- Reverse the array: [0, 1, 2]

The array becomes sorted in 2 operations, which is minimal

 **Example 2:** 

 **Input:**  nums = [1,0,2]

 **Output:**  2

 **Explanation:** 

- Reverse the array: [2, 0, 1]
- Rotate Left by one: [0, 1, 2]

The array becomes sorted in 2 operations, which is minimal.

 **Example 3:** 

 **Input:**  nums = [2,0,1,3]

 **Output:**  -1

 **Explanation:** 

It is impossible to reach `[2, 0, 1, 3]`. Thus, the answer is -1.

 

 **Constraints:** 

- 1 <= n == nums.length <= 105
- 0 <= nums[i] <= n - 1
- nums is a permutation of integers from 0 to n - 1.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 41.8 MB  
**Submitted:** 2026-10-22T16:49:38.259Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/)