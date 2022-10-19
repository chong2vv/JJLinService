package com.yd.kuma.admin.model.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户信息
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 权限身份
     */
    private String role;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户状态 -1:删除 0:冻结 1:正常
     */
    private Integer status;
    /**
     * 权限身份集合
     */
    private List<String> roles;

}
