package com.unicam.it.controller;

import com.unicam.it.entita.comune;
import com.unicam.it.entita.contest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class contestController {

    private com.unicam.it.repository.contestRepository contestRepository;

    //private String pathContest = "C:/Users/Alessio/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/contest.txt";
    private String pathContest = "C:/Users/frato/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/contest.txt";


    @Autowired
    public contestController(com.unicam.it.repository.contestRepository contestRepository){
        this.contestRepository = contestRepository;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathContest))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contestData = line.split("/");
                if(contestData.length == 6){
                    contest contest = new contest();
                    contest.setId(contestData[0]);
                    contest.setTitoloContest(contestData[1]);
                    contest.setDescrizioneContest(contestData[2]);
                    contest.setDataInizioContest(contestData[3]);
                    contest.setDataFineContest(contestData[4]);
                    contest.setComuneDiRiferimento(contestData[5]);
                    contestRepository.save(contest);
                }else {
                    System.err.println("Invalid product data in contest.txt: " + line);
                }
            }
        }catch (IOException e){
            System.err.println("Error reading contest.txt file: " + e.getMessage());
        }
    }


    @GetMapping("/contest")
    public ResponseEntity<Object> getContest(@PathParam("id") String id){
        return new ResponseEntity<>(contestRepository.findById(id), HttpStatus.OK);
    }


    @PostMapping("/contest")
    public ResponseEntity<Object> addContest(@RequestBody contest contest)
    {
        if (!contestRepository.existsById(contest.getId())) {
            contestRepository.save(contest);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathContest, true))) {
                writer.write(contest.getId() + "/" + contest.getTitoloContest() + "/" + contest.getDescrizioneContest() + "/"
                        + contest.getDataInizioContest() + "/" + contest.getDataFineContest()
                        + "/" + contest.getComuneDiRiferimento() + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error writing to contest.txt file: " + e.getMessage());
            }

            return new ResponseEntity<>("Contest Aggiunto", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il contest esiste già", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/contest/{id}")
    public ResponseEntity<Object> delContest(@PathVariable("id") String id)
    {
        if (contestRepository.existsById(id)) {
            contestRepository.deleteById(id);

            try {
                List<String> updatedLines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(pathContest));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.startsWith(id + "/")) {
                        updatedLines.add(line);
                    }
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(pathContest));
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
                writer.close();

                return new ResponseEntity<>("Contest Cancellato", HttpStatus.OK);
            } catch (IOException e) {
                System.err.println("Error updating contest.txt file: " + e.getMessage());
                return new ResponseEntity<>("Errore durante la cancellazione", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        }
    }

}
