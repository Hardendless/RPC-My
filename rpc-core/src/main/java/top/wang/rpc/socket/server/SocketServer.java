package top.wang.rpc.socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wang.rpc.RequestHandler;
import top.wang.rpc.RpcServer;
import top.wang.rpc.enumeration.RpcError;
import top.wang.rpc.exception.RpcException;
import top.wang.rpc.registry.ServiceRegistry;
import top.wang.rpc.serializer.CommonSerializer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class SocketServer implements RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private CommonSerializer serializer;

    private final ServiceRegistry serviceRegistry;

    public SocketServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }


    @Override
    public void start(int port) {
        if(serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while ((socket =serverSocket.accept()) != null) {
                logger.info("客户端连接！Ip为：" + socket.getInetAddress());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry, serializer));
            }
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }

    @Override
    public void setSerializer(CommonSerializer serializer) {
        this.serializer = serializer;
    }
}
