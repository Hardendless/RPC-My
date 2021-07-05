package top.wang.test;

import top.wang.rpc.registry.DefaultServiceRegistry;
import top.wang.rpc.registry.ServiceRegistry;
import top.wang.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        discussPostServiceImpl discussPostService = new discussPostServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(userService);
        serviceRegistry.register(discussPostService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.register(userService, 9000);
    }
}
