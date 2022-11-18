package com.zzxy.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzxy.pj.common.annotation.RequireLog;
import com.zzxy.pj.common.util.IPUtils;
import com.zzxy.pj.common.util.ShiroUtil;
import com.zzxy.pj.sys.entity.SysLog;
import com.zzxy.pj.sys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;


@Aspect//切面类
@Slf4j
@Component
public class SysLogAspect {

	@Pointcut("bean(sysUserServiceImpl)")
	 public void logPointCut() {}
	
	@Autowired
	private SysLogService service;
	
	@Pointcut("@annotation(com.zzxy.pj.common.annotation.RequireLog)")
	public void saveLog() {}

	 //@Around("logPointCut()")
	 public Object around(ProceedingJoinPoint jp)
	 throws Throwable{
		 try {
		   log.info("start:"+System.currentTimeMillis());
		   Object result=jp.proceed();//调用下一个切面方法或目标方法
		   log.info("after:"+System.currentTimeMillis());
		   return result;
		 }catch(Throwable e) {
		   log.error(e.getMessage());
		   throw e;
		 }
	 }
	 
	 @Around("saveLog()")//经过分析, 保存日志要用细粒度切入点
	 //ProceedingJoinPoint连接点, 能够获取目标方法的所有信息
	 public Object saveLog(ProceedingJoinPoint pj) throws Throwable {
		 long begin = System.currentTimeMillis();
		 Object obj = pj.proceed();//执行目标方法
		 long end = System.currentTimeMillis();
		 insertLog(end - begin, pj);
		 return obj;
	 }

	 /**
	  * 保存日志
	  * @param time 方法执行时间
	  * @param pj	连接点对象
	  * @throws SecurityException 
	  * @throws NoSuchMethodException 
	 * @throws InterruptedException 
	  */
	private void insertLog(long time, ProceedingJoinPoint pj) throws NoSuchMethodException, SecurityException, InterruptedException {
		//获取方法的签名: 包含了方法的签名信息
		//MethodSignature是Signature的儿子, 是向下造型, 需要强转
		//JDK获取方法签名是接口的, CGLB获取方法签名是实现类的
		//原因: JDK动态代理是实现接口的, CGLB是继承实现类的
		MethodSignature ms = (MethodSignature) pj.getSignature();
		//获取目标方法, 这种方法JDK动态代理获取的是接口的方法, 不能用
		//Method method = ms.getMethod();
		Object obj = pj.getTarget();//获取目标对象
		Class<?> clz = obj.getClass();//获取类对象
		//反射获取指定方法, 第一个参数为方法名, 第二个参数为参数类型
		Method method = clz.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		//获取方法名
		String name = method.getName();
		//获取类型, JDKD动态代理获取的是接口方法的类型
		//String type = ms.getDeclaringTypeName();
		String type = clz.getName();//获取类对象的名字
		String methodName = type + "." + name + "()";
		Object[] args = pj.getArgs();//获取参数的方法
		String argsStr = Arrays.toString(args);
		System.out.println(argsStr);
		String ip = IPUtils.getIpAddr();
		RequireLog logStr = method.getAnnotation(RequireLog.class);
		String operation = logStr.value();
		SysLog log = new SysLog();
		log.setIp(ip);//设置ip地址
		log.setCreatedTime(new Date());//设置创建日期
		log.setMethod(methodName);//设置方法全名
		log.setOperation(operation);//设置操作
		log.setParams(argsStr);//设置参数
		log.setTime(time);//设置方法执行时长
		log.setUsername(ShiroUtil.getUsername());//设置用户名
		service.saveLog(log);
	}
	
}
