package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.GoodsDao;
import com.ry.time.admin.model.dto.GoodsDTO;
import com.ry.time.admin.model.entity.Goods;
import com.ry.time.admin.model.vo.GoodsPagerRequestVO;
import com.ry.time.admin.service.ClassifyService;
import com.ry.time.admin.service.GoodsService;
import com.ry.time.common.util.CommonUtil;
import com.ry.time.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        return this.convertGoodsToGoodsDTO(goods);
    }

    @Override
    public boolean existByGoodsId(Long id) {
        Goods goods = goodsDao.queryById(id);
        return goods != null;
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public void updateStatus(Goods goods) {
        goodsDao.updateStatus(goods);
    }

    @Override
    public Goods create(Goods goods) {
        goods.setId(NumberUtil.genUid());

        goodsDao.insert(goods);
        return goods;
    }

    @Override
    public HSSFWorkbook getGoodsTemplateExcel() {
        return null;
    }

    @Override
    public void uploadGoodsExcel(MultipartFile file) {

    }

    private GoodsDTO convertGoodsToGoodsDTO(Goods goods) {
        GoodsDTO goodsDTO = CommonUtil.copyVo(goods, GoodsDTO.class);
        goodsDTO.setImgList(CommonUtil.stringsToList(goods.getImgList()));
        goodsDTO.setTags(CommonUtil.stringsToList(goods.getTags()));
        goodsDTO.setClassify(classifyService.queryByClassifyId(goods.getClassifyId()));
        return goodsDTO;
    }
}
