package org.carbo.jdbc.model;

public class Commune {
    private String codeINSEE;
    private String nomCommune;
    private String codePostal;
    private String libelleAcheminement;

    public Commune() {

    }

    public Commune(String codeINSEE, String nomCommune, String codePostal, String libelleAcheminement) {
        this.codeINSEE = codeINSEE;
        this.nomCommune = nomCommune;
        this.codePostal = codePostal;
        this.libelleAcheminement = libelleAcheminement;
    }

    public String getCodeINSEE() {
        return codeINSEE;
    }

    public void setCodeINSEE(String codeINSEE) {
        this.codeINSEE = codeINSEE;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLibelleAcheminement() {
        return libelleAcheminement;
    }

    public void setLibelleAcheminement(String libelleAcheminement) {
        this.libelleAcheminement = libelleAcheminement;
    }

    @Override
    public String toString() {
        return "Commune{" +
                "codeINSEE='" + codeINSEE + '\'' +
                ", nomCommune='" + nomCommune + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", libelleAcheminement='" + libelleAcheminement + '\'' +
                '}';
    }
}
