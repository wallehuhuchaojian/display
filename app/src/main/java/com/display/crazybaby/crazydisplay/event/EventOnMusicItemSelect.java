package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.MusicConfig;
import com.crazybabyluna.walle.musiclib.pojo.TrackItem;

import java.util.List;

/**
 * Created by walle on 2017/2/16.
 */

public class EventOnMusicItemSelect {
    private List<TrackItem> trackItems;
    private int index;
    private MusicConfig.SOURCETYPE sourcetype;

    public EventOnMusicItemSelect(List<TrackItem> trackItems, int index, MusicConfig.SOURCETYPE sourcetype) {
        this.trackItems = trackItems;
        this.index = index;
        this.sourcetype = sourcetype;
    }

    public List<TrackItem> getTrackItems() {
        return trackItems;
    }

    public void setTrackItems(List<TrackItem> trackItems) {
        this.trackItems = trackItems;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MusicConfig.SOURCETYPE getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(MusicConfig.SOURCETYPE sourcetype) {
        this.sourcetype = sourcetype;
    }
}
