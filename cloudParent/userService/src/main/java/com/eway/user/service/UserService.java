package com.eway.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eway.user.mapper.UserMapper;
import com.eway.user.pojo.User;

/**
* @author 程龙
* @version 创建时间：2019年11月22日 下午7:47:53
* @ClassName 类名称：
* @Description 类描述：
*/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    public User queryById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
