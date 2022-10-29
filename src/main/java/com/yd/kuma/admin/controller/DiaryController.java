package com.yd.kuma.admin.controller;
import com.yd.kuma.admin.model.dto.DiaryDTO;
import com.yd.kuma.admin.model.vo.DiaryPagerRequestVO;
import com.yd.kuma.admin.service.DiaryService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import com.yd.kuma.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 随手记(Diary)表控制层
 *
 * @author makejava
 * @since 2022-10-24 11:10:31
 */
@RestController
@RequestMapping("/vue-admin-template/diary/")
@RequiredArgsConstructor
public class DiaryController  {
    /**
     * 服务对象
     */
    private final DiaryService diaryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClassifyList(@RequestParam Map<String, Object> map) {
        DiaryPagerRequestVO diaryPagerRequestVO = JsonUtil.mapToObj(map, DiaryPagerRequestVO.class);
        diaryPagerRequestVO.initPager();
        List<DiaryDTO> diaryList = diaryService.getDiaryList(diaryPagerRequestVO);
        int count = diaryService.count();
        if (diaryList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(diaryList,count);
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

    @RequestMapping(value = "/update-status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateDiaryStatus(@RequestBody DiaryDTO diaryDTO) {
        boolean isDiary = diaryService.existByDiaryId(diaryDTO.getId());
        if (!isDiary) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.DIARY_EXISTS_ERROR);
        }
        diaryService.updateStatus(diaryDTO);
        return ResultGenerator.genSuccessResult();
    }

}

