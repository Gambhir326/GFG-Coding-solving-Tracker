class Solution {
    public boolean checkString(String s) {
        boolean true_char = false;
        for(int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c =='b'){
                true_char = true;
            }
            else if(true_char){
                return false;
            }
        }
        return true;
    }
}