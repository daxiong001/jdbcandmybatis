package com.dai.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String pwd = "daixiongkun123";
        Connection conn = DriverManager.getConnection(url,user,pwd);

        String sql1 = "UPDATE users SET `name` = 'dai2' WHERE id = 1";
        String sql2 = "UPDATE users SET `name` = 'dai3' WHERE id = 1w";

        Statement st = conn.createStatement();

        int count1 = 0;
        int count2 = 0;
        try {
            conn.setAutoCommit(false);
            count1 = st.executeUpdate(sql1);
            count2 = st.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.rollback();
            System.out.println(count1);
            System.out.println(count2);
            st.close();
            conn.close();
        }

    }
}
