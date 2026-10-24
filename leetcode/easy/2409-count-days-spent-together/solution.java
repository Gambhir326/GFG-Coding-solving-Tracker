class Solution {
    // First we make a private final static array which holds the months as [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].

    // The logic is to initialize a min max algorithm.
    private static final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start = Math.max(daysOfYear(arriveAlice), daysOfYear(arriveBob));
        int end = Math.min(daysOfYear(leaveAlice), daysOfYear(leaveBob));

        // it will return 0 if the end start subtraction is less than 0;
        // if end is greater than the start then it will return the amount of days together
        return Math.max(0 , end - start + 1); // + 1 is added because the number itself isnt counted in the date function and the date should need to be counted as well so we add +1 at the end
    }


    // The method to count all the number of days from teh given start string and the end string
    private int daysOfYear (String date) {
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3, 5));
        int total = day;

        // if the month is supposed as 7 then the m countere will stop at 6 without reaching 7 
        // And the days index value gonna add up to the total 
        for (int m = 0; m < month - 1; m++) {
            total += days[m];
        }
        return total;
    }


}