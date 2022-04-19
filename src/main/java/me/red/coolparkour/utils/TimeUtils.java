package me.red.coolparkour.utils;

public class TimeUtils {

    public String getTimeTook(Long ms) {

        Long time = System.currentTimeMillis() - ms;

        int seconds = (int) (time / 1000);

        int minAndSec = seconds%3600;
        int min = minAndSec/60;
        int sec = minAndSec%60;

        return min + ":" + sec;
    }
}
