package com.spring.aop.app;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {


    @Pointcut("execution(* com.spring.aop..*.*(..))")
    public void pointCutExecution(){
        System.out.println("oap pointCut-----------");
    }

    /**
     * within与execution相比，粒度更大，仅能实现到包和接口、类级别。
     * 而execution可以精确到方法的返回值，参数个数、修饰符、参数类型等
     */
    @Pointcut("within(com.spring.aop.dao.*)")
    public void pointCutWithIn(){
    }

    /**
     * args
     * args表达式的作用是匹配指定参数类型和指定参数数量的方法,与包名和类名无关
     */
    @Pointcut("args()")
    public void pointCutArgs(){
    }

    /**
     * 作用方法级别
     *
     * 上述所有表达式都有@ 比如@Target(里面是一个注解类xx,表示所有加了xx注解的类,和包名无关)
     */
    @Pointcut("@annotation(com.spring.aop.anno.Luban)")
    public void pointCutAnnotation(){

    }


    /**
     *  声明前置通知 ，JoinPont是srpring提供的静态变量，
     *  通过joinPoint参数可以获得目标方法的类名，方法参数，方法名等信息，这个参数可有可无。
     */
    @Before("pointCutExecution()")
    public void beforeExecution(){
        System.out.println("oap execution beforeExecution -----------");
    }
    @Before("pointCutWithIn()")
    public void beforeWithIn(){
        System.out.println("oap within pointCutWithIn -----------");
    }

    @Before("pointCutArgs()")
    public void beforeArgs(){
        System.out.println("oap args beforeArgs -----------");
    }

    /**
     * 注意:上述所有的表达式可以混合使用,|| && !
     */
    @Before("pointCutArgs()&&pointCutWithIn()")
    public void before(){
        System.out.println("oap pointCutArgs()&&pointCutWithIn() before-----------");
    }


    @Before("pointCutAnnotation()")
    public void beforeAnnotation(){
        System.out.println("oap Annotation befobeforeAnnotationreArgs -----------");
    }


    @Before("this(com.spring.aop.dao.IndexDao)")
    public void beforeThis(){
        System.out.println("oap this beforeThis ---------beforeThis-----------");
    }

    @Before("target(com.spring.aop.dao.IndexDao)")
    public void beforeTarget(){
        System.out.println("oap this beforeTarget ---------beforeTarget-----------");
    }

    //声明最终通知
    @After("execution(* com.spring.aop..*.*(..))")
    public void after(){
        System.out.println("oap after -----------");
    }
}
