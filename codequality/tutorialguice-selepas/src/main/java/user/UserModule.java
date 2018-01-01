package user;

import com.google.inject.AbstractModule;
import user.impl.UserLocaleRepositoryImpl;
import user.impl.UserServiceImpl;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserLocaleRepository.class).to(UserLocaleRepositoryImpl.class);
    }
}
