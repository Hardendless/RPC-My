package top.wang.test;

import top.wang.rpc.api.User;
import top.wang.rpc.api.UserService;
import top.wang.rpc.client.RpcClientProxy;

public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        UserService userProxy = proxy.getProxy(UserService.class);

        User userByUserId = userProxy.getUerByUserId(10);
        System.out.println("从服务器端得到的user为：" + userByUserId);

        User user = User.builder().userName("王宁").id(100).sex(true).build();
        Integer integer = userProxy.insertUserId(user);
        System.out.println("向数据库中插入数据：" + integer);
    }
}
