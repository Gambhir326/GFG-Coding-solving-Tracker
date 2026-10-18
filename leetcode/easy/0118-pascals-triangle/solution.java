class Solution {
    private static int [][] PT;

    public List<List<Integer>> generate(int numRows) {
        PT = new int[numRows][];

        for(int i = 0; i < numRows; i++) {

            PT[i] = new int[i + 1];
            Arrays.fill(PT[i], 1);

            for(int j = 1; j < i; j++) {
                PT[i][j] = (PT[i - 1][j - 1] + PT[i - 1][j]);
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
            for(int i = 0; i < numRows ; i++) {
                List<Integer> row = new ArrayList<>();

                for(int j = 0; j < PT[i].length; j++) {
                    row.add(PT[i][j]);
                }
                res.add(row);
            }
            return res;

    }
}