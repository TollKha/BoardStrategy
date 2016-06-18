package com.toll.tetris;

/**
 * Created by toll on 03.05.16.
 */
public interface ITimerCallback {
    public void OnTick(Timer timer);
    public void OnFinished(Timer timer);
}
