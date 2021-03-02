package com.gupaoedu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.entity.UserPay;

public interface UserPayMapper extends BaseMapper<UserPay> {

    int addUserPay(UserPay userPay);

    UserPay queryByName(String name);
}
