# Find Words Containing Character

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given a  **0-indexed**  array of strings `words` and a character `x`.

Return  *an  **array of indices**  representing the words that contain the character* `x`.

 **Note**  that the returned array may be in  **any**  order.

 

 **Example 1:** 

```
Input: words = ["leet","code"], x = "e"
Output: [0,1]
Explanation: "e" occurs in both words: "leet", and "code". Hence, we return indices 0 and 1.

```

 **Example 2:** 

```
Input: words = ["abc","bcd","aaaa","cbc"], x = "a"
Output: [0,2]
Explanation: "a" occurs in "abc", and "aaaa". Hence, we return indices 0 and 2.

```

 **Example 3:** 

```
Input: words = ["abc","bcd","aaaa","cbc"], x = "z"
Output: []
Explanation: "z" does not occur in any of the words. Hence, we return an empty array.

```

 

 **Constraints:** 

- 1 <= words.length <= 50
- 1 <= words[i].length <= 50
- x is a lowercase English letter.
- words[i] consists only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 46.25%)  
**Memory:** 45.9 MB (beats 99.59%)  
**Submitted:** 2026-10-19T08:59:42.742Z  

```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(words[i].indexOf(x) != -1 ) {
                res.add(i);
            }
        }
        return res;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-words-containing-character/)