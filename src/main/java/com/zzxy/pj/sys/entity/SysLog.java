package com.zzxy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//日志类
@Data
public class SysLog implements Serializable {
	/**
	 * 序列化常量
	 */
	private static final long serialVersionUID = -2076592618247408292L;
	private Integer id;
	private String username;//用户名
	private String operation;//用户操作
	private String method;//请求方法
	private String params;
	private Long time;//执行时长
	private String ip;//ip地址
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//时间处理格式
	private Date createdTime;
}
