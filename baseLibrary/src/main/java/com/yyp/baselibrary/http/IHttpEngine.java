package com.yyp.baselibrary.http;

import java.util.Map;

/**
 * Created by yangyoupeng on 2017/3/27.
 * <p>
 * 2.规范自己的一套网络引擎
 */

public interface IHttpEngine {
    
    //    get请求
    void get(String url, Map< String, Object > params, EngineCallBack callBack);
    
    //    post请求
    void post(String url, Map< String, Object > params, EngineCallBack callBack);
    
    //    上传文件
    
    //    下载文件
    
    //    http添加证书
    
}
