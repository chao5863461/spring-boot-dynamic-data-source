# 本工程可以干啥
1. 动态数据源的工程，可以演示Spring的事务隔离级别源码和AOP源码/OrderService ->  payService -> userMapper可以演示Spring IOC/

# 测试 入口
1. MapperTest.testPlaceOrder()
2. MapperTest.testPlaceOrder2();
3. 修改默认数据源，如果上面加了@transaction就不行