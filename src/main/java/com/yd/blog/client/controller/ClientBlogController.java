package com.yd.blog.client.controller;

import com.yd.blog.admin.model.dto.BlogDTO;
import com.yd.blog.admin.model.vo.BlogPagerRequestVO;
import com.yd.blog.client.service.ClientBlogService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import com.yd.blog.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wangyuandong
 */
@RestController
@RequestMapping("/client/blog/")
@RequiredArgsConstructor
public class ClientBlogController {

    private final ClientBlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClassifyList(@RequestParam Map<String, Object> map) {
        BlogPagerRequestVO blogPagerRequestVO = JsonUtil.mapToObj(map, BlogPagerRequestVO.class);
        blogPagerRequestVO.initPager();
        List<BlogDTO> blogList = blogService.getBlogList(blogPagerRequestVO);
        int count = blogService.count();
        return ResultGenerator.genSuccessPager(blogList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBlogDetail(@RequestParam Long id) {
        BlogDTO blog = blogService.getBlogDetail(id);
        if (blog == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.BLOG_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(blog);
    }
}