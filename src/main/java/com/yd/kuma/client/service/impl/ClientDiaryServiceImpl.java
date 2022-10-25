package com.yd.kuma.client.service.impl;

import com.yd.kuma.admin.dao.DiaryDao;
import com.yd.kuma.admin.model.dto.DiaryDTO;
import com.yd.kuma.admin.model.entity.Diary;
import com.yd.kuma.admin.model.vo.DiaryPagerRequestVO;
import com.yd.kuma.admin.service.ClassifyService;
import com.yd.kuma.client.service.ClientClassifyService;
import com.yd.kuma.client.service.ClientDiaryService;
import com.yd.kuma.client.service.ClientUserService;
import com.yd.kuma.common.util.CommonUtil;
import com.yd.kuma.common.util.DateUtil;
import com.yd.kuma.common.util.NumberUtil;
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

    private final ClientUserService clientUserService;

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
    public DiaryDTO createDiary(DiaryDTO diaryDTO, Long uid) {
        Diary diary = convertDiaryDtoToDiary(diaryDTO, uid);
        diaryDao.insert(diary);
        return convertDiaryToDiaryDto(diary);
    }

    private DiaryDTO convertDiaryToDiaryDto(Diary diary) {
        DiaryDTO diaryDTO = CommonUtil.copyVo(diary, DiaryDTO.class);
        diaryDTO.setTags(CommonUtil.stringsToList(diary.getTags()));
        diaryDTO.setImgList(CommonUtil.stringsToList(diary.getImgList()));
        diaryDTO.setVideoList(CommonUtil.stringsToList(diary.getVideoList()));
        diaryDTO.setClassify(classifyService.queryByClassifyId(diary.getClassifyId()));
        return diaryDTO;
    }

    private Diary convertDiaryDtoToDiary(DiaryDTO diaryDTO, Long uid) {
        Diary diary = CommonUtil.copyVo(diaryDTO, Diary.class);
        diary.setId(NumberUtil.genUid());
        diary.setCreateTime(DateUtil.getCurrentDateTimeStr());
        diary.setImgList(CommonUtil.listToString(diaryDTO.getImgList()));
        diary.setVideoList(CommonUtil.listToString(diaryDTO.getVideoList()));
        diary.setAuthor(clientUserService.queryByUserId(uid).getNickname());
        diary.setTags(CommonUtil.listToString(diaryDTO.getTags()));
        return diary;
    }
}
