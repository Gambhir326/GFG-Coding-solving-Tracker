# Concatenate and Reverse

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given two strings  **s1**  and  **s2**  as input, concatenate two strings and then reverse the joined string. 

 **Examples:** 

```
Input: s1 = "Geeks", s2 = "forGeeks"
Output: "skeeGrofskeeG" 
Explanation: Concatenating s1 and s2 to get "GeeksforGeeks" then reversing it to "skeeGrofskeeG".

```

```
Input: s1 = "Practice", s2 = "Geeks"
Output: "skeeGecitcarP"
Explanation: Concatenating s1 and s2 to get "PracticeGeeks" then reversing it to"skeeGecitcarP".
```

 **Constraints:** 
1 ≤ |s1| ≤103
1 ≤ |s2| ≤103

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-16T11:12:02.417Z  

```java
class Solution {
    static String conRevstr(String s1, String s2) {
        // code here
        String con = s1 + s2;
        String rev = new StringBuilder(con).reverse().toString();
        
        // System.out.println(rev);
        return rev;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/java-strings-set-15112/1)