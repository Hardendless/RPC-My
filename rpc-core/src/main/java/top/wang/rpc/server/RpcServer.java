package top.wang.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wang.rpc.registry.ServiceRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;

    public RpcServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    public void register(Object server, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while ((socket =serverSocket.accept()) != null) {
                logger.info("客户端连接！Ip为：" + socket.getInetAddress());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
