package com.itheima.spring.day03_eesy_02proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Producer producer = new Producer();
        IProducer proxyInstance = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        float money = (float) args[0];
                        if ("saleProduct".equals(method.getName())) {
                            result = method.invoke(producer, money * 0.8f);
                        }
                        return result;
                    }
                });
        proxyInstance.saleProduct(10000f);
    }
}
