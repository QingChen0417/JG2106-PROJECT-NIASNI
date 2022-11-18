package com.zzxy.pj.sys.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysDeptVo implements Serializable{
	private static final long serialVersionUID = 6045714419338589897L;
	private Integer id;
	private String name;
	private Integer parentId;
	private String parentName;
	private Integer sort;
	private String note;
}
