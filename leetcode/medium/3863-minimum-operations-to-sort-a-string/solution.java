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