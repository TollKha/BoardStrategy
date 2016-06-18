package com.toll.tetris;

import java.util.ArrayList;

/**
 * Created by toll on 03.05.16.
 */
public class TimerManager {
    private static ArrayList<Timer> timers = new ArrayList<Timer>();

    public static void AddTimer(Timer timer) {
        timers.add(timer);
    }

    public static void Update(float dt) {
        for (Timer timer:timers) {
            timer.Update(dt);
        }
    }
}
