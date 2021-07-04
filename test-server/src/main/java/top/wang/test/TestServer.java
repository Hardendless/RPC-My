package top.wang.test;

import top.wang.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(userService, 9000);
    }
}
