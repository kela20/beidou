package com.beidou.car.web.service;

import com.beidou.car.web.entity.CarPosition;

import java.util.List;
import java.util.Map;

public interface CarPositionService {
    /*
    * 保存车辆坐标
    * */
    Integer insertCarPosition(CarPosition carPosition);

    /*
    * 获取车辆坐标列表（Map里面可以放各种条件）
    * */
    List<CarPosition> listCarPosition(Map<String,Object> queryMap);

    /*
    * 获取最新位置
    * */
    CarPosition getLastCarPosition(String carId);
}
