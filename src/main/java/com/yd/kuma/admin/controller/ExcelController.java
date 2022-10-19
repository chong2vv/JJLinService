package com.yd.kuma.admin.controller;

import com.yd.kuma.admin.annotation.DownloadExcel;
import com.yd.kuma.admin.model.dto.GoodsForExcelDTO;
import com.yd.kuma.admin.service.GoodsService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

/**
 * Excel相关的控制器
 *
 * @author gongjiguang
 * @date 2022/9/18
 */
@Controller
@RequestMapping("/vue-admin-template/")
@RequiredArgsConstructor
public class ExcelController {

    private final GoodsService goodsService;

    @RequestMapping(value = "/goods/downloadExcelFile" , method = RequestMethod.GET)
    @DownloadExcel(fileName = "goods_template.xlsx")
    public XSSFWorkbook downloadExcel() {
        try {
            return goodsService.getGoodsTemplateExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/goods/exportExcelFile" , method = RequestMethod.GET)
    @DownloadExcel()
    public XSSFWorkbook downloadExcel(@RequestParam String fileName) {
        try {
            return goodsService.getGoodsExcel(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/goods/exportGoodsExcelFile" , method = RequestMethod.POST)
    @ResponseBody
    public String exportGoodsExcelFile(@RequestBody GoodsForExcelDTO goodsForExcelDTO) {
        try {
            String name = goodsService.getGoodsExportExcel(goodsForExcelDTO.getGoods());
            if (StringUtils.isNotBlank(name)){
                return ResultGenerator.genSuccessResult(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(ResultErrorEnum.EXPORT_GOODS_ERROR);
    }

    @RequestMapping(value = "/goods/uploadExcelFile" , method = RequestMethod.POST)
    @ResponseBody
    public String uploadExcel(MultipartHttpServletRequest multipartRequest) {
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile file = fileMap.values()
                .stream()
                .findFirst()
                .orElse(null);
        if (file == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.FILE_ERROR);
        }
        try {
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(file.getInputStream());
            goodsService.uploadGoodsExcel(hssfWorkbook);
        }catch (Exception e) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.FILE_ERROR);
        }
        return ResultGenerator.genSuccessResult();
    }
}
