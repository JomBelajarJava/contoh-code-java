package com.jombelajarjava.kucing;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleKucingDao implements KucingDao {

    private final String url;
    private final String name;
    private final String password;

    /**
     * Create DAO untuk Kucing menggunakan plain JDBC.
     *
     * @param url URL database
     * @param name Usernama database
     * @param password Password database
     */
    public SimpleKucingDao(final String url, final String name, final String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Kucing> findAll() {
        final List<Kucing> senaraiKucing = new ArrayList<>();
        final String query = "SELECT * FROM KUCING";

        try (Connection conn = DriverManager.getConnection(url, name, password);
             Statement stmt = conn.createStatement()) {

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while (resultSet.next()) {
                    final Long idResult = resultSet.getLong("ID");
                    final String namaResult = resultSet.getString("NAMA");
                    final Jantina jantinaResult = Jantina.fromString(resultSet.getString("JANTINA"));
                    final LocalDate tarikhLahirResult = LocalDate.parse(resultSet.getString("TARIKH_LAHIR"));

                    senaraiKucing.add(new Kucing(idResult, namaResult, jantinaResult, tarikhLahirResult));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return senaraiKucing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Kucing> findById(final Long id) {
        final List<Kucing> senaraiKucing = new ArrayList<>();
        final String query = "SELECT * FROM KUCING WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(url, name, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    final Long idResult = resultSet.getLong("ID");
                    final String namaResult = resultSet.getString("NAMA");
                    final Jantina jantinaResult = Jantina.fromString(resultSet.getString("JANTINA"));
                    final LocalDate tarikhLahirResult = LocalDate.parse(resultSet.getString("TARIKH_LAHIR"));

                    senaraiKucing.add(new Kucing(idResult, namaResult, jantinaResult, tarikhLahirResult));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return senaraiKucing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Kucing> findByNama(final String nama) {
        final List<Kucing> senaraiKucing = new ArrayList<>();
        final String query = "SELECT * FROM KUCING WHERE NAMA = ?";

        try (Connection conn = DriverManager.getConnection(url, name, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    final Long idResult = resultSet.getLong("ID");
                    final String namaResult = resultSet.getString("NAMA");
                    final Jantina jantinaResult = Jantina.fromString(resultSet.getString("JANTINA"));
                    final LocalDate tarikhLahirResult = LocalDate.parse(resultSet.getString("TARIKH_LAHIR"));

                    senaraiKucing.add(new Kucing(idResult, namaResult, jantinaResult, tarikhLahirResult));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return senaraiKucing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kucing insertKucing(final Kucing kucing) {
        final String insertQuery = "INSERT INTO KUCING(NAMA, JANTINA, TARIKH_LAHIR) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, name, password);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, kucing.getNama());
            stmt.setString(2, kucing.getJantina().toString());
            stmt.setDate(3, Date.valueOf(kucing.getTarikhLahir()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    final Long id = generatedKeys.getLong(1);
                    return kucing.cloneWithId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kucing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kucing updateKucing(final Kucing kucing) {
        final String updateQuery = "UPDATE KUCING SET NAMA = ?, JANTINA = ?, TARIKH_LAHIR = ? WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(url, name, password);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setString(1, kucing.getNama());
            stmt.setString(2, kucing.getJantina().toString());
            stmt.setDate(3, Date.valueOf(kucing.getTarikhLahir()));
            stmt.setLong(4, kucing.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kucing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteKucing(final Kucing kucing) {
        final String deleteQuery = "DELETE FROM KUCING WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(url, name, password);
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            stmt.setLong(1, kucing.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
