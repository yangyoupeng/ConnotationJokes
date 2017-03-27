package com.yyp.baselibrary.navigationbar;

/**
 * Created by yangyoupeng on 2017/3/23.
 */

public interface INavigationBar {
    
    /**
     * 头部的规范(绑定的布局id)
     */
    int bindLayoutId();
    
    /**
     * 绑定头部的参数
     */
    void applyView();
    
}
