package com.yyp.baselibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.yyp.baselibrary.R;

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
    
    /**
     * 设置点击事件
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnClickListener (viewId,listener);
    }
    /**
     * 设置字体文本
     */
    public void setText(int viewId, CharSequence charSequence) {
        mAlert.setText (viewId,charSequence);
    }
    /**获取view*/
    public < T extends View > T getView(int viewId) {
        return mAlert.getView (viewId);
    }
    public  static class Builder {
        private final AlertController.AlertParams params;
        
        public Builder(Context context) {
            this (context, R.style.dialog);
        }
        
        public Builder(Context context, int themeResId) {
            params = new AlertController.AlertParams (context, themeResId);
        }
        
        /**
         * 设置一些万能的参数
         */
        public Builder setFullWidth() {
            params.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }
        
        /**
         * 设置view
         */
        public Builder setLayoutView(View view) {
            params.mView = view;
            params.mViewLayoutResId = 0;
            return this;
        }
        
        /**
         * 设置布局内容的layoutId
         */
        public Builder setLayoutView(int layoutId) {
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
        
        /**
         * 设置宽度和高度
         */
        public Builder setWidthAddHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }
        
        /**
         * 设置动画
         */
        public Builder setAnimation(int styleAnimation) {
            params.mAnimation = styleAnimation;
            return this;
        }
        
        /**
         * 设置默认的动画
         */
        public Builder setDefultAnimation() {
            params.mAnimation = R.style.dialog_scale_anim;
            return this;
        }
        
        /**
         * 设置是否有动画，从底部弹出来
         */
        public Builder setFormBottom(boolean isAnimation) {
            if (isAnimation) {
                //从底部弹出的动画
                params.mAnimation = R.style.dialog_from_bottom_anim;
            }
            //            设置动画的位置在底部
            params.mGravity = Gravity.BOTTOM;
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
        
        public AlertDialog show() {
            final AlertDialog dialog = create ();
            dialog.show ();
            return dialog;
        }
        
    }
}
