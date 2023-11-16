package com.demo.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.springboot.exception.Asserts;
import com.demo.springboot.mapper.UserDao;
import com.demo.springboot.mapper.UserMapper;
import com.demo.springboot.model.User;
import com.demo.springboot.model.dto.UserParam;
import com.demo.springboot.security.AdminUserDetails;
import com.demo.springboot.security.util.JwtTokenUtil;
import com.demo.springboot.security.util.SpringUtil;
import com.demo.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl  extends ServiceImpl<UserDao, User>  implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public Map<String,Object> getUserById(@PathVariable String id) {

        return userMapper.getUserById(id);

    }

    @Override
    public User register(UserParam userParam) {
        User umsAdmin = new User();
        BeanUtils.copyProperties(userParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
//        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
//        if (umsAdminList.size() > 0) {
//            return null;
//        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        userMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public Map<String, Object> getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        User admin = getAdminByUsername(username);
        if (admin != null) {
//            List<UmsResource> resourceList = getResourceList(admin.getId());
//            return new AdminUserDetails(admin,resourceList);
            return new AdminUserDetails(admin);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public User getAdminByUsername(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        User admin = (User) redisTemplate.opsForValue().get(key);
        System.out.println("redis取值："+admin);
        if(admin!=null) return  admin;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername,username);
        List<User> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
            redisTemplate.opsForValue().set(key, admin, REDIS_EXPIRE, TimeUnit.SECONDS);
            return admin;
        }
        return null;
    }

//    @Override
//    public UmsAdminCacheService getCacheService() {
//        return SpringUtil.getBean(UmsAdminCacheService.class);
//    }
}
