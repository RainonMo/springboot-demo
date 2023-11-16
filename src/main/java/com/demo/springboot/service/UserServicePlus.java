package com.demo.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.springboot.model.User;

public interface UserServicePlus extends IService<User> {
    User findList();

}
