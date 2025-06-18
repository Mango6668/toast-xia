package xyz.chimiandroid.toastxia.application;

import android.app.Application;
import android.content.Context;

/**
 * 弹窗侠的Application
 * @author chimi
 * @version 1.5
 * @see android.app.Application
 *
 */

public class ToastXiaApplication  extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 初始化XCrash
        xcrash.XCrash.init(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
