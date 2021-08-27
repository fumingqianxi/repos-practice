package com.itheima.jdbc;

import com.itheima.jdbc.domain.Emp;
import com.itheima.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo4 {

    public static void main(String[] args) {
        List<Emp> list = new JdbcDemo4().findAll2();
        System.out.println(list);
        System.out.println(list.size());
    }

    public List<Emp> findAll() {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "0510");
            String sql = "select * from emp";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            Emp emp;
            list = new ArrayList<Emp>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                // 创建emp对象,并赋值
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);
                //装载集合
                list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return list;
    }

    public List<Emp> findAll2() {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from emp";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            Emp emp;
            list = new ArrayList<Emp>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                // 创建emp对象,并赋值
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);
                //装载集合
                list.add(emp);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, statement, conn);
        }
        return list;
    }
}
