package com.yyp.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by yangyoupeng on 2017/3/27.
 * <p>
 * 4. okhttp默认的引擎
 */

public class OkHttpEngine implements IHttpEngine {
    
    @Override
    public void get(Context context, String url, Map< String, Object > params, EngineCallBack
            callBack) {
        
    }
    
    @Override
    public void post(Context context, String url, Map< String, Object > params, EngineCallBack callBack) {
        
    }
}
