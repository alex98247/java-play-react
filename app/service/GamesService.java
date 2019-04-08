package service;

import com.palominolabs.http.url.UrlBuilder;
import models.Games;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import repository.GamesRepository;
import resource.Resource;

import javax.inject.Inject;
import java.nio.charset.CharacterCodingException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class GamesService {
    private final GamesRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public GamesService(GamesRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<Stream<Resource<Games>>> find(Http.Request request) {
        return repository.list().thenApplyAsync(gamesStream -> {
            return gamesStream.map(data -> new Resource<Games>(data, link(request, data)));
        }, ec.current());
    }

    public CompletionStage<Resource<Games>> create(Http.Request request, Resource<Games> resource) {
        final Games data = new Games(resource.getObject());
        return repository.create(data).thenApplyAsync(savedData -> {
            return new Resource<Games>(savedData, link(request, savedData));
        }, ec.current());
    }

    public CompletionStage<Optional<Resource<Games>>> lookup(Http.Request request,String id) {
        return repository.get(Long.parseLong(id)).thenApplyAsync(optionalData -> {
            return optionalData.map(data -> new Resource<Games>(data, link(request, data)));
        }, ec.current());
    }

    public CompletionStage<Optional<Resource<Games>>> update(Http.Request request,String id, Resource<Games> resource) {
        final Games data = new Games(resource.getObject());
        return repository.update(Long.parseLong(id), data).thenApplyAsync(optionalData -> {
            return optionalData.map(op -> new Resource<Games>(op, link(request, op)));
        }, ec.current());
    }

    private String link(Http.Request request, Games data) {
        final String[] hostPort = request.host().split(":");
        String host = hostPort[0];
        int port = (hostPort.length == 2) ? Integer.parseInt(hostPort[1]) : -1;
        final String scheme = request.secure() ? "https" : "http";
        try {
            return UrlBuilder.forHost(scheme, host, port)
                    .pathSegments("v1", "games", Long.toString(data.getId()))
                    .toUrlString();
        } catch (CharacterCodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
