package com.display.crazybaby.crazydisplay.event;

/**
 * Created by walle on 2017/3/28.
 */

public class EventPlayListItemClick {
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public EventPlayListItemClick(int position) {
        this.position = position;
    }
}
