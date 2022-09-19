package com.ry.time.admin.controller;

import com.ry.time.admin.annotation.DownloadExcel;
import com.ry.time.admin.model.dto.GoodsForExcelDTO;
import com.ry.time.admin.service.GoodsService;
import com.ry.time.common.constant.enums.ResultErrorEnum;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

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

    @RequestMapping(value = "/goods/exportGoodsExcelFile" , method = RequestMethod.POST)
    @DownloadExcel(fileName = "GoodsExcel.xlsx")
    public String exportGoodsExcelFile(@RequestBody GoodsForExcelDTO goodsForExcelDTO) {
        try {
            System.out.print(goodsForExcelDTO.getGoods());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            XSSFWorkbook xssfWorkbook = goodsService.getGoodsTemplateExcel();
            xssfWorkbook.write(bos);
            byte[] bytes = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(bytes);
            return ResultGenerator.genSuccessResult(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
