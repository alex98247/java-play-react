package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.ExecutionContextProvider;
import be.objectify.deadbolt.java.cache.HandlerCache;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class MyHandlerCache implements HandlerCache {
    private final DeadboltHandler defaultHandler;
    private final Map<String, DeadboltHandler> handlers = new HashMap<>();

    @Inject
    public MyHandlerCache(final ExecutionContextProvider ecProvider)
    {
        defaultHandler = new MyDeadboltHandler(ecProvider);

        handlers.put(HandlerKeys.DEFAULT.key, defaultHandler);
    }

    @Override
    public DeadboltHandler apply(final String key)
    {
        return handlers.get(key);
    }

    @Override
    public DeadboltHandler get()
    {
        return defaultHandler;
    }
}
