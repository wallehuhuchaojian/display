package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.MusicConfig;
import com.crazybabyluna.walle.musiclib.pojo.TrackItem;

/**
 * Created by walle on 2017/2/16.
 */

public class EventOnMusicChange {
   private MusicConfig.PLAYERTYPE platyertype;
    private TrackItem trackItem;

    public MusicConfig.PLAYERTYPE getPlatyertype() {
        return platyertype;
    }

    public void setPlatyertype(MusicConfig.PLAYERTYPE platyertype) {
        this.platyertype = platyertype;
    }

    public TrackItem getTrackItem() {
        return trackItem;
    }

    public void setTrackItem(TrackItem trackItem) {
        this.trackItem = trackItem;
    }

    public EventOnMusicChange(MusicConfig.PLAYERTYPE platyertype, TrackItem trackItem) {
        this.platyertype = platyertype;
        this.trackItem = trackItem;
    }
}
