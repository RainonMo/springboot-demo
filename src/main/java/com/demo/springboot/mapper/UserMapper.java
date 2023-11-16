package com.demo.springboot.mapper;

import com.demo.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {

    Map<String,Object> getUserById(@Param("id") String id);

    void insert(User umsAdmin);

    Map<String, Object> getUserByUserName(@Param("username") String username);
}
