package com.jombelajarjava.kucing;

import java.util.Locale;

public enum Jantina {
    JANTAN, BETINA;

    /**
     * Pilih enum Jantina daripada character. 'J' atau 'j' untuk JANTAN, 'B' atau 'b' untuk BETINA.
     *
     * @param firstChar Character untuk menentukan enum
     * @return Enum Jantina
     */
    public static Jantina fromChar(final char firstChar) {
        for (Jantina jantina : Jantina.values()) {
            if (String.valueOf(jantina.toString().charAt(0)).equalsIgnoreCase(String.valueOf(firstChar))) {
                return jantina;
            }
        }
        return null;
    }

    /**
     * Pilih enum Jantina daripada string. 'jantan' untuk JANTAN, 'betina' untuk BETINA.
     *
     * @param text Teks untuk pilih enum
     * @return Enum Jantina
     */
    public static Jantina fromString(final String text) {
        return Jantina.valueOf(text.toUpperCase(Locale.ENGLISH));
    }
}
