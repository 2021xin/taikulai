package com.learn.health.mapper;

import com.github.pagehelper.Page;
import com.learn.health.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberManner {
    /**
     * 获取所有会员
     * @return
     */
    List<Member> findAll();

    /**
     * 根据条件查询
     * @param queryString
     * @return
     */
    Page<Member> selectByCondition(String queryString);

    /**
     * 新增会员
     * @param member
     */
    void add(Member member);

    /**
     * 删除会员
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    Member findById(Integer id);

    /**
     * 根据电话号码询会员
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 通过用户查询会员
     */
    Member findByName(String name);

    /**
     * 编辑会员
     * @param member
     */
    void edit(Member member);

    /**
     * 根据日期统计会员数，统计指定日期之前的会员数
     * @param start
     * @param end
     * @return
     */
    Integer findMemberCountBeforeDate(@Param("start") String start,@Param("end") String end);

    /**
     * 根据日期统计会员数
     * @param date
     * @return
     */
    Integer findMemberCountByDate(String date);

    /**
     * 根据日期统计会员数，统计指定日期之后的会员数
     * @param date
     * @return
     */
    Integer findMemberCountAfterDate(String date);

    /**
     * 总会员数
     * @return
     */
    Integer findMemberTotalCount();

    /**
     * 关联用户
     * @param memberId
     * @param userId
     */
    void correlationUser(@Param("memberId") Integer memberId,@Param("userId") Long userId);
}
