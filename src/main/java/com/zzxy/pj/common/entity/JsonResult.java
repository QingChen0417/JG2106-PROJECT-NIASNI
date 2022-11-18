package com.zzxy.pj.common.entity;

import lombok.Data;

@Data
public class JsonResult {
	private Integer state = 1;//状态 1:ok 0:no
	private Object data;//成功数据
	private String message;//异常文本描述
	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(Throwable e) {
		this.state = 0;//状态码改为0
		this.message = e.getMessage();
	}
	public JsonResult() {
	}
}
