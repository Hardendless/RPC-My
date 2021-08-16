package top.wang.rpc.exception;

/**
 * 序列化异常
 * @author Hardendless
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}
