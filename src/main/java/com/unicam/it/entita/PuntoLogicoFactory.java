package com.unicam.it.entita;

public class PuntoLogicoFactory implements PuntoDiRilievoFactory {
    @Override
    public punto creaPuntoDiRilievo(String tipo, String id, String name, String comuneDiRiferimento) {
        if (tipo.equals("logico")) {
            //richiedere argomento punto logico
            PuntoLogico puntoLogico = new PuntoLogico();
            puntoLogico.setId(id);
            puntoLogico.setName(name);
            puntoLogico.setComuneDiRiferimento(comuneDiRiferimento);
            //settare argomento richiesto
            return puntoLogico;
        } else {
            throw new IllegalArgumentException("Tipo di punto di rilievo non valido: " + tipo);
        }
    }
}
