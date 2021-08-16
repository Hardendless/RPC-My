package top.wang.test;

import top.wang.rpc.registry.DefaultServiceRegistry;
import top.wang.rpc.registry.ServiceRegistry;
import top.wang.rpc.socket.server.SocketServer;

public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        discussPostServiceImpl discussPostService = new discussPostServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(userService);
        serviceRegistry.register(discussPostService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9000);
    }
}
