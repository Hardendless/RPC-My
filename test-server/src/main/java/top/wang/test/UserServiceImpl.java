package top.wang.test;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wang.rpc.api.User;
import top.wang.rpc.api.UserService;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUerByUserId(Integer id) {
        logger.info("客户端查询了{}的用户", id);
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        logger.info("插入数据成功：{}", user);
        return 1;
    }
}
