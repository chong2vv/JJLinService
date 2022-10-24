package com.yd.kuma.client.controller;

import com.yd.kuma.admin.model.dto.BlogDTO;
import com.yd.kuma.admin.model.dto.DiaryDTO;
import com.yd.kuma.admin.model.vo.BlogPagerRequestVO;
import com.yd.kuma.admin.model.vo.DiaryPagerRequestVO;
import com.yd.kuma.client.service.ClientBlogService;
import com.yd.kuma.client.service.ClientDiaryService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import com.yd.kuma.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/24 13:37
 */
@RestController
@RequestMapping("/client/diary/")
@RequiredArgsConstructor
public class ClientDiaryController {
    private final ClientDiaryService diaryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDiaryList(@RequestParam Map<String, Object> map) {
        DiaryPagerRequestVO diaryPagerRequestVO = JsonUtil.mapToObj(map, DiaryPagerRequestVO.class);
        diaryPagerRequestVO.initPager();
        List<DiaryDTO> diaryDTOList = diaryService.getDiaryList(diaryPagerRequestVO);
        int count = diaryService.count();
        return ResultGenerator.genSuccessPager(diaryDTOList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDiaryDetail(@RequestParam Long id) {
        DiaryDTO diaryDTO = diaryService.getDiaryDetail(id);
        if (diaryDTO == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.DIARY_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(diaryDTO);
    }
}
