package com.demo.service;

import com.demo.model.User;

import java.util.List;

/**
 * User service interface class
 *
 * @author sharygus@gmail.com
 * @date 2016/9/6 15:00
 * @since V1.0
 */
public interface IUserService {

    int saveOrUpdate(User user);

    int delete(Long id);

    List<User> list(User user);

    User get(Long id);

}
