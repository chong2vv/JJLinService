package com.yd.kuma.client.controller;

import com.yd.kuma.admin.model.dto.BlogDTO;
import com.yd.kuma.admin.model.vo.BlogPagerRequestVO;
import com.yd.kuma.admin.service.BlogService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyuandong
 */
@RestController
@RequestMapping("/client/blog/")
@RequiredArgsConstructor
public class ClientBlogController {

    private final BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClassifyList(BlogPagerRequestVO blogPagerRequestVO) {
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
