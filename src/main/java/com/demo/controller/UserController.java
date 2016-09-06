package com.demo.controller;

import com.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户控制器
 *
 * @author yonggao.xia@56qq.com
 * @date 2016/9/6 18:08
 * @since V1.0
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(User user){
        System.out.println("ADD USER:" + user);
        return "index";
    }

}
