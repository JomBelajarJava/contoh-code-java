package greeting.impl;

import greeting.GreetingRepository;
import greeting.GreetingService;
import user.UserService;

import javax.inject.Inject;
import java.util.Locale;

public class GreetingServiceImpl implements GreetingService {

    private final UserService userService;
    private final GreetingRepository greetingRepository;

    @Inject
    public GreetingServiceImpl(final UserService userService, final GreetingRepository greetingRepository) {
        this.userService = userService;
        this.greetingRepository = greetingRepository;
    }

    @Override
    public String getGreeting(final String name) {
        final Locale userLocale = userService.getLocale(name);
        return greetingRepository.fetchGreeting(userLocale.getDisplayLanguage());
    }
}
