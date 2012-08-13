package me.epikglow.game.client;

import org.lwjgl.Sys;

public class Timer {
    private long lastFrame;
    private long delta = 0;
    
    public Timer() {
        lastFrame = getTime();
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
