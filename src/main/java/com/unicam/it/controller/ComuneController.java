package com.unicam.it.controller;


import com.unicam.it.entita.Comune;
import com.unicam.it.repository.ComuneRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class ComuneController {

    private ComuneRepository comuneRepository;

    //private String pathComune = "C:/Users/Alessio/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/comuni.txt";
    private String pathComune = "C:/Users/frato/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/comuni.txt";

    @Autowired
    public ComuneController(ComuneRepository comuneRepository){
        this.comuneRepository = comuneRepository;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathComune))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] comuneData = line.split(" ");
                if(comuneData.length == 3){
                    Comune comune = new Comune();
                    comune.setId(comuneData[0]);
                    comune.setName(comuneData[1]);
                    comune.setRegione(comuneData[2]);
                    comuneRepository.save(comune);
                }else {
                    System.err.println("Invalid product data in comune.txt: " + line);
                }
            }
        }catch (IOException e){
            System.err.println("Error reading comune.txt file: " + e.getMessage());
        }
    }


    @RequestMapping(value="/comuni")
    public ResponseEntity<Object> getComuni(){
        return new ResponseEntity<>(comuneRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/comune")
    public ResponseEntity<Object> getComune(@PathParam("id") String id){
        return new ResponseEntity<>(comuneRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/comune")
    public ResponseEntity<Object> addComune(@RequestBody Comune comune)
    {
        if (!comuneRepository.existsById(comune.getId())) {
            comuneRepository.save(comune);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathComune, true))) {
                writer.write(comune.getId() + " " + comune.getName() + " " + comune.getRegione()  + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error writing to comuni.txt file: " + e.getMessage());
            }

            return new ResponseEntity<>("Comune Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il comune esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> delComune(@PathVariable("id") String id)
    {
        if (comuneRepository.existsById(id)) {
            comuneRepository.deleteById(id);

            // Update prodotti.txt (assuming simple deletion by ID)
            try {
                List<String> updatedLines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(pathComune));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(id + " ")) {
                        updatedLines.add(line);
                    }
                }
                reader.close();

                // Rewrite file with updated content
                BufferedWriter writer = new BufferedWriter(new FileWriter(pathComune));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
                writer.close();

                return new ResponseEntity<>("Comune Cancellato", HttpStatus.OK);
            } catch (IOException e) {
                System.err.println("Error updating comuni.txt file: " + e.getMessage());
                // You may want to consider returning an error response here
                return new ResponseEntity<>("Errore durante la cancellazione", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Comune non trovato", HttpStatus.NOT_FOUND);
        }
    }


}
