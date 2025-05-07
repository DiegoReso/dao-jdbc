package application;

import db.*;
import model.entities.Department;
import model.entities.Seller;


import java.sql.*;
import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department obj = new Department(1,"Books");
        System.out.println(obj);

        Seller seller = new Seller(1,"Diego Reis", "diego@me.com", new Date(), 3000.0 , obj);

        System.out.println(seller);

    }
}
