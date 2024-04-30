
package com.unicam.it.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class puntoDiRilievo {

    @Id
    private String id;
    private String name;
    private String comuneDiRiferimento;

    private String argomento;
    private String longitudine;
    private String latitudine;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getComuneDiRiferimento() {return comuneDiRiferimento;}

    public void setComuneDiRiferimento(String comuneDiRiferimento) {
        this.comuneDiRiferimento = comuneDiRiferimento;
    }

    public String getArgomento() {
        return argomento;
    }

    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }
}



