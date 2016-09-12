package com.demo.service.impl;

import com.demo.dao.IUserDao;
import com.demo.model.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiayongao@gmail.com
 * @date 2016/9/13 0013 上午 00:03
 * @since V1.0
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public int saveOrUpdate(User user) {
        if(null == user.getId()){
            return userDao.insert(user);
        }
        return userDao.update(user);
    }

    @Override
    public int delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> list(User user) {
        return userDao.list(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }
}
