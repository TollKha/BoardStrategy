package com.toll.tetris;

/**
 * Created by toll on 03.05.16.
 */
public class Timer {
    private float remainTime;
    private float startTime;
    private float tick;
    private ITimerCallback callback;
    private boolean isStarted;

    Timer(float time, float tickInterval, ITimerCallback callback) {
        this.startTime = time;
        this.tick = tickInterval;
        this.callback = callback;
    }

    public void Start(){
        remainTime = startTime;
        Resume();
    }

    public void Pause() {
        isStarted = false;
    }

    public void Resume() {
        isStarted = true;
    }

    public void Update(float dt)
    {
        if (!isStarted)
            return;

        if(remainTime <= 0) {
            if (callback != null)
                callback.OnFinished(this);
            return;
        }

        remainTime -= dt;
    }

    public boolean IsFinished() {
        return  remainTime < 0;
    }

    public boolean IsStarted() {
        return isStarted;
    }
}
