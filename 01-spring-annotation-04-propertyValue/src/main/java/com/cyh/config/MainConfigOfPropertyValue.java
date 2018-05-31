package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cyh.bean.Person;

/**
 * ʹ�� @PropertySource ��ȡ�ⲿ�����ļ��е�k/v���浽���еĻ���������;
 * �������ⲿ�������ļ��Ժ�ʹ��${}ȡ�������ļ���ֵ
 */
@Configuration
@PropertySource(value = {"classpath:/person.properties"})
public class MainConfigOfPropertyValue {

    @Bean
    public Person person() {
        return new Person();
    }

}
