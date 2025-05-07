package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO seller " +
                    "(name, email, birthdate, base_salary, departmentId) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, seller.getName());
            st.setString(2,seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());


            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0 ){
                ResultSet rs = st.getGeneratedKeys();
                 if(rs.next()){
                     int id = rs.getInt(1);
                     seller.setId(id);
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
    public void update(Seller seller) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE seller " +
                            "SET name = ?, email = ?, birthdate = ?, base_salary = ?, departmentId = ? " +
                            "WHERE id = ? ");
            st.setString(1, seller.getName());
            st.setString(2,seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());
            st.setInt(6, seller.getId());


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
            st = conn.prepareStatement("DELETE FROM seller WHERE id = ?");
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*," +
                    "department.name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.departmentId = department.Id " +
                    "WHERE seller.Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);

                Seller seller = instatiateSeller(rs, dep);

                return  seller;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {

        Seller seller = new Seller();
        seller.setId(rs.getInt("id"));
        seller.setName(rs.getString("name"));
        seller.setEmail(rs.getString("email"));
        seller.setBirthDate(rs.getDate("birthdate"));
        seller.setBaseSalary(rs.getDouble("base_salary"));
        seller.setDepartment(dep);

        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("departmentId"));
        dep.setName(rs.getString("DepName"));

        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*," +
                    "department.name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.departmentId = department.Id " +
                    "ORDER BY name");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){

                Department dep = map.get(rs.getInt("departmentId"));

                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentId"), dep);
                }

                Seller seller = instatiateSeller(rs, dep);
                list.add(seller);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*," +
                    "department.name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.departmentId = department.Id " +
                    "WHERE departmentId = ? " +
                    "ORDER BY name");

            st.setInt(1,department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){

                Department dep = map.get(rs.getInt("departmentId"));

                if(dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentId"), dep);
                }

                Seller seller = instatiateSeller(rs, dep);
                list.add(seller);
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
