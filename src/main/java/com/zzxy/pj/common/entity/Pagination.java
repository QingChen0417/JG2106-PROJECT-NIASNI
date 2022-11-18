package com.zzxy.pj.common.entity;

import java.util.List;

import lombok.Data;

//分页对象
@Data
public class Pagination {
	private int curPage;//当前页
	private int pageSize;//每页条数
	private int countSize;//总条数
	private int countPage;//总页数
	private List<?> pageData;//分页数据
	/**
	 * 分页数据处理
	 * @param curPage	当前页参数
	 * @param countSize	总条数参数
	 * @param pageSize	每页条数参数
	 */
	public Pagination(int curPage, int countSize, int pageSize) {
		this.countSize = countSize;
		pageSize = pageSize <= 0 ? 10 : pageSize;
		this.pageSize = pageSize >= 50 ? 50 : pageSize;
		this.countPage = (countSize + pageSize - 1) / pageSize;
		curPage = curPage <= 1 ? 1 : curPage;
		this.curPage = curPage >= this.countPage ? this.countPage : curPage;
	}
}
