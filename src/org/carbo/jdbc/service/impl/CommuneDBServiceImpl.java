package org.carbo.jdbc.service.impl;

import org.carbo.jdbc.model.Commune;
import org.carbo.jdbc.service.CommuneDBService;

import java.sql.*;

public class CommuneDBServiceImpl implements CommuneDBService{
    private Connection connect;
    private PreparedStatement preparedStatement = null;
    private final String INSERT_COMMUNE = "insert into communes values (?, ?, ?, ?) ";
    private final String SELECT_COMMUNE = "select * from communes WHERE codeINSEE = ?";

    public CommuneDBServiceImpl(Connection connect) {
        this.connect = connect;
        try {
            preparedStatement = connect.prepareStatement(INSERT_COMMUNE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public PreparedStatement writeCommune(Commune commune) {
        try {
            preparedStatement.setString(1, commune.getCodeINSEE());
            preparedStatement.setString(2, commune.getNomCommune());
            preparedStatement.setString(3, commune.getCodePostal());
            preparedStatement.setString(4, commune.getLibelleAcheminement());
            preparedStatement.addBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return preparedStatement;
    }

    @Override
    public Commune getCommuneById(String id) {

        try {

            preparedStatement = connect.prepareStatement(SELECT_COMMUNE);
            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Commune commune = new Commune();
            while (resultSet.next()) {
                commune.setCodeINSEE(resultSet.getString("codeINSEE"));
                commune.setNomCommune(resultSet.getString("nomCommune"));
                commune.setCodePostal(resultSet.getString("codePostal"));
                commune.setLibelleAcheminement(resultSet.getString("libelleAcheminement"));
            }
            return commune;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Commune getCommuneByName(String name) {
        try {
            String SELECT_COMMUNE_BY_NAME = "select * from communes WHERE nomCommune = ?";
            preparedStatement = connect.prepareStatement(SELECT_COMMUNE_BY_NAME);
            preparedStatement.setString(1,name);

            ResultSet resultSet = preparedStatement.executeQuery();

            Commune commune = new Commune();
            while (resultSet.next()) {
                commune.setCodeINSEE(resultSet.getString("codeINSEE"));
                commune.setNomCommune(resultSet.getString("nomCommune"));
                commune.setCodePostal(resultSet.getString("codePostal"));
                commune.setLibelleAcheminement(resultSet.getString("libelleAcheminement"));
            }
            return commune;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public int countCommuneByCP(String codePostal) {
        try {
            int count  = 0;
            String SELECT_COMMUNE_START_WITH_CODE = "select * from communes where codePostal LIKE ?";
            preparedStatement = connect.prepareStatement(SELECT_COMMUNE_START_WITH_CODE);
            preparedStatement.setString(1,codePostal+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
            return count;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


}
