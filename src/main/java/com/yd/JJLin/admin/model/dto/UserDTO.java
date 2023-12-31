package com.yd.JJLin.admin.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户展示层对象
 *
 * @author wangyuandong
 * @date 2022/9/4
 */
@Data
public class UserDTO {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 权限身份
     */
    private List<String> roles;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户状态 -1:删除 0:冻结 1:正常
     */
    private Integer status;

    /**
     * 用户id strubf
     */
    private String uid;
}
