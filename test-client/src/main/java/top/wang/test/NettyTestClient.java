package top.wang.test;

import top.wang.rpc.RpcClient;
import top.wang.rpc.RpcClientProxy;
import top.wang.rpc.api.HelloObject;
import top.wang.rpc.api.HelloService;
import top.wang.rpc.api.User;
import top.wang.rpc.api.UserService;
import top.wang.rpc.netty.client.NettyClient;

public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is Hello");
        String res = helloService.hello(object);
        System.out.println(res);

    }
}
