# Subarrays Distinct Element Sum of Squares II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a  **0-indexed** integer array `nums`.

The  **distinct count**  of a subarray of `nums` is defined as:

- Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length. Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].

Return  *the sum of the  **squares**  of  **distinct counts**  of all subarrays of* `nums`.

Since the answer may be very large, return it  **modulo**  `109 + 7`.

A subarray is a contiguous  **non-empty**  sequence of elements within an array.

 

 **Example 1:** 

```
Input: nums = [1,2,1]
Output: 15
Explanation: Six possible subarrays are:
[1]: 1 distinct value
[2]: 1 distinct value
[1]: 1 distinct value
[1,2]: 2 distinct values
[2,1]: 2 distinct values
[1,2,1]: 2 distinct values
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.

```

 **Example 2:** 

```
Input: nums = [2,2]
Output: 3
Explanation: Three possible subarrays are:
[2]: 1 distinct value
[2]: 1 distinct value
[2,2]: 1 distinct value
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.
```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 105

## Solution

**Language:** Java  
**Runtime:** 230 ms (beats 35.71%)  
**Memory:** 102.8 MB (beats 53.57%)  
**Submitted:** 2026-10-16T10:54:01.133Z  

```java
class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        lazy = new long[4 * n];
        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long cur = 0, ans = 0;
        for (int r = 0; r < n; r++) {
            int prev = last[nums[r]];
            long s = query(1, 0, n - 1, prev + 1, r);
            cur = (cur + 2 * (s % MOD) + (r - prev)) % MOD;
            update(1, 0, n - 1, prev + 1, r);
            ans = (ans + cur) % MOD;
            last[nums[r]] = r;
        }
        return (int) ans;
    }

    // add 1 to every position in [l, r]
    void update(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return;
        if (l <= lo && hi <= r) {
            sum[node] += hi - lo + 1;
            lazy[node] += 1;
            return;
        }
        push(node, lo, hi);
        int mid = (lo + hi) / 2;
        update(2 * node, lo, mid, l, r);
        update(2 * node + 1, mid + 1, hi, l, r);
        sum[node] = sum[2 * node] + sum[2 * node + 1];
    }

    // sum of values in [l, r]
    long query(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return sum[node];
        push(node, lo, hi);
        int mid = (lo + hi) / 2;
        return query(2 * node, lo, mid, l, r) + query(2 * node + 1, mid + 1, hi, l, r);
    }

    // pass pending "+x" down to the children
    void push(int node, int lo, int hi) {
        if (lazy[node] == 0) return;
        int mid = (lo + hi) / 2;
        long x = lazy[node];
        sum[2 * node] += x * (mid - lo + 1);
        lazy[2 * node] += x;
        sum[2 * node + 1] += x * (hi - mid);
        lazy[2 * node + 1] += x;
        lazy[node] = 0;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/)