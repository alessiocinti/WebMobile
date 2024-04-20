package com.unicam.it.entita;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class contest {

    @Id
    private String id;
    private String titoloContest;
    private String descrizioneContest;
    private String dataInizioContest;
    private String dataFineContest;
    private String comuneDiRiferimento;



    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getTitoloContest() {return titoloContest;}
    public void setTitoloContest(String titoloContest) {this.titoloContest = titoloContest;}

    public String getDescrizioneContest() {return descrizioneContest;}
    public void setDescrizioneContest(String descrizioneContest) {this.descrizioneContest = descrizioneContest;}

    public String getDataInizioContest() {return dataInizioContest;}
    public void setDataInizioContest(String dataInizioContest) {this.dataInizioContest = dataInizioContest;}

    public String getDataFineContest() {return dataFineContest;}
    public void setDataFineContest(String dataFineContest) {this.dataFineContest = dataFineContest;}

    public String getComuneDiRiferimento() {return comuneDiRiferimento;}
    public void setComuneDiRiferimento(String comuneDiRiferimento) {this.comuneDiRiferimento = comuneDiRiferimento;}
}
