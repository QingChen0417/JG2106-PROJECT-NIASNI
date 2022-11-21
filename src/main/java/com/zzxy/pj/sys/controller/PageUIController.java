package com.zzxy.pj.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.zzxy.pj.common.util.ShiroUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String pageUI(Map<String, Object> map) {
		times.incrementAndGet();//加1的方法
		map.put("user", ShiroUtil.getUsername());
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

	@RequestMapping("base/base_list")
	public String doBaseUI(Model model) {
		//模拟数据
		List<Item> data = new ArrayList<>();
		data.add(new Item(1,"足球","体育"));
		data.add(new Item(1,"动画片","动漫"));
		model.addAttribute("list",data);
		return "sys/base_list";
	}
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Item implements Serializable{
	private int id;
	private String name;
	private String type;
}
