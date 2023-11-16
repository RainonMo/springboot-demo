package com.demo.springboot.controller;

import com.demo.springboot.common.CommonResult;
import com.demo.springboot.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value = "测试接口", tags = "测试的接口")
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("测试接口")
    public String test(){
        return "hello springboot";
    }

    // 注意：这里@Autowired是报错的，因为@Autowired按照类名注入的
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("redis测试-Add")
    @PostMapping("/add")
    public CommonResult<User> add(User user) {
        redisTemplate.opsForValue().set(String.valueOf(user.getId()), user);
        return CommonResult.success(redisTemplate.opsForValue().get(String.valueOf(user.getId())));
    }

    /**
     * @return user list
     */
    @ApiOperation("redis测试-Find")
    @GetMapping("/find/{userId}")
    public CommonResult<User> edit(@PathVariable("userId") String userId) {
        return CommonResult.success(redisTemplate.opsForValue().get(userId));
    }

}
