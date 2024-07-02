package com.unicam.it.controller;

import com.unicam.it.entita.contenuto;
import com.unicam.it.Dao.contenutoDao;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class contenutoController {
    @Autowired
    private contenutoDao contenutoDao;


    // Metodo per recuperare un contenuto tramite ID
    @GetMapping("/contenuto")
    public ResponseEntity<contenuto> getContenutoById(@PathParam("id") String id) {
        contenuto contenuto = contenutoDao.getContenutoById(id);
        if (contenuto != null) {
            return new ResponseEntity<>(contenuto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contenuto")
    public ResponseEntity<String> addContenuto(@RequestBody contenuto contenuto) {
        if (!contenutoDao.existsById(contenuto.getId())) {
            contenutoDao.addContenuto(contenuto);
            return new ResponseEntity<>("Contenuto Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il contenuto esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo per eliminare un contenuto tramite ID
    @DeleteMapping("/contenuto/{id}")
    public ResponseEntity<String> deleteContenutoById(@PathVariable("id") String id) {
        if (contenutoDao.existsById(id)) {
            contenutoDao.deleteContenutoById(id);
            return new ResponseEntity<>("Contenuto Cancellato", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Contenuto non trovato", HttpStatus.NOT_FOUND);
        }
    }
}
