package application;

import db.*;
import model.entities.Department;


import java.sql.*;

public class Program {
    public static void main(String[] args) {

        Department obj = new Department(1,"Books");
        System.out.println(obj);

    }
}
