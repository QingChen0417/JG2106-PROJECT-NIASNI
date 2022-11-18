package com.zzxy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaoCacheTest {

	//测试myBatis的一级缓存
	// myBatis的流程
	//		SqlSessionFactory
	//		SqlSession	一次请求会产生一个sqlSession
	//		Executor
	//		MappedStatement
	
	@Autowired
	private SqlSessionFactory ssf;
	
	@Test
	public void testOneCache() {
		//工厂创建一个SqlSession
		SqlSession session = ssf.openSession();
		List<Object> list = session.selectList(""
				+ "com.zzxy.pj.sys.dao.SysMenuDao.findObjects");
		System.out.println(list);
		
		//同一个sqlSession去查同一条sql语句会触发一级缓存
		//但实际开发中, 一次请求同时调用同一sql的情况很少
		//所有myBatis的一级缓存没什么用,默认开启一级缓存
		session = ssf.openSession();
		list = session.selectList(""
				+ "com.zzxy.pj.sys.dao.SysMenuDao.findObjects");
		System.out.println(list);
	}
	
	@Test
	public void testTwoCache() {
		//二级缓存生效需要commit
		SqlSession session = ssf.openSession();
		List<Object> list = session.selectList(""
				+ "com.zzxy.pj.sys.dao.SysMenuDao.findObjects");
		System.out.println(list);
		session.commit();
		
		session = ssf.openSession();
		list = session.selectList(""
				+ "com.zzxy.pj.sys.dao.SysMenuDao.findObjects");
		System.out.println(list);
		session.commit();
	}
	
	@Test
	public void readOnlyTest() {
		SqlSession session = ssf.openSession();
		List<Object> list1 = session.selectList(""
				+ "com.zzxy.pj.sys.dao.SysLogDao.findLogObject");
		System.out.println(list1);
		session.commit();
		
		session = ssf.openSession();
		List<Object> list2 = session.selectList(""
				+ "com.zzxy.pj.sys.dao.SysLogDao.findLogObject");
		System.out.println(list2);
		System.out.println(list1 == list2);
		session.commit();
	}
}
