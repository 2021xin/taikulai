package com.learn.health.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.health.constant.MessageConstant;
import com.learn.health.entity.*;
import com.learn.health.mapper.MemberManner;
import com.learn.health.mapper.OrderManner;
import com.learn.health.mapper.OrdersettingManner;
import com.learn.health.mapper.UserManner;
import com.learn.health.service.OrderService;
import com.learn.health.tool.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Data 2022/9/26
 * @Time 22:40
 * @Author Yan Taixin
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersettingManner ordersettingManner;
    @Autowired
    private MemberManner memberManner;
    @Autowired
    private OrderManner orderManner;
    @Autowired
    private UserManner userManner;

    @Override
    public Result order(Map map,String name) throws Exception {
//      1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();

        OrderSetting orderSetting = ordersettingManner.findByOrderDate(date);
        if (orderSetting == null) {
            // 没有可以选择的日期，无法预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

//      2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        int number = orderSetting.getNumber();
        int reservations = orderSetting.getReservations();
        if (reservations >= number) {
            // 已经预约满
            return new Result(false, MessageConstant.ORDER_FULL);
        }

//      3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
        Member member = memberManner.findByName(name);
        if (member != null) {
            // 判断是否重复预约
            Integer id = member.getId();
            // 获取套餐id
            String setmealId = (String) map.get("setmealId");
            Order order = new Order(id, date, Integer.parseInt(setmealId));
            List<Order> list = orderManner.findByCondition(order);
            if (list != null && list.size() > 0) {
                // 用户在重复预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        } else {
            // 用户信息不完善
            return new Result(false, MessageConstant.INCOMPLETE_INFORMATION);
        }

//      5、预约成功，更新当日的已预约人数
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(date);
        order.setOrderType((String)map.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        orderManner.add(order);

        // 设置已预约人数
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        ordersettingManner.editReservationsByOrderDate(orderSetting);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderManner.findById4Detail(id);
        Date date = (Date)map.get("orderDate");
        if(date != null){
            map.put("orderDate",DateUtils.parseDate2String(date));
        }
        return map;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean, Long uid) {
        Long mid = userManner.queryMidByUid(uid);
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 分页查询
        List<Map<String, Object>> checkGroups = orderManner.queryContain(queryPageBean.getQueryString(),mid);
        PageInfo<Map<String, Object>> checkItemPageInfo = new PageInfo<>(checkGroups);
        return new PageResult(checkItemPageInfo.getTotal(), checkGroups);
    }

    @Override
    public Result improveInformation(Map<String, Object> map, String name) {
        Member member = memberManner.findByName(name);
        member= new Member();
        member.setName(name);
        member.setSex((String)map.get("sex"));
        member.setIdCard((String)map.get("idCard"));
        member.setPhoneNumber((String)map.get("telephoneNumber"));
        member.setRegTime(new Date());
        memberManner.add(member);
        memberManner.correlationUser(member.getId(),userManner.loadUserByUsername(name).getId());
        return new Result(true,MessageConstant.POERATION_SUCCESS);
    }

    @Override
    public Result isMember(String name) {
        Member member = memberManner.findByName(name);
        if (member != null) {
            return new Result(true, null);
        } else {
            // 用户信息不完善
            return new Result(false, null);
        }
    }
}
