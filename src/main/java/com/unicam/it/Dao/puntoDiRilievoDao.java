package com.unicam.it.Dao;


import com.unicam.it.entita.puntoDiRilievo;
import com.unicam.it.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class puntoDiRilievoDao {

    // Metodo per recuperare tutti i punti di rilievo
    public List<puntoDiRilievo> getAllPuntiDiRilievo() {
        List<puntoDiRilievo> puntiDiRilievoList = new ArrayList<>();
        String query = "SELECT * FROM puntidirilievo";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nome = rs.getString("nome");
                String comuneDiRiferimento = rs.getString("comuneDiRiferimento");
                puntoDiRilievo puntoDiRilievo = new puntoDiRilievo(id, nome, comuneDiRiferimento);
                puntiDiRilievoList.add(puntoDiRilievo);
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero dei punti di rilievo: " + e.getMessage());
        }

        return puntiDiRilievoList;
    }

    public puntoDiRilievo getPuntoDiRilievoById(String id) {
        puntoDiRilievo puntoDiRilievo = null;
        String query = "SELECT * FROM puntidirilievo WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String comuneDiRiferimento = rs.getString("comuneDiRiferimento");
                    puntoDiRilievo = new puntoDiRilievo(id, nome, comuneDiRiferimento);
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero del punto di rilievo: " + e.getMessage());
        }

        return puntoDiRilievo;
    }

    public void addPuntoDiRilievo(puntoDiRilievo puntoDiRilievo) {
        String query = "INSERT INTO puntidirilievo (id, nome, comuneDiRiferimento) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, puntoDiRilievo.getId());
            stmt.setString(2, puntoDiRilievo.getName());
            stmt.setString(3, puntoDiRilievo.getComuneDiRiferimento());
            stmt.executeUpdate();
            System.out.println("Punto di rilievo aggiunto con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiunta del punto di rilievo: " + e.getMessage());
        }
    }

    public boolean existsById(String id) {
        String query = "SELECT COUNT(*) FROM puntidirilievo WHERE id = ?";
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
            System.out.println("Errore durante la verifica dell'esistenza del punto di rilievo: " + e.getMessage());
        }

        return exists;
    }

    public void deleteById(String id) {
        String query = "DELETE FROM puntidirilievo WHERE id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Punto di rilievo eliminato con successo.");

        } catch (SQLException e) {
            System.out.println("Errore durante l'eliminazione del punto di rilievo: " + e.getMessage());
        }
    }
}