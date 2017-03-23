package com.yyp.baselibrary.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by yangyoupeng on 2017/3/21.
 */

class AlertController {
    private AlertDialog mAlertDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;
    
    public AlertController(AlertDialog alertDialog, Window window) {
        this.mAlertDialog = alertDialog;
        this.mWindow = window;
    }
    
    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }
    
    /**
     * 设置文本
     */
    public void setText(int i, CharSequence charSequence) {
        mViewHelper.setText (i, charSequence);
    }
    
    public < T extends View > T getView(int viewId) {
        return mViewHelper.getView (viewId);
    }
    
    /**
     * 设置点击事件
     */
    public void setOnClickListener(int i, View.OnClickListener listener) {
        mViewHelper.setOnClickListener (i, listener);
    }
    
    /**
     * 获取dialog
     */
    public AlertDialog getAlerDialog() {
        return mAlertDialog;
    }
    
    public Window getWindow() {
        return mWindow;
    }
    
    
    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        /**
         * 点击空白是否能够取消,true是能够取消的
         */
        public boolean mCancelable = true;
        /**
         * 取消dialog 使监听失效
         */
        public DialogInterface.OnCancelListener mOnCancelListener;
        /**
         * 关闭Dismiss dialog 使监听失效
         */
        public DialogInterface.OnDismissListener mOnDismissListener;
        /**
         * 按键监听
         */
        public DialogInterface.OnKeyListener mOnKeyListener;
        /**
         * 设置view的布局
         */
        public View mView;
        /**
         * 布局的ID
         */
        public int mViewLayoutResId;
        /**
         * 用来存放修改字体文本
         */
        public SparseArray< CharSequence > mTextArray = new SparseArray<> ();
        /**
         * 用来存放点击监听
         */
        public SparseArray< View.OnClickListener > mOnClickListenerArray = new SparseArray<> ();
        //        设置宽度  默认宽度为 ViewGroup.LayoutParams.WRAP_CONTENT
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        //        设置高度  默认高度为 ViewGroup.LayoutParams.WRAP_CONTENT
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        //         设置动画，默认为0
        public int mAnimation = 0;
        //        设置动画的位置，默认在中心
        public int mGravity = Gravity.CENTER;
        
        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }
        
        /**
         * 绑定和设置参数
         */
        public void apply(AlertController mAlert) {
            //            1.设置dialog的布局
            DialogViewHelper dialogViewHelper = null;
            if (mViewLayoutResId != 0) {
                dialogViewHelper = new DialogViewHelper (mContext, mViewLayoutResId);
            }
            if (mView != null) {
                dialogViewHelper = new DialogViewHelper ();
                dialogViewHelper.setContentView (mView);
            }
            if (dialogViewHelper == null) {
                throw new IllegalArgumentException ("参数设置错误，请查看是否调用setContentView");
            }
            //            给dialog设置布局
            mAlert.getAlerDialog ().setContentView (dialogViewHelper.getContentView ());
            
            // 设置 Controller的辅助类
            mAlert.setViewHelper (dialogViewHelper);
            
            //            2.设置字体文本
            for (int i = 0; i < mTextArray.size (); i++) {
                mAlert.setText (mTextArray.keyAt (i), mTextArray.valueAt (i));
            }
            
            //            3.设置点击事件
            for (int i = 0; i < mOnClickListenerArray.size (); i++) {
                mAlert.setOnClickListener (mOnClickListenerArray.keyAt (i), mOnClickListenerArray.valueAt
                        (i));
            }
            
            //            4.设置自定义效果如：动画，宽高等
            Window window = mAlert.getWindow ();
            //            设置位置
            
            window.setGravity (mGravity);
            //            设置宽高
            WindowManager.LayoutParams attributes = window.getAttributes ();
            attributes.width = mWidth;
            attributes.height = mHeight;
            window.setAttributes (attributes);
            //            设置动画
            if (mAnimation != 0) {
                window.setWindowAnimations (mAnimation);
            }
        }
    }
}
