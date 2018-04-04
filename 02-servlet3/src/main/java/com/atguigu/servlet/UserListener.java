package com.atguigu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ������Ŀ��������ֹͣ
 * @author lfy
 *
 */
public class UserListener implements ServletContextListener {

    //����ServletContext������ʼ��
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        System.out.println("UserListener...contextInitialized...");
    }

    //����ServletContext����
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("UserListener...contextDestroyed...");
    }

}
