package com.starfire.familytree.folk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starfire.familytree.folk.entity.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author luzh
 * @since 2019-08-09
 */
public interface PeopleMapper extends BaseMapper<People> {

    public List<People> getPeoplesByGeneration(@Param("gen") int gen);

    public People getForefather(@Param("gen") int gen);

    Page<People> getPage(Page<People> page, @Param("param")Map<String, Object> param);

    People getFamilyTree(String branch);

    List<People> getPeopleByName(@Param("name")String name);

    public String getBranchByName(@Param("name")String name);

    public List<Map<String,Object>> getNamesByPinyin(@Param("pinyin")String pinyin);

    public List<Map<String, Object>> getPeopleNumByGender(@Param("villageCode")String villageCode);

    public List<Map<String, Object>> getPeopleNumByEducation(@Param("villageCode")String villageCode);

    public List<Map<String, Object>> getPeopleNumByProTeam(@Param("villageCode")String villageCode);

    public List<Map<String, Object>> getPeopleNumByBranch(@Param("villageCode")String villageCode);

    public List<Map<String, Object>> getGenderByGenerations(@Param("villageCode")String villageCode);

}
