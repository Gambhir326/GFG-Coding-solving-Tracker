# Longest Palindromic Substring

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string `s`, return  *the longest*   *palindromic*   *substring*  in `s`.

 

 **Example 1:** 

```
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

```

 **Example 2:** 

```
Input: s = "cbbd"
Output: "bb"

```

 

 **Constraints:** 

- 1 <= s.length <= 1000
- s consist of only digits and English letters.

## Solution

**Language:** Java  
**Runtime:** 17 ms (beats 52.23%)  
**Memory:** 42.7 MB (beats 99.27%)  
**Submitted:** 2026-11-05T14:33:17.574Z  

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd length palindrome (centered at i, e.g., "aba")
            int len1 = expandAroundCenter(s, i, i);
            
            // Case 2: Even length palindrome (centered between i and i+1, e.g., "abba")
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int maxLen = Math.max(len1, len2);

            // If a longer palindrome is found, update the start and end indices
            if (maxLen > (end - start + 1)) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of palindrome = (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-palindromic-substring/)