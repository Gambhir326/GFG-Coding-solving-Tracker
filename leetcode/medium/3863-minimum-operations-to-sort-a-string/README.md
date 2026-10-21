# Minimum Operations to Sort a String

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a string `s` consisting of lowercase English letters.

In one operation, you can select any  **substring**  of `s` that is  **not**  the entire string and  **sort**  it in  **non-descending alphabetical**  order.

Return the  **minimum**  number of operations required to make `s` sorted in  **non-descending**  order. If it is not possible, return -1.

 

 **Example 1:** 

 **Input:**  s = "dog"

 **Output:**  1

 **Explanation:** ​​​​​​​

- Sort substring "og" to "go".
- Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.

 **Example 2:** 

 **Input:**  s = "card"

 **Output:**  2

 **Explanation:** 

- Sort substring "car" to "acr", so s = "acrd".
- Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.

 **Example 3:** 

 **Input:**  s = "gf"

 **Output:**  -1

 **Explanation:** 

- It is impossible to sort s under the given constraints. Thus, the answer is -1.

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 11 ms (beats 89.71%)  
**Memory:** 47.4 MB (beats 92.65%)  
**Submitted:** 2026-10-21T11:26:45.921Z  

```java
class Solution {
    public int minOperations(String s) {
        // If s is sorted then we return 0;
        // If s can't be sorted and the string length is 2 then the substrings has only 1 length hence we return -1;
        // if min appears at the beginning or the max appears at the end then we return 1;
        // If min and max are located neither at the beiginning nor the ending then we return 2;
        // we return 3 when the min is at the end and max at the beginning


        // If s is sorted then we return 0;
        int n = s.length();
        boolean isSorted = true;
        for(int i = 0; i < n - 1 ; i++) {
            if(s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if(isSorted) {
            return 0;
        }

        // If s can't be sorted and the string length is 2 then the substrings has only 1 length hence we return -1;
        if(n == 2) {
            return -1;
        }

        // if min appears at the beginning or the max appears at the end then we return 1;
        char min = 'z';
        char max = 'a';

        for(int i = 0; i < n ; i++) {
            char ch = s.charAt(i);
            if(ch < min) min = ch;
            if(ch > max) max = ch;
        }

        if(s.charAt(0) == min || s.charAt(n - 1) == max){
            return 1;
        }

         // If min and max are located neither at the beiginning nor the ending then we return 2;
         boolean minInStart = false;
         for(int i = 0; i < n - 1; i++) {
            char c = s.charAt(i);
            if(c == min) {
                minInStart = true;
                break;
            }
         }

         boolean maxInEnd = false;
         for(int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if(c == max) {
                maxInEnd = true;
                break;
            }
         } 

         if(minInStart || maxInEnd) {
            return 2;
         }


         // we return 3 when the min is at the end and max at the beginning
         return 3;

    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-operations-to-sort-a-string/)