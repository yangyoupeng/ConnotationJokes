package com.yyp.framelibrary;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.yyp.baselibrary.navigationbar.AbsNavigationBar;

/**
 * Created by yangyoupeng on 2017/3/23.
 */

public class DefulterNavigationBar< D extends DefulterNavigationBar.Builder
        .DefulterNavigationBarParams > extends AbsNavigationBar< DefulterNavigationBar.Builder
        .DefulterNavigationBarParams > {
    
    public DefulterNavigationBar(Builder.DefulterNavigationBarParams params) {
        super (params);
    }
    
    @Override
    public int bindLayoutId() {
        //        3.添加view布局id
        return R.layout.title_bar;
    }
    
    @Override
    public void applyView() {
        //       4, 添加view的效果（参数）
        setText (R.id.title, getmParams ().title);
        setText (R.id.right_text, getmParams ().rightText);
        
        setClickListener(R.id.right_text,getmParams ().rightClickListener);
        setClickListener(R.id.back,getmParams ().leftClickListener);
    
    }
    
    public static class Builder extends AbsNavigationBar.Builder {
        
        private DefulterNavigationBarParams params;
        
        public Builder(Context context, ViewGroup parent) {
            super (context, parent);
            params = new DefulterNavigationBarParams (context, parent);
        }
        public Builder(Context context) {
            super (context, null);
            params = new DefulterNavigationBarParams (context, null);
        }
    
           @Override
        public DefulterNavigationBar builder() {
            DefulterNavigationBar defulterNavigationBar = new DefulterNavigationBar (params);
            return defulterNavigationBar;
        }
    
        //        2.设置所有效果（组成所有效果）
        
        //        设置标题
        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }
        
        //        右边text内容
        public Builder setRightText(String rightText) {
            params.rightText = rightText;
            return this;
        }
        
        //        右边text点击事件
        public Builder setRightClickListener(View.OnClickListener rightClickListener) {
            params.rightClickListener = rightClickListener;
            return this;
        }
        
        //        左边text内容
        public Builder setLeftText(String leftText) {
            params.leftText = leftText;
            return this;
        }
        
        //        左边text点击事件
        public Builder setLeftClickListener(View.OnClickListener leftClickListener) {
            params.leftClickListener = leftClickListener;
            return this;
        }
         
        public static class DefulterNavigationBarParams extends AbsNavigationParams {
            //           1. 放置所有效果（参数资源）
            
            public String title;
            
            public String rightText;
            public View.OnClickListener rightClickListener;
            
            public String leftText;
            public View.OnClickListener leftClickListener = new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    ((Activity) mContext).finish ();
                }
            };
                
            public DefulterNavigationBarParams(Context context, ViewGroup parent) {
                super (context, parent);
            }
    
        }
    }
}
