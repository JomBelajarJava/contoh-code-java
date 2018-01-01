import com.google.inject.Guice;
import com.google.inject.Injector;
import greeting.GreetingModule;
import greeting.GreetingService;
import user.UserModule;

public class Main {
    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new UserModule(), new GreetingModule());
        final GreetingService greetingService = injector.getInstance(GreetingService.class);

        System.out.println(greetingService.getGreeting("Kassim"));
    }
}
