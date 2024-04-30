package com.unicam.it.entita;

public class PuntoFisico implements punto {
    private String id;
    private String name;
    private String comuneDiRiferimento;
    private double longitudine;
    private double latitudine;

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

    public double getLongitudine() {
        return longitudine;
    }
    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }
    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }
}
