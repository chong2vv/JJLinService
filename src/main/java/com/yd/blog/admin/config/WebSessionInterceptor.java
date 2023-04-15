package com.yd.blog.admin.config;

import com.yd.blog.admin.annotation.DownloadExcel;
import com.yd.blog.common.model.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

import static com.yd.blog.common.constant.enums.ResultErrorEnum.SYSTEM_ERROR;

/**
 * web session 拦截器
 *
 * @author wangyuandong
 * @date 2022/9/18
 */

public class WebSessionInterceptor implements HandlerInterceptor {

    private static final String EXCEL_DATA_TYPE_NAME = "XSSFWorkbook";

    @Override
    public void postHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
                           @Nullable Object handler, ModelAndView view) throws Exception {
        //后台ajax请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod detailHandlerMethod = (HandlerMethod) handler;
            DownloadExcel downloadExcel = detailHandlerMethod.getMethodAnnotation(DownloadExcel.class);
            if (downloadExcel != null && view != null && response != null && request != null) {
                if (!view.getModelMap().containsKey(EXCEL_DATA_TYPE_NAME)) {
                    String json = ResultGenerator.genErrorResult(SYSTEM_ERROR);
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().print(json);
                    response.getWriter().close();
                    response.flushBuffer();
                    return;
                }
                String name = request.getParameter("fileName");
                if (StringUtils.isBlank(name)){
                    name = downloadExcel.fileName();
                }

                XSSFWorkbook workbook = (XSSFWorkbook) view.getModelMap().get(EXCEL_DATA_TYPE_NAME);
                String fileName = new String(name.getBytes("GBK"), "iso8859-1");
                response.reset();
                // 指定下载的文件名
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                OutputStream output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                workbook.write(bufferedOutPut);
                bufferedOutPut.flush();
                bufferedOutPut.close();
                output.close();
            }
        }
    }

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
                             @Nullable Object handler) {
        return true;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response,
                                @Nullable Object handler, Exception ex) {
    }


}
