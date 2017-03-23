package com.yyp.baselibrary.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by yangyoupeng on 2017/3/21.
 * <p>
 * dialog  view 的辅助处理类
 */

class DialogViewHelper {
    
    /**
     * view的布局
     */
    private View contentView = null;
    /**
     * 用来防止内存泄露
     */
    private SparseArray< WeakReference< View > > mViews;
    private TextView view;
    
    public DialogViewHelper(Context context, int layoutResId) {
        this ();
        contentView = LayoutInflater.from (context).inflate (layoutResId, null);
    }
    
    public DialogViewHelper() {
        mViews = new SparseArray<> ();
    }
    
    /**
     * 设置布局
     */
    public void setContentView(View newLayoutView) {
        this.contentView = newLayoutView;
    }
    
    /**
     * 设置字体文本
     */
    private CharSequence mCharSequence;
    
    public void setText(int viewId, CharSequence charSequence) {
        //        每次都判断一下，减少findViewById的次数
        TextView textView = getView (viewId);
        if (textView != null) {
            textView.setText (charSequence);
        }
    }
    
    /**
     * 设置点击事件
     */
    
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View viewById = getView (viewId);
        if (viewById != null) {
            viewById.setOnClickListener (listener);
        }
    }
    
    public View getContentView() {
        return contentView;
    }
    
    public < T extends View > T getView(int viewId) {
        WeakReference< View > viewWeakReference = mViews.get (viewId);
        
        View view = null;
        if (viewWeakReference != null) {
            view = viewWeakReference.get ();
        }
        
        if (view == null) {
            view = contentView.findViewById (viewId);
            if (view != null) {
                mViews.put (viewId, new WeakReference<> (view));
            }
        }
        return (T)view;
    }
}
