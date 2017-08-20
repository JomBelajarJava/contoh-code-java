package com.jombelajarjava.daftar;

import com.jombelajarjava.kucing.Kucing;
import com.jombelajarjava.kucing.KucingDao;

import java.util.List;

public class PendaftaranService {

    private final KucingDao kucingDAO;

    /**
     * Create service Pendaftaran
     *
     * @param kucingDAO DAO Kucing
     */
    public PendaftaranService(final KucingDao kucingDAO) {
        this.kucingDAO = kucingDAO;
    }

    /**
     * Cari semua Kucing yang berdaftar.
     *
     * @return Senarai Kucing
     */
    public List<Kucing> searchAll() {
        return kucingDAO.findAll();
    }

    /**
     * Cari semua Kucing mengikut ID.
     *
     * @param id ID kucing
     * @return Senarai Kucing
     */
    public List<Kucing> searchById(final Long id) {
        return kucingDAO.findById(id);
    }

    /**
     * Cari semua Kucing mengikut nama.
     *
     * @param nama Nama kucing
     * @return Senarai Kucing
     */
    public List<Kucing> searchByNama(final String nama) {
        return kucingDAO.findByNama(nama);
    }

    /**
     * Daftar Kucing.
     *
     * @param kucing Kucing yang ingin didaftarkan
     * @return Kucing yang telah didaftar
     */
    public Kucing add(final Kucing kucing) {
        return kucingDAO.insertKucing(kucing);
    }

    /**
     * Kemaskini Kucing.
     *
     * @param kucing Kucing yang ingin dikemaskini
     * @return Kucing yang telah dikemaskini
     */
    public Kucing edit(final Kucing kucing) {
        return kucingDAO.updateKucing(kucing);
    }

    /**
     * Padam Kucing daripada sistem.
     *
     * @param kucing Kucing yang ingin dipadam
     * @return Boolean sama ada Kucing telah berjaya dipadam
     */
    public boolean remove(final Kucing kucing) {
        return kucingDAO.deleteKucing(kucing);
    }
}
