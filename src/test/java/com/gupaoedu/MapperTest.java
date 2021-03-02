package com.gupaoedu;


import com.gupaoedu.datasource.DataSource;
import com.gupaoedu.datasource.DataSourceNames;
import com.gupaoedu.entity.User;
import com.gupaoedu.mapper.UserMapper;
import com.gupaoedu.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DynamicDSApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
public class MapperTest {

    private static final Logger logger = LoggerFactory.getLogger(MapperTest.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderService orderService;

    @Test
    public void testPlaceOrder()
    {
        orderService.placeOrder("992");
    }
//
//    @Test
//    public void testPlaceOrder1()
//    {
//        orderService.placeOrder1();
//    }

    @Test
    public void testPlaceOrder2()
    {
        orderService.placeOrder2("990");
    }

//    @Test
//    public void testPlaceOrder3()
//    {
//        orderService.placeOrder3();
//    }
//
    @Test
    public void testPlaceOrder4()
    {
        orderService.placeOrder4();
    }
//
//    /**
//     */
//    @Test
//    public void testInsertUserWithMapper()
//    {
//        User user = new User();
//        user.setId("128");
//        user.setName("123Name");
//        userMapper.addUser(user);
//    }

    @Test
    @Transactional
    @DataSource(name = DataSourceNames.SLAVE)
    public void testInsertUserWithMapperToSlave()
    {
        User user = new User();
        user.setId("123");
        user.setName("123Name");
        userMapper.addUser(user);

        user.setId("124");
        user.setName("124Name");
        userMapper.addUser(user);
    }
//
//    @Test
//    public void testQueryUserWithMapper()
//    {
//        User user = userMapper.queryByName("123Name");
//        System.out.println(user.toString());
//    }

}
