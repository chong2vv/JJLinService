package com.yd.JJLin.admin.model.dto;

import lombok.Data;

@Data
public class ProjectArchiveDTO {
    /**
     * 文章数
     */
    private Integer count;
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String month;
}
