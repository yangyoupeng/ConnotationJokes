package com.yyp.baselibrary;

/**
 * Created by yangyoupeng on 2017/3/16.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target(ElementType.FIELD)代表属于的一个位置。 FIELD(代表放在属性上)，METHOD(代表放在方法上),TYPE(代表放在类上),CONSTRUCTOR
 * (代表放在构造函数上)
 */
@Target (ElementType.FIELD)
/**
 * @Retention(RetentionPolicy.RUNTIME)  代表什么时候运行。
 * CLASS  代表编译时注解,    RUNTIME  代表运行时注解 ,SOURCE  代表源码资源
 * */
@Retention (RetentionPolicy.RUNTIME)
public @interface ViewById {
    // view Id 值所放的位置-->  ViewById(R.id.main_activiy);
    int value();
    
}
