package com.unicam.it.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class contenuto {
    @Id
    private String id;

    private String desc;

    private String regione;



    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getdesc() {return desc;}
    public void setdesc(String name) {this.desc = name;}

    public String getRegione() {return regione;}
    public void setRegione(String regione) {this.regione = regione;}
}
