package com.ry.time.common.util;

import com.ry.time.admin.annotation.ExcelTdName;
import com.ry.time.common.model.ExcelTdNameSort;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Excel数据操作工具类
 *
 * @author gongjiguang
 * @date 2022/09/18
 */
public class ExcelExportUtil {

    public static <T> XSSFWorkbook export(List<T> result, Class<T> o) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个标题样式
        XSSFCellStyle titleStyle = titleStyle(workbook);
        XSSFRow row = sheet.createRow((short) 0);
        //生成标题
        List<ExcelTdNameSort> nameList = setTitleName(o, titleStyle, row);
        // 生成一个金额样式
        XSSFCellStyle amountStyle = amountStyle(workbook);
        // 生成一个百分比样式
        XSSFCellStyle percentageStyle = percentageStyle(workbook);

        try {
            saveData(result, o, sheet, nameList, amountStyle, percentageStyle);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return workbook;
        }
        return workbook;
    }

    public static <T> List<T> importList(XSSFSheet sheet, Class<T> o) throws InstantiationException,
            IllegalAccessException {
        Map<Integer, ExcelTdNameSort> fieldNameMap = getExcelTdNameSortMap(o);
        List<T> result = new ArrayList<>();
        for (int iRow = 1; iRow <= sheet.getLastRowNum(); iRow++) {
            XSSFRow row = sheet.getRow(iRow);
            XSSFCell cell = row.getCell(0);
            if (cell == null || StringUtils.isBlank(cell.getStringCellValue())) {
                continue;
            }
            T req = o.newInstance();
            fieldNameMap.forEach((num, name) -> {
                try {
                    setObjectValue(o, row, req, num, name);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
            result.add(req);
        }
        return result;
    }

    /**
     * 标题样式
     *
     * @param workbook 工作簿
     * @return 样式
     */
    private static XSSFCellStyle titleStyle(XSSFWorkbook workbook) {
        // 生成一个样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.VIOLET.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    /**
     * 获取金额样式
     *
     * @param workbook 工作簿
     * @return 样式
     */
    private static XSSFCellStyle amountStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(workbook.createDataFormat().getFormat("$#,##0.0000"));
        return cellStyle;
    }

    /**
     * 获取百分比样式
     *
     * @param workbook 工作簿
     * @return 样式
     */
    private static XSSFCellStyle percentageStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        return cellStyle;
    }

    /**
     * 插入标题
     *
     * @param o     类信息
     * @param style 标题样式
     * @param row   行对象
     * @param <T>   类
     * @return 方法集合
     */
    private static <T> List<ExcelTdNameSort> setTitleName(Class<T> o, XSSFCellStyle style, XSSFRow row) {
        Map<Integer, ExcelTdNameSort> fieldNameMap = getExcelTdNameSortMap(o);
        List<ExcelTdNameSort> nameList = new ArrayList<>();
        fieldNameMap.forEach((num, name) -> {
            XSSFCell cell = row.createCell(num);
            cell.setCellStyle(style);
            cell.setCellValue(new XSSFRichTextString(name.getTitleName()));
            nameList.add(name);
        });

        return nameList;
    }

    /**
     * 获取ExcelTdNameSort的map集合
     *
     * @param o   类
     * @param <T> 类对象
     * @return map集合
     */
    private static <T> Map<Integer, ExcelTdNameSort> getExcelTdNameSortMap(Class<T> o) {
        Field[] fields = o.getDeclaredFields();
        Map<Integer, ExcelTdNameSort> fieldNameMap = new TreeMap<>();
        for (Field field : fields) {
            ExcelTdName name = field.getDeclaredAnnotation(ExcelTdName.class);
            if (name != null) {
                field.setAccessible(true);
                int[] nums = name.sort();
                for (int i = 0; i < nums.length; i++) {
                    String methodName = "get" + field.getName().replaceFirst(field.getName().substring(0, 1)
                            , field.getName().substring(0, 1).toUpperCase());
                    ExcelTdNameSort excelTdNameSort = new ExcelTdNameSort(name.value()[i], methodName,
                            name.cellFormat(), field.getType());
                    fieldNameMap.put(nums[i], excelTdNameSort);
                }
            }
        }
        return fieldNameMap;
    }

    /**
     * 保存数据
     *
     * @param result   数据
     * @param o        类信息
     * @param sheet    表信息
     * @param nameList 方法名集合
     * @param <T>      类
     * @throws NoSuchMethodException 未找到方法名称
     */
    private static <T> void saveData(List<T> result, Class<T> o, XSSFSheet sheet, List<ExcelTdNameSort> nameList,
                                     XSSFCellStyle amountStyle, XSSFCellStyle percentageStyle) throws NoSuchMethodException {
        XSSFRow row;
        XSSFCell cell;
        if (result != null && !result.isEmpty()) {
            int iRow = 1;
            for (T res : result) {
                row = sheet.createRow((short) iRow);

                for (int i = 0; i < nameList.size(); i++) {
                    ExcelTdNameSort excelTdNameSort = nameList.get(i);
                    Method m = o.getMethod(excelTdNameSort.getMethodName());
                    cell = row.createCell(i);
                    int cellFormat = excelTdNameSort.getCellFormat();
                    if (cellFormat == 1) {
                        cell.setCellStyle(amountStyle);
                    } else if (cellFormat == 2) {
                        cell.setCellStyle(percentageStyle);
                    } else {
                        cell.setCellType(CellType.STRING);

                    }
                    setValue(cell, res, m);
                }
                iRow++;
            }
        }
    }

    /**
     * 插入对应数值
     *
     * @param cell 单元格
     * @param res  对象类
     * @param m    方法
     * @param <T>  类
     */
    private static <T> void setValue(XSSFCell cell, T res, Method m) {
        try {
            Class<?> returnType = m.getReturnType();
            Object value = m.invoke(res);
            String valueStr = Optional.ofNullable(value.toString()).orElse("");
            if (Byte.class == returnType || byte.class == returnType) {
                byte b = StringUtils.isBlank(valueStr) ? 0 : Byte.parseByte(valueStr);
                cell.setCellValue(b);
            } else if (Short.class == returnType || short.class == returnType) {
                short s = StringUtils.isBlank(valueStr) ? 0 : Short.parseShort(valueStr);
                cell.setCellValue(s);
            } else if (Integer.class == returnType || int.class == returnType || AtomicInteger.class == returnType) {
                int i = StringUtils.isBlank(valueStr) ? 0 : Integer.parseInt(valueStr);
                cell.setCellValue(i);
            } else if (Long.class == returnType || long.class == returnType || AtomicLong.class == returnType) {
                long l = StringUtils.isBlank(valueStr) ? 0L : Long.parseLong(valueStr);
                cell.setCellValue(l);
            } else if (Float.class == returnType || float.class == returnType) {
                float f = StringUtils.isBlank(valueStr) ? 0f : Float.parseFloat(valueStr);
                cell.setCellValue(f);
            } else if (Double.class == returnType || double.class == returnType) {
                double d = StringUtils.isBlank(valueStr) ? 0d : Double.parseDouble(valueStr);
                cell.setCellValue(d);
            } else if (Character.class == returnType || char.class == returnType) {
                char c = StringUtils.isBlank(valueStr) ? 0 : valueStr.charAt(0);
                cell.setCellValue(c);
            } else {
                cell.setCellValue(valueStr);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            cell.setCellValue("");
            e.printStackTrace();
        }
    }

    private static <T> void setObjectValue(Class<T> o, XSSFRow row, T req, Integer num, ExcelTdNameSort name) throws NoSuchMethodException {
        XSSFCell cell = row.getCell(num);
        if (cell == null){
            return;
        }
        String setMethodName = name.getMethodName().replace("get", "set");
        Method setMethod = o.getMethod(setMethodName, name.getParameterType());
        try {
            if (String.class == name.getParameterType()) {
                cell.setCellType(CellType.STRING);
                String value = cell.getStringCellValue();
                setMethod.invoke(req, value);
            } else {
                BigDecimal value;
                if (cell.getCellType() == CellType.NUMERIC) {
                    value = BigDecimal.valueOf(cell.getNumericCellValue());
                } else {
                    value = new BigDecimal(cell.getStringCellValue()
                            .replace("%", "").trim())
                            .divide(new BigDecimal(100), RoundingMode.HALF_UP);
                }
                if (Byte.class == name.getParameterType() || byte.class == name.getParameterType()) {
                    byte b = value.byteValue();
                    setMethod.invoke(req, b);
                } else if (Short.class == name.getParameterType() || short.class == name.getParameterType()) {
                    short s = value.shortValue();
                    setMethod.invoke(req, s);
                } else if (Integer.class == name.getParameterType() || int.class == name.getParameterType() ||
                        AtomicInteger.class == name.getParameterType()) {
                    int i = value.intValue();
                    setMethod.invoke(req, i);
                } else if (Long.class == name.getParameterType() || long.class == name.getParameterType() ||
                        AtomicLong.class == name.getParameterType()) {
                    long l = value.longValue();
                    setMethod.invoke(req, l);
                } else if (Float.class == name.getParameterType() || float.class == name.getParameterType()) {
                    float f = value.floatValue();
                    setMethod.invoke(req, f);
                } else if (Double.class == name.getParameterType() || double.class == name.getParameterType()) {
                    double d = value.doubleValue();
                    setMethod.invoke(req, d);
                } else if (Character.class == name.getParameterType() || char.class == name.getParameterType()) {
                    char c = value.toString().charAt(0);
                    setMethod.invoke(req, c);
                } else {
                    setMethod.invoke(req, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
