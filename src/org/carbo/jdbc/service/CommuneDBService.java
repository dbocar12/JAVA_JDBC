package org.carbo.jdbc.service;

import org.carbo.jdbc.model.Commune;

import java.sql.PreparedStatement;

public interface CommuneDBService {
    PreparedStatement writeCommune(Commune commune) ;
    Commune getCommuneById(String id) ;
    Commune getCommuneByName(String name);
    int countCommuneByCP(String codePostal);
}

