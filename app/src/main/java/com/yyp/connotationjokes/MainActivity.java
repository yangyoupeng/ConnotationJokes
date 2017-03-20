package com.yyp.connotationjokes;

import android.widget.ImageView;

import com.yyp.framelibrary.BaseSkinActivity;

public class MainActivity extends BaseSkinActivity{
    
    @Override
    protected void initTitle() {
        
    }
    
    @Override
    protected void initView() {
        ImageView view = viewById (R.id.iv);
    }
    
    
    
    @Override
    protected void initData() {
        
    }
    
    @Override
    protected void setContentView() {
        setContentView (R.layout.activity_main);
        startActivity (MainActivity.class);
    }
}
