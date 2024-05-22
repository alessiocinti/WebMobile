package com.unicam.it.controller;
import com.unicam.it.entita.comune;
import com.unicam.it.Dao.itinerarioDao;
import com.unicam.it.entita.itinerario;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class itinerarioController {

    @Autowired
    private itinerarioDao itinerarioDao;


    // Metodo per recuperare un itinerario tramite ID
    @GetMapping("/itinerario")
    public ResponseEntity<itinerario> getItinerario(@PathParam("id") String id) {
        itinerario itinerario = itinerarioDao.getItinerarioById(id);
        if (itinerario != null) {
            return new ResponseEntity<>(itinerario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodo per aggiungere un itinerario
    @PostMapping("/itinerario")
    public ResponseEntity<String> addItinerario(@RequestBody itinerario itinerario) {
        if (!itinerarioDao.existsById(itinerario.getId())) {
            itinerarioDao.addItinerario(itinerario);
            return new ResponseEntity<>("Itinerario Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("L'itinerario esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo per eliminare un itinerario tramite ID
    @DeleteMapping("/itinerario/{id}")
    public ResponseEntity<String> deleteItinerario(@PathVariable("id") String id) {
        if (itinerarioDao.existsById(id)) {
            itinerarioDao.deleteItinerarioById(id);
            return new ResponseEntity<>("Itinerario Cancellato", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Itinerario non trovato", HttpStatus.NOT_FOUND);
        }
    }

}
