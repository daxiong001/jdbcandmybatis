package com.dai.mapper;

import com.dai.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {

    List<Brand> selectAllBrand();

    Brand selectById(int id);

//    List<Brand> selectByCondition(@Param("status") int status,@Param("companyName")String companyName,
//                                  @Param("brandName")String brandName);

//    List<Brand> selectByCondition(Brand brand);

    List<Brand> selectByCondition(Map map);

    List<Brand> selectByConditionSingle(Brand brand);

    void add(Brand brand);

    int update(Brand brand);

    void delete(int id);

    void deleteByIds(int[] ids);
}
