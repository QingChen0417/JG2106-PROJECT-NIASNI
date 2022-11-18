package com.zzxy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Service//Service Component Controller效果一样
@Slf4j
public class SysTimeAspect {
	
	//切入点: bean括号的名字是容器管理对象(目标对象)的id
	//					id如果有指定就是指定的, 每有指定就是类名首字母小写
	@Pointcut("bean(sysUserServiceImpl)")
	public void doTime() {}

	//@Before前置通知, 要指定切入点, 在目标方法执行前执行
	//	在环绕通知之后执行(2.0以上的版本)
	@Before("doTime()")
	public void doBefore() {
		System.out.println("time doBefore()");
	}
	
	//@After后置通知, 要指定切入点, 在目标方法执行后执行
	//	在环绕通知之前执行, 在返回通知之后执行, 在异常通知之后执行
	@After("doTime()")
	public void doAfter() {
		System.out.println("time doAfter()");
	}
	
	//@AfterReturning返回通知, 要指定切入点, 在后置通知之前执行
	//	如果有异常通知不执行返回通知
	@AfterReturning("doTime()")
	public void doAfterReturning() {
		System.out.println("time doAfterReturnIng()");
	}
	
	//@AfterThrowing异常通知, 有异常才执行
	@AfterThrowing("doTime()")
	public void doAfterThrowing() {
		System.out.println("time doAfterThrowing()");
	}
	
	//@Around环绕通知, 要指定切入点, 在目标方法前
	//	在所有通知前执行, 在所有通知后执行
	@Around("doTime()")
	public Object doAround(ProceedingJoinPoint pj) throws Throwable {
		System.out.println("time doAround()");
		Object proceed = pj.proceed();//调用目标方法, 或跳到下一个切入点
		System.out.println("time afterDoAround()");
		log.info("" + proceed);
		return proceed;
	}
}
