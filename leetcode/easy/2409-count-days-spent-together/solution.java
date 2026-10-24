class Solution {
private static final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start = Math.max(daysOfYear(arriveAlice), daysOfYear(arriveBob));
        int end = Math.min(daysOfYear(leaveAlice), daysOfYear(leaveBob));
        return Math.max(0 , end - start + 1);
    }

    private int daysOfYear (String date) {
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3, 5));
        int total = day;
        for (int m = 0; m < month - 1; m++) {
            total += days[m];
        }
        return total;
    }


}