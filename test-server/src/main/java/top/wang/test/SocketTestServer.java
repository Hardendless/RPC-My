package top.wang.test;

import top.wang.rpc.api.HelloService;
import top.wang.rpc.registry.DefaultServiceRegistry;
import top.wang.rpc.registry.ServiceRegistry;
import top.wang.rpc.serializer.HessianSerializer;
import top.wang.rpc.socket.server.SocketServer;

public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.setSerializer(new HessianSerializer());
        socketServer.start(9000);
    }
}
