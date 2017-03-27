package com.yyp.baselibrary.http;

/**
 * Created by yangyoupeng on 2017/3/27.
 * <p>
 * 3.请求的回调
 */

public interface EngineCallBack {
    
    //    失败
    void onError(Exception e);
    
    //    成功
    void onSuccess(String result);
    
    //    默认
    public static final EngineCallBack ENGINE_CALL_BACK = new EngineCallBack () {
        @Override
        public void onError(Exception e) {
            
        }
        
        @Override
        public void onSuccess(String result) {
            
        }
    };
    
}
