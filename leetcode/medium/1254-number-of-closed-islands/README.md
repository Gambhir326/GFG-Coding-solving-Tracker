# Number of Closed Islands

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a 2D `grid` consists of `0s` (land) and `1s` (water).  An  *island*  is a maximal 4-directionally connected group of `0s` and a  *closed island*  is an island  **totally**  (all left, top, right, bottom) surrounded by `1s.`

Return the number of  *closed islands*.

 

 **Example 1:** 

```
Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
```

 **Example 2:** 

```
Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1

```

 **Example 3:** 

```
Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

```

 

 **Constraints:** 

- 1 <= grid.length, grid[0].length <= 100
- 0 <= grid[i][j] <=1

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 7.89%)  
**Memory:** 45.6 MB (beats 98.94%)  
**Submitted:** 2026-10-25T03:20:43.508Z  

```java
class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;

                boolean closed = true;
                java.util.ArrayDeque<int[]> stack = new java.util.ArrayDeque<>();
                stack.push(new int[]{i, j});
                grid[i][j] = 1;

                while (!stack.isEmpty()) {
                    int[] c = stack.pop();
                    if (c[0] == 0 || c[1] == 0 || c[0] == m - 1 || c[1] == n - 1) closed = false;
                    for (int[] d : dirs) {
                        int x = c[0] + d[0], y = c[1] + d[1];
                        if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 0) {
                            grid[x][y] = 1;
                            stack.push(new int[]{x, y});
                        }
                    }
                }
                if (closed) count++;
            }
        }
        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-closed-islands/)