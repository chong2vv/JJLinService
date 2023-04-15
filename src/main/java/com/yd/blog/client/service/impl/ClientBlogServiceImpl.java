package com.yd.blog.client.service.impl;

import com.yd.blog.admin.dao.BlogDao;
import com.yd.blog.admin.model.dto.BlogDTO;
import com.yd.blog.admin.model.entity.Blog;
import com.yd.blog.admin.model.vo.BlogPagerRequestVO;
import com.yd.blog.client.service.ClientBlogService;
import com.yd.blog.client.service.ClientClassifyService;
import com.yd.blog.common.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private final ClientClassifyService classifyService;
    @Override
    public List<BlogDTO> getBlogList(BlogPagerRequestVO blogPagerRequestVO) {
        blogPagerRequestVO.initPager();
        List<Blog> blogList = blogDao.queryPager(blogPagerRequestVO);
        return blogList.stream()
                .map(this::convertBlogToBlogDto)
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return 0;
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
        blogDto.setClassify(classifyService.queryByClassifyId(blog.getClassifyId()));
        blogDto.setImgList(CommonUtil.stringsToList(blog.getImgList()));
        blogDto.setVideoList(CommonUtil.stringsToList(blog.getVideoList()));
        return blogDto;
    }
}
