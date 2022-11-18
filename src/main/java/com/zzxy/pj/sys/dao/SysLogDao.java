package com.zzxy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zzxy.pj.sys.entity.SysLog;

//日志数据层
@Mapper
public interface SysLogDao {
	/**
	 * 按用户名查找日志总条数
	 * @param username
	 * @return
	 */
	Integer getRowCount(String username);
	
	/**
	 * 按用户名查找日志信息且分页
	 * @param username	用户名
	 * @param start		开始条数(service中处理)
	 * @param pageSize	查询的条数
	 * @return
	 */
	//List<SysLog> findLogObject(@Param("username") String username, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

	/**
	 * 根据id删除日志信息
	 * @param ids id数组
	 * @return
	 */
	Integer deleteLogByIds(Integer[] ids);

	List<SysLog> findLogObject(String username);

	/**
	 * 保存日志
	 * @param log
	 * @return 
	 */
	Integer saveLog(SysLog log);
}
