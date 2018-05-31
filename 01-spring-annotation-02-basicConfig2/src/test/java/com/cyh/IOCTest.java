package com.cyh;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.cyh.bean.Blue;
import com.cyh.bean.Person;
import com.cyh.config.MainConfig2;

public class IOCTest {
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test02() {
        System.out.println("ioc�����������....");

        System.out.println("�� 1 �λ�ȡ����ʼ");
        Object bean = applicationContext.getBean("person");
        System.out.println("�� 1 �λ�ȡ������");

        System.out.println("�� 2 �λ�ȡ����ʼ");
        Object bean2 = applicationContext.getBean("person");
        System.out.println("�� 2 �λ�ȡ������");

        System.out.println(bean == bean2);
    }

    @Test
    public void test03() {
        Environment environment = applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);

        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @Test
    public void testImport() {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }

        Blue blue = applicationContext.getBean(Blue.class);
        System.out.println(blue);

        // ����Bean��ȡ�ĵ��� getObject() ���������Ķ���
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
        Object colorFactoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean == colorFactoryBean2);
        // ��ȡ����Bean������Ҫ��һ��&
        Object colorFactoryBean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean3.getClass());
    }

}
