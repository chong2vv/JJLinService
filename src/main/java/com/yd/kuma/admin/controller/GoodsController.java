package com.yd.kuma.admin.controller;

import com.yd.kuma.admin.model.dto.GoodsDTO;
import com.yd.kuma.admin.model.vo.GoodsPagerRequestVO;
import com.yd.kuma.admin.service.GoodsService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import com.yd.kuma.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @author gongjiguang
 * @date 2022/9/13
 */
@RestController
@RequestMapping("/vue-admin-template/goods/")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGoodsList(@RequestParam Map<String, Object> map) {
        GoodsPagerRequestVO goodsPagerRequestVO = JsonUtil.mapToObj(map, GoodsPagerRequestVO.class);
        List<GoodsDTO> classifyList = goodsService.getGoodsList(goodsPagerRequestVO);
        int count = goodsService.count(goodsPagerRequestVO.getStatus());
        return ResultGenerator.genSuccessPager(classifyList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Long id) {
        GoodsDTO goods = goodsService.queryByGoodsId(id);
        if (goods == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.GOODS_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(goods);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody GoodsDTO goodsDTO) {
        boolean existClassify = goodsService.existByGoodsId(goodsDTO.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.GOODS_EXISTS_ERROR);
        }
        goodsService.update(goodsDTO);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/op", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody GoodsDTO goodsDTO) {
        boolean existClassify = goodsService.existByGoodsId(goodsDTO.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.GOODS_EXISTS_ERROR);
        }
        goodsService.updateStatus(goodsDTO);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody GoodsDTO goodsDTO) {
        return ResultGenerator.genSuccessResult(goodsService.create(goodsDTO));
    }

}
