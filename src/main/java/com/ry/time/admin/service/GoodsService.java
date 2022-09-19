package com.ry.time.admin.service;

import com.ry.time.admin.model.dto.GoodsDTO;
import com.ry.time.admin.model.vo.GoodsPagerRequestVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * 商品业务接口
 *
 * @author gongjiguang
 * @date 2022/9/13
 */
public interface GoodsService {

    /**
     * 分页查询
     *
     * @param goodsPagerRequestVO 商品请求参数
     * @return 分页数据
     */
    List<GoodsDTO> getGoodsList(GoodsPagerRequestVO goodsPagerRequestVO);

    /**
     * 商品数量
     *
     * @param status 状态
     * @return 商品数量
     */
    int count(Integer status);

    /**
     * 商品详情
     *
     * @param id 商品id
     * @return 商品详情
     */
    GoodsDTO queryByGoodsId(Long id);

    /**
     * 判断商品是否存在
     *
     * @param id 商品id
     * @return true 存在 false 不存在
     */
    boolean existByGoodsId(Long id);
    /**
     * 更新商品
     *
     * @param goodsDTO 商品信息
     */
    void update(GoodsDTO goodsDTO);
    /**
     * 更新商品状态
     *
     * @param goodsDTO 商品
     */
    void updateStatus(GoodsDTO goodsDTO);
    /**
     * 创建商品
     *
     * @param goodsDTO 商品
     * @return 商品dto
     */
    GoodsDTO create(GoodsDTO goodsDTO);

    /**
     * 获取商品的excel
     *
     * @return excel
     */
    XSSFWorkbook getGoodsTemplateExcel();

    /**
     * 获取指定商品按模板生成excel
     * @param list 生成的数据
     * @return excel
     */
    XSSFWorkbook getGoodsExportExcel(List<GoodsDTO> list);

    /**
     * 导入商品
     *
     * @param hssfWorkbook 文件
     */
    void uploadGoodsExcel(XSSFWorkbook hssfWorkbook);
}
