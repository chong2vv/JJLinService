package com.ry.time.client.controller;

import com.ry.time.client.model.vo.GoodsHomeDTO;
import com.ry.time.client.service.ClientGoodsService;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端商品相关接口
 *
 * @author gongjiguang
 * @date 2022/9/21
 */
@RestController
@RequestMapping("/client/goods/")
@RequiredArgsConstructor
public class ClientGoodsController {

    private final ClientGoodsService clientGoodsService;

    @RequestMapping(value = "/home/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHomeList(@RequestParam(value = "classify_id", required = false) Integer classifyId) {
        List<GoodsHomeDTO> goodsHomeList = clientGoodsService.getHomeList(classifyId);
        if (CollectionUtils.isEmpty(goodsHomeList)) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(goodsHomeList);
    }
}
