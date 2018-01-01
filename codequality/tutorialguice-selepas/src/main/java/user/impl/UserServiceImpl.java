package user.impl;

import user.UserLocaleRepository;
import user.UserService;

import java.util.Locale;

public class UserServiceImpl implements UserService {

    private final UserLocaleRepository userLocaleRepository;

    public UserServiceImpl(final UserLocaleRepository userLocaleRepository) {
        this.userLocaleRepository = userLocaleRepository;
    }

    @Override
    public Locale getLocale(final String name) {
        return userLocaleRepository.fetchLocale(name);
    }
}
