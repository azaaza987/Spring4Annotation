package com.cyh.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.cyh.aop.MathCalculator.*(..))")
    public void pointcut() {}

    /**
     * ����ʹ������+������
     */
    @Before("com.cyh.aop.LogAspects.pointcut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(
                "@Before: " + joinPoint.getSignature().getName() + " ���У������б���: " + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * ǰ��֪ͨ���ڷ���ִ��֮ǰ��һ��ִ��
     * ��Ӧ����⣺����֪ͨ���ڷ���ִ��֮��Ҳһ��ִ��
     * @param joinPoint
     */
    @After("com.cyh.aop.LogAspects.pointcut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("@After: " + joinPoint.getSignature().getName() + " ����");
    }

    /**
     * ��������
     * JoinPoint joinPoint һ��Ҫд�ڵ�һλ
     */
    @AfterReturning(value = "pointcut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("@AfterReturning: " + joinPoint.getSignature().getName() + " ���������������: " + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("@AfterThrowing: " + joinPoint.getSignature().getName() + " ���쳣��: " + e);
    }
}
