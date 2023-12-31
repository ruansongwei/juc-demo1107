package com.tuling.zhouyuspring.zhouyu.service;


import com.tuling.zhouyuspring.spring.BeanPostProcessor;
import com.tuling.zhouyuspring.spring.Component;

import java.lang.reflect.Field;

/**
 * @author 周瑜
 */
@Component
public class ZhouyuValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ZhouyuValue.class)) {
                field.setAccessible(true);
                try {
                    field.set(bean, field.getAnnotation(ZhouyuValue.class).value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // bean
        return bean;
    }
}
