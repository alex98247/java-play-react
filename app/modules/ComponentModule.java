package modules;

import com.google.inject.AbstractModule;
import service.*;
import tools.DbGame;
import tools.DbGameImpl;

public class ComponentModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameService.class).
                to(GameServiceImpl.class);
        bind(ClaimService.class).
                to(ClaimServiceImpl.class);
        bind(UserService.class).
                to(UserServiceImpl.class);
        bind(WishlistService.class).
                to(WishlistServiceImpl.class);
        bind(DbGame.class).
                to(DbGameImpl.class);
    }
}
