package com.zzxy.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zzxy.pj.common.entity.Pagination;
import com.zzxy.pj.sys.service.SysLogService;

@SpringBootTest
public class SysLogServiceTest {

	@Autowired
	private SysLogService service;
	
	@Test
	public void findLogBuUsernameTest() {
		Pagination pagin = service.findLogByUsername("ad", 2, 10);
		for (Object sysLog : pagin.getPageData()) {
			System.out.println(sysLog);
		}
	}
}
