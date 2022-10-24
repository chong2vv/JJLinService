package com.yd.kuma.admin.dao;

import com.yd.kuma.admin.model.entity.Diary;
import com.yd.kuma.admin.model.vo.DiaryPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 随手记(Diary)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-24 11:10:31
 */
@Mapper
public interface DiaryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Diary queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param diaryPagerRequestVO 查询条件
     * @return 对象列表
     */
    List<Diary> queryPager(DiaryPagerRequestVO diaryPagerRequestVO);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    int count();

    /**
     * 新增数据
     *
     * @param diary 实例对象
     * @return 影响行数
     */
    int insert(Diary diary);

    /**
     * 更新数据
     *
     * @param diary 实例对象
     * @return 影响行数
     */
    int update(Diary diary);
}

