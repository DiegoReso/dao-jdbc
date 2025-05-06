package application;

import db.*;


import java.sql.*;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st =null;

        try{

            conn = DB.getConnection();
            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET base_salary = 2500 WHERE departmentId = 1");

            int rows2 = st.executeUpdate("UPDATE seller SET base_salary = 2000 WHERE departmentId = 2");

            conn.commit();

            System.out.println("rows1 " + rows1);
            System.out.println("rows1 " + rows2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by " + e.getMessage());
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback! Caused by " + e.getMessage());
            }
        }finally {
            DB.closeConnection();
            DB.closeStatement(st);
        }

    }
}
