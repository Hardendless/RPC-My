package top.wang.rpc;

import top.wang.rpc.entity.RpcRequest;
import top.wang.rpc.serializer.CommonSerializer;

/**
 * 客户端类通用接口
 * @author Hardendless
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

    void setSerializer(CommonSerializer serializer);


}
