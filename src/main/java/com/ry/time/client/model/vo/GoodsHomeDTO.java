package com.ry.time.client.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 商品首页信息
 *
 * @author gongjiguang
 * @date 2022/9/21
 */
@Data
public class GoodsHomeDTO {

    private Long id;

    private String title;

    @JsonProperty("cover_img")
    private String coverImg;

    @JsonProperty("classify_id")
    private Integer classifyId;


}
