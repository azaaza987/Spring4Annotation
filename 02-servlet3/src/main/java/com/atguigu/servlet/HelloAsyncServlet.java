package com.atguigu.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1��֧���첽����asyncSupported=true
@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out
            .println("���߳̿�ʼ������" + Thread.currentThread() + "==>" + System.currentTimeMillis());
        //2�������첽ģʽ
        AsyncContext startAsync = req.startAsync();

        //3��ҵ���߼������첽����;��ʼ�첽����
        startAsync.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(
                        "���߳̿�ʼ������" + Thread.currentThread() + "==>" + System.currentTimeMillis());
                    sayHello();
                    /**
                     * java.lang.IllegalStateException:
                     * It is illegal to call this method
                     * if the current request is not in asynchronous mode
                     * (i.e. isAsyncStarted() returns false)
                     */
                    /////ע�͵���仰������ᱨ��������////////////////////startAsync.complete();

                    //��ȡ���첽������
                    AsyncContext asyncContext = req.getAsyncContext();
                    //4����ȡ��Ӧ
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().write("Async request success");
                    System.out.println(
                        "���߳̽���������" + Thread.currentThread() + "==>" + System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out
            .println("���߳̽���������" + Thread.currentThread() + "==>" + System.currentTimeMillis());
    }

    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread() + " sayHello() processing...");
        Thread.sleep(3000);
    }
}
