package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.BlogDao;
import com.ry.time.admin.model.entity.Blog;
import com.ry.time.admin.model.vo.BlogPagerRequestVO;
import com.ry.time.admin.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Blog> getBlogList(BlogPagerRequestVO blogPagerRequestVO) {
        return blogDao.queryPager(blogPagerRequestVO);
    }

    @Override
    public int count() {
        return blogDao.count();
    }

    @Override
    public Blog getBlogDetail(Long id) {
        return blogDao.queryById(id);
    }

    @Override
    public Object createBlog(Blog blog) {
        return blogDao.insert(blog);
    }
}
