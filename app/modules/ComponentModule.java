package modules;

import com.google.inject.AbstractModule;
import service.GameService;
import service.GameServiceImpl;

public class ComponentModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GameService.class).
                to(GameServiceImpl.class);
    }
}
