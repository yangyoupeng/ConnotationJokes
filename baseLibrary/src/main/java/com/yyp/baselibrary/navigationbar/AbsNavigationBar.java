package com.yyp.baselibrary.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangyoupeng on 2017/3/23.
 * <p>
 * 头部的Builder基类
 */

public abstract class AbsNavigationBar< T extends AbsNavigationBar.Builder.AbsNavigationParams >
        implements INavigationBar {
    
    private T mParams;
    private View mNavigationView;
    
    public AbsNavigationBar(T params) {
        this.mParams = params;
        createAndBindView ();
    }
    
    public T getmParams() {
        return mParams;
    }
    
    /**
     * 设置标题文本
     */
    protected void setText(int viewId, String title1) {
        TextView tv = findViewById (viewId);
        if (!TextUtils.isEmpty (title1)) {
            tv.setVisibility (View.VISIBLE);
            tv.setText (title1);
        }
    }
    
    /**
     * 设置点击
     */
    protected void setClickListener(int text_id, View.OnClickListener clickListener) {
        if (text_id != 0) {
            findViewById (text_id).setOnClickListener (clickListener);
        }
        
    }
    
    protected < T extends View > T findViewById(int viewId) {
        return (T) mNavigationView.findViewById (viewId);
    }
    
    /**
     * 绑定和创建View
     */
    private void createAndBindView() {
        
        if (mParams.mParent == null) {
            //            获取系统decorView中的布局lineraLayout
            ViewGroup viewGroup = (ViewGroup) ((Activity) mParams.mContext).getWindow ()
                    .getDecorView ();
            mParams.mParent = (ViewGroup) viewGroup.getChildAt (0);
        }
        
        if (mParams.mParent == null) {
            return;
        }
        
        //        1.创建view
        mNavigationView = LayoutInflater.from (mParams.mContext).inflate (bindLayoutId (),
                mParams.mParent, false);
        
        //        2.添加view
        mParams.mParent.addView (mNavigationView, 0);
        //        3.绑定view
        applyView ();
        
    }
    
    /**
     * builder抽象类， 规范所需参数的组成
     */
    public abstract static class Builder {
        private AbsNavigationParams params;
        
        public Builder(Context context, ViewGroup parent) {
            params = new AbsNavigationParams (context, parent);
        }
        
        public abstract AbsNavigationBar builder();
        
        
        /**
         * 所需参数,所有成员变量和方法，都需public
         */
        public static class AbsNavigationParams {
            
            public Context mContext;
            public ViewGroup mParent;
            
            public AbsNavigationParams(Context context, ViewGroup parent) {
                this.mContext = context;
                this.mParent = parent;
            }
        }
        
    }
    
}
