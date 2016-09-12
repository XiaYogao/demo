package com.demo.controller;

import com.demo.base.BaseController;
import com.demo.model.User;
import com.demo.service.IUserService;
import com.demo.utils.MD5EncryptUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        String encryptPwd = MD5EncryptUtil.md5(user.getPassword());
        user.setPassword(encryptPwd);
        userService.saveOrModify(user);
        return success();
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(User user) {
        List<User> users = userService.find(user);
        return success(users);
    }

    @RequestMapping(value = "{id}")
    public String get(@PathVariable(value = "id") Long id) {
        User user = userService.get(id);
        return success(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request) {
        User user = new User();
        user.setUsername(username);
        List<User> users = userService.find(user);
        if (CollectionUtils.isEmpty(users)) {
            return error("user not exist");
        }

        User sessionUser = users.get(0);
        if (!sessionUser.getPassword().equals(MD5EncryptUtil.md5(password))) {
            return error("password invalid");
        }
        request.getSession().setAttribute("SESSION_USER", sessionUser);
        return success("login success");
    }


}
