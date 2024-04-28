package com.unicam.it.controller;
import com.unicam.it.entita.comune;
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
    private com.unicam.it.repository.itinerarioRepository itinerarioRepository;

    private String pathItinerario = "C:/Users/Alessio/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/itinerari.txt";
    //private String pathItinerario = "C:/Users/frato/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/itinerari.txt";

    @Autowired
    public itinerarioController(com.unicam.it.repository.itinerarioRepository itinerarioRepository){
        this.itinerarioRepository = itinerarioRepository;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathItinerario))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itinerarioData = line.split("/");
                if(itinerarioData.length == 4){
                    itinerario itinerario = new itinerario();
                    itinerario.setId(itinerarioData[0]);
                    itinerario.setTitolo(itinerarioData[1]);
                    itinerario.setComune(itinerarioData[2]);
                    itinerario.setPunti(itinerarioData[3]);
                    itinerarioRepository.save(itinerario);
                }else {
                    System.err.println("Invalid product data in itinerario.txt: " + line);
                }
            }
        }catch (IOException e){
            System.err.println("Error reading itinerario.txt file: " + e.getMessage());
        }
    }

    @GetMapping("/itinerario")
    public ResponseEntity<Object> getItinerario(@PathParam("id") String id){
        return new ResponseEntity<>(itinerarioRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/itinerario")
    public ResponseEntity<Object> addItinerario(@RequestBody itinerario itinerario)
    {
        if (!itinerarioRepository.existsById(itinerario.getId())) {
            itinerarioRepository.save(itinerario);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathItinerario, true))) {
                writer.write(itinerario.getId() + "/" + itinerario.getTitolo() + "/" + itinerario.getComune()
                        + "/" + itinerario.getPunti() + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error writing to itinerari.txt file: " + e.getMessage());
            }

            return new ResponseEntity<>("Itinerario Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("L'itinerario esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/itinerario/{id}")
    public ResponseEntity<Object> delItinerario(@PathVariable("id") String id)
    {
        if (itinerarioRepository.existsById(id)) {
            itinerarioRepository.deleteById(id);

            try {
                List<String> updatedLines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(pathItinerario));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(id + "/")) {
                        updatedLines.add(line);
                    }
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(pathItinerario));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
                writer.close();

                return new ResponseEntity<>("Itinerario Cancellato", HttpStatus.OK);
            } catch (IOException e) {
                System.err.println("Error updating itinerari.txt file: " + e.getMessage());
                return new ResponseEntity<>("Errore durante la cancellazione", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Itinerario non trovato", HttpStatus.NOT_FOUND);
        }
    }

}
