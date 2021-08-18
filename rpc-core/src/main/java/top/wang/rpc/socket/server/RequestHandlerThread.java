package top.wang.rpc.socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wang.rpc.RequestHandler;
import top.wang.rpc.entity.RpcRequest;
import top.wang.rpc.entity.RpcResponse;
import top.wang.rpc.registry.ServiceRegistry;
import top.wang.rpc.serializer.CommonSerializer;
import top.wang.rpc.socket.util.ObjectReader;
import top.wang.rpc.socket.util.ObjectWriter;

import java.io.*;
import java.net.Socket;

/**
 * 处理RpcRequest的工作线程
 * @author Hardendless
 */
public class RequestHandlerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;
    private CommonSerializer serializer;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry, CommonSerializer serializer) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
        this.serializer = serializer;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            RpcRequest rpcRequest = (RpcRequest) ObjectReader.readObject(inputStream);
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handle(rpcRequest, service);
            RpcResponse<Object> response = RpcResponse.success(result);
            ObjectWriter.writeObject(outputStream, response, serializer);
        } catch (IOException e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }

}
