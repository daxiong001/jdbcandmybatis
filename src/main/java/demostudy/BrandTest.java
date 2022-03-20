package demostudy;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandTest {


    public static Connection getConn() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/db"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        return dataSource.getConnection();
    }

    @Test
    public void testSelectAll() throws Exception{
        String sql = "select * from tv_brand";
        PreparedStatement psp = BrandTest.getConn().prepareStatement(sql);
        ResultSet rs = psp.executeQuery();
        Brand brand = null;
        List<Brand> list = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);
            list.add(brand);
        }
        System.out.println(list);
    }

    @Test
    public void insert() throws Exception{
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球一圈";
        int status = 1;
        String sql ="INSERT INTO `test`.`tv_brand`(`brand_name`, `company_name`, `ordered`, `description`, `status`) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement pst = BrandTest.getConn().prepareStatement(sql);
        pst.setString(1,brandName);
        pst.setString(2,companyName);
        pst.setInt(3,ordered);
        pst.setString(4,description);
        pst.setInt(5,status);

        int i = pst.executeUpdate();
        if (i>0){
            System.out.println("执行成功！！");
        }

    }

    @Test
    public void update() throws Exception{

        Connection conn = BrandTest.getConn();
        String sql = "update tv_brand set ordered = ? where id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,2200);
        pst.setInt(2,4);
        int i = pst.executeUpdate();
        if (i>0){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

    }
}
