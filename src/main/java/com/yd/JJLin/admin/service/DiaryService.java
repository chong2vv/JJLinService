package com.yd.JJLin.admin.service;

import com.yd.JJLin.admin.model.dto.DiaryDTO;
import java.util.List;

import com.yd.JJLin.admin.model.vo.DiaryPagerRequestVO;

/**
 * 随手记(Diary)表服务接口
 *
 * @author makejava
 * @since 2022-10-24 11:10:34
 */
public interface DiaryService {
    /**
     * 获取blog列表
     *
     * @param diaryPagerRequestVO blog请求信息
     * @return blog列表
     */
    List<DiaryDTO> getDiaryList(DiaryPagerRequestVO diaryPagerRequestVO);

    /**
     * 获取总数量
     *
     * @return 数量
     */
    int count();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiaryDTO getDiaryDetail(Long id);

    /**
     * 创建新的diary对象
     *
     * @param diaryDTO diary对象
     * @return diary对象
     */
    DiaryDTO createDiary(DiaryDTO diaryDTO);

    /**
     * 根据id判断文章是否存在
     *
     * @param id 主键id
     * @return true 存在 false 不存在
     */
    boolean existByDiaryId(Long id);

    /**
     * 修改分类
     *
     * @param diaryDTO 文章信息
     * @return 1 成功
     */
    void update(DiaryDTO diaryDTO);

    /**
     * 更新状态
     * @param diaryDTO
     */
    void updateStatus(DiaryDTO diaryDTO);
}

