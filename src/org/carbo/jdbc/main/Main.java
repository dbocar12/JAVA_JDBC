package org.carbo.jdbc.main;

import org.carbo.jdbc.service.CommuneDBService;
import org.carbo.jdbc.service.CommuneImporter;
import org.carbo.jdbc.service.impl.CommuneCSVImporter;
import org.carbo.jdbc.service.impl.CommuneDBServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        try {

            Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/Commune?"
                            + "user=sa&password=sa" +"&useSSL=false");
            String path = "data/communes.csv";

            CommuneImporter testCSV = new CommuneCSVImporter(connect);
           testCSV.importCommunes(path);

            CommuneDBService test = new CommuneDBServiceImpl(connect);
            System.out.println("----------------------------------- GET COMMUNE BY ID ----------------------------");
            System.out.println(test.getCommuneById("97301"));

            System.out.println("----------------------------------- GET COMMUNE BY NAME ----------------------------");
            System.out.println(test.getCommuneByName("ZELLENBERG"));

            System.out.println("----------------------------------- COUNT CODE POSTAL START WITH ----------------------------");

            System.out.println("Count = "+ test.countCommuneByCP("33"));


            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
