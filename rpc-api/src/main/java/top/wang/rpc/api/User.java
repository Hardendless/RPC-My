package top.wang.rpc.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class User implements Serializable {
    //客户端和服务器端共有的
    private Integer id;
    private String userName;
    private Boolean sex;
}
