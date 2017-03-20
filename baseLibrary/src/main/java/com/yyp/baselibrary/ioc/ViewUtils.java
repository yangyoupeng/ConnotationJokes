package com.yyp.baselibrary.ioc;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yangyoupeng on 2017/3/16.
 */

public class ViewUtils {
    
    public static void inject(Activity activity) {
        inject (new ViewField (activity), activity);
    }
    
    public static void inject(View view) {
        inject (new ViewField (view), view);
    }
    
    public static void inject(View view, Object object) {
        inject (new ViewField (view), object);
    }
    
    /**
     * 兼容前面三个方法 ，
     * viewField  帮助类
     * object   -->通过反射获取传递过来的类
     */
    public static void inject(ViewField viewField, Object object) {
        injectFinder (viewField, object);
        injectEvent (viewField, object);
    }
    
    /**
     * 注入属性
     */
    private static void injectFinder(ViewField viewField, Object object) {
        //1.获取当前View的所有属性
        Class< ? > aClass = object.getClass ();
        //        获取当前类所有的属性包括 private ,procted,public等修饰符修饰的
        Field[] fields = aClass.getDeclaredFields ();
        
        //2. 获取findViewById里面的value值
        for (Field field : fields) {
            //
            ViewById annotation = field.getAnnotation (ViewById.class);
            
            //3.判断获取的值是否为空，findViewById 找到相应的view
            if (annotation != null) {
                // 获取注解里面的ID值 --> R.id.tvText
                int value = annotation.value ();
                // 通过帮助类方法findViewById 把ID值传入里面
                View viewById = viewField.findViewById (value);
                //能够注入所有修饰符  所修饰的属性
                field.setAccessible (true);
                // 4.view不是空的情况下，动态注入找到的View
                if (viewById != null) {
                    try {
                        field.set (object, viewById);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace ();
                    }
                }
            }
        }
    }
    
    /**
     * 注入事件
     */
    private static void injectEvent(ViewField viewField, Object object) {
        //   1. 获取所有当前类所有的属性
        Class< ? > clickClass = object.getClass ();
        //  获取所有方法
        Method[] declaredMethods = clickClass.getDeclaredMethods ();
        
        for (Method declaredMethod : declaredMethods) {
            // 2. 找到当前方法view的id 值
            OnClick annotation = declaredMethod.getAnnotation (OnClick.class);
            
            // 3.不等于null 获取id值，传入帮助类viewfield方法中findViewById
            if (annotation != null) {
                int[] value = annotation.value ();
                for (int i : value) {
                    View viewById = viewField.findViewById (i);
                    
                    //扩展判断是否有网络，
                    boolean isCheckNet = declaredMethod.getAnnotation (Network.class) != null;
                    
                    if (viewById != null) {
                        // 4.设置setOnClickListener
                        viewById.setOnClickListener (new DeclaredOnClickListener (declaredMethod,
                                object, isCheckNet));
                    }
                }
            }
        }
    }
    
    public static class DeclaredOnClickListener implements View.OnClickListener {
        private Method method;
        private Object mObject;
        private boolean mIsCheckNet;
        
        public DeclaredOnClickListener(Method declaredMethods, Object object, boolean isCheckNet) {
            this.method = declaredMethods;
            this.mObject = object;
            this.mIsCheckNet = isCheckNet;
        }
        
        @Override
        public void onClick(View v) {
            //需要不需要判断网络
            if (mIsCheckNet) {
                //如果网络没连接，弹出一个吐丝
                if (!nextworkAvailablt (v.getContext ())) {
                    Toast.makeText (v.getContext (), "SB，没网了，还点个屁啊", Toast.LENGTH_SHORT).show ();
                    return;
                }
            }
            
            //可以获取所有方法
            method.setAccessible (true);
            try {
                //通过反射执行方法
                method.invoke (mObject, v);
            } catch (Exception e) {
                e.printStackTrace ();
                try {
                    method.invoke (mObject, new Object[]{});
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace ();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace ();
                }
            }
        }
    }
    
    /**
     * 判断当前网络是否可用
     */
    private static boolean nextworkAvailablt(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService (Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo ();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected ()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return false;
    }
}
