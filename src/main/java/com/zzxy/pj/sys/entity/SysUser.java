package com.zzxy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = -5705536495400037078L;
	private Integer id;
	private String username;
	private String password;
	private String salt;//盐
	private String email;
	private String mobile;
	private Integer valid = 1;//是否禁用, 1启用, 0禁用
	private Integer deptId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createdTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
