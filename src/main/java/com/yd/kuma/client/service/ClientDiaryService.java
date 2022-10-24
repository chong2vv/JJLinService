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
}
