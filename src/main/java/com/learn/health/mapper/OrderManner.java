package com.learn.health.mapper;

import com.learn.health.entity.Order;
import com.learn.health.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderManner {
    /**
     * 新增预约
     * @param order
     */
    void add(Order order);

    /**
     * 动态条件查询
     * @param order
     * @return
     */
    List<Order> findByCondition(Order order);

    /**
     * 根据预约id查询预约信息，包括体检人信息、套餐信息
     * @param id
     * @return
     */
    Map findById4Detail(Integer id);

    /**
     * 根据日期统计预约数
     * @param date
     * @return
     */
    Integer findOrderCountByDate(String date);

    /**
     * 根据日期统计预约数，统计指定日期之后的预约数
     * @param date
     * @return
     */
    Integer findOrderCountAfterDate(String date);

    /**
     * 根据日期统计到诊数
     * @param date
     * @return
     */
    Integer findVisitsCountByDate(String date);

    /**
     * 根据日期统计到诊数，统计指定日期之后的到诊数
     * @param date
     * @return
     */
    Integer findVisitsCountAfterDate(String date);

    /**
     * 热门套餐，查询前5条
     * @return
     */
    List<Map> findHotSetmeal();

    /**
     * 查询订单
     *
     * @param queryString
     * @param uid
     * @return
     */
    List<Map<String, Object>> queryContain(@Param("value") String queryString,@Param("mid") Long mid);
}
