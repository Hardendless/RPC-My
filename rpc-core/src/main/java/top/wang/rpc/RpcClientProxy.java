package top.wang.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wang.rpc.socket.client.SocketClient;
import top.wang.rpc.entity.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

public class RpcClientProxy implements InvocationHandler{

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private final RpcClient client;

    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    // jdk动态代理的过程，每一个代理对象调用方法，就会经过此方法增强（反射过去request对象，socket发送到客户端）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        logger.info("调用方法: {}#{}", method.getDeclaringClass().getName(), method.getName());
        //构建request，使用了lombok中的builder，代码简洁
        RpcRequest rpcRequest = new RpcRequest(UUID.randomUUID().toString(), method.getDeclaringClass().getName(), method.getName(),
                args, method.getParameterTypes());
        //数据传输，服务器返回结果
        return client.sendRequest(rpcRequest);
    }
}
