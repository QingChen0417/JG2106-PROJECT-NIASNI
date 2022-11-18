package com.zzxy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzxy.pj.common.entity.JsonResult;
import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.sys.service.SysLogService;

//@Controller
@RestController//相当于@Controller + @ResponseBody
@RequestMapping("log")
public class SysLogController {

	@Autowired
	private SysLogService service;
	
	@RequestMapping("findLogObjects")
	public JsonResult findLogObjects(String username,
			@RequestParam(defaultValue = "1") Integer curPage,
			@RequestParam(defaultValue = "0") Integer pageSize) {
		Pagination pagina = service.findLogByUsername(username, curPage, pageSize);
		return new JsonResult(pagina);
	}
	
	@RequestMapping("deleteLog")
	public JsonResult deleteLog(@RequestParam("ids[]") Integer[] ids) {
		int n = service.deleteLog(ids);
		return new JsonResult(n);
	}
}
