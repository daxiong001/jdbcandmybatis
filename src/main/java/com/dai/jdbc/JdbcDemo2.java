package com.dai.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDemo2 {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String pwd = "daixiongkun123";
        Connection conn = DriverManager.getConnection(url,user,pwd);

        String sql = "select * from users";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString("name");
            String money = rs.getString("extra");
            System.out.println(id);
            System.out.println(name);
            System.out.println(money);
        }
        rs.close();
        st.close();
        conn.close();
    }
}
