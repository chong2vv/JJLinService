package com.yd.blog.admin.service.impl;

import com.yd.blog.admin.model.dto.DiaryDTO;
import com.yd.blog.admin.model.vo.DiaryPagerRequestVO;
import com.yd.blog.admin.service.ClassifyService;
import com.yd.blog.common.util.CommonUtil;
import com.yd.blog.common.util.DateUtil;
import com.yd.blog.common.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.yd.blog.admin.dao.DiaryDao;
import com.yd.blog.admin.model.entity.Diary;
import com.yd.blog.admin.service.DiaryService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 随手记(Diary)表服务实现类
 *
 * @author makejava
 * @since 2022-10-24 11:10:34
 */
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryDao diaryDao;

    private final ClassifyService classifyService;

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

    @Override
    public void updateStatus(DiaryDTO diaryDTO) {
        Diary diary = converUpdateDiaryDtoToDiary(diaryDTO);
        diaryDao.updateStatus(diary);
    }

    private DiaryDTO convertDiaryToDiaryDto(Diary diary) {
        DiaryDTO diaryDTO = CommonUtil.copyVo(diary, DiaryDTO.class);
        diaryDTO.setTags(CommonUtil.stringsToList(diary.getTags()));
        diaryDTO.setImgList(CommonUtil.stringsToList(diary.getImgList()));
        diaryDTO.setVideoList(CommonUtil.stringsToList(diary.getVideoList()));
        diaryDTO.setClassify(classifyService.queryByClassifyId(diary.getClassifyId()));
        return diaryDTO;
    }

    private Diary converUpdateDiaryDtoToDiary(DiaryDTO diaryDTO) {
        Diary diary = CommonUtil.copyVo(diaryDTO, Diary.class);
        diary.setTags(CommonUtil.listToString(diaryDTO.getTags()));
        diary.setImgList(CommonUtil.listToString(diaryDTO.getImgList()));
        diary.setVideoList(CommonUtil.listToString(diaryDTO.getVideoList()));
        return diary;
    }

    private Diary convertDiaryDtoToDiary(DiaryDTO diaryDTO) {
        Diary diary = CommonUtil.copyVo(diaryDTO, Diary.class);
        diary.setId(NumberUtil.genUid());
        diary.setCreateTime(DateUtil.getCurrentDateTimeStr());
        diary.setImgList(CommonUtil.listToString(diaryDTO.getImgList()));
        diary.setVideoList(CommonUtil.listToString(diaryDTO.getVideoList()));
        diary.setTags(CommonUtil.listToString(diaryDTO.getTags()));
        return diary;
    }
}

