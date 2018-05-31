package com.cyh.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.cyh.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @Profile��ָ��������ĸ�����������²��ܱ�ע�ᵽ�����У���ָ�����κλ����¶���ע��������
 *
 * 1�������˻�����ʶ�� bean ֻ����������������ʱ�����ע�ᵽ�����С�Ĭ���� default ����
 * 2����д���������ϣ�ֻ����ָ���Ļ�����ʱ������������������������ò��ܿ�ʼ��Ч
 * 3����û�б�ע������ʶ��bean���κλ����¶��Ǽ��ص�
 */
//@Profile("test") // ֻ�е�����Ϊtest��ʱ���������������Ч
@Configuration
@PropertySource("classpath:/dbconfig.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    // ��ʽһ
    @Value("${db.user}")
    private String username;
    private String driverClass;

    /**
     * û�м� @Profile �������κλ����¶���Ч
     */
    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    /**
     * ��ʽ�����ڷ��������ʹ�� @Value
     */
    @Profile("test")
    @Bean
    public DataSource dataSourceTest(@Value("${db.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhtest");
        return dataSource;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev(@Value("${db.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhdev");
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public DataSource dataSourceProd(@Value("${db.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhprod");
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        // ������
        driverClass = resolver.resolveStringValue("${db.driverClass}");
    }
}
