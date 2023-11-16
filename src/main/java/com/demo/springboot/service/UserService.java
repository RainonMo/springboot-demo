package com.demo.springboot.service;

import com.demo.springboot.model.User;
import com.demo.springboot.model.dto.UserParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {
    Map<String, Object> getUserById(String id);

    User register(UserParam userParam);

    Map<String, Object> getUserByUserName(String username);

    String login(String username, String password);

    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取后台管理员
     */
    User getAdminByUsername(String username);
}
