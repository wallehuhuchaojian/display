package com.display.crazybaby.crazydisplay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


/**
 * Created by walle on 2017/8/17.
 */

public class SystemOverlayMenuService extends Service {

    private final IBinder mBinder = new LocalBinder();

    //下方白色+号小按钮
    private FloatingActionButton rightLowerButton;

    public static final int  MENU_SHOW=1;
    public static final int MENU_DISMISS=2;
    public static final int MENU_TOHOME=3;
    private FloatingActionMenu rightLowerMenu;

    private boolean serviceWillBeDismissed;

    public SystemOverlayMenuService() {
    }

    public class LocalBinder extends Binder {
        SystemOverlayMenuService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SystemOverlayMenuService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Set up the white button on the lower right corner
        // more or less with default parameter
//        //创建居于设备顶部居中的大红色按钮
//
//        // Set up the large red button on the top center side
//        // With custom button and content sizes and margins
//        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_size);
//        int redActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
//        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_size);
//        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_margin);
//        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.red_action_menu_radius);
//        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
//        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);

//        ImageView fabIconStar = new ImageView(this);
//        fabIconStar.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_important));
//
//        FloatingActionButton.LayoutParams fabIconStarParams = new FloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
//        fabIconStarParams.setMargins(redActionButtonContentMargin,
//                redActionButtonContentMargin,
//                redActionButtonContentMargin,
//                redActionButtonContentMargin);

//        WindowManager.LayoutParams params2 = FloatingActionButton.Builder.getDefaultSystemWindowParams(this);
//        params2.width = redActionButtonSize;
//        params2.height = redActionButtonSize;



//        //为顶部中心位置的大红色按钮增加弹出的子菜单
//        // Set up customized SubActionButtons for the right center menu
//        SubActionButton.Builder tCSubBuilder = new SubActionButton.Builder(this);
//        tCSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//
//        //小红色关闭叉子那个按钮Builder
//        SubActionButton.Builder tCRedBuilder = new SubActionButton.Builder(this);
//        tCRedBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//
//        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        blueContentParams.setMargins(blueSubActionButtonContentMargin,
//                blueSubActionButtonContentMargin,
//                blueSubActionButtonContentMargin,
//                blueSubActionButtonContentMargin);

