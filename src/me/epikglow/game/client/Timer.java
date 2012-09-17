package me.epikglow.game.client;

import org.lwjgl.Sys;

public class Timer {
    private long lastFrame;
    private int delta;
    
    public Timer() {
        lastFrame = getTime();
        delta = 0;
    }
    
    private long getTime() {
        return (Sys.getTime() * 1000L) / Sys.getTimerResolution();
    }
    
    public int getDelta() {
        long currentTime = getTime();
        delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        System.out.println("delta = " + delta);
        return delta;
    }
}
