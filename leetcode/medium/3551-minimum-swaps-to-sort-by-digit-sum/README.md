# Minimum Swaps to Sort by Digit Sum

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an array `nums` of  **distinct**  positive integers. You need to sort the array in  **increasing**  order based on the sum of the digits of each number. If two numbers have the same digit sum, the  **smaller**  number appears first in the sorted order.

Return the  **minimum**  number of swaps required to rearrange `nums` into this sorted order.

A  **swap**  is defined as exchanging the values at two distinct positions in the array.

 

 **Example 1:** 

 **Input:**  nums = [37,100]

 **Output:**  1

 **Explanation:** 

- Compute the digit sum for each integer: [3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]
- Sort the integers based on digit sum: [100, 37]. Swap 37 with 100 to obtain the sorted order.
- Thus, the minimum number of swaps required to rearrange nums is 1.

 **Example 2:** 

 **Input:**  nums = [22,14,33,7]

 **Output:**  0

 **Explanation:** 

- Compute the digit sum for each integer: [2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]
- Sort the integers based on digit sum: [22, 14, 33, 7]. The array is already sorted.
- Thus, the minimum number of swaps required to rearrange nums is 0.

 **Example 3:** 

 **Input:**  nums = [18,43,34,16]

 **Output:**  2

 **Explanation:** 

- Compute the digit sum for each integer: [1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]
- Sort the integers based on digit sum: [16, 34, 43, 18]. Swap 18 with 16, and swap 43 with 34 to obtain the sorted order.
- Thus, the minimum number of swaps required to rearrange nums is 2.

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 109
- nums consists of distinct positive integers.

## Solution

**Language:** Java  
**Runtime:** 1211 ms (beats 9.40%)  
**Memory:** 187 MB (beats 24.16%)  
**Submitted:** 2026-10-17T09:43:02.641Z  

```java
class Solution {

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    private int compareByDigitSum(int num1, int num2) {
        int num1DigitSum = getDigitSum(num1);
        int num2DigitSum = getDigitSum(num2);
        if (num1DigitSum != num2DigitSum) {
            return Integer.compare(num1DigitSum, num2DigitSum);
        }
        return Integer.compare(num1, num2);
    }


    public int minSwaps(int[] nums) {
        int n = nums.length;

        Integer[] afterSorted = new Integer[n];
        for (int i = 0; i < n; i++) {
            afterSorted[i] = nums[i];
        }

        Arrays.sort(afterSorted, this::compareByDigitSum);

        Map<Integer, Integer> valToIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valToIdx.put(nums[i], i);
        }

        int swapCount = 0;
        for (int i = 0; i < n; i++) {
            int expectedVal = afterSorted[i];
            int currVal = nums[i];

            if (expectedVal == currVal) continue;

            int idx = valToIdx.get(expectedVal);

            nums[i] = expectedVal;
            nums[idx] = currVal;

            valToIdx.put(expectedVal, i);
            valToIdx.put(currVal, idx);

            swapCount++;
        }

        return swapCount;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/)