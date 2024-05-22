package com.unicam.it.Dao;

import com.unicam.it.entita.itinerario;
import com.unicam.it.dati.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class itinerarioDao {

    // Metodo per recuperare un itinerario tramite ID
    public itinerario getItinerarioById(String id) {
        itinerario itinerario = null;
        String query = "SELECT * FROM itinerari WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String titolo = rs.getString("titolo");
                    String comuneDiRiferimento = rs.getString("comuneDiRiferimento");
                    String puntiSelezionati = rs.getString("puntiSelezionati");
                    itinerario = new itinerario(id, titolo, comuneDiRiferimento, puntiSelezionati);
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero dell'itinerario: " + e.getMessage());
        }

        return itinerario;
    }

    public void addItinerario(itinerario itinerario) {
        String query = "INSERT INTO itinerari (id, titolo, comuneDiRiferimento, puntiSelezionati) VALUES (?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, itinerario.getId());
            stmt.setString(2, itinerario.getTitolo());
            stmt.setString(3, itinerario.getComune());
            stmt.setString(4, itinerario.getPunti());
            stmt.executeUpdate();
            System.out.println("Itinerario aggiunto con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiunta dell'itinerario: " + e.getMessage());
        }
    }

    // Metodo per eliminare un itinerario tramite ID
    public void deleteItinerarioById(String id) {
        String query = "DELETE FROM itinerari WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Itinerario eliminato con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione dell'itinerario: " + e.getMessage());
        }
    }


    // Metodo per verificare se un itinerario esiste già tramite ID
    public boolean existsById(String id) {
        String query = "SELECT COUNT(*) FROM itinerari WHERE id = ?";
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
            System.out.println("Errore durante la verifica dell'esistenza dell'itinerario: " + e.getMessage());
        }

        return exists;
    }

}