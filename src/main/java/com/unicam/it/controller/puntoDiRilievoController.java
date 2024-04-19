package com.unicam.it.controller;


import com.unicam.it.entita.puntoDiRilievo;
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

    private com.unicam.it.repository.puntoDiRilievoRepository puntoDiRilievoRepository;

    //private String pathPuntiDiRilievo = "C:/Users/Alessio/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/puntiDiRilievo.txt";
    private String pathPuntiDiRilievo = "C:/Users/frato/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/puntiDiRilievo.txt";

    @Autowired
    public puntoDiRilievoController(com.unicam.it.repository.puntoDiRilievoRepository puntoDiRilievoRepository){
        this.puntoDiRilievoRepository = puntoDiRilievoRepository;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathPuntiDiRilievo))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] puntoDiRilievoData = line.split(" ");
                if(puntoDiRilievoData.length == 3){
                    puntoDiRilievo puntoDiRilievo = new puntoDiRilievo();
                    puntoDiRilievo.setId(puntoDiRilievoData[0]);
                    puntoDiRilievo.setName(puntoDiRilievoData[1]);
                    puntoDiRilievo.setComuneDiRiferimento(puntoDiRilievoData[2]);
                    puntoDiRilievoRepository.save(puntoDiRilievo);
                }else {
                    System.err.println("Invalid product data in puntiDiRilievo.txt: " + line);
                }
            }
        }catch (IOException e){
            System.err.println("Error reading puntiDiRilievo.txt file: " + e.getMessage());
        }
    }

    @RequestMapping(value="/puntiDiRilievo")
    public ResponseEntity<Object> getPuntiDiRilievo(){
        return new ResponseEntity<>(puntoDiRilievoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/puntoDiRilievo")
    public ResponseEntity<Object> getPuntoDiRilievo(@PathParam("id") String id){
        return new ResponseEntity<>(puntoDiRilievoRepository.findById(id), HttpStatus.OK);
    }


    @PostMapping("/puntoDiRilievo")
    public ResponseEntity<Object> addPuntoDiRilievo(@RequestBody puntoDiRilievo puntoDiRilievo)
    {
        if (!puntoDiRilievoRepository.existsById(puntoDiRilievo.getId())) {
            puntoDiRilievoRepository.save(puntoDiRilievo);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathPuntiDiRilievo, true))) {
                writer.write(puntoDiRilievo.getId() + " " + puntoDiRilievo.getName() + " " + puntoDiRilievo.getComuneDiRiferimento()  + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error writing to puntiDiRilievo.txt file: " + e.getMessage());
            }

            return new ResponseEntity<>("PuntoDiRilievo Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il PuntoDiRilievo esiste già", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/puntoDiRilievo/{id}")
    public ResponseEntity<Object> delPuntoDiRilievo(@PathVariable("id") String id)
    {
        if (puntoDiRilievoRepository.existsById(id)) {
            puntoDiRilievoRepository.deleteById(id);

            try {
                List<String> updatedLines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(pathPuntiDiRilievo));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(id + " ")) {
                        updatedLines.add(line);
                    }
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(pathPuntiDiRilievo));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
                writer.close();

                return new ResponseEntity<>("PuntoDiRilievo Cancellato", HttpStatus.OK);
            } catch (IOException e) {
                System.err.println("Error updating puntiDiRilievo.txt file: " + e.getMessage());
                return new ResponseEntity<>("Errore durante la cancellazione", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("PuntoDiRilievo non trovato", HttpStatus.NOT_FOUND);
        }
    }

}
