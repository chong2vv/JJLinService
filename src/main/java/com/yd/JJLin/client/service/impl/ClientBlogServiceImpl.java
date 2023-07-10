package com.yd.JJLin.client.service.impl;

import com.yd.JJLin.admin.dao.BlogDao;
import com.yd.JJLin.admin.model.dto.BlogArchiveDTO;
import com.yd.JJLin.admin.model.dto.BlogDTO;
import com.yd.JJLin.admin.model.entity.Blog;
import com.yd.JJLin.admin.model.vo.BlogPagerRequestVO;
import com.yd.JJLin.client.service.ClientBlogService;
import com.yd.JJLin.client.service.ClientCategoriesService;
import com.yd.JJLin.common.util.CommonUtil;
import com.yd.JJLin.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClientBlogService 类实现
 *
 * @Author: wangyuandong
 * @Date: 2022/9/22 13:14
 */
@Service
@RequiredArgsConstructor
public class ClientBlogServiceImpl implements ClientBlogService {

    private final BlogDao blogDao;

    private final ClientCategoriesService categoriesService;
    @Override
    public List<BlogDTO> getBlogList(BlogPagerRequestVO blogPagerRequestVO) {
        blogPagerRequestVO.initPager();
        List<Blog> blogList = blogDao.queryPager(blogPagerRequestVO);
        return blogList.stream()
                .map(this::convertBlogToBlogDto)
                .collect(Collectors.toList());
    }

    /**
     * @param blogPagerRequestVO 请求参数，年、月
     * @return 日志列表
     */
    @Override
    public List<BlogDTO> getArchiveBlogList(BlogPagerRequestVO blogPagerRequestVO) {
        List<Blog> blogList = blogDao.findByYearMonth(blogPagerRequestVO);
        return blogList.stream()
                .map(this::convertBlogToBlogDto)
                .collect(Collectors.toList());
    }

    @Override
    public int count(int status) {
        return blogDao.count(status);
    }

    /**
     * @return 数量
     */
    @Override
    public int homeListCount() {
        return blogDao.homeCount();
    }

    @Override
    public BlogDTO getBlogDetail(Long id) {
        Blog blog = blogDao.queryById(id);
        if (blog == null) {
            return null;
        }
        return convertBlogToBlogDto(blog);
    }

    private BlogDTO convertBlogToBlogDto(Blog blog) {
        BlogDTO blogDto = CommonUtil.copyVo(blog, BlogDTO.class);
        blogDto.setTags(CommonUtil.stringsToList(blog.getTags()));
        blogDto.setIdString(Long.toString(blog.getId()));
        blogDto.setCategories(categoriesService.queryByCategoriesId(blog.getClassifyId()));
        blogDto.setImgList(CommonUtil.stringsToList(blog.getImgList()));
        blogDto.setVideoList(CommonUtil.stringsToList(blog.getVideoList()));
        return blogDto;
    }

    /**
     * @return 归档数组
     */
    @Override
    public List<BlogArchiveDTO> groupByYearMonth() {
        List<Map<String, Object>> list = blogDao.groupByYearMonth();
        return list.stream()
                .map(this::convertMapToBlogArchiveDto)
                .collect(Collectors.toList());
    }

    /**
     * @return 归档
     */
    @Override
    public List<BlogArchiveDTO> groupByYear() {
        List<Map<String, Object>> list = blogDao.groupByYear();
        return list.stream()
                .map(this::convertMapToBlogArchiveDto)
                .collect(Collectors.toList());
    }

    private BlogArchiveDTO convertMapToBlogArchiveDto(Map<String, Object> map) {
        return JsonUtil.mapToObj(map, BlogArchiveDTO.class);
    }
}
