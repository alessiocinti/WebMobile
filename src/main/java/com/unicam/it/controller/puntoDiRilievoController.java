package com.unicam.it.controller;

import com.unicam.it.entita.puntoDiRilievo;
import com.unicam.it.Dao.puntoDiRilievoDao;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class puntoDiRilievoController {

    @Autowired
    private puntoDiRilievoDao puntoDiRilievoDao;

    // Metodo per recuperare tutti i punti di rilievo
    @GetMapping("/puntiDiRilievo")
    public ResponseEntity<List<puntoDiRilievo>> getPuntiDiRilievo() {
        List<puntoDiRilievo> puntiDiRilievoList = puntoDiRilievoDao.getAllPuntiDiRilievo();
        return new ResponseEntity<>(puntiDiRilievoList, HttpStatus.OK);
    }


    // Metodo per recuperare un punto di rilievo tramite ID
    @GetMapping("/puntoDiRilievo")
    public ResponseEntity<puntoDiRilievo> getPuntoDiRilievo(@PathParam("id") String id) {
        puntoDiRilievo puntoDiRilievo = puntoDiRilievoDao.getPuntoDiRilievoById(id);
        if (puntoDiRilievo != null) {
            return new ResponseEntity<>(puntoDiRilievo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodo per aggiungere un punto di rilievo
    @PostMapping("/puntoDiRilievo")
    public ResponseEntity<String> addPuntoDiRilievo(@RequestBody puntoDiRilievo puntoDiRilievo) {
        if (!puntoDiRilievoDao.existsById(puntoDiRilievo.getId())) {
            puntoDiRilievoDao.addPuntoDiRilievo(puntoDiRilievo);
            return new ResponseEntity<>("PuntoDiRilievo Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il PuntoDiRilievo esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/puntoDiRilievo/{id}")
    public ResponseEntity<String> delPuntoDiRilievo(@PathVariable("id") String id) {
        if (puntoDiRilievoDao.existsById(id)) {
            puntoDiRilievoDao.deleteById(id);
            return new ResponseEntity<>("PuntoDiRilievo Cancellato", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("PuntoDiRilievo non trovato", HttpStatus.NOT_FOUND);
        }
    }
}
