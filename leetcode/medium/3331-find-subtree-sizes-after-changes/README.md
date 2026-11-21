# Find Subtree Sizes After Changes

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a tree rooted at node 0 that consists of `n` nodes numbered from `0` to `n - 1`. The tree is represented by an array `parent` of size `n`, where `parent[i]` is the parent of node `i`. Since node 0 is the root, `parent[0] == -1`.

You are also given a string `s` of length `n`, where `s[i]` is the character assigned to node `i`.

We make the following changes on the tree  **one**  time  **simultaneously**  for all nodes `x` from `1` to `n - 1`:

- Find the closest node y to node x such that y is an ancestor of x, and s[x] == s[y].
- If node y does not exist, do nothing.
- Otherwise, remove the edge between x and its current parent and make node y the new parent of x by adding an edge between them.

Return an array `answer` of size `n` where `answer[i]` is the  **size**  of the subtree rooted at node `i` in the  **final**  tree.

 

Original
Java
 **Example 1:** 

 **Input:**  parent = [-1,0,0,1,1,1], s = "abaabc"

 **Output:**  [6,3,1,1,1,1]

 **Explanation:** 

The parent of node 3 will change from node 1 to node 0.

 **Example 2:** 

 **Input:**  parent = [-1,0,4,0,1], s = "abbba"

 **Output:**  [5,2,1,1,1]

 **Explanation:** 

The following changes will happen at the same time:

- The parent of node 4 will change from node 1 to node 0.
- The parent of node 2 will change from node 4 to node 1.

 

 **Constraints:** 

- n == parent.length == s.length
- 1 <= n <= 105
- 0 <= parent[i] <= n - 1 for all i >= 1.
- parent[0] == -1
- parent represents a valid tree.
- s consists only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 147 ms (beats 72.09%)  
**Memory:** 235.9 MB (beats 76.74%)  
**Submitted:** 2026-11-21T12:33:30.226Z  

```java
class Solution {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    private int[] resultSize;
    private int[] last;

    private void dfs(int currNode, String s, int[] parent) {
        int c = s.charAt(currNode) - 'a';

        int nearestAncestor = last[c];

        last[c] = currNode;

        for (int child : adj.getOrDefault(currNode, Collections.emptyList()))
            dfs(child, s, parent);

        last[c] = nearestAncestor;

        if (currNode != 0) {
            int newParent = parent[currNode];

            if (nearestAncestor != -1)
                newParent = nearestAncestor;

            parent[currNode] = newParent;
            resultSize[newParent] += resultSize[currNode];
        }
    }

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        for (int i = 1; i < n; i++) {
            int u = parent[i];
            int v = i;
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        resultSize = new int[n];
        Arrays.fill(resultSize, 1);

        last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s, parent);

        return resultSize;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-subtree-sizes-after-changes/)