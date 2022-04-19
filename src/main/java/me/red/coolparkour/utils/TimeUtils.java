package me.red.coolparkour.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public String getTimeTook(Long ms) {

        Long time = System.currentTimeMillis() - ms;

        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(ms);
        ms -= TimeUnit.MILLISECONDS.toMinutes(ms);
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(ms);
        ms -= TimeUnit.MILLISECONDS.toSeconds(ms);
        int microseconds = (int) TimeUnit.MILLISECONDS.toMicros(ms);

        return minutes + ":" + seconds + ":" + microseconds;
    }
}
