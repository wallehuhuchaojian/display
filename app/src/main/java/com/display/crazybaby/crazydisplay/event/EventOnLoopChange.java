package com.display.crazybaby.crazydisplay.event;

import com.crazybabyluna.walle.musiclib.MusicConfig;

/**
 * Created by walle on 2017/3/17.
 */

public class EventOnLoopChange {
    MusicConfig.PLAYERLOOP playerloop;

    public MusicConfig.PLAYERLOOP getPlayerloop() {
        return playerloop;
    }

    public void setPlayerloop(MusicConfig.PLAYERLOOP playerloop) {
        this.playerloop = playerloop;
    }

    public EventOnLoopChange(MusicConfig.PLAYERLOOP playerloop) {
        this.playerloop = playerloop;
    }
}
