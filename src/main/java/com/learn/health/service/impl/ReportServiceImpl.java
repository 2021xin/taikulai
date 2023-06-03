package com.learn.health.service.impl;

import com.learn.health.mapper.MemberManner;
import com.learn.health.mapper.OrderManner;
import com.learn.health.service.ReportService;
import com.learn.health.tool.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Data 2022/10/15
 * @Time 21:35
 * @Author Yan Taixin
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberManner memberManner;
    @Autowired
    private OrderManner orderManner;

    @Override
    public Map<String, Object> getBusinessReport() throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 报表日期
        String today = DateUtils.parseDate2String(DateUtils.getToday());
        // 今日新增会员
        Integer todayNewMember = memberManner.findMemberCountByDate(today);
        // 总会员数
        Integer totalMember = memberManner.findMemberTotalCount();
        // 获取本周新增会员
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        Integer thisWeekNewMember = memberManner.findMemberCountBeforeDate(thisWeekMonday, today);
        // 获取本月新增会员
        String fristDayThistMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        Integer thisMonthNewMember = memberManner.findMemberCountBeforeDate(fristDayThistMonth, today);
        // 今日预约数
        Integer todayOrderNumber = orderManner.findOrderCountByDate(today);
        // 本周预约数
        Integer thisWeekOrderNumber = orderManner.findOrderCountAfterDate(thisWeekMonday);
        // 本月预约数
        Integer thisMonthOrderNumber = orderManner.findOrderCountAfterDate(fristDayThistMonth);
        // 今日到诊数
        Integer todayVisitsNumber = orderManner.findVisitsCountByDate(today);
        // 本周到诊数
        Integer thisWeekVisitsNumber = orderManner.findVisitsCountAfterDate(thisWeekMonday);
        // 本月到诊数
        Integer thisMonthVisitsNumber = orderManner.findVisitsCountAfterDate(fristDayThistMonth);
        // 热门套餐
        List<Map> hotSetmeal = orderManner.findHotSetmeal();

        map.put("reportDate", today);
        map.put("todayNewMember", todayNewMember);
        map.put("totalMember", totalMember);
        map.put("thisWeekNewMember", thisWeekNewMember);
        map.put("thisMonthNewMember", thisMonthNewMember);
        map.put("todayOrderNumber", todayOrderNumber);
        map.put("thisWeekOrderNumber", thisWeekOrderNumber);
        map.put("thisMonthOrderNumber", thisMonthOrderNumber);
        map.put("todayVisitsNumber", todayVisitsNumber);
        map.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        map.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        map.put("hotSetmeal", hotSetmeal);
        return map;
    }
}
