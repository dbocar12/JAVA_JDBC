package org.carbo.jdbc.utils;

import org.carbo.jdbc.model.Commune;

import java.util.function.Function;

public class CommuneUtils {

     public static Function<String, Commune> lineToCommune = line -> {
        String[] elements = line.split(";");
        String codeINSEE = elements[0];
        String nomCommune = elements[1];
        String codePostal = elements[2];
        String libelleAcheminement = elements[4];
        return new Commune(codeINSEE, nomCommune, codePostal,libelleAcheminement);
    };

}
