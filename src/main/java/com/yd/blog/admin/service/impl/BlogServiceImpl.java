package com.yd.blog.admin.service.impl;

import com.yd.blog.admin.dao.BlogDao;
import com.yd.blog.admin.model.dto.BlogDTO;
import com.yd.blog.admin.model.entity.Blog;
import com.yd.blog.admin.model.vo.BlogPagerRequestVO;
import com.yd.blog.admin.service.BlogService;
import com.yd.blog.admin.service.CategoriesService;
import com.yd.blog.admin.service.ClassifyService;
import com.yd.blog.common.util.CommonUtil;
import com.yd.blog.common.util.DateUtil;
import com.yd.blog.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BlogService实现类
 *
 * @author wangyuandong
 * @date 2022/9/19
 */
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogDao blogDao;

    private final CategoriesService categoriesService;

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

    @Override
    public boolean existByBlogId(Long id) {
        Blog blog = blogDao.queryById(id);
        return blog != null;
    }

    @Override
    public void update(BlogDTO blogDto) {
        Blog blog = converUpdateBlogDtoToBlog(blogDto);
        blogDao.update(blog);
    }

    @Override
    public void updateStatus(BlogDTO blogDTO) {
        Blog blog = converUpdateBlogDtoToBlog(blogDTO);
        blogDao.updateStatus(blog);
    }

    private BlogDTO convertBlogToBlogDto(Blog blog) {
        BlogDTO blogDto = CommonUtil.copyVo(blog, BlogDTO.class);
        blogDto.setTags(CommonUtil.stringsToList(blog.getTags()));
        blogDto.setImgList(CommonUtil.stringsToList(blog.getImgList()));
        blogDto.setVideoList(CommonUtil.stringsToList(blog.getVideoList()));
        blogDto.setCategories(categoriesService.queryByCategoriesId(blog.getClassifyId()));
        return blogDto;
    }

    private Blog converUpdateBlogDtoToBlog(BlogDTO blogDto) {
        Blog blog = CommonUtil.copyVo(blogDto, Blog.class);
        blog.setTags(CommonUtil.listToString(blogDto.getTags()));
        blog.setImgList(CommonUtil.listToString(blogDto.getImgList()));
        blog.setVideoList(CommonUtil.listToString(blogDto.getVideoList()));
        return blog;
    }

    private Blog convertBlogDtoToBlog(BlogDTO blogDto) {
        Blog blog = CommonUtil.copyVo(blogDto, Blog.class);
        blog.setId(NumberUtil.genUid());
        blog.setCreateTime(DateUtil.getCurrentDateTimeStr());
        blog.setImgList(CommonUtil.listToString(blogDto.getImgList()));
        blog.setVideoList(CommonUtil.listToString(blogDto.getVideoList()));
        blog.setTags(CommonUtil.listToString(blogDto.getTags()));
        return blog;
    }

}
