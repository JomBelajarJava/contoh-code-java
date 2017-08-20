package com.jombelajarjava.kucing;

import java.util.List;

public interface KucingDao {

    /**
     * Dapatkan semua senarai Kucing yang ada dalam sistem.
     *
     * @return Senarai Kucing
     */
    List<Kucing> findAll();

    /**
     * Dapatkan semua Kucing yang mempunyai ID seperti yang diberi.
     *
     * @param id ID kucing
     * @return Senarai Kucing yang mempunyai ID yang sama seperti yang diberi
     */
    List<Kucing> findById(Long id);

    /**
     * Dapatkan semua Kucing yang mempunyai nama seperti yang diberi.
     *
     * @param nama Nama kucing
     * @return Senarai Kucing yang mempunyai nama yang sama seperti yang diberi
     */
    List<Kucing> findByNama(String nama);

    /**
     * Masukkan object Kucing ke dalam database.
     *
     * @param kucing Object Kucing yang ingin disimpan
     * @return Object Kucing yang telah disimpan
     */
    Kucing insertKucing(Kucing kucing);

    /**
     * Kemaskini object Kucing di dalam database.
     *
     * @param kucing Object Kucing yang ingin dikemaskini
     * @return Object Kucing yang telah dikemaskini
     */
    Kucing updateKucing(Kucing kucing);

    /**
     * Padam object Kucing dari database.
     *
     * @param kucing Object Kucing yang ingin dipadam
     * @return Boolean sama ada Kucing berjaya dipadam
     */
    boolean deleteKucing(Kucing kucing);
}
