package com.dai.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestResultSet {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String pwd = "daixiongkun123";
        Connection conn = DriverManager.getConnection(url,user,pwd);

        List<Account> list = new ArrayList<>();
        Statement sts = conn.createStatement();
        String sql = "select * from users";
        ResultSet rs = sts.executeQuery(sql);

        while (rs.next()){
            Account account = new Account();
            int id = rs.getInt(1);
            String name = rs.getString("name");
            String extra = rs.getString("extra");

            account.setId(id);
            account.setName(name);
            account.setExtra(extra);
            list.add(account);
        }
        System.out.println(list);
    }
}
