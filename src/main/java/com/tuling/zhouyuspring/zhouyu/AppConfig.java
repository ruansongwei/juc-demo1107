package com.tuling.zhouyuspring.zhouyu;


import com.tuling.zhouyuspring.spring.Autowired;
import com.tuling.zhouyuspring.spring.ComponentScan;
import com.tuling.zhouyuspring.zhouyu.service.UserService;

/**
 * @author 周瑜
 */

@ComponentScan("com.tuling.zhouyuspring.zhouyu")
public class AppConfig {
    @Autowired
    private UserService userService;

}
