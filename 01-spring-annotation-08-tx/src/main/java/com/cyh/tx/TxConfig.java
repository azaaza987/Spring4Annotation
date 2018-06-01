package com.cyh.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan("com.cyh.tx")
@EnableTransactionManagement
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhtest");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}

/**
 * �������
 * 1�������������
 * 		����Դ�����ݿ�������Spring-jdbcģ��
 * 2����������Դ��JdbcTemplate��Spring�ṩ�ļ����ݿ�����Ĺ��ߣ���������
 * 3���������ϱ�ע @Transactional ��ʾ��ǰ������һ�����񷽷�
 * 4��@EnableTransactionManagement ��������ע������������
 * 5�������������������������
 * 		@Bean
 * 		public PlatformTransactionManager transactionManager()
 *
 *
 * ԭ��
 * 1����@EnableTransactionManagement
 * 		���� TransactionManagementConfigurationSelector �������лᵼ�����
 * 		�����������
 * 		AutoProxyRegistrar
 * 		ProxyTransactionManagementConfiguration
 * 2����AutoProxyRegistrar
 * 		��������ע��һ�� InfrastructureAdvisorAutoProxyCreator ���������һ�� SmartInstantiationAwareBeanPostProcessor
 * 		���ú��ô����������ڶ��󴴽��Ժ󣬰�װ���󣬷���һ�����������ǿ�������������ִ�з������������������е���
 * 3����ProxyTransactionManagementConfiguration ����ʲô
 * 		��������ע��������ǿ��
* 		1����������ǿ��Ҫ������ע�����Ϣ��AnnotationTransactionAttributeSource ��������ע��
* 		2����������������
* 			TransactionInterceptor������������������Ϣ�����������
* 			����һ�� MethodInterceptor����Ŀ�귽��ִ�е�ʱ��ִ����������
* 			���˴�����������ֻ��һ������������������
* 				1�����Ȼ�ȡ������ص�����
* 				2�����ٻ�ȡ PlatformTransactionManager���������û������κ� transactionManager
* 					���ջ�������а������ͻ�ȡһ�� PlatformTransactionManager
* 				3����ִ��Ŀ�귽��
* 					����쳣����ȡ������������������������ع�������
* 					�������������������������ύ����
 */
