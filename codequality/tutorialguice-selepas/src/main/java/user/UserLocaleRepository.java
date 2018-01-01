package user;

import java.util.Locale;

public interface UserLocaleRepository {
    Locale fetchLocale(String name);
}
