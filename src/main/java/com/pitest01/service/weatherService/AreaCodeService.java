package com.pitest01.service.weatherService;


import com.pitest01.bean.weahterBean.areaCode;

import java.util.List;

public interface AreaCodeService {

    public List<areaCode> findAllAreaCode(String areaName);

    public areaCode findOneAreaCode(String areaName);

}
