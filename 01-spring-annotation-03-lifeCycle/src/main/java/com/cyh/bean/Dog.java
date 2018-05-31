package com.cyh.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Dog implements ApplicationContextAware {

    public Dog() {
        System.out.println("Dog constructor...");
    }

    /**
     * ���󴴽�����ֵ֮�����
     *
     * ʵ������ InitDestroyAnnotationBeanPostProcessor ��� BeanPostProcessor �ڹ���
     * �������� postProcessBeforeInitialization() �����б����õ�
     */
    @PostConstruct
    public void init() {
        System.out.println("Dog....@PostConstruct...");
    }

    /**
     * �����Ƴ�����֮ǰ
     *
     * ʵ������ InitDestroyAnnotationBeanPostProcessor ��� BeanPostProcessor �ڹ���
     * �������� postProcessBeforeDestruction() �����б����õ�
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Dog....@PreDestroy...");
    }

    /**
     * �˷�����Ŀ������Debug��������� BeanPostProcessor
     *
     * ͨ��Debug���Է��֣��� setApplicationContext() ������ʵ���� ApplicationContextAwareProcessor ��� BeanPostProcessor ��
     * postProcessBeforeInitialization() �����б����õ�
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware�ص�setApplicationContext��" + applicationContext);
    }
}

