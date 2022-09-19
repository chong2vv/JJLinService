package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.GoodsDao;
import com.ry.time.admin.model.dto.GoodsDTO;
import com.ry.time.admin.model.dto.GoodsExcelDTO;
import com.ry.time.admin.model.entity.Goods;
import com.ry.time.admin.model.vo.GoodsPagerRequestVO;
import com.ry.time.admin.service.ClassifyService;
import com.ry.time.admin.service.GoodsService;
import com.ry.time.common.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GoodsService实现类
 *
 * @author gongjiguang
 * @date 2022/9/13
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsDao goodsDao;

    private final ClassifyService classifyService;

    @Override
    public List<GoodsDTO> getGoodsList(GoodsPagerRequestVO goodsPagerRequestVO) {
        goodsPagerRequestVO.initPager();
        List<Goods> goodsList = goodsDao.queryPager(goodsPagerRequestVO);
        return goodsList.stream()
                .map(this::convertGoodsToGoodsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int count(Integer status) {
        return goodsDao.count(status);
    }

    @Override
    public GoodsDTO queryByGoodsId(Long id) {
        Goods goods = goodsDao.queryById(id);
        if (goods == null) {
            return null;
        }
        return this.convertGoodsToGoodsDTO(goods);
    }

    @Override
    public boolean existByGoodsId(Long id) {
        Goods goods = goodsDao.queryById(id);
        return goods != null;
    }

    @Override
    public void update(GoodsDTO goodsDTO) {
        Goods goods = convertGoodsDtoToGoods(goodsDTO);
        goodsDao.update(goods);
    }

    @Override
    public void updateStatus(GoodsDTO goodsDTO) {
        Goods goods = convertGoodsDtoToGoods(goodsDTO);
        goodsDao.updateStatus(goods);
    }

    @Override
    public GoodsDTO create(GoodsDTO goodsDTO) {
        Goods goods = convertGoodsDtoToGoods(goodsDTO);
        goods.setId(NumberUtil.genUid());
        goods.setCreateTime(DateUtil.getCurrentDateTimeStr());
        goodsDao.insert(goods);
        return convertGoodsToGoodsDTO(goods);
    }

    @Override
    public XSSFWorkbook getGoodsTemplateExcel() {
        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/goods_template.xlsx");
            FileInputStream excelFile = new FileInputStream(file);
            return new XSSFWorkbook(excelFile);
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadGoodsExcel(XSSFWorkbook hssfWorkbook) {
        XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        ExcelUtil.removeRow(sheet,0);
        try {
            List<GoodsExcelDTO> excelList = ExcelExportUtil.importList(sheet, GoodsExcelDTO.class);
            List<Goods> list = excelList.stream()
                    .map(this::convertGoodsExcelDtoToGoods)
                    .collect(Collectors.toList());
            goodsDao.insertBatch(list);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换商品实体为商品DTO
     *
     * @param goods 商品实体
     * @return 商品DTO
     */
    private GoodsDTO convertGoodsToGoodsDTO(Goods goods) {
        GoodsDTO goodsDTO = CommonUtil.copyVo(goods, GoodsDTO.class);
        goodsDTO.setImgList(CommonUtil.stringsToList(goods.getImgList()));
        goodsDTO.setTags(CommonUtil.stringsToList(goods.getTags()));
        goodsDTO.setClassify(classifyService.queryByClassifyId(goods.getClassifyId()));
        return goodsDTO;
    }

    private Goods convertGoodsDtoToGoods(GoodsDTO goodsDTO) {
        Goods goods = CommonUtil.copyVo(goodsDTO, Goods.class);
        goods.setImgList(CommonUtil.listToString(goodsDTO.getImgList()));
        goods.setTags(CommonUtil.listToString(goodsDTO.getTags()));
        return goods;
    }

    private Goods convertGoodsExcelDtoToGoods(GoodsExcelDTO goodsExcelDTO) {
        Goods goods = CommonUtil.copyVo(goodsExcelDTO, Goods.class);
        goods.setId(NumberUtil.genUid());
        goods.setCreateTime(DateUtil.getCurrentDateTimeStr());
        if (goodsExcelDTO.getCoverImg()==null) {
            goods.setCoverImg("");
        }
        goods.setStatus(1);
        return goods;
    }
}
