package com.yd.kuma.client.service.impl;

import com.yd.kuma.admin.dao.GoodsDao;
import com.yd.kuma.admin.model.dto.GoodsDTO;
import com.yd.kuma.admin.model.entity.Goods;
import com.yd.kuma.admin.model.vo.GoodsPagerRequestVO;
import com.yd.kuma.admin.service.ClassifyService;
import com.yd.kuma.client.model.vo.GoodsHomeDTO;
import com.yd.kuma.client.service.ClientGoodsService;
import com.yd.kuma.common.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClientGoodsService实现类
 *
 * @author gongjiguang
 * @date 2022/9/21
 */

@Service
@RequiredArgsConstructor
public class ClientGoodsServiceImpl implements ClientGoodsService {

    private final GoodsDao goodsDao;
    private final ClassifyService classifyService;

    @Override
    public List<GoodsHomeDTO> getHomeList(Integer classifyId) {
        GoodsPagerRequestVO goodsPagerRequestVO = new GoodsPagerRequestVO();
        goodsPagerRequestVO.setPage(1);
        goodsPagerRequestVO.setCount(6);
        goodsPagerRequestVO.setIsHomeList(1);
        goodsPagerRequestVO.setClassifyId(classifyId);
        List<Goods> goodsList = goodsDao.queryPager(goodsPagerRequestVO);
        return goodsList.stream()
                .map(this::convertGoodsToGoodsHomeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GoodsHomeDTO> getList(GoodsPagerRequestVO goodsPagerRequestVO) {
        goodsPagerRequestVO.setStatus(1);
        List<Goods> goodsList = goodsDao.queryPager(goodsPagerRequestVO);
        return goodsList.stream()
                .map(this::convertGoodsToGoodsHomeDTO)
                .collect(Collectors.toList());
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
    public int count(Integer status) {
        return goodsDao.count(status);
    }


    /**
     * 转换Goods为GoodsHomeDTO
     *
     * @param goods 商品
     * @return 商品首页
     */
    private GoodsHomeDTO convertGoodsToGoodsHomeDTO(Goods goods) {
        GoodsHomeDTO goodsHomeDTO = new GoodsHomeDTO();
        goodsHomeDTO.setId(goods.getId());
        goodsHomeDTO.setTitle(goods.getTitle());
        goodsHomeDTO.setCoverImg(goods.getCoverImg());
        goodsHomeDTO.setClassifyId(goods.getClassifyId());
        goodsHomeDTO.setImgList(CommonUtil.stringsToList(goods.getImgList()));
        goodsHomeDTO.setContent(goods.getContent());
        goodsHomeDTO.setExcerpt(goods.getExcerpt());
        goodsHomeDTO.setTimer(goods.getTimer());
        goodsHomeDTO.setMaterial(goods.getMaterial());
        goodsHomeDTO.setQty(goods.getQty());
        goodsHomeDTO.setClassify(classifyService.queryByClassifyId(goods.getClassifyId()));
        return goodsHomeDTO;
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
}
