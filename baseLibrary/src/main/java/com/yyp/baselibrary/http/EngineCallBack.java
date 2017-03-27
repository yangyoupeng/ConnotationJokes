package com.yyp.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by yangyoupeng on 2017/3/27.
 * <p>
 * 3.请求的回调
 */

public interface EngineCallBack {
    //开始执行，在执行之前会回掉的方法
    void onPreExecute(Context context, Map< String, Object > params);
    
    //    失败
    void onError(Exception e);
    
    //    成功
    void onSuccess(String result);
    
    //    默认
    final EngineCallBack ENGINE_CALL_BACK = new EngineCallBack () {
        @Override
        public void onPreExecute(Context context, Map< String, Object > params) {
            
        }
        
        @Override
        public void onError(Exception e) {
            
        }
        
        @Override
        public void onSuccess(String result) {
            
        }
    };
    
}
