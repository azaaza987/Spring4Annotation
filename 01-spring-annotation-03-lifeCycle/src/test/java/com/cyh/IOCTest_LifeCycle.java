package com.cyh;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.cyh.config.MainConfigOfLifeCycle;

public class IOCTest_LifeCycle {

    @Test
    public void test01() {
        AbstractApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("�����������============================");

        applicationContext.getBean("car");

        System.out.println("���������ر�============================");
        applicationContext.close();
    }
}
