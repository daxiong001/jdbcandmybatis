package com.dai.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String pwd = "daixiongkun123";
        Connection conn = DriverManager.getConnection(url,user,pwd);

        String sql = "UPDATE users SET `name` = 'dai' WHERE id = 1;";

        Statement st = conn.createStatement();
        int count = st.executeUpdate(sql);
        System.out.println(count);
        st.close();
        conn.close();
    }
}
