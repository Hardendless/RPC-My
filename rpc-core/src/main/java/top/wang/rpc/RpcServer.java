package top.wang.rpc;

import top.wang.rpc.serializer.CommonSerializer;

/**
 * 服务器类通用接口
 * @author Hardendless
 */
public interface RpcServer {

    void start(int port);

    void setSerializer(CommonSerializer serializer);


}
