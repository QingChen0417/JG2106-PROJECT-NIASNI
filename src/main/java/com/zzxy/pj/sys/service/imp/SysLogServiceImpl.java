package com.zzxy.pj.sys.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzxy.pj.common.entity.PageProperties;
import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.common.util.Assert;
import com.zzxy.pj.sys.dao.SysLogDao;
import com.zzxy.pj.sys.entity.SysLog;
import com.zzxy.pj.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao dao;
	
	@Autowired
	private PageProperties pp;
	
	public Pagination findLogByUsername(String username, Integer curPage, Integer pageSize) {
		pageSize = pageSize == 0 || pageSize == null ? pp.getPageSize() : pageSize;
		System.out.println(pageSize);
		Page<SysLog> page = PageHelper.startPage(curPage, pageSize);
		List<SysLog> list = dao.findLogObject(username);//pageHelper对象不需要limit了
		//page.getTotal();PageHelper对象会自动查找数据总条数, 返回值为long
		Pagination pageObj = new Pagination(curPage, (int)page.getTotal(), pageSize);
		pageObj.setPageData(list);
		/*
		 * Integer count = dao.getRowCount(username); Pagination pageObj = new
		 * Pagination(curPage, count, pageSize); //跳过多少条=(当前页-1) * 每页条数 Integer start =
		 * (pageObj.getCurPage() - 1) * pageObj.getPageSize(); start = start <= 0 ? 0 :
		 * start; List<SysLog> list = dao.findLogObject(username, start,
		 * pageObj.getPageSize()); pageObj.setPageData(list);
		 */
		return pageObj;
	}

	//开始事务
	@Transactional
	public int deleteLog(Integer[] ids) {
		Assert.isEmpty(ids == null || ids.length == 0, "请选择要删除的数据");
		Integer n = dao.deleteLogByIds(ids);
		Assert.isEmpty(n == 0, "数据已被删除");
		return n;
	}

	//Propagation.REQUIRES_NEW : 开启一个新事务
	@Async//异步注解, 交给另外一个线程去执行
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer saveLog(SysLog log) {
		/*
		 * try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO 自动生成的
		 * catch 块 e.printStackTrace(); }
		 */		return dao.saveLog(log);
	}
	
}
