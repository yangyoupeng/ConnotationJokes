package com.yyp.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yyp.baselibrary.ioc.ViewUtils;

/**
 * Created by yangyoupeng on 2017/3/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        //        初始化布局
        setContentView ();
        //      注解，特定的方法，子类一般都能使用
        ViewUtils.inject (this);
        //        初始化头
        initTitle ();
        //        初始化界面
        initView ();
        //        初始化数据
        initData ();
    }
    
    protected abstract void setContentView();
    
    protected abstract void initTitle();
    
    protected abstract void initView();
    
    protected abstract void initData();
    
    //    开启一个activity
    protected void startActivity(Class< ? > clazz) {
        Intent intent = new Intent (this, clazz);
        startActivity (intent);
    }
    
    //    findViewById  返回一个view,(泛型在方法上的使用)
    protected < T extends View > T viewById(int viewId) {
        return (T) findViewById (viewId);
    }
}
