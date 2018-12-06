package com.hfad.tabletrainer;

public class Time {

    int             minutes;
    int             seconds;
    boolean         run;
    String          time;

    public Time(int minutes) {
        this.minutes = minutes-1;
        this.seconds = 60;
        run = false;

    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
