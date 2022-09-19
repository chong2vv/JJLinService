package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.BlogDao;
import com.ry.time.admin.model.dto.BlogDTO;
import com.ry.time.admin.model.entity.Blog;
import com.ry.time.admin.model.vo.BlogPagerRequestVO;
import com.ry.time.admin.service.BlogService;
import com.ry.time.common.util.CommonUtil;
import com.ry.time.common.util.DateUtil;
import com.ry.time.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BlogService实现类
 *
 * @author gongjiguang
 * @date 2022/9/19
 */
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogDao blogDao;

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
        return blogDao.count();
    }

    @Override
    public BlogDTO getBlogDetail(Long id) {
        Blog blog = blogDao.queryById(id);
        if (blog == null) {
            return null;
        }
        return convertBlogToBlogDto(blog);
    }

    @Override
    public BlogDTO createBlog(BlogDTO blogDto) {
        Blog blog = convertBlogDtoToBlog(blogDto);
        blogDao.insert(blog);
        return convertBlogToBlogDto(blog);
    }

    private BlogDTO convertBlogToBlogDto(Blog blog) {
        BlogDTO blogDto = CommonUtil.copyVo(blog, BlogDTO.class);
        blogDto.setTags(CommonUtil.stringsToList(blog.getTags()));
        return blogDto;
    }

    private Blog convertBlogDtoToBlog(BlogDTO blogDto) {
        Blog blog = CommonUtil.copyVo(blogDto, Blog.class);
        blog.setId(NumberUtil.genUid());
        blog.setCreateTime(DateUtil.getCurrentDateTimeStr());
        blog.setTags(CommonUtil.listToString(blogDto.getTags()));
        return blog;
    }

}
