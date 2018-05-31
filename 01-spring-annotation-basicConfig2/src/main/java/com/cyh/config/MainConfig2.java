package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.cyh.bean.Color;
import com.cyh.bean.ColorFactoryBean;
import com.cyh.bean.Person;
import com.cyh.bean.Red;
import com.cyh.condition.LinuxCondition;
import com.cyh.condition.MyImportBeanDefinitionRegistrar;
import com.cyh.condition.MyImportSelector;
import com.cyh.condition.WindowsCondition;

/**
 * @Conditional Ҳ���Է������ϣ�����ͳһ��������
 */
//@Conditional(WindowsCondition.class)
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {

    //@Scope("prototype")
    @Lazy
    @Bean
    public Person person() {
        System.out.println("�����������Person....");
        return new Person("����", 25);
    }

    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    /**
     * ��������ע�������
     * 1������ɨ��+�����עע�⣨@Controller/@Service/@Repository/@Component��[�Լ�д����]
     * 2����@Bean[����ĵ���������������]
     * 3����@Import[���ٸ������е���һ�����]
     * 		1����@Import(Ҫ���뵽�����е����)�������оͻ��Զ�ע����������idĬ����ȫ����
     * 		2����ImportSelector:������Ҫ����������ȫ�������飻
     * 		3����ImportBeanDefinitionRegistrar:�ֶ�ע��bean��������
     * 4����ʹ��Spring�ṩ�� FactoryBean������Bean��
     * 		1����Ĭ�ϻ�ȡ�����ǹ���bean����getObject�����Ķ���
     * 		2����Ҫ��ȡ����Bean����������Ҫ��idǰ���һ��&
     * 			&colorFactoryBean
     */
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
