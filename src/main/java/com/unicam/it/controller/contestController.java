package com.unicam.it.controller;

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

    private String pathContest = "C:/Users/Alessio/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/contest.txt";
    //private String pathContest = "C:/Users/frato/OneDrive/Desktop/IdSProject/src/main/java/com/unicam/it/dati/contest.txt";


    @Autowired
    public contestController(com.unicam.it.repository.contestRepository contestRepository){
        this.contestRepository = contestRepository;
        try (BufferedReader reader = new BufferedReader(new FileReader(pathContest))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contestData = line.split(" ");
                if(contestData.length == 6){
                    contest contest = new contest();
                    contest.setId(contestData[0]);
                    contest.setTitoloContest(contestData[1]);
                    contest.setDescrizioneContest(contestData[2]);
                    contestRepository.save(contest);
                }else {
                    System.err.println("Invalid product data in contest.txt: " + line);
                }
            }
        }catch (IOException e){
            System.err.println("Error reading contest.txt file: " + e.getMessage());
        }
    }





}
