package com.ry.time.admin.controller;

import com.ry.time.admin.model.dto.GoodsDTO;
import com.ry.time.admin.model.entity.Goods;
import com.ry.time.admin.model.vo.GoodsPagerRequestVO;
import com.ry.time.admin.service.GoodsService;
import com.ry.time.common.constant.enums.ResultErrorEnum;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String getClassifyList(GoodsPagerRequestVO goodsPagerRequestVO) {
        List<GoodsDTO> classifyList = goodsService.getGoodsList(goodsPagerRequestVO);
        int count = goodsService.count(goodsPagerRequestVO.getStatus());
        return ResultGenerator.genSuccessPager(classifyList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Integer id) {
        GoodsDTO goods = goodsService.queryByGoodsd(id);
        return ResultGenerator.genSuccessResult(goods);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody Goods goods) {
        boolean existClassify = goodsService.existByGoodsId(goods.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        goodsService.update(goods);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/op", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody Goods goods) {
        boolean existClassify = goodsService.existByGoodsId(goods.getId());
        if (!existClassify) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        goodsService.updateStatus(goods);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Goods goods) {
        return ResultGenerator.genSuccessResult(goodsService.create(goods));
    }

    @RequestMapping(value = "/downloadExcelFile" , method = RequestMethod.POST)
    @DownloadExcel(fileName = "goods.xls")
    public HSSFWorkbook downloadExcel() {
        try {
            return goodsService.queryAllRedisOcpm(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
