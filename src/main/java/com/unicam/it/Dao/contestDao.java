package com.unicam.it.Dao;

import com.unicam.it.entita.contest;
import com.unicam.it.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class contestDao {

    // Metodo per recuperare un contest tramite ID
    public contest getContestById(String id) {
        contest contest = null;
        String query = "SELECT * FROM contest WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String titolo = rs.getString("titolo");
                    String descrizione = rs.getString("descrizione");
                    String dataInizio = rs.getString("dataInizio");
                    String dataFine = rs.getString("dataFine");
                    String comuneDiRiferimento = rs.getString("comuneDiRiferimento");
                    contest = new contest(id, titolo, descrizione, dataInizio, dataFine, comuneDiRiferimento);
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero del contest: " + e.getMessage());
        }

        return contest;
    }

    // Metodo per l'aggiunta di un contest
    public void addContest(contest contest) {
        String query = "INSERT INTO contest (id, titolo, descrizione, dataInizio, dataFine, comuneDiRiferimento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contest.getId());
            stmt.setString(2, contest.getTitoloContest());
            stmt.setString(3, contest.getDescrizioneContest());
            stmt.setString(4, contest.getDataInizioContest());
            stmt.setString(5, contest.getDataFineContest());
            stmt.setString(6, contest.getComuneDiRiferimento());
            stmt.executeUpdate();
            System.out.println("Contest aggiunto con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiunta del contest: " + e.getMessage());
        }
    }

    // Metodo per eliminare un contest tramite ID
    public void deleteContestById(String id) {
        String query = "DELETE FROM contest WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Contest eliminato con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione del contest: " + e.getMessage());
        }
    }

    // Metodo per verificare se un contest esiste già tramite ID
    public boolean existsById(String id) {
        String query = "SELECT COUNT(*) FROM contest WHERE id = ?";
        boolean exists = false;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore durante la verifica dell'esistenza del contest: " + e.getMessage());
        }

        return exists;
    }
}