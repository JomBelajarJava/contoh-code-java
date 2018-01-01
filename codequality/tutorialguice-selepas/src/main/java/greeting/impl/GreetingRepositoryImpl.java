package greeting.impl;

import greeting.GreetingRepository;

public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public String fetchGreeting(final String language) {
        switch (language) {
            case "English": return "Hello!";
            case "Malay": return "Selamat sejahtera!";
            default: return "Hello!";
        }
    }
}
