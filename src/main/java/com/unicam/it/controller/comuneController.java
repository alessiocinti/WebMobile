package com.unicam.it.controller;

import com.unicam.it.dati.databaseConnection;
import com.unicam.it.Dao.comuneDao;
import com.unicam.it.entita.comune;
import com.unicam.it.repository.comuneRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class comuneController {

    private final comuneRepository comuneRepository;

    @Autowired
    public comuneController(comuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    /*
    @RequestMapping(value = "/comuni", method = RequestMethod.GET)
    public ResponseEntity<Object> getComuni() {
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
    }*/

    @GetMapping("/comuni")
    public ResponseEntity<List<comune>> getComuni() {
        List<comune> comuni = comuneDao.getAllComuni();
        return new ResponseEntity<>(comuni, HttpStatus.OK);
    }

    @GetMapping("/comune")
    public ResponseEntity<Object> getComune(@RequestParam("id") String id) {
        comune comune = comuneDao.getComuneById(id);
        if (comune != null) {
            return new ResponseEntity<>(comune, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comune non trovato", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/comune")
    public ResponseEntity<String> addComune(@RequestBody comune comune) {
        try {
            comuneDao.addComune(comune);
            return new ResponseEntity<>("Comune aggiunto con successo", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Errore durante l'aggiunta del comune: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/comune/{id}")
    public ResponseEntity<String> delComune(@PathVariable("id") String id) {
        boolean deleted = comuneDao.deleteComuneById(id);
        if (deleted) {
            return new ResponseEntity<>("Comune cancellato con successo", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comune non trovato", HttpStatus.NOT_FOUND);
        }
    }
}