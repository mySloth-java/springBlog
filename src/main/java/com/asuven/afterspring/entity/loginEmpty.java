package com.asuven.afterspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//登录用户实体类
public class loginEmpty {
    private Integer loginId;
    private String loginName;
//    @JsonIgnore
    //前端页面不展示密码，没必要加，加了后端密码无法添加
    private String loginPassword;
    private String loginStatus;
    private Date loginDate;

    //下列属性用于接受前端数据存储，不需要存入数据库
    private List<menuEmpty> menu;

}
