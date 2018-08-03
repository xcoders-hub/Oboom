package com.example.mapper;


import com.example.entity.Country;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountryMapper {
    int deleteByPrimaryKey(String code);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}