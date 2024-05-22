package com.unicam.it.entita;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class itinerario {
    @Id
    private String id;

    private String titolo;

    private String comune;

    private String punti;

    public itinerario(String id, String titolo, String comuneDiRiferimento, String puntiSelezionati)
    {
        this.id = id;
        this.titolo =titolo;
        this.comune = comuneDiRiferimento;
        this.punti = puntiSelezionati;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getTitolo() {return titolo;}
    public void setTitolo(String titolo) {this.titolo = titolo;}

    public String getComune() {return comune;}
    public void setComune(String comune) {this.comune = comune;}

    public String getPunti() {return punti;}
    public void setPunti(String punti) {this.punti = punti;}

}
