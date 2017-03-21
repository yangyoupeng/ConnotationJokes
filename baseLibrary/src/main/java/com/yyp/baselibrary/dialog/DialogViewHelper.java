package com.yyp.baselibrary.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by yangyoupeng on 2017/3/21.
 * <p>
 * dialog  view 的辅助处理类
 */

class DialogViewHelper {
     
    /**view的布局*/
    private View contentView = null;
    
    public DialogViewHelper(Context context, int layoutResId) {
        contentView = LayoutInflater.from (context).inflate (layoutResId, null);
        
    }
    
    public DialogViewHelper() {
        
    }
    
    /**
     * 设置布局
     */
    public void setContentView(View newContentView) {
        this.contentView = newContentView;
    }
    
    public View getContentView() {
        return contentView;
    }
    
    /**
     * 设置字体文本
     */
    private CharSequence mCharSequence;
    
    public void setText(int viewId, CharSequence charSequence) {
        this.mCharSequence = charSequence;
    }
    
    public CharSequence getText() {
        return mCharSequence;
    }
    
    
    /**
     * 设置点击事件
     */
    private View.OnClickListener mListener;
    
    public void setOnClick(int viewId, View.OnClickListener listener) {
        this.mListener = listener;
    }
    
    public View.OnClickListener getOnClick() {
        return mListener;
    }
}
