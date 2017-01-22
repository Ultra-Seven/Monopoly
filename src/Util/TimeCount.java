package Util;

/**
 * Created by lenovo on 2016/4/3.
 * The time calculator to handle all things retaining to time
 */
public class TimeCount {
    //time instance
    private static TimeCount uniqueInstance;
    //count of round
    private int round = 0;
    //time bound
    private int timeBoundary;
    private int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /*
     * day/month/year
     */
    private int year = 2014;
    private int month = 1;
    private int day = 1;
    private TimeCount() {

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static TimeCount getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TimeCount();
        }
        return uniqueInstance;
    }
    public int getRound() {
        return this.round;
    }

    public void setTimeBoundary(int timeBoundary) {
        this.timeBoundary = timeBoundary;
    }

    public void timeGoing() {
        round++;
        day++;
        if(day > months[month-1]) {
            day = 1;
            month++;
        }
        StockMarket.getUniqueInstance().end();
    }
    public boolean isOver() {
        return round >= timeBoundary;
    }
    public boolean isMonth() {
        return day == 1;
    }
    public String getTime() {
        return "Today is " + day + "/" + month + "/" + year;
    }
    public boolean isWeekend() {
        return (round % 7 == 3) || (round % 7 == 4);
    }
}
