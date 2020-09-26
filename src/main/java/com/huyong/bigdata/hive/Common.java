package com.huyong.bigdata.hive;

import java.sql.*;

public class Common {
    public static Connection getCollection() throws SQLException {
        return  DriverManager.getConnection("jdbc:hive2://192.168.43.199:10000/test", "hive", "");
    }

    public static void main(String[] args) throws SQLException {
        Connection con = getCollection();
        Statement stmt = con.createStatement();
        String tableName = "testHiveDriverTable";
        stmt.execute("drop table if exists " + tableName);
        stmt.execute("create table " + tableName + " (key string, value string)");
        // show tables
        String sql = "show tables '" + tableName + "'";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            System.out.println(res.getString(1));
        }
        // describe table
        sql = "describe " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        // load data into table
        // NOTE: filepath has to be local to the hive server
        // NOTE: /tmp/a.txt is a ctrl-A separated file with two fields per line
        String filepath = "/tmp/temp.txt";
        sql = "load data inpath '" + filepath + "' into table " + tableName;
        System.out.println("Running: " + sql);
        stmt.execute(sql);

        // select * query
        sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getString(1)) + "\t" + res.getString(2));
        }

        // regular hive query
        sql = "select count(1) from " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}
