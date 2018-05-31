package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.cyh.bean.Person;

/**
 * �ų���
 * excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}
 *
 * ֻҪ��ע����Ҫ���� useDefaultFilters = false ������Ч
 * includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)}, useDefaultFilters = false
 *
 * ֻҪĳ��ָ�����ͣ�
 * @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BookService.class)
 */
@Configuration
@ComponentScans(value = {@ComponentScan(value = "com.cyh",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)},
        useDefaultFilters = false)})
public class MainConfig {

    /**
     * ��������ע��һ��Bean������Ϊ����ֵ�����ͣ�
     * Ĭ�����÷�������Ϊid����������� @Bean ע����ʹ�� value/name ����ָ��������ָ��������Ϊ׼
     * @return
     */
    @Bean("person")
    public Person person01() {
        return new Person("lisi", 20);
    }


}
