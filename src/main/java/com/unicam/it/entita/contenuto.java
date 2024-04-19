package com.unicam.it.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class contenuto {
    @Id
    private String id;

    private String desc;

    private String comune;



    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getDesc() {return desc;}
    public void setDesc(String name) {this.desc = name;}

    public String getComune() {return comune;}
    public void setComune(String comune) {this.comune = comune;}
}
