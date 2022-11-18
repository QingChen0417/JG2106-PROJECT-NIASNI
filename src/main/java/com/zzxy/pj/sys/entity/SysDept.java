package com.zzxy.pj.sys.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//部门表
@Data
public class SysDept {

	private Integer id;
	private String name;
	private Integer parentId;
	private Integer sort;//排序
	private String note;//描述
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createdTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
	
}
