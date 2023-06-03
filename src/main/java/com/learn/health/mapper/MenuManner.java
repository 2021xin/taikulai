package com.learn.health.mapper;

import com.learn.health.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Data 2022/12/20
 * @Time 19:22
 * @Author Yan Taixin
 */
@Mapper
public interface MenuManner {
    /**
     * 根据所有rid查询mid
     * @param rids
     * @return
     */
    List<Long> findAllMidByRid(@Param("rids") List<Long> rids);

    /**
     * 根据菜单id集合查询所有菜单
     * @param mids
     * @return
     */
    List<Menu> findAllByMid(@Param("mids") List<Long> mids);

    /**
     * 根据父id查询所有子菜单
     * @param id
     * @return
     */
    List<Menu> getAllByParentId(Long id);
}
