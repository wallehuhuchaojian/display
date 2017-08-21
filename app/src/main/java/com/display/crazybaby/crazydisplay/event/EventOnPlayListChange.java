package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.MusicConfig;
import com.crazybabyluna.walle.musiclib.pojo.TrackItem;

import java.util.List;

/**
 * Created by walle on 2017/2/16.
 */

public class EventOnPlayListChange {
    private MusicConfig.PLAYERTYPE platyertype;
    private List<TrackItem> playList;
    private int index;

    public MusicConfig.PLAYERTYPE getPlatyertype() {
        return platyertype;
    }

    public void setPlatyertype(MusicConfig.PLAYERTYPE platyertype) {
        this.platyertype = platyertype;
    }

    public List<TrackItem> getPlayList() {
        return playList;
    }

    public void setPlayList(List<TrackItem> playList) {
        this.playList = playList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public EventOnPlayListChange(MusicConfig.PLAYERTYPE platyertype, List<TrackItem> playList, int index) {
        this.platyertype = platyertype;
        this.playList = playList;
        this.index = index;
    }
}
