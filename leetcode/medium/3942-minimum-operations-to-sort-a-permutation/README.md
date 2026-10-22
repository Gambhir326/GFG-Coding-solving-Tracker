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
**Runtime:** 3 ms (beats 90.00%)  
**Memory:** 94.9 MB (beats 81.67%)  
**Submitted:** 2026-10-22T16:51:41.466Z  

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 2) {                       // any order of 0..n-1 is a rotation here
            return nums[0] == 0 ? 0 : 1;
        }

        boolean asc = true, dsc = true;
        for (int i = 1; i < n && (asc || dsc); i++) {
            int prev = nums[i - 1], cur = nums[i];
            if (asc && cur != (prev == n - 1 ? 0 : prev + 1)) asc = false;
            if (dsc && prev != (cur == n - 1 ? 0 : cur + 1)) dsc = false;
        }

        if (asc) {
            int s = nums[0];
            if (s == 0) return 0;
            return Math.min(n - s, s + 2);  // rotate left vs reverse-rotate-reverse
        }
        if (dsc) {
            int fromEnd = (n - nums[n - 1]) % n + 1;  // reverse, then rotate
            int fromFront = (nums[0] + 1) % n + 1;    // rotate, then reverse
            return Math.min(fromEnd, fromFront);
        }
        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/)