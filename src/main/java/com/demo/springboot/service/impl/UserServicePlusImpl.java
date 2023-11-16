package com.demo.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.springboot.mapper.UserDao;
import com.demo.springboot.model.User;
import com.demo.springboot.service.UserServicePlus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServicePlusImpl extends ServiceImpl<UserDao, User>
        implements UserServicePlus {


    @Resource
    private UserDao userDao;

    @Override
    public User findList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", "1");
        User user = userDao.selectOne(queryWrapper);
        return user;
    }
}
