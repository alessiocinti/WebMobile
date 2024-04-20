package com.unicam.it.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class contenuto {
    @Id
    private String id;

    private String desc;

    private String punto;



    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getDesc() {return desc;}
    public void setDesc(String name) {this.desc = name;}

    public String getPunto() {return punto;}
    public void setPunto(String punto) {this.punto = punto;}
}
