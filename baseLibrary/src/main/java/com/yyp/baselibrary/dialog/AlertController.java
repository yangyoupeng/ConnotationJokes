package com.yyp.baselibrary.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;

/**
 * Created by yangyoupeng on 2017/3/21.
 */

class AlertController {
    private AlertDialog mAlertDialog;
    private Window mWindow;
    
    public AlertController(AlertDialog alertDialog, Window window) {
        this.mAlertDialog = alertDialog;
        this.mWindow = window;
    }
    
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
         * 点击空白是否能够取消
         */
        public boolean mCancelable = false;
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
        
        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }
    
        public AlertParams() {
            
        }
    
        /**
         * 绑定和设置参数
         */
        public void apply(AlertController mAlert) {
            
            //            1.设置dialog的布局
            DialogViewHelper dialogViewHelper = null;
            if (mViewLayoutResId == 0) {
                dialogViewHelper = new DialogViewHelper (mContext, mViewLayoutResId);
            }
            if (mView == null) {
                dialogViewHelper = new DialogViewHelper ();
                dialogViewHelper.setContentView (mView);
            }
            if (dialogViewHelper == null) {
                throw new IllegalArgumentException ("参数设置错误，请查看是否调用setContentView");
            }
            
//            mAlert.getAlerDialog ().setContentView (dialogViewHelper.getContentView ());
//            mAlert.getAlerDialog ().
            
            
            //            2.设置字体文本
            for (int i = 0; i < mTextArray.size (); i++) {
                dialogViewHelper.setText (mTextArray.keyAt (i), mTextArray.valueAt (i));
            }
            
            //            3.设置点击事件
            for (int i = 0; i < mOnClickListenerArray.size (); i++) {
                dialogViewHelper.setOnClick (mOnClickListenerArray.keyAt (i),
                        mOnClickListenerArray.valueAt (i));
            }
        }
    }
}
