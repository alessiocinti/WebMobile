package com.unicam.it.controller;

import com.unicam.it.entita.comune;
import com.unicam.it.Dao.contestDao;
import com.unicam.it.entita.contest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class contestController {

    @Autowired
    private contestDao contestDAO;


    // Metodo per recuperare un contest tramite ID
    @GetMapping("/contest")
    public ResponseEntity<contest> getContestById(@PathParam("id") String id) {
        contest contest = contestDAO.getContestById(id);
        if (contest != null) {
            return new ResponseEntity<>(contest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/contest")
    public ResponseEntity<String> addContest(@RequestBody contest contest) {
        if (!contestDAO.existsById(contest.getId())) {
            contestDAO.addContest(contest);
            return new ResponseEntity<>("Contest Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il contest esiste già", HttpStatus.BAD_REQUEST);
        }
    }


    // Metodo per eliminare un contest tramite ID
    @DeleteMapping("/contest/{id}")
    public ResponseEntity<String> deleteContestById(@PathVariable("id") String id) {
        if (contestDAO.existsById(id)) {
            contestDAO.deleteContestById(id);
            return new ResponseEntity<>("Contest Cancellato", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        }
    }

}
