# Number of Ways to Reorder Array to Get Same BST

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an array `nums` that represents a permutation of integers from `1` to `n`. We are going to construct a binary search tree (BST) by inserting the elements of `nums` in order into an initially empty BST. Find the number of different ways to reorder `nums` so that the constructed BST is identical to that formed from the original array `nums`.

- For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.

Return  *the number of ways to reorder*  `nums`  *such that the BST formed is identical to the original BST formed from*  `nums`.

Since the answer may be very large,  **return it modulo** `109 + 7`.

 

 **Example 1:** 

```
Input: nums = [2,1,3]
Output: 1
Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.

```

 **Example 2:** 

```
Input: nums = [3,4,5,1,2]
Output: 5
Explanation: The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]

```

 **Example 3:** 

```
Input: nums = [1,2,3]
Output: 0
Explanation: There are no other orderings of nums that will yield the same BST.

```

 

 **Constraints:** 

- 1 <= nums.length <= 1000
- 1 <= nums[i] <= nums.length
- All integers in nums are distinct.

## Solution

**Language:** Java  
**Runtime:** 56 ms (beats 51.82%)  
**Memory:** 85.6 MB (beats 76.64%)  
**Submitted:** 2026-10-18T09:33:48.664Z  

```java
class Solution {
    static final long MOD = 1_000_000_007L;
    private long[][] PT;

    private long solve(List<Integer> nums){
        int m = nums.size();
        if(m < 3)  {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = nums.get(0);

        for(int i = 1; i < m; i++) {
            if(root > nums.get(i)){
                right.add(nums.get(i));
            }
            else {
                left.add(nums.get(i));
            }
        }

        long x = solve(left) % MOD;
        long y = solve(right) % MOD;
        long z = PT[m - 1][left.size()];
        return(((x * y) % MOD) * z) % MOD;
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        PT = new long[n + 1][];

        for(int i = 0; i <= n ; i++) {

            PT[i] = new long[i + 1];

            Arrays.fill(PT[i], 1);

            for(int j = 1; j < i; j++) {
                PT[i][j] = (PT[i - 1][j - 1] + PT[i - 1][j] )% MOD;
            }
        }
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            list.add(nums[i]);
        }
        return(int) ((solve(list) - 1 + MOD) % MOD);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/)