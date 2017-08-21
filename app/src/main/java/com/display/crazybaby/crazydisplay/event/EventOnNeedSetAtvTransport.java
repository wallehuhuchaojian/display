package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.pojo.TrackItem;

/**
 * Created by walle on 2017/2/16.
 */

public class EventOnNeedSetAtvTransport {
    private String ip;
    private TrackItem trackItem;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public TrackItem getTrackItem() {
        return trackItem;
    }

    public void setTrackItem(TrackItem trackItem) {
        this.trackItem = trackItem;
    }

    public EventOnNeedSetAtvTransport(String ip, TrackItem trackItem) {
        this.ip = ip;
        this.trackItem = trackItem;
    }
}
