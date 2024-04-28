package com.unicam.it.controller;

import com.unicam.it.entita.contenuto;
import com.unicam.it.entita.puntoDiRilievo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class contenutoController {
    private com.unicam.it.repository.contenutoRepository contenutoRepository;

    private String pathContenuto = "C:/Users/Alessio/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/contenuti.txt";
    //private String pathContenuto = "C:/Users/frato/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/contenuti.txt";

    @Autowired
    public contenutoController(com.unicam.it.repository.contenutoRepository contenutoRepository){
        this.contenutoRepository = contenutoRepository;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathContenuto))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contenutoData = line.split("/");
                if(contenutoData.length == 3){
                    contenuto contenuto = new contenuto();
                    contenuto.setId(contenutoData[0]);
                    contenuto.setDesc(contenutoData[1]);
                    contenuto.setPunto(contenutoData[2]);
                    contenutoRepository.save(contenuto);
                }else {
                    System.err.println("Invalid product data in contenuti.txt.txt: " + line);
                }
            }
        }catch (IOException e){
            System.err.println("Error reading contenuti.txt.txt file: " + e.getMessage());
        }
    }
    @GetMapping("/contenuto")
    public ResponseEntity<Object> getContenuto(@PathParam("id") String id){
        return new ResponseEntity<>(contenutoRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/contenuto")
    public ResponseEntity<Object> addContenuto(@RequestBody contenuto contenuto)
    {
        if (!contenutoRepository.existsById(contenuto.getId())) {
            contenutoRepository.save(contenuto);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathContenuto, true))) {
                writer.write(contenuto.getId() + "/" + contenuto.getDesc() + "/" + contenuto.getPunto()  + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error writing to contenuti.txt.txt file: " + e.getMessage());
            }

            return new ResponseEntity<>("Contenuto Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il contenuto esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/contenuto/{id}")
    public ResponseEntity<Object> delContenuto(@PathVariable("id") String id)
    {
        if (contenutoRepository.existsById(id)) {
            contenutoRepository.deleteById(id);

            try {
                List<String> updatedLines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(pathContenuto));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(id + "/")) {
                        updatedLines.add(line);
                    }
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(pathContenuto));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
                writer.close();

                return new ResponseEntity<>("Contenuto Cancellato", HttpStatus.OK);
            } catch (IOException e) {
                System.err.println("Error updating contenuto.txt file: " + e.getMessage());
                return new ResponseEntity<>("Errore durante la cancellazione", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Contenuto non trovato", HttpStatus.NOT_FOUND);
        }
    }
}
