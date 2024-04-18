package com.unicam.it.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class puntoDiRilievo {

    @Id
    private String id;
    private String name;
    private String comuneDiRiferimento;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getComuneDiRiferimento() {return comuneDiRiferimento;}

    public void setComuneDiRiferimento(String comuneDiRiferimento) {
        this.comuneDiRiferimento = comuneDiRiferimento;
    }
}
