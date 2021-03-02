package com.gupaoedu.service;


import com.gupaoedu.entity.User;

public interface PayService {
    public void pay(String userId);

    public void pay2(String userId);

    public void pay3();

    public void pay4();

    public User findUser4RequireNew(String name);

    public User findUser4Require(String name);

    public User findUser3(String name);
}
