package com.zzxy.pj.common.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.zzxy.page")
@Configuration//对象的创建交给容器管理, 加了改注解表示这个类是配置类
public class PageProperties {

	private Integer pageSize;
	
}
