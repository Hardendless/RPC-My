package top.wang.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    //客户端和服务器端共有的
    private Integer id;
    private String userName;
    private Boolean sex;
}
