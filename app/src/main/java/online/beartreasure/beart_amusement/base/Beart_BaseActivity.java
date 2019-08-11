package online.beartreasure.beart_amusement.base;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Beart_BaseActivity extends AppCompatActivity {
    /**
     * Activity封装
     * 是否全屏
     * 是否取消actionbar
     * 封装Toast
     * 封装Toolbar
     * 虚拟按键控件
     *
     * @第四組:组长
     */
    private static Toast bearttoast;
    protected final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private Activity mactivity;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //防止创建两次
        preventCreateTwice();
        // 禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //得到视图
        decorView = getWindow().getDecorView();
        //获取上下文
        mContext = getApplicationContext();
        //获取当前
        mactivity = this;
//        highApiEffects();
        initView();
        initDatas();
        settingView();
//        hideBottomUI();
    }

    /**
     * 设置是否全屏和显示隐藏虚拟按键
     *
     * @第四組:组长
     */
    public void settingBottomUI(boolean ishideBottom) {
        if (ishideBottom) {
            hideBottomUI();
        } else {
            showBottomUIMenu();
        }
    }

    /**
     * 显示虚拟按键，并且全屏
     *
     * @第四組:组长
     */
    public void showBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(0);
        }
    }

    /**
     * 隐藏虚拟按键和状态栏
     * 沉浸 式键盘遮挡输入框解决方案
     *
     * @第四組:组长
     */
    public void hideBottomUI() {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if (android.os.Build.VERSION.SDK_INT >= 19) {
            uiFlags |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;    //View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        decorView.setSystemUiVisibility(uiFlags);
    }

    /**
     * 判断是否存在虚拟按键
     *
     * @第四組:组长
     */
    public boolean checkDeviceHasNavigationBar() { //todo
        boolean hasNavigationBar = false;
        Resources rs = getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            @SuppressLint("PrivateApi") Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }

    /**
     * 沉浸式
     *
     * @第四組:组长
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏 @底部 这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 防止重复创建的问题，第一次安装完成启动，和home键退出点击launcher icon启动会重复
     *
     * @第四組:组长
     */
    private void preventCreateTwice() {
        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
        }
    }

    /**
     * 验证手机号 正则表达式
     *
     * @第四組:组长
     */
    protected boolean beartisMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 显示长时间的Toast
     *
     * @第四組:组长
     */
    @SuppressLint("ShowToast")
    public void beartToast(String msg) {
        if (bearttoast != null) {
            bearttoast.setText(msg);
        } else {
            bearttoast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }
        bearttoast.show();
    }

    /**
     * 可在线程运行的Toast
     *
     * @第四組:组长
     */
    public void beartThreadeToast(final String msg) {
        mactivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                beartToast(msg);
            }
        });
    }

    /**
     * 获取布局
     *
     * @第四組:组长
     */
    public abstract int getLayout();

    /**
     * 初始化控件
     *
     * @第四組:组长
     */
    public abstract void initView();

    /**
     * 初始化数据
     *
     * @第四組:组长
     */
    public abstract void initDatas();

    /**
     * 设置控件
     *
     * @第四組:组长
     */
    public abstract void settingView();
}