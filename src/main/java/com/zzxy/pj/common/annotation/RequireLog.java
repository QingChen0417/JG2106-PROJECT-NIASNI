package com.zzxy.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
@Retention(RetentionPolicy.RUNTIME)//运行期生效
@Target(ElementType.METHOD)//作用于方法
public @interface RequireLog {
	
	String value();//方法操作标识
	
}
