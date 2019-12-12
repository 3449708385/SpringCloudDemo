package com.mgp.lcnservertest.dao;

import com.mgp.commons.bean.LcnTest;
import com.mgp.commons.bean.LcnTestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcnTestMapper {
    long countByExample(LcnTestExample example);

    int deleteByExample(LcnTestExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LcnTest record);

    int insertSelective(LcnTest record);

    List<LcnTest> selectByExample(LcnTestExample example);

    LcnTest selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LcnTest record, @Param("example") LcnTestExample example);

    int updateByExample(@Param("record") LcnTest record, @Param("example") LcnTestExample example);

    int updateByPrimaryKeySelective(LcnTest record);

    int updateByPrimaryKey(LcnTest record);
}