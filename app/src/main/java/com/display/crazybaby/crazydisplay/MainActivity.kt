package com.display.crazybaby.crazydisplay

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import com.crazybabyluna.walle.musiclib.MusicConfig
import com.crazybabyluna.walle.musiclib.MusicProxy
import com.crazybabyluna.walle.musiclib.pojo.TrackItem
import com.display.crazybaby.crazydisplay.event.EventOnPosition
import com.display.crazybaby.crazydisplay.event.EventonStatusChange
import com.display.crazybaby.crazydisplay.tool.NetTools
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.imageResource
import java.io.File
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus
import android.R.attr.name
import android.R.attr.name
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
//import android.webkit.WebSettings
//import android.webkit.WebView
//import android.webkit.WebViewClient
import android.widget.*
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


class MainActivity : Activity() {
    private var mDecorView: View? = null
    var  urls ="https://crazybaby.com/cn/static/air";
    var  urls_sepecs ="https://crazybaby.com/cn/static/air/specs";
    var TAG="MainActivity";
    private var  airName="air.mht"
    private var  summaryName="summary.mht"



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        loadUrl();
        initMusic()
        playControl()
//        webSet()

        initWeb()
        EventBus.getDefault().register(this);
    }
    private fun load(){
        if (NetTools.isNetworkConnected(this))
            loadUrl()
        else
            loadLocal(urls)
    }
    private val web : WebView  by lazy{
        findViewById(R.id.my_web) as WebView
    }
    private val play :ImageView  by lazy{
        findViewById(R.id.bt_play) as ImageView
    }
    private val container :RelativeLayout  by lazy{
        findViewById(R.id.rl_web) as RelativeLayout
    }
    private val nex :ImageView  by lazy{
        findViewById(R.id.bt_next) as ImageView
    }
    private val bar :ProgressBar  by lazy{
        findViewById(R.id.progressBar) as ProgressBar
    }
    private val pre :ImageView  by lazy{
        findViewById(R.id.bt_pre) as ImageView
    }
    private val volLess :ImageView  by lazy{
        findViewById(R.id.iv_vol_less) as ImageView
    }
    private val volMore :ImageView  by lazy{
        findViewById(R.id.bt_vol_plus) as ImageView
    }
