package com.unicam.it.Dao;

import com.unicam.it.entita.comune;
import com.unicam.it.dati.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class comuneDao {

    public static List<comune> getAllComuni() {
        List<comune> comuni = new ArrayList<>();
        String query = "SELECT id, nome, regione FROM comuni";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nome = rs.getString("nome");
                String regione = rs.getString("regione");
                comune comune = new comune(id, nome, regione);
                comuni.add(comune);
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero dei comuni: " + e.getMessage());
        }

        return comuni;
    }

    public static comune getComuneById(String id) {
        String query = "SELECT id, nome, regione FROM comuni WHERE id = ?";
        comune comune = null;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String regione = rs.getString("regione");
                    comune = new comune(id, nome, regione);
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il recupero del comune: " + e.getMessage());
        }

        return comune;
    }

    public static void addComune(comune comune) {
        String query = "INSERT INTO comuni (id, nome, regione) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, comune.getId());
            stmt.setString(2, comune.getName());
            stmt.setString(3, comune.getRegione());
            stmt.executeUpdate();
            System.out.println("Comune aggiunto con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiunta del comune: " + e.getMessage());
        }
    }

    public static boolean deleteComuneById(String id) {
        String query = "DELETE FROM comuni WHERE id = ?";
        boolean deleted = false;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            deleted = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Errore durante la cancellazione del comune: " + e.getMessage());
        }

        return deleted;
    }
}