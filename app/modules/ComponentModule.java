package modules;

import com.google.inject.AbstractModule;
import service.GameService;
import service.GameServiceImpl;
import tools.DbGame;
import tools.DbGameImpl;

public class ComponentModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameService.class).
                to(GameServiceImpl.class);
        bind(DbGame.class).
                to(DbGameImpl.class);
    }
}
