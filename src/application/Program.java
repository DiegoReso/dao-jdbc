package application;

import db.*;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


import java.sql.*;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println("=== TEST 1: seller findByDepartment ===");
        List<Seller> sellerList = sellerDao.findByDepartment(new Department(1, "Computer"));
        for (Seller s: sellerList){
            System.out.println(s);
        }

        System.out.println();
        System.out.println("=== TEST 1: seller findAll ===");
        sellerList = sellerDao.findAll();
        for (Seller s: sellerList){
            System.out.println(s);
        }

    }
}
