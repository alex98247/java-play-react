package modules;

import com.google.inject.AbstractModule;
import service.*;

public class ComponentModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameService.class).
                to(GameServiceImpl.class);
        bind(UserService.class).
                to(UserServiceImpl.class);
        bind(ClaimService.class).
                to(ClaimServiceImpl.class);
    }
}
