package top.wang.rpc.api;

public interface UserService {
    User getUerByUserId(Integer id);
    //增加一个插入的功能
    Integer insertUserId(User user);
}
