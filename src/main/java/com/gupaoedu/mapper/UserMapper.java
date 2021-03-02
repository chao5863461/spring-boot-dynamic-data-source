package com.gupaoedu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.entity.User;

public interface UserMapper extends BaseMapper<User> {

    int addUser(User user);

    User queryByName(String name);
}
