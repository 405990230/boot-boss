package com.boss.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {


    @Pointcut("execution(* com.boss.spring.aop..*.*(..))")
    public void pointCut(){
        System.out.println("oap pointCut-----------");
    }

    /**
     *  声明前置通知 ，JoinPont是srpring提供的静态变量，
     *  通过joinPoint参数可以获得目标方法的类名，方法参数，方法名等信息，这个参数可有可无。
     */
    @Before("pointCut()")
    public void before(){
        System.out.println("oap before -----------");
    }


    //声明最终通知
    @After("execution(* com.boss.spring.aop..*.*(..))")
    public void after(){
        System.out.println("oap after -----------");
    }
}
