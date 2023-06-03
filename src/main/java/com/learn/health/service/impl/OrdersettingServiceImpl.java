package com.learn.health.service.impl;

import com.learn.health.entity.OrderSetting;
import com.learn.health.mapper.OrdersettingManner;
import com.learn.health.service.OrdersettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Data 2022/9/16
 * @Time 17:56
 * @Author Yan Taixin
 */
@Service
@Transactional
public class OrdersettingServiceImpl implements OrdersettingService {
    @Autowired
    private OrdersettingManner ordersettingManner;

    @Override
    public void add(List<OrderSetting> data) {
        List<OrderSetting> update = new ArrayList<>();
        List<OrderSetting> insert = new ArrayList<>();

        // 查询所有数据
        if(data != null && data.size() > 0){
            for (OrderSetting datum : data) {
                long count  = ordersettingManner.queryByData(datum.getOrderDate());
                if(count > 0 ){
                    // 已经存在
                    update.add(datum);
                }else{
                    // 不存在
                    insert.add(datum);
                }
            }
        }

        if(!update.isEmpty()) ordersettingManner.update(update);
        if(!insert.isEmpty()) ordersettingManner.insert(insert);
    }

    @Override
    public List<Map<String, Object>> getOrderData(String date) {
        String begin = date + "-1";
        String end = date + "-31";
        List<OrderSetting> list = ordersettingManner.getOrderData(begin,end);
        List<Map<String,Object>> orderDate= new ArrayList<>();
        if(list != null && list.size() > 0){
            for (OrderSetting orderDatum : list) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("date",orderDatum.getOrderDate().getDate());
                map.put("number",orderDatum.getNumber());
                map.put("reservations",orderDatum.getReservations());
                orderDate.add(map);
            }
        }
        return orderDate;
    }

    @Override
    public void updateOrderNumber(OrderSetting orderSetting) {
        // 查询数据库中是否有这条记录，没有插入，有更新
        long count  = ordersettingManner.queryByData(orderSetting.getOrderDate());
        if(count > 0 ){
            // 已经存在
            ordersettingManner.updateOrderNumber(orderSetting);
        }else{
            // 不存在
            ArrayList<OrderSetting> list = new ArrayList<>();
            list.add(orderSetting);
            ordersettingManner.insert(list);
        }
    }

}
