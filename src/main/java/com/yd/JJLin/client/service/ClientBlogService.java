package com.yd.JJLin.client.service;

import com.yd.JJLin.admin.model.dto.BlogArchiveDTO;
import com.yd.JJLin.admin.model.dto.BlogDTO;
import com.yd.JJLin.admin.model.vo.BlogPagerRequestVO;

import java.util.List;

/**
 * @author wangyuandong
 */

public interface ClientBlogService {

    /**
     * 获取blog列表
     *
     * @param blogPagerRequestVO blog请求信息
     * @return blog列表
     */
    List<BlogDTO> getBlogList(BlogPagerRequestVO blogPagerRequestVO);

    /**
     * 获取归档后的日志列表
     * @param blogPagerRequestVO 请求参数，年、月
     * @return 日志列表
     */
    List<BlogDTO> getArchiveBlogList(BlogPagerRequestVO blogPagerRequestVO);

    /**
     * 获取总数量
     *
     * @return 数量
     */
    int count(int status);

    /**
     * 获取首页推荐总数量
     *
     * @return 数量
     */
    int homeListCount();


    /**
     * 按年月归档
     * @return 日志列表
     */
    List<BlogArchiveDTO> groupByYearMonth();

    /**
     * 按年归档
     * @return 日志列表
     */
    List<BlogArchiveDTO> groupByYear();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogDTO getBlogDetail(Long id);
}
