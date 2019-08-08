package online.beartreasure.beart_amusement.utils;

import android.app.Application;

import com.mob.MobSDK;

public class Beart_MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
