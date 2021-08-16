package top.wang.rpc;

import top.wang.rpc.entity.RpcRequest;

/**
 * 客户端类通用接口
 * @author Hardendless
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

}
