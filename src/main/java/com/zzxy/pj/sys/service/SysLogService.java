package com.zzxy.pj.sys.service;


import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.sys.entity.SysLog;

public interface SysLogService {
	/**
	 * 通过用户名查找日志信息
	 * @param username	用户名
	 * @param curPage	当前页
	 * @param pageSize	每页条数
	 * @return
	 */
	Pagination findLogByUsername(String username, Integer curPage, Integer pageSize);

	/**
	 * 删除日志(有权限, 权限后面再写)
	 * @param ids 日志id数组
	 * @return
	 */
	int deleteLog(Integer[] ids);

	/**
	 * 保存日志
	 * @param log
	 */
	Integer saveLog(SysLog log);
	
}
