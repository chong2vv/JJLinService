package com.yd.blog.client.service.impl;

import com.yd.blog.admin.dao.TagDao;
import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;
import com.yd.blog.client.service.ClientTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientTagServiceImpl implements ClientTagService {

    private final TagDao tagDao;

    /**
     * 分页查询
     *
     * @param tagPagerRequestVO      分页对象
     * @return 查询结果
     */
    @Override
    public List<Tag> queryByPage(TagPagerRequestVO tagPagerRequestVO) {
        tagPagerRequestVO.initPager();
        tagPagerRequestVO.setStatus(1);
        return tagDao.queryAllByLimit(tagPagerRequestVO);
    }
}
