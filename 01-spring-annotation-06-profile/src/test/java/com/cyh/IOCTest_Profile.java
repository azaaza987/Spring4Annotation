package com.cyh;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cyh.bean.Yellow;
import com.cyh.config.MainConfigOfProfile;

public class IOCTest_Profile {

    /**
     * ����л�����
     * 1��ʹ�������ж�̬����: �����������λ�ü��� -Dspring.profiles.active=test
     * 2������ķ�ʽ����ĳ�ֻ���
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        String[] dataSources = applicationContext.getBeanNamesForType(DataSource.class);
        for (String x : dataSources) {
            System.out.println(x);
        }
    }

    /**
     * 2������ķ�ʽ����ĳ�ֻ���
     */
    @Test
    public void test01() {
        //1������һ��applicationContext��ע�⣺���޲����Ĺ�������
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2��������Ҫ����Ļ���
        applicationContext.getEnvironment().setActiveProfiles("test");
        //3��ע����������
        applicationContext.register(MainConfigOfProfile.class);
        //4������ˢ������
        applicationContext.refresh();

        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String string : namesForType) {
            System.out.println(string);
        }

        Yellow y = applicationContext.getBean(Yellow.class);
        System.out.println(y);

        applicationContext.close();
    }
}
