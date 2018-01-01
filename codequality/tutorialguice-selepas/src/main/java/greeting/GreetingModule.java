package greeting;

import com.google.inject.AbstractModule;
import greeting.impl.GreetingRepositoryImpl;
import greeting.impl.GreetingServiceImpl;

public class GreetingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GreetingService.class).to(GreetingServiceImpl.class);
        bind(GreetingRepository.class).to(GreetingRepositoryImpl.class);
    }
}
