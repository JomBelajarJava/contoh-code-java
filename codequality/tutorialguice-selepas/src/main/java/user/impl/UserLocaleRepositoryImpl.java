package user.impl;

import user.UserLocaleRepository;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

public class UserLocaleRepositoryImpl implements UserLocaleRepository {

    private final static Locale MALAY = new Locale("ms", "my");

    @Override
    public Locale fetchLocale(final String name) {
        switch (name) {
            case "Kassim": return MALAY;
            case "Johnny": return ENGLISH;
            default: return ENGLISH;
        }
    }
}
