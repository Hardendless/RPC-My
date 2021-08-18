package top.wang.test;

import top.wang.rpc.api.*;
import top.wang.rpc.RpcClientProxy;
import top.wang.rpc.serializer.KryoSerializer;
import top.wang.rpc.socket.client.SocketClient;

public class SocketTestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9000);
        client.setSerializer(new KryoSerializer());
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
