package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.pojo.TrackItem;

/**
 * Created by hu on 17-3-19.
 */

public class EventOnSkiptoItem {
    TrackItem trackItem;

    public EventOnSkiptoItem(TrackItem trackItem) {
        this.trackItem = trackItem;
    }

    public TrackItem getTrackItem() {
        return trackItem;
    }

    public void setTrackItem(TrackItem trackItem) {
        this.trackItem = trackItem;
    }
}
