package com.tuling.zhouyuspring.zhouyu.service;


import com.tuling.zhouyuspring.spring.BeanPostProcessor;
import com.tuling.zhouyuspring.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 周瑜
 */
@Component
public class ZhouyuBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (beanName.equals("userService")) {
            Object proxyInstance = Proxy.newProxyInstance(ZhouyuBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 切面
                    System.out.println("切面逻辑");

                    return method.invoke(bean, args);
                }
            });

            return proxyInstance;
        }

        // bean
        return bean;
    }
}
