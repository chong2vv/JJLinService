package com.yd.kuma.client.service;
import com.yd.kuma.admin.model.dto.DiaryDTO;
import com.yd.kuma.admin.model.vo.DiaryPagerRequestVO;

import java.util.List;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/24 13:38
 */
public interface ClientDiaryService {
    /**
     * 获取diary列表
     *
     * @param diaryPagerRequestVO diary请求信息
     * @return diary列表
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
     */
    void update(DiaryDTO diaryDTO);
}
