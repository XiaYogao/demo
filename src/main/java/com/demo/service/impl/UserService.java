package com.demo.service.impl;

import com.demo.base.BaseService;
import com.demo.base.IBaseDao;
import com.demo.dao.IUserDao;
import com.demo.model.User;
import com.demo.service.IUserService;
import com.demo.utils.MD5EncryptUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiayongao@gmail.com
 * @date 2016/9/13 0013 上午 00:03
 * @since V1.0
 */
@Service
public class UserService extends BaseService<User, Long> implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    protected IBaseDao<User, Long> getDao() {
        return userDao;
    }


}
