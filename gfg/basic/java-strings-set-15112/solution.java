class Solution {
    static String conRevstr(String s1, String s2) {
        // code here
        String con = s1 + s2;
        String rev = new StringBuilder(con).reverse().toString();
        
        // System.out.println(rev);
        return rev;
    }
}