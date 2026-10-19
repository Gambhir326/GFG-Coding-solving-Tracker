class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        int n = words.length;
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                if(words[i].charAt(j) == x){
                    res.add(i);
                    break;
                }  
            }
        }
        return res;













        // for(int i = 0; i < words.length; i++) {
        //     if(words[i].indexOf(x) != -1)  {
        //         res.add(i);
        //     }
        // }
        // return res;
    }
}