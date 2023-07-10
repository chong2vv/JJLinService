package com.yd.JJLin.client.controller;

import com.yd.JJLin.admin.model.dto.DiaryDTO;
import com.yd.JJLin.admin.model.vo.DiaryPagerRequestVO;
import com.yd.JJLin.client.service.ClientDiaryService;
import com.yd.JJLin.client.service.ClientUserService;
import com.yd.JJLin.common.constant.enums.ResultErrorEnum;
import com.yd.JJLin.common.model.ResultGenerator;
import com.yd.JJLin.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    private final ClientUserService userService;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createDiary(@RequestBody DiaryDTO diaryDTO) {
        return ResultGenerator.genSuccessResult(diaryService.createDiary(diaryDTO));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateDiary(@RequestBody DiaryDTO diaryDTO) {
        boolean isDiary = diaryService.existByDiaryId(diaryDTO.getId());
        if (!isDiary) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.DIARY_EXISTS_ERROR);
        }
        diaryService.update(diaryDTO);
        return ResultGenerator.genSuccessResult();
    }


}
