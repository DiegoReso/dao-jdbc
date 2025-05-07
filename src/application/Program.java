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
        System.out.println("=== TEST 2: seller findByDepartment ===");
        List<Seller> sellerList = sellerDao.findByDepartment(new Department(1, "Computer"));
        for (Seller s: sellerList){
            System.out.println(s);
        }

        System.out.println();
        System.out.println("=== TEST 3: seller findAll ===");
        sellerList = sellerDao.findAll();
        for (Seller s: sellerList){
            System.out.println(s);
        }

        Department newDepartment = new Department(2, null);

        System.out.println();
        System.out.println("=== TEST 4: seller insert ===");
//        Seller newSeller =new Seller(null, "Greg", "greg@email.com", new Date(), 4000.0, newDepartment);
//        sellerDao.insert(newSeller);
//        System.out.println("Adicionado novo id : " + newSeller.getId());


        System.out.println();
        System.out.println("=== TEST 5: seller update ===");
        Seller sellerUpdate = sellerDao.findById(2);
        sellerUpdate.setName("Willian Estromboloffe");
        sellerDao.update(sellerUpdate);
        System.out.println("Atualizado no banco de dados!");

        System.out.println();
        System.out.println("=== TEST 6: seller delete ===");
        sellerDao.deleteById(1);
        System.out.println("Vendedor excluido do banco de dados!");
    }
}
