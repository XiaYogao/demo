package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 *
 * @author sharygus@gmail.com
 * @date 2016/9/6 14:39
 * @since V1.0
 */
@Controller
@RequestMapping({"/demo", "/"})
public class DemoController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
