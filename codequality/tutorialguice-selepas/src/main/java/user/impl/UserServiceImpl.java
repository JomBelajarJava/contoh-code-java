package user.impl;

import user.UserLocaleRepository;
import user.UserService;

import javax.inject.Inject;
import java.util.Locale;

public class UserServiceImpl implements UserService {

    private final UserLocaleRepository userLocaleRepository;

    @Inject
    public UserServiceImpl(final UserLocaleRepository userLocaleRepository) {
        this.userLocaleRepository = userLocaleRepository;
    }

    @Override
    public Locale getLocale(final String name) {
        return userLocaleRepository.fetchLocale(name);
    }
}
