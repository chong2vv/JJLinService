package com.yd.JJLin.client.service.impl;

import com.yd.JJLin.admin.dao.DiaryDao;
import com.yd.JJLin.admin.model.dto.DiaryDTO;
import com.yd.JJLin.admin.model.entity.Diary;
import com.yd.JJLin.admin.model.vo.DiaryPagerRequestVO;
import com.yd.JJLin.client.service.ClientClassifyService;
import com.yd.JJLin.client.service.ClientDiaryService;
import com.yd.JJLin.common.util.CommonUtil;
import com.yd.JJLin.common.util.DateUtil;
import com.yd.JJLin.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/24 13:39
 */
@Service
@RequiredArgsConstructor
public class ClientDiaryServiceImpl implements ClientDiaryService {
    private final DiaryDao diaryDao;
    private final ClientClassifyService classifyService;


    @Override
    public List<DiaryDTO> getDiaryList(DiaryPagerRequestVO diaryPagerRequestVO) {
        diaryPagerRequestVO.initPager();
        List<Diary> diaryList = diaryDao.queryPager(diaryPagerRequestVO);
        return diaryList.stream()
                .map(this::convertDiaryToDiaryDto)
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return diaryDao.count();
    }

    @Override
    public DiaryDTO getDiaryDetail(Long id) {
        Diary diary = diaryDao.queryById(id);
        if (diary == null) {
            return null;
        }
        return convertDiaryToDiaryDto(diary);
    }

    @Override
    public DiaryDTO createDiary(DiaryDTO diaryDTO) {
        Diary diary = convertDiaryDtoToDiary(diaryDTO);
        diaryDao.insert(diary);
        return convertDiaryToDiaryDto(diary);
    }

    @Override
    public boolean existByDiaryId(Long id) {
        Diary diary = diaryDao.queryById(id);
        return diary != null;
    }

    @Override
    public void update(DiaryDTO diaryDTO) {
        Diary diary = converUpdateDiaryDtoToDiary(diaryDTO);
        diaryDao.update(diary);
    }

    private Diary converUpdateDiaryDtoToDiary(DiaryDTO diaryDTO) {
        Diary diary = CommonUtil.copyVo(diaryDTO, Diary.class);
        diary.setTags(CommonUtil.listToString(diaryDTO.getTags()));
        diary.setImgList(CommonUtil.listToString(diaryDTO.getImgList()));
        diary.setVideoList(CommonUtil.listToString(diaryDTO.getVideoList()));
        return diary;
    }

    private DiaryDTO convertDiaryToDiaryDto(Diary diary) {
        DiaryDTO diaryDTO = CommonUtil.copyVo(diary, DiaryDTO.class);
        diaryDTO.setTags(CommonUtil.stringsToList(diary.getTags()));
        diaryDTO.setImgList(CommonUtil.stringsToList(diary.getImgList()));
        diaryDTO.setVideoList(CommonUtil.stringsToList(diary.getVideoList()));
        diaryDTO.setClassify(classifyService.queryByClassifyId(diary.getClassifyId()));
        diaryDTO.setIdString(Long.toString(diary.getId()));
        return diaryDTO;
    }
    private Diary convertDiaryDtoToDiary(DiaryDTO diaryDTO) {
        Diary diary = CommonUtil.copyVo(diaryDTO, Diary.class);
        diary.setId(NumberUtil.genUid());
        diary.setCreateTime(DateUtil.getCurrentDateTimeStr());
        diary.setImgList(CommonUtil.listToString(diaryDTO.getImgList()));
        diary.setVideoList(CommonUtil.listToString(diaryDTO.getVideoList()));
        diary.setTags(CommonUtil.listToString(diaryDTO.getTags()));
        diary.setContent("");
        diary.setStatus(1);
        return diary;
    }
}
