package com.display.crazybaby.crazydisplay;

import android.util.Log;
import com.crazybabyluna.walle.musiclib.MusicConfig;
import com.crazybabyluna.walle.musiclib.MusicProxy;
import com.crazybabyluna.walle.musiclib.api.ControlHandle;
import com.crazybabyluna.walle.musiclib.api.PlayerEventBack;
import com.crazybabyluna.walle.musiclib.pojo.CurrentPlayerInfo;
import com.crazybabyluna.walle.musiclib.pojo.TrackItem;
import com.display.crazybaby.crazydisplay.event.EventOnLoopChange;
import com.display.crazybaby.crazydisplay.event.EventOnMusicChange;
import com.display.crazybaby.crazydisplay.event.EventOnPlayListChange;
import com.display.crazybaby.crazydisplay.event.EventOnPlayerTypeChange;
import com.display.crazybaby.crazydisplay.event.EventOnPosition;
import com.display.crazybaby.crazydisplay.event.EventPlayerOffline;
import com.display.crazybaby.crazydisplay.event.EventonStatusChange;
import com.display.crazybaby.crazydisplay.event.PlayListEmpty;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by walle on 2017/2/15.
 */

public class MusicCenter implements PlayerEventBack,ControlHandle {
    private  static MusicCenter center;
    private boolean hasShowBar=false;
    private boolean isOpen=true;




    public boolean isOpen() {

        return isOpen;
    }

    public void setOpen(boolean open) {
//        App app=g
//        if (playerFragment!=null) {
//            if (open)
//                playerFragment.open();
//            else
//                playerFragment.close();
//        }
        isOpen = open;
    }

    public boolean isHasShowBar() {
        return hasShowBar;
    }

    public void setHasShowBar(boolean hasShowBar) {
        this.hasShowBar = hasShowBar;
    }

    private MusicCenter(){
    center=this;
    }
    public static MusicCenter getMusicCenter(){
        if (center==null)
            new MusicCenter();
        return center;
    }
    public CurrentPlayerInfo getPlayerInfo(){
        return MusicProxy.getMusicProxy().getCurrentPlayerInfo();
    }

    @Override
    public void onPosition(MusicConfig.PLAYERTYPE platyertype, int position, int duration) {
        EventBus.getDefault().post(new EventOnPosition(position,duration));
    }

    @Override
    public void onStatusChange(MusicConfig.PLAYERTYPE platyertyp, MusicConfig.PLAYERSTATUS platyerstatus) {
        EventBus.getDefault().post(new EventonStatusChange(platyertyp,platyerstatus));
    }

    @Override
    public void onPlayListChange(MusicConfig.PLAYERTYPE platyertype, List<TrackItem> playList, int index) {
        EventBus.getDefault().post(new EventOnPlayListChange(platyertype,playList,index));
    }

    @Override
    public void onPlayerTypeChange(MusicConfig.PLAYERTYPE platyertype) {
        EventBus.getDefault().post(new EventOnPlayerTypeChange(platyertype));
    }

    @Override
    public void onPlayerLoopChange(MusicConfig.PLAYERLOOP playerloop) {
        EventBus.getDefault().post(new EventOnLoopChange(playerloop));
    }

    @Override
    public void onMusicChange(MusicConfig.PLAYERTYPE platyertype, TrackItem trackItem) {
        EventBus.getDefault().post(new EventOnMusicChange(platyertype,trackItem));
    }

    @Override
    public void onNeedSetAtvTransport(MusicConfig.PLAYERTYPE platyertype, String ip, TrackItem trackItem) throws Exception {
//        EventBus.getDefault().post(new EventOnNeedSetAtvTransport(ip,trackItem));
//        DeviceInfo deviceInfo= DeviceTools.getInstans().getDeviceFromIp(ip);
//        String met= Tools.getMetadata(trackItem);
//        UpnpProxy.getUpnpProxy().setATVTransport(deviceInfo.getWifiDeviceInfo().getDevice(),trackItem.getDlnaUrl(),met,this);
    }


    @Override
    public void onError(MusicConfig.PLAYERTYPE platyertype, String msg) {
        Log.e("Music error:","PLAYERTYPE:"+platyertype+",msg:"+msg);
        if (msg.equals(MusicConfig.MusicError.playerListIsEmpty)){
            EventBus.getDefault().post(new PlayListEmpty());
        }else  if (msg!=null&&msg.equals(MusicConfig.MusicError.dlnaPlayerOffLine)){
            EventBus.getDefault().post(new EventPlayerOffline());
        }
    }

    @Override
    public void controlPlay() {
        MusicProxy.getMusicProxy().controlPlay();

    }

    @Override
    public void controlPause() {
        MusicProxy.getMusicProxy().controlPause();
    }

    @Override
    public void controlSeekto(int position) {
        MusicProxy.getMusicProxy().controlSeekto(position);
    }

    @Override
    public void controlSkipto(int index) {
        MusicProxy.getMusicProxy().controlSkipto(index);
    }

    @Override
    public void controlNext() {
        MusicProxy.getMusicProxy().controlNext();
    }

    @Override
    public void controlPre() {
        MusicProxy.getMusicProxy().controlPre();
    }

    @Override
    public void controlChangePlayType(MusicConfig.PLAYERTYPE playertype) {
        MusicProxy.getMusicProxy().controlChangePlayType(playertype);
    }

    @Override
    public void controlChangePlayTypeWithouStop(MusicConfig.PLAYERTYPE playertype) {
        MusicProxy.getMusicProxy().controlChangePlayTypeWithouStop(playertype);
    }

    @Override
    public void controlPlaylist(List<TrackItem> playlist, int index) {
        MusicProxy.getMusicProxy().controlPlaylist(playlist,index);
    }

    @Override
    public void controlPlaylist(List<TrackItem> playlist) {
        MusicProxy.getMusicProxy().controlPlaylist(playlist);
    }

    @Override
    public void controlPlaylist(List<TrackItem> playlist, int index, MusicConfig.PLAYERTYPE playertype) {
        MusicProxy.getMusicProxy().controlPlaylist(playlist,index,playertype);
    }

    @Override
    public void controlLoopModel(MusicConfig.PLAYERLOOP playerloop) {
        MusicProxy.getMusicProxy().controlLoopModel(playerloop);
    }



//    @Override
//    public void setAvtSucc(boolean isSucc, Device device) {
//        if (isSucc) {
//
//            if (getPlayerInfo().getDeviceTag().equals(DeviceTools.getInstans().getDevicIp(device))) {
//                controlPlay();
//            }
//
//        }
////        if (isSucc)
////        controlPlay();
//    }
//
//    @Override
//    public void event(String msg) {
//
//    }
}
