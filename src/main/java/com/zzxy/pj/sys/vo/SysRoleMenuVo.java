package com.zzxy.pj.sys.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenuVo implements Serializable {
	private static final long serialVersionUID = 2437599938907715176L;
	
	private Integer id;//角色id
	private String name;//角色名
	private String note;//角色描述
	private List<Integer> menuIds;//角色对应的菜单集合
}
