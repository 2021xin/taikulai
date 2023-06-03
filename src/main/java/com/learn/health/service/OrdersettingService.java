package com.learn.health.service;

import com.learn.health.entity.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Data 2022/9/16
 * @Time 17:55
 * @Author Yan Taixin
 */
public interface OrdersettingService {
    /**
     * 批量导入预约设置数据
     * @param data
     */
    void add(List<OrderSetting> data);

    /**
     * List<Map<String, Object>> getOrderData(String date)
     * @param date
     * @return
     */
    List<Map<String, Object>> getOrderData(String date);

    /**
     * 修改可预约人数
     * @param orderSetting
     */
    void updateOrderNumber(OrderSetting orderSetting);
}
