package com.tuling.zhouyuspring.zhouyu;


import com.tuling.zhouyuspring.spring.ZhouyuApplicationContext;
import com.tuling.zhouyuspring.zhouyu.service.UserInterface;
import com.tuling.zhouyuspring.zhouyu.service.UserService;

/**
 * @author 周瑜
 */
public class Test {

    public static void main(String[] args) {

        // 扫描--->创建单例Bean BeanDefinition BeanPostPRocess
        ZhouyuApplicationContext applicationContext = new ZhouyuApplicationContext(AppConfig.class);

//        UserInterface userService = (UserInterface) applicationContext.getBean("userService");
      UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
