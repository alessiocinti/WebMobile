package com.unicam.it.entita;

public class PuntoLogico implements punto {
    private String id;
    private String name;
    private String comuneDiRiferimento;
    private String argomento;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getComuneDiRiferimento() {
        return comuneDiRiferimento;
    }
    @Override
    public void setComuneDiRiferimento(String comuneDiRiferimento) {
        this.comuneDiRiferimento=comuneDiRiferimento;
    }

    public String getArgomento() {
        return argomento;
    }
    public void setArgomento(String argomento) {
        this.argomento = argomento;
    }
}
