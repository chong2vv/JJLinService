package com.yd.JJLin.admin.model.entity;

import lombok.Data;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

/**
 * 消息信息
 *
 * @author wangyuandong
 * @date 2022-09-03
 */
@Data
public class Message  {

    /**
     * 信息id
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 主题
     */
    private String title;
    /**
     * 信息内容
     */
    private String content;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     *  时间戳
     */
    private Long timestamp;

    /**
     *  发件人
     */
    private String uid;


    public Long getTimestamp() {
        long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(createTime, new ParsePosition(0)).getTime();
        return time;
    }
}

