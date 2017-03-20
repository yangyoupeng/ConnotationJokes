package com.yyp.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Created by yangyoupeng on 2017/3/16.
 * <p>
 * view的帮助类
 */

public class ViewField {
    
    private Activity mActivity;
    private View mView;
    
    public ViewField(Activity activity) {
        this.mActivity = activity;
    }
    
    public ViewField(View view) {
        this.mView = view;
    }
    
    /**
     * 判断是activity还是fragment，加载相应的viewId
     */
    public View findViewById(int viewId) {
        return mActivity != null ? mActivity.findViewById (viewId) : mView.findViewById (viewId);
    }
    
}
