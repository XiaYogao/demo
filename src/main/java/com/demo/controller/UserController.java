package com.demo.controller;

import com.demo.base.BaseController;
import com.demo.model.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(User user) {
        userService.saveOrUpdate(user);
        return success();
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(User user) {
        List<User> users = userService.list(user);
        return success(users);
    }

    @RequestMapping(value = "{id}")
    public String get(@PathVariable(value = "id")Long id) {
        User user = userService.get(id);
        return success(user);
    }


}
