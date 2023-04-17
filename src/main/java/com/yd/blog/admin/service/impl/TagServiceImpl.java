package com.yd.blog.admin.service.impl;

import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.dao.TagDao;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;
import com.yd.blog.admin.service.TagService;
import com.yd.blog.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 热门标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 12:56:16
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(Integer id) {
        return tagDao.queryById(id);
    }
    @Override
    public boolean existByTagId(Integer id) {
        Tag tag = tagDao.queryById(id);
        return tag != null;
    }

    /**
     * @param title 主键
     * @return 实例对象
     */
    @Override
    public boolean existByTagTitle(String title){
        Tag tag = tagDao.queryByTitle(title);
        return tag != null;
    }

    /**
     * 分页查询
     *
     * @param tagPagerRequestVO      分页对象
     * @return 查询结果
     */
    @Override
    public List<Tag> queryByPage(TagPagerRequestVO tagPagerRequestVO) {
        tagPagerRequestVO.initPager();
        return tagDao.queryAllByLimit(tagPagerRequestVO);
    }


    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag insert(Tag tag) {
        tag.setCreateTime(DateUtil.getCurrentDateTimeStr());
        tagDao.insert(tag);
        return tag;
    }

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag update(Tag tag) {
        tagDao.update(tag);
        return this.queryById(tag.getId());
    }

    @Override
    public int count(Integer status) {
        return tagDao.count(status);
    }

}
