package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DerpatmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DerpatmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO department " +
                            "(id, name) " +
                            "VALUES " +
                            "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, department.getId());
            st.setString(2,department.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0 ){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Unexpected error! No rows affected");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE department " +
                    "SET id = ?, name = ?" +
                    "WHERE id = ? ");
            st.setInt(1,department.getId());
            st.setString(2, department.getName());
            st.setInt(3,department.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM department WHERE id = ?");
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * " +
                    "FROM department " +
                    "WHERE id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);

                return  dep;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("id"));
        dep.setName(rs.getString("name"));

        return dep;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * " +
                    "FROM department");

            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while(rs.next()){

                Department department = instantiateDepartment(rs);
                list.add(department);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
