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
    private Date dataInizioContest;
    private Date dataFineContest;
    private String comuneDiRiferimento;



    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getTitoloContest() {return titoloContest;}
    public void setTitoloContest(String titoloContest) {this.titoloContest = titoloContest;}

    public String getDescrizioneContest() {return descrizioneContest;}
    public void setDescrizioneContest(String descrizioneContest) {this.descrizioneContest = descrizioneContest;}

    public Date getDataInizioContest() {return dataInizioContest;}
    public void setDataInizioContest(Date dataInizioContest) {this.dataInizioContest = dataInizioContest;}

    public Date getDataFineContest() {return dataFineContest;}
    public void setDataFineContest(Date dataFineContest) {this.dataFineContest = dataFineContest;}

    public String getComuneDiRiferimento() {return comuneDiRiferimento;}
    public void setComuneDiRiferimento(String comuneDiRiferimento) {this.comuneDiRiferimento = comuneDiRiferimento;}
}
