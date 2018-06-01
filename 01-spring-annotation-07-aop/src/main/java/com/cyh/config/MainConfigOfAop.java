package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.cyh.aop.LogAspects;
import com.cyh.aop.MathCalculator;

@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAop {

    @Bean
    public MathCalculator calculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}


/**
 * 1������aopģ�飻Spring AOP��(spring-aspects)
 * 2������һ��ҵ���߼��ࣨMathCalculator������ҵ���߼����е�ʱ����־���д�ӡ������֮ǰ���������н��������������쳣��xxx��
 * 3������һ����־�����ࣨLogAspects��������������ķ�����Ҫ��̬��֪MathCalculator.div���е�����Ȼ��ִ�У�
 * 		֪ͨ������
 * 			ǰ��֪ͨ(@Before)��logStart����Ŀ�귽��(div)����֮ǰ����
 * 			����֪ͨ(@After)��logEnd����Ŀ�귽��(div)���н���֮�����У����۷����������������쳣������
 * 			����֪ͨ(@AfterReturning)��logReturn����Ŀ�귽��(div)��������֮������
 * 			�쳣֪ͨ(@AfterThrowing)��logException����Ŀ�귽��(div)�����쳣�Ժ�����
 * 			����֪ͨ(@Around)����̬�����ֶ��ƽ�Ŀ�귽�����У�joinPoint.proceed()��
 * 4�����������Ŀ�귽����ע��ʱ�ε����У�֪ͨע�⣩
 * 5�����������ҵ���߼��ࣨĿ�귽�������ࣩ�����뵽������
 * 6���������Spring�ĸ�����������(���������ϼ�һ��ע�⣺@Aspect)
 * [7]�����������м� @EnableAspectJAutoProxy ����������ע���aopģʽ��
 * 		��Spring�кܶ�� @EnableXXX;
 *
 * ������
 * 	1������ҵ���߼�����������඼���뵽�����У�����Spring�ĸ��������ࣨ@Aspect��
 * 	2�������������ϵ�ÿһ��֪ͨ�����ϱ�ע֪ͨע�⣬����Spring��ʱ�ε����У��������ʽ��
 *  3������������ע���aopģʽ��@EnableAspectJAutoProxy
 *
 *
 * AOPԭ��������������ע����ʲô�����������ʲôʱ�������������Ĺ�����ʲô����
 * 		@EnableAspectJAutoProxy
 * 1��@EnableAspectJAutoProxy ��ʲô��
 * 		���������� @Import(AspectJAutoProxyRegistrar.class) �������е��� AspectJAutoProxyRegistrar
 * 			���� AspectJAutoProxyRegistrar �Զ����������ע��bean��BeanDefinition
 * 			internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator *
 * 		һ�仰���ǣ���������ע��һ�� AnnotationAwareAspectJAutoProxyCreator
 *
 * 2�� AnnotationAwareAspectJAutoProxyCreator �Ĳ�ι�ϵ��
 * 		AnnotationAwareAspectJAutoProxyCreator
 * 			->AspectJAwareAdvisorAutoProxyCreator
 * 				->AbstractAdvisorAutoProxyCreator
 * 					->AbstractAutoProxyCreator
 * 						->implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * 						��ע���ô���������bean��ʼ�����ǰ�������飩���Զ�װ��BeanFactory
 *
 *      ��̬��������Ҫ��ע����һЩ������
 *          AbstractAutoProxyCreator.setBeanFactory()
 *          AbstractAutoProxyCreator �к��ô��������߼�
 *          AbstractAdvisorAutoProxyCreator.setBeanFactory() --����--> initBeanFactory()
 *          AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 *
 * ���̣�
 * 		1�������������࣬����ioc����
 * 		2����ע�������࣬Ȼ���ٵ��� refresh����ˢ��������
 * 		3����registerBeanPostProcessors(beanFactory); ע��bean�ĺ��ô�����������bean�Ĵ�����
 * 			1�����Ȼ�ȡioc�����Ѿ������˵���Ҫ������������� BeanPostProcessor
 * 			2�����������мӱ�� BeanPostProcessor
 * 			3��������ע��ʵ���� PriorityOrdered �ӿڵ� BeanPostProcessor
 * 			4�����ٸ�������ע��ʵ���� Ordered �ӿڵ� BeanPostProcessor
 * 			5����ע��ûʵ�����ȼ��ӿڵ� BeanPostProcessor
 * 			6����ע�� BeanPostProcessor��ʵ���Ͼ��Ǵ��� BeanPostProcessor ���󲢱�����������
 * 				���� internalAutoProxyCreator �� BeanPostProcessor��AnnotationAwareAspectJAutoProxyCreator��
 * 				1��������Bean��ʵ��
 * 				2����populateBean() ��bean�ĸ������Ը�ֵ
 * 				3����initializeBean() ��ʼ��bean
 * 					1����invokeAwareMethods()������Aware�ӿڵķ����ص�
 * 					2����applyBeanPostProcessorsBeforeInitialization()��Ӧ�ú��ô������� postProcessBeforeInitialization()
 * 					3����invokeInitMethods()��ִ���Զ���ĳ�ʼ������
 * 					4����applyBeanPostProcessorsAfterInitialization()��ִ�к��ô������� postProcessAfterInitialization()
 * 				4����BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator) �����ɹ���--> aspectJAdvisorsBuilder
 * 			7������ BeanPostProcessor ע�ᵽ BeanFactory ��
 * 				ʵ�����ǵ��� beanFactory.addBeanPostProcessor(postProcessor) ����
 *
 *      =======�����Ǵ�����ע�� AnnotationAwareAspectJAutoProxyCreator �Ĺ���========
 *
 * 			AnnotationAwareAspectJAutoProxyCreator �̳��� InstantiationAwareBeanPostProcessor
 * 		4����finishBeanFactoryInitialization(beanFactory) ��� BeanFactory ��ʼ������������ʣ�µĵ�ʵ��bean
 * 			1����������ȡ���������е�Bean�����δ������� getBean(beanName)
 * 				getBean -> doGetBean() -> getSingleton()
 * 			2��������bean
 * 				��AnnotationAwareAspectJAutoProxyCreator ������bean����֮ǰ����һ�����أ�
 * 				 InstantiationAwareBeanPostProcessor ����� postProcessBeforeInstantiation()��
 * 				1�����ȴӻ����л�ȡ��ǰbean������ܻ�ȡ����˵��bean��֮ǰ���������ģ�ֱ��ʹ�ã������ٴ���
 * 					ֻҪ�����õ�Bean���ᱻ��������
 * 				2����createBean() ����bean
 * 					AnnotationAwareAspectJAutoProxyCreator �����κ�bean����֮ǰ�ȳ��Է���bean��ʵ��
 * 					��BeanPostProcessor ����Bean���󴴽���ɡ���ʼ��ǰ����õġ�
 * 					��InstantiationAwareBeanPostProcessor ���ڴ���Beanʵ��֮ǰ�ȳ����ú��ô��������ض���ġ�
 * 					1����resolveBeforeInstantiation(beanName, mbdToUse);����BeforeInstantiation
 * 						ϣ�����ô������ڴ��ܷ���һ�������������ܷ��ش�������ʹ�ã�������ܾͼ���
 * 						1�������ô������ȳ��Է��ض���
 * 							bean = applyBeanPostProcessorsBeforeInstantiation()
 * 							���õ����к��ô������������ InstantiationAwareBeanPostProcessor ��ִ�� postProcessBeforeInstantiation()��
 * 							if (bean != null) {
 * 							    bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 * 							}
 * 					2����doCreateBean(beanName, mbdToUse, args) ������ȥ����һ��beanʵ������3.6����һ����
 *
 *
 * AnnotationAwareAspectJAutoProxyCreator��InstantiationAwareBeanPostProcessor��	�����ã�
 * 1����ÿһ��bean����֮ǰ������ postProcessBeforeInstantiation()
 * 		���� MathCalculator �� LogAspect �Ĵ���
 * 		1�����жϵ�ǰbean�Ƿ��� advisedBeans �У�������������Ҫ��ǿbean��
 * 		2�����жϵ�ǰbean�Ƿ��ǻ������͵� Advice��Pointcut��Advisor��AopInfrastructureBean�������Ƿ������棨@Aspect��
 * 		3�����Ƿ���Ҫ����
 * 			1������ȡ��ѡ����ǿ�������������֪ͨ��������List<Advisor> candidateAdvisors��
 * 				ÿһ����װ��֪ͨ��������ǿ���� InstantiationModelAwarePointcutAdvisor
 * 				�ж�ÿһ����ǿ���Ƿ��� AspectJPointcutAdvisor ���͵ģ��Ǿͷ���true
 * 			2����������� super.shouldSkip����Զ����false
 * 2������������
 *      postProcessAfterInitialization()
 * 		return wrapIfNecessary(bean, beanName, cacheKey);//�����Ҫ��װ�������
 * 		1������ȡ��ǰbean��������ǿ����֪ͨ������  ��Object[] specificInterceptors��
 * 			1���ҵ����к�ѡ����ǿ��������Щ֪ͨ��������Ҫ���뵱ǰbean�����ģ�
 * 			2����ȡ������beanʹ�õ���ǿ��
 * 			3������ǿ������
 * 		2�������浱ǰbean�� advisedBeans �У���ʾ��ǰBean�Ѿ�����ǿ�������
 * 		3���������ǰbean��Ҫ��ǿ��������ǰbean�Ĵ������
 * 			1������ȡ������ǿ����֪ͨ������
 * 			2�������浽 proxyFactory
 * 			3���������������Spring�Զ�����
 * 				JdkDynamicAopProxy(config); jdk��̬����
 * 				ObjenesisCglibAopProxy(config); cglib�Ķ�̬����
 * 		4�����������з��ص�ǰ���ʹ��cglib��ǿ�˵Ĵ������
 * 		5�����Ժ������л�ȡ���ľ����������Ĵ������ִ��Ŀ�귽����ʱ�򣬴������ͻ�ִ��֪ͨ����������
 * 	3����Ŀ�귽��ִ��
 * 		�����б���������Ĵ������cglib��ǿ��Ķ��󣩣�����������汣������ϸ��Ϣ��������ǿ����Ŀ�����
 * 		1����CglibAopProxy.intercept() ����Ŀ�귽����ִ��
 * 		2�������� ProxyFactory �����ȡ��Ҫִ�е�Ŀ�귽����������
 * 			��List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);��
 * 			1����List<Object> interceptorList ����������������5����
 * 				һ��Ĭ�ϵ� ExposeInvocationInterceptor ���ĸ���ǿ��
 * 			2�����������е���ǿ��������תΪ Interceptor
 * 				��Ҫ�ǵ��� registry.getInterceptors(advisor) ����
 * 			3��������ǿ��תΪ List<MethodInterceptor>
 * 				����� MethodInterceptor��ֱ�Ӽ��뵽������
 * 				������ǣ�ʹ�� AdvisorAdapter ����ǿ��תΪ MethodInterceptor
 * 				ת����ɷ��� MethodInterceptor[]
 * 		3�������û������������ֱ��ִ��Ŀ�귽��
 * 			����������ÿһ��֪ͨ�����ֱ���װΪ���������������� MethodInterceptor ���ƣ�
 * 		4�����������������������Ҫִ�е�Ŀ�����Ŀ�귽����������������Ϣ���봴��һ�� CglibMethodInvocation ����
 * 			������ Object retVal =  mi.proceed()
 * 		5�������������Ĵ�������
 * 	        �������ǵ��� ReflectiveMethodInvocation#proceed() ������
 * 			1)�����û��������ִ��Ŀ�귽����������������������������������-1����ִ�е������һ��������������ֱ��ִ��Ŀ�귽��
 * 			2)����ʽ��ȡÿһ����������������ִ��invoke������ÿһ���������ȴ���һ��������ִ����ɷ����Ժ�����ִ��
 * 				���������Ļ��ƣ���֤֪ͨ������Ŀ�귽����ִ��˳��
 *
 * 	�ܽ᣺
 * 		1����@EnableAspectJAutoProxy ����AOP����
 * 		2����@EnableAspectJAutoProxy ���������ע��һ����� AnnotationAwareAspectJAutoProxyCreator
 * 		3����AnnotationAwareAspectJAutoProxyCreator ��һ�� InstantiationAwareBeanPostProcessor �ĺ��ô�����
 * 		4���������Ĵ������̣�
 * 			1����registerBeanPostProcessors() ע����ô����������� AnnotationAwareAspectJAutoProxyCreator ����
 * 			2����finishBeanFactoryInitialization() ��ʼ��ʣ�µĵ�ʵ��bean
 * 				1��������ҵ���߼�������������
 * 				2����AnnotationAwareAspectJAutoProxyCreator ��������Ĵ�������
 * 				3�������������֮���ж�����Ƿ���Ҫ��ǿ
 * 					����ǣ��������֪ͨ������װ����ǿ����Advisor������ҵ���߼��������һ���������cglib��
 * 		5����ִ��Ŀ�귽����
 * 			1�����������ִ��Ŀ�귽��
 * 			2����CglibAopProxy.intercept()
 * 				1�����õ�Ŀ�귽����������������ǿ����װ�������� MethodInterceptor��
 * 				2������������������ʽ���ƣ����ν���ÿһ������������ִ��
 * 				3����Ч����
 * 					����ִ�У�ǰ��֪ͨ -> Ŀ�귽�� -> ����֪ͨ -> ����֪ͨ
 * 					�����쳣��ǰ��֪ͨ -> Ŀ�귽�� -> ����֪ͨ -> �쳣֪ͨ
 */
