package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cyh.bean.Car;

/**
 * bean���������ڣ�
 * 		bean����---��ʼ��----���ٵĹ���
 * ��������bean���������ڣ�
 * ���ǿ����Զ����ʼ�������ٷ�����������bean���е���ǰ�������ڵ�ʱ�������������Զ���ĳ�ʼ�������ٷ���
 *
 * ���죨���󴴽���
 * 		��ʵ����������������ʱ�򴴽�����
 * 		��ʵ������ÿ�λ�ȡ��ʱ�򴴽�����
 *
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 * ��ʼ����
 * 		���󴴽���ɣ�����ֵ�ã����ó�ʼ������
 * BeanPostProcessor.postProcessAfterInitialization
 * ���٣�
 * 		��ʵ���������رյ�ʱ��
 * 		��ʵ������������������bean����������������ٷ���
 *
 *
 * �����õ����������е�BeanPostProcessor������ִ��beforeInitialization��
 * һ������null������forѭ��������ִ�к����postProcessorsBeforeInitialization
 *
 * BeanPostProcessorԭ��:
 * 1��populateBean(beanName, mbd, instanceWrapper);��bean�������Ը�ֵ
 * 2��initializeBean
 *     1��applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *     2��invokeInitMethods(beanName, wrappedBean, mbd);ִ���Զ����ʼ������
 *     3��applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *
 *
 * 1����ָ����ʼ�������ٷ�����ͨ�� @Bean ָ�� init-method �� destroy-method
 * 2����ͨ���� Bean ʵ�� InitializingBean�������ʼ���߼�����DisposableBean�����������߼���
 * 3��������ʹ��JSR250
 * 		@PostConstruct����bean������ɲ������Ը�ֵ��ɣ���ִ�г�ʼ������
 * 		@PreDestroy������������bean֮ǰ֪ͨ���ǽ���������
 * 4����BeanPostProcessor��������Ҫ�������£�
 * 		postProcessBeforeInitialization:
 * 	        �ڳ�ʼ��֮ǰ����
 * 	        before InitializingBean#afterPropertiesSet or a custom init-method
 * 	        ��⣺����������֮ǰ����
 * 		postProcessAfterInitialization:
 * 		    �ڳ�ʼ��֮����
 * 		    after InitializingBean#afterPropertiesSet or a custom init-method
 * 		    ��⣺����������֮�����
 *
 * Spring�ײ�� BeanPostProcessor ��ʹ�ã�
 * 		bean��ֵ��ע�����������@Autowired����������ע�⹦�ܣ�@Async, xxxBeanPostProcessor;
 * 	    AutowiredAnnotationBeanPostProcessor
 */
@Configuration
@ComponentScan("com.cyh.bean")
public class MainConfigOfLifeCycle {

    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
