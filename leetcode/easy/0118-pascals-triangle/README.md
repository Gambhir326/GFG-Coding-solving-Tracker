# Pascal's Triangle

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an integer `numRows`, return the first numRows of  **Pascal's triangle**.

In  **Pascal's triangle**, each number is the sum of the two numbers directly above it as shown:

 

 **Example 1:** 

```
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

```

 **Example 2:** 

```
Input: numRows = 1
Output: [[1]]

```

 

 **Constraints:** 

- 1 <= numRows <= 30

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 95.46%)  
**Memory:** 43.3 MB (beats 68.30%)  
**Submitted:** 2026-10-18T12:54:34.157Z  

```java
class Solution {
    private static int [][] PT;

    public List<List<Integer>> generate(int numRows) {
        PT = new int[numRows][];

        for(int i = 0; i < numRows; i++) {

            PT[i] = new int[i + 1];
            Arrays.fill(PT[i], 1);

            for(int j = 1; j < i; j++) {
                PT[i][j] = (PT[i - 1][j - 1] + PT[i - 1][j]);
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
            for(int i = 0; i < numRows ; i++) {
                List<Integer> row = new ArrayList<>();

                for(int j = 0; j < PT[i].length; j++) {
                    row.add(PT[i][j]);
                }
                res.add(row);
            }
            return res;

    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/pascals-triangle/)