package top.wang.test;

import top.wang.rpc.api.HelloService;
import top.wang.rpc.api.UserService;
import top.wang.rpc.netty.server.NettyServer;
import top.wang.rpc.registry.DefaultServiceRegistry;
import top.wang.rpc.serializer.KryoSerializer;

public class NettyTestServer {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        HelloService helloService = new HelloServiceImpl();
        DefaultServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(userService);
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.setSerializer(new KryoSerializer());
        server.start(9999);
    }
}
