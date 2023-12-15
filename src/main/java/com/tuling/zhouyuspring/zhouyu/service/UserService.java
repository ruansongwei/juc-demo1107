package com.tuling.zhouyuspring.zhouyu.service;


import com.tuling.zhouyuspring.spring.Autowired;
import com.tuling.zhouyuspring.spring.BeanNameAware;
import com.tuling.zhouyuspring.spring.Component;

/**
 * @author 周瑜
 */
@Component
public class UserService implements UserInterface, BeanNameAware {

    @Autowired
    private OrderService orderService;

    @ZhouyuValue("xxx")
    private String test;


    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void test() {
        System.out.println(beanName);
    }
}
