package com.learn.health.service.impl;

import com.learn.health.entity.Member;
import com.learn.health.mapper.MemberManner;
import com.learn.health.service.MemberService;
import com.learn.health.tool.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Data 2022/10/1
 * @Time 17:39
 * @Author Yan Taixin
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberManner memberManner;

    @Override
    public Member findByTelephone(String telephone) {
        return memberManner.findByTelephone(telephone);
    }

    @Override
    public void addMember(Member member) {
        String password = member.getPassword();
        if(password != null){
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberManner.add(member);
    }

    @Override
    public List<Integer> findMemberNumberByDate(List<String> date) {
        List<Integer> list = new ArrayList<>();
        for (String str : date) {
            String start = str + "-01";
            String end = str + "-31";
            list.add(memberManner.findMemberCountBeforeDate(start,end));
        }
        return list;
    }
}
