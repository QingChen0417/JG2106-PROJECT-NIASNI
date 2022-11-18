package com.zzxy.pj.sys.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzxy.pj.sys.entity.SysDept;

import lombok.Data;

@Data
public class SysUserDeptVo implements Serializable {
	private static final long serialVersionUID = 7679533263318148048L;
	private Integer id;
	private String username;
	private String password;
	private String salt;//盐
	private String email;
	private String mobile;
	private Integer valid = 1;//是否禁用, 1启用, 0禁用
	private SysDept sysDept;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createdTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
