package com.zzxy.pj.sys.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//提问:Controller的注解所在的类在项目启动后立即创建对象,为什么这样?
//@Scope("prototype")原型模式
//@Scope("singleton")//单例模式
//@Lazy//延时加载,只有单例模式有用
//@RestController	相当于@Controller+@ResponseBody
public class PageUIController {
	
	//改对象利用了CPU的算法保证了线程安全
	private AtomicLong times = new AtomicLong();
	
	/**
	 * 菜单管理主页人口
	 * @return
	 */
//	@RequestMapping("sys/menu_list")
//	public String doMenuUI() {
//		return "sys/menu_list";
//	}
//	
//	@RequestMapping("sys/log_list")
//	public String doLogPageUI() {
//		return "sys/log_list";
//	}
	
	//"{module}/{page}" : 可以统一接受访问路径
	//参数需加@PathVariable()注解
	@RequestMapping("{module}/{page}")
	public String doModuleUI(@PathVariable("page") String page) {
			return "sys/"+page;
	}
	
	@RequestMapping("doIndexUI")
	public String pageUI() {
		times.incrementAndGet();//加1的方法
		return "starter";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
}
