package com.demo.controller;

import com.demo.base.BaseController;
import com.demo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author yoo.xia@56qq.com
 * @date 2016/9/6 18:08
 * @since V1.0
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController extends BaseController {

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(User user) {
        System.out.println("ADD USER:" + user);
        return "index";
    }

}
