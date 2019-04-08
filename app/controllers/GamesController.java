package controllers;

import models.Games;
import play.mvc.*;
import resource.Resource;
import service.GamesService;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@With(GamesAction.class)
public class GamesController extends Controller {
    private HttpExecutionContext ec;
    private GamesService service;

    @Inject
    public GamesController(HttpExecutionContext ec, GamesService service) {
        this.ec = ec;
        this.service = service;
    }

    public CompletionStage<Result> list(Http.Request request) {
        return service.find(request).thenApplyAsync(posts -> {
            final List<Resource<Games>> postList = posts.collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }

    public CompletionStage<Result> show(Http.Request request, String id) {
        return service.lookup(request, id).thenApplyAsync(optionalResource -> {
            return optionalResource.map(resource ->
                    ok(Json.toJson(resource))
            ).orElseGet(Results::notFound);
        }, ec.current());
    }

    public CompletionStage<Result> update(Http.Request request, String id) {
        JsonNode json = request.body().asJson();
        Resource<Games> resource = Json.fromJson(json, Resource<Games>.class);
        return service.update(request, id, resource).thenApplyAsync(optionalResource -> {
            return optionalResource.map(r ->
                    ok(Json.toJson(r))
            ).orElseGet(Results::notFound
            );
        }, ec.current());
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        final Resource<Games> resource = Json.fromJson(json, Resource.class);
        return service.create(request, resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
    }, ec.current());
    }
}
