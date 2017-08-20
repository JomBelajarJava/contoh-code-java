package com.jombelajarjava;

import com.jombelajarjava.daftar.PendaftaranService;
import com.jombelajarjava.kucing.Kucing;
import com.jombelajarjava.kucing.SimpleKucingDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

class H2Application {
    private static final String DATABASE_URL = "jdbc:h2:~/tutorialh2/db";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    private final DataSource dataSource;
    private final PendaftaranService pendaftaranService;
    private final Scanner scan;

    /**
     * Create aplikasi.
     */
    H2Application() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DATABASE_URL);
        config.setUsername(NAME);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);

        pendaftaranService = new PendaftaranService(new SimpleKucingDao(dataSource));
        scan = new Scanner(System.in);

        init();
    }

    /**
     * Create table database untuk Kucing jika tiada.
     */
    private void init() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS KUCING (" +
                            "ID IDENTITY, " +
                            "NAMA VARCHAR(100) NOT NULL, " +
                            "JANTINA ENUM('jantan', 'betina'), " +
                            "TARIKH_LAHIR DATE NOT NULL" +
                            ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Daftar kucing menggunakan maklumat daripada input.
     */
    private void daftarKucing() {
        System.out.print("\nMasukkan nama kucing: ");
        final String nama = scan.nextLine().trim();

        System.out.print("Masukkan jantina kucing ([J/j]antan/[B/b]etina): ");
        final char jantina = scan.nextLine().trim().charAt(0);

        System.out.print("Masukkan tarikh lahir (yyyy-mm-dd): ");
        final LocalDate tarikhLahir = LocalDate.parse(scan.nextLine().trim());

        final Kucing kucing = new Kucing(nama, jantina, tarikhLahir);
        final Long id = pendaftaranService.add(kucing).getId();

        System.out.println("\nPendaftaran selesai.\nID kucing anda: " + id);
    }

    /**
     * Kemaskini kucing menggunakan maklumat daripada input. Exit jika tiada kucing yang mempunyai ID yang sama seperti
     * input.
     */
    private void kemaskiniKucing() {
        System.out.print("\nMasukkan ID kucing untuk dikemaskini: ");
        final Long id = Long.parseLong(scan.nextLine().trim());

        final List<Kucing> senaraiKucing = pendaftaranService.searchById(id);
        final Kucing kucing = senaraiKucing.size() > 0 ? senaraiKucing.get(0) : null;
        if (kucing == null) {
            System.out.println("Tiada kucing dengan ID itu.");
            return;
        }

        System.out.println("\nMasukkan nilai baru atau biarkan kosong untuk menggunakan nilai asal:");

        System.out.print("Nama (" + kucing.getNama() + "): ");
        final String inputNama = scan.nextLine().trim();
        final String nama = inputNama.isEmpty() ? kucing.getNama() : inputNama;

        System.out.print("Jantina (" + kucing.getJantina() + "): ");
        final String inputJantina = scan.nextLine().trim();
        final char jantina = inputJantina.isEmpty() ? kucing.getJantina().toString().charAt(0) : inputJantina.charAt(0);

        System.out.print("Tarikh Lahir (" + kucing.getTarikhLahir() + "): ");
        final String inputTarikhLahir = scan.nextLine().trim();
        final LocalDate tarikhLahir = inputTarikhLahir.isEmpty() ? kucing.getTarikhLahir() : LocalDate.parse(inputTarikhLahir);

        final Kucing kucingYangDikemaskini = pendaftaranService.edit(new Kucing(id, nama, jantina, tarikhLahir));
        System.out.println("\nKucing telah dikemaskini:");
        System.out.println(kucingYangDikemaskini);
    }

    /**
     * Padam kucing daripada sistem. Exit jika tiada kucing yang mempunyai ID yang sama seperti input. Pengguna perlu
     * menulis semula nama kucing sebagai pengesahan.
     */
    private void padamKucing() {
        System.out.print("\nMasukkan ID kucing untuk dipadamkan daripada sistem: ");
        final Long id = Long.parseLong(scan.nextLine().trim());

        final List<Kucing> senaraiKucing = pendaftaranService.searchById(id);
        final Kucing kucing = senaraiKucing.size() > 0 ? senaraiKucing.get(0) : null;
        if (kucing == null) {
            System.out.println("Tiada kucing dengan ID itu.");
            return;
        }

        System.out.print("Tulis semula nama kucing untuk dipadamkan (" + kucing.getNama() + "): ");
        final String input = scan.nextLine().trim();
        if (input.equals(kucing.getNama())) {
            pendaftaranService.remove(kucing);
            System.out.println("Kucing telah berjaya dipadamkan.");
        }
    }

    /**
     * Senaraikan semua kucing.
     */
    private void senaraikanKucing() {
        final List<Kucing> senaraiKucing = pendaftaranService.searchAll();

        System.out.println("\nSenarai kucing:");
        for (Kucing kucing : senaraiKucing) {
            System.out.println(kucing);
        }
    }

    /**
     * Cari kucing menggunakan nama daripada input.
     */
    private void cariKucing() {
        System.out.print("Nama kucing untuk dicari: ");
        final String input = scan.nextLine().trim();

        final List<Kucing> senaraiKucing = pendaftaranService.searchByNama(input);

        System.out.println("\nSenarai kucing:");
        for (Kucing kucing : senaraiKucing) {
            System.out.println(kucing);
        }
    }

    /**
     * Mulakan aplikasi.
     */
    void start() {
        System.out.print("Selamat Datang ke Pendaftaran Pet Kucing!\n" +
                "\n" +
                "Tekan nombor mengikut senarai di bawah:\n" +
                "1. Daftar kucing.\n" +
                "2. Kemaskini kucing.\n" +
                "3. Padam maklumat kucing.\n" +
                "4. Senaraikan semua kucing.\n" +
                "5. Cari kucing mengikut nama.\n" +
                "\n" +
                "Pilihan: ");

        final int nombor = Integer.parseInt(scan.nextLine().trim());

        try {
            switch (nombor) {
                case 1:
                    daftarKucing();
                    break;
                case 2:
                    kemaskiniKucing();
                    break;
                case 3:
                    padamKucing();
                    break;
                case 4:
                    senaraikanKucing();
                    break;
                case 5:
                    cariKucing();
                    break;
                default:
                    break;
            }
        } finally {
            scan.close();
        }
    }
}
