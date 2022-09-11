package com.ry.time.admin.service.impl;

import com.ry.time.admin.dao.ClassifyDao;
import com.ry.time.admin.model.entity.Classify;
import com.ry.time.admin.model.vo.ClassifyPagerRequestVO;
import com.ry.time.admin.service.ClassifyService;
import com.ry.time.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassifyService实现类
 *
 * @author gongjiguang
 * @date 2022/9/10
 */
@Service
@RequiredArgsConstructor
public class ClassifyServiceImpl implements ClassifyService {

    private final ClassifyDao classifyDao;

    @Override
    public List<Classify> getClassifyList(ClassifyPagerRequestVO classifyPagerRequestVO) {
        classifyPagerRequestVO.initPager();
        return classifyDao.queryPager(classifyPagerRequestVO);
    }

    @Override
    public int count(Integer status) {
        return classifyDao.count(status);
    }

    @Override
    public Classify queryByClassifyId(Integer id) {
        return classifyDao.queryById(id);
    }

    @Override
    public boolean existByClassifyId(Integer id) {
        Classify classify = classifyDao.queryById(id);
        return classify != null;
    }

    @Override
    public void update(Classify classify) {
        classifyDao.update(classify);
    }

    @Override
    public void updateStatus(Classify classify) {
        classifyDao.updateStatus(classify);
    }

    @Override
    public Classify create(Classify classify) {
        classify.setCreateTime(DateUtil.getCurrentDateTimeStr());
        classifyDao.insert(classify);
        return classify;
    }
}