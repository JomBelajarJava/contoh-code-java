import greeting.GreetingService;
import greeting.impl.GreetingRepositoryImpl;
import greeting.impl.GreetingServiceImpl;
import user.impl.UserLocaleRepositoryImpl;
import user.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        final GreetingService greetingService = new GreetingServiceImpl(
                new UserServiceImpl(new UserLocaleRepositoryImpl()),
                new GreetingRepositoryImpl());

        System.out.println(greetingService.getGreeting("Kassim"));
    }
}
