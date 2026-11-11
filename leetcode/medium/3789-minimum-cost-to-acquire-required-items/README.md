# Minimum Cost to Acquire Required Items

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given five integers `cost1`, `cost2`, `costBoth`, `need1`, and `need2`.

There are three types of items available:

- An item of type 1 costs cost1 and contributes 1 unit to the type 1 requirement only.
- An item of type 2 costs cost2 and contributes 1 unit to the type 2 requirement only.
- An item of type 3 costs costBoth and contributes 1 unit to both type 1 and type 2 requirements.

You must collect enough items so that the total contribution toward type 1 is  **at least**  `need1` and the total contribution toward type 2 is  **at least**  `need2`.

Return an integer representing the  **minimum**  possible total cost to achieve these requirements.

 

Original
Java
 **Example 1:** 

 **Input:** 
int cost1 = 3;
int cost2 = 2;
int costBoth = 1;
int need1 = 3;
int need2 = 2;

 **Output:**  3

 **Explanation:** 

After buying three type 3 items, which cost `3 * 1 = 3`, the total contribution to type 1 is 3 (`>= need1 = 3`) and to type 2 is 3 (`>= need2 = 2`).
Any other valid combination would cost more, so the minimum total cost is 3.

 **Example 2:** 

 **Input:** 
int cost1 = 5;
int cost2 = 4;
int costBoth = 15;
int need1 = 2;
int need2 = 3;

 **Output:**  22

 **Explanation:** 

We buy `need1 = 2` items of type 1 and `need2 = 3` items of type 2: `2  *5 + 3*  4 = 10 + 12 = 22`.
Any other valid combination would cost more, so the minimum total cost is 22.

 **Example 3:** 

 **Input:** 
int cost1 = 5;
int cost2 = 4;
int costBoth = 15;
int need1 = 0;
int need2 = 0;

 **Output:**  0

 **Explanation:** 

Since no items are required (`need1 = need2 = 0`), we buy nothing and pay 0.

 

 **Constraints:** 

- 1 <= cost1, cost2, costBoth <= 106
- 0 <= need1, need2 <= 109

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 41.18%)  
**Memory:** 41.5 MB (beats 100.00%)  
**Submitted:** 2026-11-11T10:59:27.416Z  

```java
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        return Math.max(bound(need1, cost1, need2, cost2, costBoth), bound(need2, cost2, need1, cost1, costBoth));
    }

    private long bound(long needA, int costA, long needB, int costB, int costBoth) {
        long pA = Math.min(costA, costBoth);  // a takes its full price
        long pB = Math.min(costB, costBoth - pA);  // b gets what's left of the bundle
        return(int) needA * pA + needB * pB;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-cost-to-acquire-required-items/)