package com.yyp.baselibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyoupeng on 2017/3/20.
 * <p>
 * 异常捕捉类(单例设计模式)
 */

public class ExceptionOnCrashHandler implements Thread.UncaughtExceptionHandler {
    
    private static final String TAG = "ExceptionOnCrashHandler";
    
    
    /**
     * 获取系统默认的全局异常类
     */
    private static Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;
    
    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String> ();
    
    private ExceptionOnCrashHandler() {
    }
    
    public static ExceptionOnCrashHandler getInstance() {
        return Handler.crashHandler;
    }
    
    /**
     * 静态内部类单例模式
     */
    public static class Handler {
        private static final ExceptionOnCrashHandler crashHandler = new ExceptionOnCrashHandler ();
    }
    
    
    private Context mContext;
    
    /**
     * 用来获取应用的一些信息
     */
    public void init(Context context) {
        this.mContext = context;
        //    把当前类设置为全局的异常类
        Thread.currentThread ().setUncaughtExceptionHandler (this);
        // 获取系统默认的UncaughtException处理器
        mDefaultUncaughtExceptionHandler = Thread.currentThread ()
                .getDefaultUncaughtExceptionHandler ();
    }
    
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (handleException (e) && mDefaultUncaughtExceptionHandler != null) {
            //    让系统默认处理,不然控制台看不到异常
            mDefaultUncaughtExceptionHandler.uncaughtException (t, e);
        } else {
            try {
                t.sleep (1000);
            } catch (InterruptedException e1) {
                Log.e(TAG, "error : ", e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
    
    /**
     * 1.自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //2.收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }
    
    /**
     * 2.收集设备参数信息
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }
    
    /**
     *3. 保存错误信息到文件中
     * @param ex
     * @return  返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        
        Writer writer = new StringWriter ();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            
            String time = formatter.format(new Date ());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }
    
}
