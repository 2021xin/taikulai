package com.learn.health.mapper;

import com.learn.health.entity.OrderSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Data 2022/9/16
 * @Time 17:57
 * @Author Yan Taixin
 */
@Mapper
public interface OrdersettingManner {
    /**
     * 批量导入预约设置数据
     * @param data
     */
    void insert(@Param("data") List<OrderSetting> data);

    /**
     * 更新数据
     * @param data
     */
    void update(@Param("data")List<OrderSetting> data);

    /**
     * 根据日期查询记录
     * @param orderDate
     */
    long queryByData(Date orderDate);

    /**
     * 获取预约数据
     *
     * @param begin
     * @param date
     * @return
     */
    List<OrderSetting> getOrderData(@Param("begin") String begin,@Param("end") String date);

    /**
     * 修改可预约人数
     * @param orderSetting
     */
    void updateOrderNumber(OrderSetting orderSetting);

    /**
     * 查询预约日期
     * @param date
     * @return
     */
    OrderSetting findByOrderDate(Date date);

    /**
     * 更新可预约人数
     * @param orderSetting
     */
    void editReservationsByOrderDate(OrderSetting orderSetting);
}
