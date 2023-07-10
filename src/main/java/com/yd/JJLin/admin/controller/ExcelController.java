package com.yd.JJLin.admin.controller;

import com.yd.JJLin.admin.annotation.DownloadExcel;
import com.yd.JJLin.admin.model.dto.ProjectForExcelDTO;
import com.yd.JJLin.admin.service.ProjectService;
import com.yd.JJLin.common.constant.enums.ResultErrorEnum;
import com.yd.JJLin.common.model.ResultGenerator;
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
 * @author wangyuandong
 * @date 2022/9/18
 */
@Controller
@RequestMapping("/vue-admin-template/")
@RequiredArgsConstructor
public class ExcelController {

    private final ProjectService projectService;

    @RequestMapping(value = "/project/downloadExcelFile" , method = RequestMethod.GET)
    @DownloadExcel(fileName = "project_template.xlsx")
    public XSSFWorkbook downloadExcel() {
        try {
            return projectService.getProjectTemplateExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/project/exportExcelFile" , method = RequestMethod.GET)
    @DownloadExcel()
    public XSSFWorkbook downloadExcel(@RequestParam String fileName) {
        try {
            return projectService.getProjectExcel(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/project/exportProjectExcelFile" , method = RequestMethod.POST)
    @ResponseBody
    public String exportProjectExcelFile(@RequestBody ProjectForExcelDTO projectForExcelDTO) {
        try {
            String name = projectService.getProjectExportExcel(projectForExcelDTO.getProject());
            if (StringUtils.isNotBlank(name)){
                return ResultGenerator.genSuccessResult(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(ResultErrorEnum.EXPORT_PROJECT_ERROR);
    }

    @RequestMapping(value = "/project/uploadExcelFile" , method = RequestMethod.POST)
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
            projectService.uploadProjectExcel(hssfWorkbook);
        }catch (Exception e) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.FILE_ERROR);
        }
        return ResultGenerator.genSuccessResult();
    }
}
