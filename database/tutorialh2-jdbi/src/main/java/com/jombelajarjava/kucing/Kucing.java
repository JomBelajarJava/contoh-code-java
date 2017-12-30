package com.jombelajarjava.kucing;

import java.time.LocalDate;

public class Kucing {

    private static Long DEFAULT_ID = 0L;

    private final Long id;
    private final String nama;
    private final Jantina jantina;
    private final LocalDate tarikhLahir;

    /**
     * Create Kucing menggunakan id default.
     *
     * @param nama Nama kucing
     * @param jantina Jantina kucing
     * @param tarikhLahir Tarikh lahir kucing
     */
    public Kucing(final String nama, final Jantina jantina, final LocalDate tarikhLahir) {
        this(DEFAULT_ID, nama, jantina, tarikhLahir);
    }

    /**
     * Create Kucing menggunakan id default.
     *
     * @param nama Nama kucing
     * @param jantina Jantina kucing
     * @param tarikhLahir Tarikh lahir kucing
     */
    public Kucing(final String nama, final char jantina, final LocalDate tarikhLahir) {
        this(nama, Jantina.fromChar(jantina), tarikhLahir);
    }

    /**
     * Create Kucing.
     *
     * @param id ID kucing
     * @param nama Nama kucing
     * @param jantina Jantina kucing
     * @param tarikhLahir Tarikh lahir kucing
     */
    public Kucing(final Long id, final String nama, final Jantina jantina, final LocalDate tarikhLahir) {
        this.id = id;
        this.nama = nama;
        this.jantina = jantina;
        this.tarikhLahir = tarikhLahir;
    }

    /**
     * Create Kucing.
     *
     * @param id ID kucing
     * @param nama Nama kucing
     * @param jantina Jantina kucing
     * @param tarikhLahir Tarikh lahir kucing
     */
    public Kucing(final Long id, final String nama, final char jantina, final LocalDate tarikhLahir) {
        this(id, nama, Jantina.fromChar(jantina), tarikhLahir);
    }

    /**
     * Clone object Kucing menggunakan ID yang diberi.
     *
     * @param id ID kucing
     * @return Object Kucing
     */
    public Kucing cloneWithId(final Long id) {
        return new Kucing(id, nama, jantina, tarikhLahir);
    }

    /**
     * Dapatkan ID.
     *
     * @return ID kucing
     */
    public Long getId() {
        return id;
    }

    /**
     * Dapatkan nama.
     *
     * @return Nama kucing
     */
    public String getNama() {
        return nama;
    }

    /**
     * Dapatkan jantina.
     *
     * @return Jantina kucing
     */
    public Jantina getJantina() {
        return jantina;
    }

    /**
     * Dapatkan tarikh lahir.
     *
     * @return Tarikh lahir kucing
     */
    public LocalDate getTarikhLahir() {
        return tarikhLahir;
    }

    /**
     * Dapatkan string yang mengandungi semua data object Kucing ini.
     *
     * @return String object Kucing
     */
    @Override
    public String toString() {
        return "Kucing{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", jantina=" + jantina +
                ", tarikhLahir=" + tarikhLahir +
                '}';
    }
}
