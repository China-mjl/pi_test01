package com.pitest01.mapper.weatherMapper;

import com.pitest01.bean.weahterBean.areaCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaCodeMapper {

    public List<areaCode> selectAllAreaCode(String areaName);

    public areaCode selectOneAreaCode(String areaName);
}
