package com.display.crazybaby.crazydisplay.event;

/**
 * Created by walle on 2017/2/16.
 */

public class EventOnPosition {
    private int position, duration;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public EventOnPosition(int position, int duration) {
        this.position = position;
        this.duration = duration;
    }
}
