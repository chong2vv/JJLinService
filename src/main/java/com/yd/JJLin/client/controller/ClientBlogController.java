package com.yd.JJLin.client.controller;

import com.yd.JJLin.admin.model.dto.BlogArchiveDTO;
import com.yd.JJLin.admin.model.dto.BlogDTO;
import com.yd.JJLin.admin.model.vo.BlogPagerRequestVO;
import com.yd.JJLin.client.service.ClientBlogService;
import com.yd.JJLin.common.constant.enums.ResultErrorEnum;
import com.yd.JJLin.common.model.ResultGenerator;
import com.yd.JJLin.common.util.JsonUtil;
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

    /**
     * 首页显示的值
     */
    static final int HOME_LIST_STATE = 1;
    static final int HOME_LIST_COUNT = 5;

    private final ClientBlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBlogList(@RequestParam Map<String, Object> map) {
        BlogPagerRequestVO blogPagerRequestVO = JsonUtil.mapToObj(map, BlogPagerRequestVO.class);
        List<BlogDTO> blogList = blogService.getBlogList(blogPagerRequestVO);
        int count = blogService.count(1);
        return ResultGenerator.genSuccessPager(blogList,count);
    }

    @RequestMapping(value = "/year_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getYearBlogList(@RequestParam Map<String, Object> map) {
        BlogPagerRequestVO blogPagerRequestVO = JsonUtil.mapToObj(map, BlogPagerRequestVO.class);
        if (blogPagerRequestVO.getYear() == null || blogPagerRequestVO.getMonth() == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.BLOG_ARGUMENT_ERROR);
        }
        blogPagerRequestVO.setStatus(HOME_LIST_STATE);
        List<BlogDTO> blogList = blogService.getArchiveBlogList(blogPagerRequestVO);
        return ResultGenerator.genSuccessResult(blogList);
    }

    @RequestMapping(value = "/post_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostBlogList(@RequestParam Map<String, Object> map) {
        BlogPagerRequestVO blogPagerRequestVO = JsonUtil.mapToObj(map, BlogPagerRequestVO.class);
        blogPagerRequestVO.initPager();
        blogPagerRequestVO.setCount(HOME_LIST_COUNT);
        blogPagerRequestVO.setIsHomeList(HOME_LIST_STATE);
        List<BlogDTO> blogList = blogService.getBlogList(blogPagerRequestVO);
        int count = blogService.homeListCount();
        return ResultGenerator.genSuccessPager(blogList,count);
    }

    @RequestMapping(value = "/archive_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getArchiveBlogList() {
        List<BlogArchiveDTO> blogList = blogService.groupByYearMonth();
        return ResultGenerator.genSuccessResult(blogList);
    }

    @RequestMapping(value = "/archive_year_list", method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public String getArchiveYearBlogList() {
        List<BlogArchiveDTO> blogList = blogService.groupByYear();
        return ResultGenerator.genSuccessResult(blogList);
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
