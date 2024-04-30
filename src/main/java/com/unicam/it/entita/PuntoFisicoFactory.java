package com.unicam.it.entita;

public class PuntoFisicoFactory implements PuntoDiRilievoFactory {
    @Override
    public punto creaPuntoDiRilievo(String tipo, String id, String name, String comuneDiRiferimento) {
        if (tipo.equals("fisico")) {
            //richiedere latitudine e longitudine punto fisico
            PuntoFisico puntoFisico = new PuntoFisico();
            puntoFisico.setId(id);
            puntoFisico.setName(name);
            puntoFisico.setComuneDiRiferimento(comuneDiRiferimento);
            //settare latitudine e longitudine richiesta
            return puntoFisico;
        } else {
            throw new IllegalArgumentException("Tipo di punto di rilievo non valido: " + tipo);
        }
    }
}
