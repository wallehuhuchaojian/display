package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.MusicConfig;

/**
 * Created by walle on 2017/2/16.
 */

public class EventonStatusChange {
    MusicConfig.PLAYERTYPE platyertype;
    MusicConfig.PLAYERSTATUS platyerstatus;

    public MusicConfig.PLAYERTYPE getPlatyertype() {
        return platyertype;
    }

    public void setPlatyertype(MusicConfig.PLAYERTYPE platyertype) {
        this.platyertype = platyertype;
    }

    public MusicConfig.PLAYERSTATUS getPlatyerstatus() {
        return platyerstatus;
    }

    public void setPlatyerstatus(MusicConfig.PLAYERSTATUS platyerstatus) {
        this.platyerstatus = platyerstatus;
    }

    public EventonStatusChange(MusicConfig.PLAYERTYPE platyertype, MusicConfig.PLAYERSTATUS platyerstatus) {
        this.platyertype = platyertype;
        this.platyerstatus = platyerstatus;
    }
}
