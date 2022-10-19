package com.yd.kuma.client.controller;

import com.yd.kuma.admin.model.dto.GoodsDTO;
import com.yd.kuma.admin.model.vo.GoodsPagerRequestVO;
import com.yd.kuma.client.model.vo.GoodsHomeDTO;
import com.yd.kuma.client.service.ClientGoodsService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import com.yd.kuma.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getList(@RequestParam Map<String, Object> map) {
        GoodsPagerRequestVO goodsPagerRequestVO = JsonUtil.mapToObj(map, GoodsPagerRequestVO.class);
        goodsPagerRequestVO.initPager();
        List<GoodsHomeDTO> goodsHomeList = clientGoodsService.getList(goodsPagerRequestVO);
        int count = clientGoodsService.count(goodsPagerRequestVO.getStatus());
        return ResultGenerator.genSuccessPager(goodsHomeList, count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Long id) {
        GoodsDTO goods = clientGoodsService.queryByGoodsId(id);
        if (goods == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.GOODS_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(goods);
    }
}
