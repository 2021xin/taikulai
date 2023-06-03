package com.learn.health.service;


import com.learn.health.entity.Member;

import java.util.List;

/**
 * @Data 2022/10/1
 * @Time 17:26
 * @Author Yan Taixin
 */
public interface MemberService {
    /**
     * 根据手机号查询会员信息
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 新建会员
     * @param member
     */
    void addMember(Member member);

    /**
     * 根据日期查询会员数量
     * @param date
     * @return
     */
    List<Integer> findMemberNumberByDate(List<String> date);
}
