package com.demo.springboot.controller;

import com.demo.springboot.common.CommonResult;
import com.demo.springboot.common.ReturnJson;
import com.demo.springboot.model.User;
import com.demo.springboot.model.dto.UserLoginParam;
import com.demo.springboot.model.dto.UserParam;
import com.demo.springboot.service.UserService;
import com.demo.springboot.service.UserServicePlus;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<User> register(@Validated @RequestBody UserParam userParam) {
        User umsAdmin = userService.register(userParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }


    @GetMapping("/{username}")
    public CommonResult<Map<String,Object>> getUserByUserName(@PathVariable String username){
        Map<String,Object> data =  userService.getUserByUserName(username);
        return CommonResult.success(data);

    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UserLoginParam userLoginParam) {
        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }


    @Resource
    private UserServicePlus userServicePlus;

    @GetMapping("/test")
    @ApiOperation(value = "测试查询")
    public ReturnJson test(){
        return ReturnJson.ok(userServicePlus.findList());
    }
}
