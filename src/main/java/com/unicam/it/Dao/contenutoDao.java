package com.unicam.it.Dao;

import com.unicam.it.entita.contenuto;
import com.unicam.it.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class contenutoDao {


    // Metodo per recuperare un contenuto tramite ID
    public contenuto getContenutoById(String id) {
        contenuto contenuto = null;
        String query = "SELECT * FROM contenuti WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String desc = rs.getString("descrizione");
                    String puntoDiRiferimento = rs.getString("puntoDiRiferimento");
                    contenuto = new contenuto(id, desc, puntoDiRiferimento);
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero del contenuto: " + e.getMessage());
        }

        return contenuto;
    }

    public void addContenuto(contenuto contenuto) {
        String query = "INSERT INTO contenuti (id, descrizione, puntoDiRiferimento) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contenuto.getId());
            stmt.setString(2, contenuto.getDesc());
            stmt.setString(3, contenuto.getPunto());
            stmt.executeUpdate();
            System.out.println("Contenuto aggiunto con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiunta del contenuto: " + e.getMessage());
        }
    }

    // Metodo per eliminare un contenuto tramite ID
    public void deleteContenutoById(String id) {
        String query = "DELETE FROM contenuti WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Contenuto eliminato con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione del contenuto: " + e.getMessage());
        }
    }

    public boolean existsById(String id) {
        String query = "SELECT COUNT(*) FROM contenuti WHERE id = ?";
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
            System.out.println("Errore durante la verifica dell'esistenza del contenuto: " + e.getMessage());
        }

        return exists;
    }


}