//    private val flagTop :View  by lazy{
//        findViewById(R.id.flag_top) as View
//    }
    private val flagBottom :View  by lazy{
        findViewById(R.id.flag_bottom) as View
    }
    var musicList  :List<TrackItem>? = null
    private fun initMusic(){
        var music=MusicSource();
         musicList= music.getMusicList(this)
        Log.d(TAG,"musiclist"+musicList.toString())
        MusicProxy.init(this,MusicCenter.getMusicCenter());
    }



    private  fun initWeb(){
//        if (web!=null)
//            web!!.destroy();
//        web= WebView(this);
        webSet();
//        if (container.childCount!=0){
//            container.removeAllViews()
//        }
//        web?.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        container.addView(web)
        load();

    }
    var isFirst=true;
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what==1) {
                initWeb();
                reload()
            }else if (msg.what==2){
                var isback= isInBackgroud(this@MainActivity)
                if (isback){

                }else{
                    dissMenu()
                }
            }
        }
    }

    public fun  isInBackgroud(context: Context):Boolean{
       var am :ActivityManager= context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager;
        var tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
             var topActivity:ComponentName = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private fun reload(){


        handler.sendEmptyMessageDelayed(1,1000*60*30)
    }
    private var downTime:Long=0L
    private fun playControl(){
        play.setOnClickListener {
            if (musicList==null|| musicList!!.size==0){
                Toast.makeText(this,"no music",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (isFirst) {
                MusicCenter.getMusicCenter().controlPlaylist(musicList, 0);
                isFirst=false;
            }
            else {
                if (MusicCenter.getMusicCenter().playerInfo.playerstatus != MusicConfig.PLAYERSTATUS.playing)
                    MusicCenter.getMusicCenter().controlPlay();
                else
                    MusicCenter.getMusicCenter().controlPause();
            }

        }
        nex.setOnClickListener { MusicCenter.getMusicCenter().controlNext() }
        pre.setOnClickListener { MusicCenter.getMusicCenter().controlPre() }
        flagBottom.setOnLongClickListener {
           showMenu()
            handler.sendEmptyMessageDelayed(2,1000*5)
            return@setOnLongClickListener true

        }
        volLess.setOnClickListener{
            var mAudioManager = this.getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager;
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,
                    AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
        volMore.setOnClickListener{
            var mAudioManager = this.getSystemService(android.content.Context.AUDIO_SERVICE) as AudioManager;
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,
                    AudioManager.FX_FOCUS_NAVIGATION_UP);
        }

    }





    private  fun webSet(){
        web.isHorizontalScrollBarEnabled = false;//水平不显示
        web.isVerticalScrollBarEnabled = false; //垂直不显示
        val webSettings = web!!.settings
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled=true;

//支持插件
//        webSettings.setPluginsEnabled(true);

//设置自适应屏幕，两者合用
//

//缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = true; //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = true; //隐藏原生的缩放控件

//其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK; //关闭webview中缓存
        webSettings.allowFileAccess = true; //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true; //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true; //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8";//设置编码格式

    }
    private fun loadUrl(){

        web?.loadUrl(urls)
        web?.setWebChromeClient(object :MyWebChromeClient(){})
        web?.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                if (NetTools.isNetworkConnected(applicationContext)) {
                return super.shouldOverrideUrlLoading(p0, p1)
                }else{
                    if (p1.equals(urls)){
                        loadLocal(urls)
                    }else{
                        loadLocal(urls_sepecs)
                    }
                }
            return  false
                ;
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                var file =File(filesDir,"demo.mht");
                var saveFile=File(filesDir.absolutePath+"/"+"test")
                if (saveFile.exists()){
//                    var list=saveFile.listFiles();
//                    for (fil in list){
//                        fil.delete()
//                    }

                }else saveFile.mkdir();
                var name="String name"
                if (url!!.equals(urls))
                name=airName;
                else
                    name=summaryName

               var newFile =File(saveFile.absolutePath+"/"+name)

                if (newFile.exists())
                    newFile.delete();
                    newFile.createNewFile()


                web!!.saveWebArchive(newFile.getAbsolutePath());

            }
        })

//        NetTools.saveWebData(url,this)

    }
    var test:Boolean=false
    private fun loadLocal( url :String){
        if (test){
            web!!.loadUrl("file:///android_asset/Air by crazybaby.html");
            return
        }

        var path=filesDir.absolutePath+"/"+"test"
        var dir=File(path)
        if (dir.exists()){
            var list=dir.listFiles();
            if (list!=null&&list.size>0) {


                if (url.equals(this.urls)) {
                    for (file in list) {
                        if (file.name.equals(airName)) {
                            Log.d(TAG,"path="+getFileUri(file.name).toString())
                            web!!.loadUrl(Uri.fromFile(file).toString());
                        }
                    }
                }else{
                    for (file in list) {
                        if (file.name.equals(summaryName)) {
                            Log.d(TAG,"path="+getFileUri(file.name).toString())
                            web!!.loadUrl(Uri.fromFile(file).toString());
                        }
                    }
                }

            }



        }
    }
    private fun getFileUri(name:String) :Uri{
        val uri = Uri.parse("content://com.crazybaby.hmtl/"+NetTools.dirName+"/"+name)
        return uri;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun subscribePostion(onPosition: EventOnPosition) {
        bar.max=onPosition.duration
        bar.progress=onPosition.position
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun subscribe(status: EventonStatusChange) {
      when (status.platyerstatus){
          MusicConfig.PLAYERSTATUS.complete ->nex.performClick()
          MusicConfig.PLAYERSTATUS.pauseing ->pauseing()
          MusicConfig.PLAYERSTATUS.playing ->playing()
          MusicConfig.PLAYERSTATUS.stop ->pauseing()


      }
    }
    private fun pauseing(){
        play.imageResource=R.drawable.iv_player_play
    }
    private fun playing(){
        play.imageResource=R.drawable.iv_palyer_pause
    }

    override fun onResume() {
        super.onResume()
        dissMenu()

    }


    override fun onPause() {
        super.onPause()
        showMenu();
    }
    private fun showMenu(){
        var inents=Intent(this,SystemOverlayMenuService::class.java);
        inents.putExtra("action", SystemOverlayMenuService.MENU_SHOW)
        startService(inents)
    }
    private fun dissMenu(){
        var inent=Intent(this,SystemOverlayMenuService::class.java);
        inent.putExtra("action", SystemOverlayMenuService.MENU_DISMISS)
        startService(inent)
    }
}


