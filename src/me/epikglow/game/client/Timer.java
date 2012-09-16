package me.epikglow.game.client;

import org.lwjgl.Sys;

public class Timer {
    private long lastFrame;
    private long delta;
    
    public Timer() {
        lastFrame = getTime();
        delta = 0;
    }
    
    private long getTime() {
        return (Sys.getTime() * 1000L) / Sys.getTimerResolution();
    }
    
    public long getDelta() {
        long currentTime = getTime();
        delta = currentTime - lastFrame;
        lastFrame = getTime();
        return delta;
    }
}
