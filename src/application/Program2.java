package application;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Department department = new Department(12, "Tablets");

        System.out.println("=== TEST 1: department insert ===");
//        departmentDao.insert(department);
//        System.out.println("A Department was inserted");

        System.out.println();
        System.out.println("=== TEST 2: department findById ===");
        department = departmentDao.findById(1);
        System.out.println("The department has been found: " + department.getName());

        System.out.println();
        System.out.println("=== TEST 3: department delete ===");
        departmentDao.deleteById(12);
        System.out.println("The department has been deleted");

        System.out.println();
        System.out.println("=== TEST 4: department update ===");
        Department departmentUpdate = departmentDao.findById(11);
        departmentUpdate.setName("Apple com Estromboloff");
        departmentDao.update(departmentUpdate);
        System.out.println("The department has been updated.");

        System.out.println();
        System.out.println("=== TEST 5: department findAll ===");
        List<Department> list = departmentDao.findAll();
        for (Department d : list){
            System.out.println(d);
        }


    }
}
