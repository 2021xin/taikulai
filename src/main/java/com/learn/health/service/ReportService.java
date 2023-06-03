package com.learn.health.service;

import java.util.Map;

/**
 * @Data 2022/10/15
 * @Time 21:34
 * @Author Yan Taixin
 */
public interface ReportService {
    /**
     * 获取运营数据
     * @return
     */
    Map<String,Object> getBusinessReport() throws Exception;
}
