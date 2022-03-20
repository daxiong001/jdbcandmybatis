package com.dai.test;

import com.dai.mapper.BrandMapper;
import com.dai.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    public static SqlSession getConn() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        return sqlSessionFactory.openSession();
    }

    @Test
    public void testSelectAll() throws IOException {
        BrandMapper mapper = MyBatisTest.getConn().getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAllBrand();
        System.out.println(brands);
    }

    @Test
    public void testSelectOne() throws IOException {
        int id = 1;
        BrandMapper mapper = MyBatisTest.getConn().getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        System.out.println(brand);
    }

    @Test
    public void tetSelectByCondition() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);

        Map hashMap = new HashMap<>();
        hashMap.put("status",status);
        hashMap.put("companyName",companyName);
        //hashMap.put("brandName",brandName);

        BrandMapper mapper = MyBatisTest.getConn().getMapper(BrandMapper.class);

        //List<Brand> brands = mapper.selectByCondition(status, companyName, brandName);
        //List<Brand> brands = mapper.selectByCondition(brand);
        List<Brand> brands = mapper.selectByCondition(hashMap);
        System.out.println(brands);
    }

    @Test
    public void testSelectBySingle() throws Exception{
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";
        Brand brand = new Brand();
        //brand.setCompanyName(companyName);
        SqlSession sqlSession = MyBatisTest.getConn();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);
    }

    @Test
    public void testAdd() throws Exception{
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        String description = "sdfdsfsdf";
        Integer ordered = 12;

        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        SqlSession sqlSession = MyBatisTest.getConn();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        System.out.println(brand.getId());
        sqlSession.commit();
    }

    @Test
    public void testUpdate() throws Exception{
        int status = 1;
        String companyName = "1111华为,中华有为";
//        String brandName = "华为2222";
//        String description = "中华有为中华有为sdfdsfsdf";
//        Integer ordered = 12;

        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setDescription(description);
//        brand.setOrdered(ordered);
        brand.setId(status);

        SqlSession sqlSession = MyBatisTest.getConn();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.update(brand);
        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testDelete() throws Exception{
        int id = 1;
        Brand brand = new Brand();
        brand.setId(id);
        SqlSession sqlSession = MyBatisTest.getConn();
        sqlSession.getMapper(BrandMapper.class).delete(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteIds()throws Exception{
        int[] id = {2,3,4};
        Brand brand = new Brand();

        SqlSession sqlSession = MyBatisTest.getConn();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(id);
        sqlSession.commit();
    }
}
