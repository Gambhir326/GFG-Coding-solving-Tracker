# Delete Alternate Characters

![Difficulty](https://img.shields.io/badge/Difficulty-Basic-red)

## Problem

Given a string  **s**  as input. Delete the characters at odd indices of the string and return the modified string.

 **Examples :** 

```
Input: s = "Geeks"
Output: "Ges" 
Explanation: Deleted "e" at index 1 and "k" at index 3.

```

```
Input: s = "GeeksforGeeks"
Output: "GesoGes"
Explanation: Deleted e, k, f, r, e, k at index 1, 3, 5, 7, 9, 11.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-10-14T14:34:12.735Z  

```java
class Solution {
    static String delAlternate(String s) {
        // code here
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i+=2) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/java-delete-alternate-characters4036/1)