package com.coin.mapper;

import com.coin.entity.TDictI18n;
import com.coin.entity.TDictI18nExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDictI18nMapper {
    long countByExample(TDictI18nExample example);

    int deleteByExample(TDictI18nExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDictI18n row);

    int insertSelective(TDictI18n row);

    List<TDictI18n> selectByExample(TDictI18nExample example);

    TDictI18n selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDictI18n row, @Param("example") TDictI18nExample example);

    int updateByExample(@Param("row") TDictI18n row, @Param("example") TDictI18nExample example);

    int updateByPrimaryKeySelective(TDictI18n row);

    int updateByPrimaryKey(TDictI18n row);
}