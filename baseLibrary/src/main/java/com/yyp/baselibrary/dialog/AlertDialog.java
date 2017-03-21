package com.yyp.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

/**
 * Created by yangyoupeng on 2017/3/21.
 * <p>
 * 查看源码，自定义万能AlerDialog
 */

public class AlertDialog extends Dialog {
    
    private AlertController mAlert;
    
    public AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super (context, themeResId);
        
        mAlert = new AlertController (this, getWindow ());
    }
    
    public static class Builder {
        
        private AlertController.AlertParams params;
        
        
        public Builder(Context context) {
            this (context, 0);
        }
        
        public Builder(Context context, int themeResId) {
            params = new AlertController.AlertParams ();
            params.mThemeResId = themeResId;
            params.mContext = context;
        }
        
              
        /**
         */
        public AlertDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog (params.mContext, params.mThemeResId);
            params.apply (dialog.mAlert);
            dialog.setCancelable (params.mCancelable);
            if (params.mCancelable) {
                dialog.setCanceledOnTouchOutside (true);
            }
            dialog.setOnCancelListener (params.mOnCancelListener);
            dialog.setOnDismissListener (params.mOnDismissListener);
            if (params.mOnKeyListener != null) {
                dialog.setOnKeyListener (params.mOnKeyListener);
            }
            return dialog;
        }
        
        
        /**
         */
        public AlertDialog show() {
            final AlertDialog dialog = create ();
            dialog.show ();
            return dialog;
        }
        
        public Builder setContentView(View view) {
            params.mView = view;
            params.mViewLayoutResId = 0;
            return this;
        }
        
        /**
         * 设置布局内容的layoutId
         */
        public Builder setContentView(int layoutId) {
            params.mView = null;
            params.mViewLayoutResId = layoutId;
            return this;
        }
        
        /**
         * 设置文本
         */
        public Builder setText(int viewId, CharSequence text) {
            params.mTextArray.put (viewId, text);
            return this;
        }
        
        /**
         * 设置点击事件
         */
        public Builder setOnClickListener(int view, View.OnClickListener listener) {
            params.mOnClickListenerArray.put (view, listener);
            return this;
        }
        
        public Builder setCancelable(boolean cancelable) {
            params.mCancelable = cancelable;
            return this;
        }
        
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            params.mOnCancelListener = onCancelListener;
            return this;
        }
        
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            params.mOnDismissListener = onDismissListener;
            return this;
        }
        
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            params.mOnKeyListener = onKeyListener;
            return this;
        }
        
    }
}
