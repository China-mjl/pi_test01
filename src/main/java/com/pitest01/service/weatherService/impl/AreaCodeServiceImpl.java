package com.pitest01.service.weatherService.impl;

import com.pitest01.bean.weahterBean.areaCode;
import com.pitest01.mapper.weatherMapper.AreaCodeMapper;
import com.pitest01.service.weatherService.AreaCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AreaCodeService")
public class AreaCodeServiceImpl implements AreaCodeService {

    @Resource
    private AreaCodeMapper areaCodeMapper;

    @Override
    public List<areaCode> findAllAreaCode(String areaName) {
        return areaCodeMapper.selectAllAreaCode(areaName);
    }

    @Override
    public areaCode findOneAreaCode(String areaName) {
        return areaCodeMapper.selectOneAreaCode(areaName);
    }
}
