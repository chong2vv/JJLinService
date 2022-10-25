package com.yd.kuma.admin.service.impl;

import com.yd.kuma.admin.dao.GoodsDao;
import com.yd.kuma.admin.model.dto.GoodsDTO;
import com.yd.kuma.admin.model.dto.GoodsExcelDTO;
import com.yd.kuma.admin.model.entity.Goods;
import com.yd.kuma.admin.model.vo.GoodsPagerRequestVO;
import com.yd.kuma.admin.service.ClassifyService;
import com.yd.kuma.admin.service.GoodsService;
import com.yd.kuma.common.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
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
        if (goodsDTO.getCoverImg() == null) {
            goods.setCoverImg("");
        }
        if (goodsDTO.getStatus() == null) {
            goods.setStatus(1);
        }
        goods.setCreateTime(DateUtil.getCurrentDateTimeStr());
        goodsDao.insert(goods);
        return convertGoodsToGoodsDTO(goods);
    }

    @Override
    public XSSFWorkbook getGoodsTemplateExcel() {
        try {
            Resource resource = new DefaultResourceLoader().getResource("classpath:static/goods_template.xlsx");
            return new XSSFWorkbook(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XSSFWorkbook getGoodsExcel(String name) {

        return null;
    }

    @Override
    public String getGoodsExportExcel(List<GoodsDTO> list) {

        return null;
    }

    @Override
    public void uploadGoodsExcel(XSSFWorkbook hssfWorkbook) {
        XSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        ExcelUtil.removeRow(sheet, 0);
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
        goodsDTO.setVideoList(CommonUtil.stringsToList(goods.getVideoList()));
        goodsDTO.setTags(CommonUtil.stringsToList(goods.getTags()));
        goodsDTO.setClassify(classifyService.queryByClassifyId(goods.getClassifyId()));
        return goodsDTO;
    }

    private Goods convertGoodsDtoToGoods(GoodsDTO goodsDTO) {
        Goods goods = CommonUtil.copyVo(goodsDTO, Goods.class);
        goods.setImgList(CommonUtil.listToString(goodsDTO.getImgList()));
        goods.setVideoList(CommonUtil.listToString(goodsDTO.getVideoList()));
        goods.setTags(CommonUtil.listToString(goodsDTO.getTags()));
        return goods;
    }

    private Goods convertGoodsExcelDtoToGoods(GoodsExcelDTO goodsExcelDTO) {
        Goods goods = CommonUtil.copyVo(goodsExcelDTO, Goods.class);
        goods.setId(NumberUtil.genUid());
        goods.setCreateTime(DateUtil.getCurrentDateTimeStr());
        if (goodsExcelDTO.getCoverImg() == null) {
            goods.setCoverImg("");
        }
        goods.setStatus(1);
        return goods;
    }
}
