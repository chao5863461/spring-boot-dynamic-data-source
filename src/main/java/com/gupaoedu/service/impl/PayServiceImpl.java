package com.gupaoedu.service.impl;

import com.gupaoedu.datasource.DataSource;
import com.gupaoedu.datasource.DataSourceNames;
import com.gupaoedu.entity.User;
import com.gupaoedu.entity.UserPay;
import com.gupaoedu.mapper.UserMapper;
import com.gupaoedu.mapper.UserPayMapper;
import com.gupaoedu.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PayServiceImpl implements PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class.getName());

    private static AtomicInteger count = new AtomicInteger(0);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserPayMapper userPayMapper;

    @Autowired
    PayService payService;

    @Transactional
    @DataSource(name = DataSourceNames.SLAVE)
    public void pay(String userId) {
        logger.info("准备执行findUser4RequireNew方法");
        String userName = userId+ "_Name";
        User user = payService.findUser4RequireNew(userName);

        logger.info("从从库拿到了user对象");
        if (null == user)
        {
//            System.out.println("从从库中拿到的user为空");
            logger.info("从从库中拿到的user为空，返回");

        }else
        {
            logger.info("从从库中拿到了user：" + user.toString());
            logger.info("开始在主库中插入userPay记录!");
            //FIXME 打印SQL连接语句
            UserPay userPay1 = new UserPay();
            userPay1.setId(user.getId());
            userPay1.setName(user.getName());
            userPay1.setAmount(10);
            userPayMapper.addUserPay(userPay1);
            logger.info("在主库中插入userPay记录成功!");
        }
    }

    @Transactional
    @DataSource(name = DataSourceNames.SLAVE)
    public void pay2(String userId) {
        String userName = userId + "_Name";
        User user = payService.findUser4Require(userName);
        if (null == user)
        {
//            System.out.println("从从库中拿到的user为空");
            logger.info("从从库中拿到的user为空，返回");
        }else
        {
            logger.info("从从库中拿到了user：" + user.toString());
            logger.info("开始在主库中插入userPay记录!");
            //FIXME 打印SQL连接语句
            User user1 = new User();
            user1.setId("124");
            user1.setName("124Name");
            //插入到主库当中
            userMapper.addUser(user1);
        }
    }


    @Transactional
    @DataSource(name = DataSourceNames.SLAVE)
    @Override
    public void pay4() {


        //从从库里取用户信息
        User user = payService.findUser4RequireNew("123Name");
        if (null == user)
        {
//            System.out.println("从从库中拿到的user为空");
            logger.info("从从库中拿到的user为空");

        }else
        {
            logger.info("从从库中拿到了user：" + user.toString());
            logger.info("开始在主库中插入userPay记录!");
            //FIXME 打印SQL连接语句
            User user1 = new User();
            user1.setId(count.getAndIncrement()+"");
            user1.setName(count.getAndIncrement()+"Name");
            //插入到主库当中
            userMapper.addUser(user1);
//            System.out.println("从从库中拿到了user：" + user.toString());
        }
    }



    @Transactional
    //@DataSource(value = DataSourceType.MASTER)
    @Override
    public void pay3() {
        User user = payService.findUser3("123Name");
        if (null == user)
        {
//            System.out.println("从从库中拿到的user为空");
            logger.info("从从库中拿到的user为空，返回");
        }else
        {
            logger.info("从从库中拿到了user：" + user.toString());
            logger.info("开始在主库中插入userPay记录!");
            //FIXME 打印SQL连接语句
            User user1 = new User();
            user1.setId("124");
            user1.setName("124Name");
            //插入到主库当中
            userMapper.addUser(user1);
        }
    }

    @Override
    @DataSource(name = DataSourceNames.SLAVE)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User findUser4RequireNew(String name) {
        User user = userMapper.queryByName(name);
//        logger.info("findUser4RequireNew 方法执行完了，对应的dataType为{}", DynamicDataSourceContextHolder.getDateSoureType());
        logger.info("从从库中查询：findUser4RequireNew 方法执行完了，对应的dataType为{}");

        return user;
    }

    @Override
    @DataSource(name = DataSourceNames.SLAVE)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    public User findUser4Require(String name) {
        User user = userMapper.queryByName(name);
        return user;
    }

    @Override
    //@DataSource(value = DataSourceType.SLAVE)
    public User findUser3(String name) {
        User user = userMapper.queryByName(name);
        return user;
    }
}
