package com.yyp.connotationjokes;

import android.app.Application;

/**
 * Created by yangyoupeng on 2017/3/20.
 */

public class BaseApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate ();
        //        初始化，自定义捕捉异常
//        ExceptionOnCrashHandler.getInstance ().init (this);
        
    }
}
