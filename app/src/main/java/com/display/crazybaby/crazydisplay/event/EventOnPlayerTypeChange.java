package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.MusicConfig;

/**
 * Created by walle on 2017/2/16.
 */

public class EventOnPlayerTypeChange {
    MusicConfig.PLAYERTYPE platyertype;

    public MusicConfig.PLAYERTYPE getPlatyertype() {
        return platyertype;
    }

    public void setPlatyertype(MusicConfig.PLAYERTYPE platyertype) {
        this.platyertype = platyertype;
    }

    public EventOnPlayerTypeChange(MusicConfig.PLAYERTYPE platyertype) {
        this.platyertype = platyertype;
    }
}
