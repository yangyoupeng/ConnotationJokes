package com.yyp.baselibrary.http;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyoupeng on 2017/3/27.
 * 1.自己的一套网络框架实现
 */

public class HttpUtils {
    //    url地址
    private String mUrl;
    
    //    请求的方式，默认为get类型
    private int mType = GET_TYPE;
    private static final int POST_TYPE = 0x0011;
    private static final int GET_TYPE = 0x0011;
    
    //    请求的参数
    private Map< String, Object > mMapParams;
    
    //   默认为okHttp引擎
    private static IHttpEngine mHttpEngine = new OkHttpEngine ();
    
    private Context mContext;
    
    private HttpUtils(Context context) {
        this.mContext = context;
        mMapParams = new HashMap<> ();
    }
    
    public static HttpUtils with(Context context) {
        return new HttpUtils (context);
    }
    
    //    在Appliction里面初始化引擎
    public static void init(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }
    
    /**
     * url地址
     */
    public HttpUtils url(String url) {
        this.mUrl = url;
        return this;
    }
    
    /**
     * get请求方式
     */
    public HttpUtils get() {
        mType = GET_TYPE;
        return this;
    }
    
    /**
     * post请求方式
     */
    public HttpUtils post() {
        mType = POST_TYPE;
        return this;
    }
    
    
    /**
     * 添加参数
     */
    public HttpUtils addParam(String key, Object value) {
        mMapParams.put (key, value);
        return this;
    }
    
    /**
     * 添加多个参数
     */
    public HttpUtils addParams(Map< String, Object > valueParams) {
        mMapParams.putAll (valueParams);
        return this;
    }
    
    /**
     * 回调处理
     */
    private void excued(EngineCallBack callBack) {
        if (callBack == null) {
            callBack = EngineCallBack.ENGINE_CALL_BACK;
        }
        
        if (mType == POST_TYPE) {
            post (mUrl, mMapParams, callBack);
        }
        if (mType == GET_TYPE) {
            get (mUrl, mMapParams, callBack);
        }
    }
    
    /**
     * 每次可以自带引擎
     */
    public void exchangeEngline(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }
    
    private void get(String url, Map< String, Object > params, EngineCallBack callBack) {
        mHttpEngine.get (url, params, callBack);
    }
    
    
    private void post(String url, Map< String, Object > params, EngineCallBack callBack) {
        mHttpEngine.post (url, params, callBack);
    }
}
