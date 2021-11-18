package org.carbo.jdbc.service.impl;

import org.carbo.jdbc.model.Commune;
import org.carbo.jdbc.service.CommuneDBService;
import org.carbo.jdbc.service.CommuneImporter;
import org.carbo.jdbc.utils.CommuneUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class CommuneCSVImporter implements CommuneImporter {

    private Connection connect ;
    private Map<String,Commune> communes = new HashMap<>();
    private PreparedStatement preparedStatement ;
    public CommuneCSVImporter(Connection connect) {
        this.connect = connect;

    }

    @Override
    public void importCommunes(String path) {

         CommuneDBServiceImpl communeDBService = new CommuneDBServiceImpl(connect);
         PreparedStatement statement = null;
         try {
            BufferedReader lineReader=new BufferedReader(new FileReader(path));
            String lineText;
            lineReader.readLine(); // ignore first line
            while ((lineText = lineReader.readLine())!=null){
                Commune com = CommuneUtils.lineToCommune.apply(lineText);
                Commune prevCommune = communes.put(com.getCodeINSEE(),com);
                if (prevCommune != null) {
                    System.out.println("doublon:"+prevCommune);
                } else {
                    statement = communeDBService.writeCommune(com);
                }
            }
             int [] nb_lines_inserted = statement.executeBatch();
            //System.out.println(Arrays.toString(nb_lines_inserted).length());
            lineReader.close();
            System.out.println("Data has been inserted successfully.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