        // Set custom layout params
//        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
//        tCSubBuilder.setLayoutParams(blueParams);
//        tCRedBuilder.setLayoutParams(blueParams);
//
//        ImageView tcIcon1 = new ImageView(this);
//        ImageView tcIcon2 = new ImageView(this);
//        ImageView tcIcon3 = new ImageView(this);
//        ImageView tcIcon4 = new ImageView(this);
//        ImageView tcIcon5 = new ImageView(this);
//        ImageView tcIcon6 = new ImageView(this);
//
//        tcIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        tcIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        tcIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        tcIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        tcIcon5.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        tcIcon6.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//
//        SubActionButton tcSub1 = tCSubBuilder.setContentView(tcIcon1, blueContentParams).build();
//        SubActionButton tcSub2 = tCSubBuilder.setContentView(tcIcon2, blueContentParams).build();
//        SubActionButton tcSub3 = tCSubBuilder.setContentView(tcIcon3, blueContentParams).build();
//        SubActionButton tcSub4 = tCSubBuilder.setContentView(tcIcon4, blueContentParams).build();
//        SubActionButton tcSub5 = tCSubBuilder.setContentView(tcIcon5, blueContentParams).build();
//        SubActionButton tcSub6 = tCRedBuilder.setContentView(tcIcon6, blueContentParams).build();




    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent==null)
            return super.onStartCommand(intent, flags, startId);
        int action=intent.getIntExtra("action",0);
        if (action==MENU_SHOW){
            showMenu();
        }else if (action==MENU_DISMISS){
            menuDismiss();
        }else {
            toHome();
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void toHome(){
        Intent home=new Intent(Intent.ACTION_MAIN);
        home.addFlags(FLAG_ACTIVITY_NEW_TASK);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
    WindowManager.LayoutParams wmParams;
    WindowManager mWindowManager;
    View menuView;
    ImageView ibMenu;
    private boolean ishow=false;
    private void showMenu(){
        if (ishow)
            return;
        if (wmParams==null) {
            wmParams = new WindowManager.LayoutParams();
            //获取的是WindowManagerImpl.CompatModeWrapper
            mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);

            //设置window type
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            //设置图片格式，效果为背景透明
            wmParams.format = PixelFormat.RGBA_8888;
            //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            //调整悬浮窗显示的停靠位置为左侧置顶
            wmParams.gravity = Gravity.RIGHT | Gravity.TOP;
            // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
            wmParams.x = 30;
            wmParams.y = 150;

            //设置悬浮窗口长宽数据
            wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

         /*// 设置悬浮窗口长宽数据
        wmParams.width = 200;
        wmParams.height = 80;*/

            LayoutInflater inflater = LayoutInflater.from(getApplication());
            //获取浮动窗口视图所在布局
            menuView = LayoutInflater.from(getApplication()).inflate(R.layout.view_floatmenu, null);
            ibMenu = (ImageView) menuView.findViewById(R.id.ib_menu);
            mWindowManager.addView(menuView, wmParams);


            ishow = true;
        }else {
            mWindowManager.addView(menuView, wmParams);
            ishow = true;
        }

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });


















//        ImageView fabIconNew = new ImageView(this);
//        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        WindowManager.LayoutParams params = FloatingActionButton.Builder.getDefaultSystemWindowParams(this);
//
//        //右边下方小按钮
//        rightLowerButton = new FloatingActionButton.Builder(this)
//                .setContentView(fabIconNew)
//                .setSystemOverlay(true) //使该按钮作为系统悬浮按钮显示在设备屏幕上
//                .setLayoutParams(params)
//                .build();
//
//        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
//        ImageView rlIcon1 = new ImageView(this);
//        ImageView rlIcon2 = new ImageView(this);
//        ImageView rlIcon3 = new ImageView(this);
//        ImageView rlIcon4 = new ImageView(this);
//
//        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
//
//        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
//        // Set 4 default SubActionButtons
//        SubActionButton rlSub1 = rLSubBuilder.setContentView(rlIcon1).build();
//        SubActionButton rlSub2 = rLSubBuilder.setContentView(rlIcon2).build();
//        SubActionButton rlSub3 = rLSubBuilder.setContentView(rlIcon3).build();
//        SubActionButton rlSub4 = rLSubBuilder.setContentView(rlIcon4).build();
//        rightLowerMenu = new FloatingActionMenu.Builder(this, true)
//                .addSubActionView(rlSub1, rlSub1.getLayoutParams().width, rlSub1.getLayoutParams().height)
//                .addSubActionView(rlSub2, rlSub2.getLayoutParams().width, rlSub2.getLayoutParams().height)
//                .addSubActionView(rlSub3, rlSub3.getLayoutParams().width, rlSub3.getLayoutParams().height)
//                .addSubActionView(rlSub4, rlSub4.getLayoutParams().width, rlSub4.getLayoutParams().height)
//                .setStartAngle(180)
//                .setEndAngle(270)
//                .attachTo(rightLowerButton)
//                .build();

    }
    private void menuDismiss(){
        if (mWindowManager!=null&&ishow){
            mWindowManager.removeView(menuView);
            ishow=false;
        }
//        if(rightLowerMenu != null && rightLowerMenu.isOpen()) rightLowerMenu.close(false);
//        if(rightLowerButton != null) rightLowerButton.detach();
    }

    @Override
    public void onDestroy() {
        if (mWindowManager!=null&&ishow){
            mWindowManager.removeView(menuView);
            ishow=false;
        }
        super.onDestroy();
    }

}
