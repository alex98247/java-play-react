package repository;

import models.Games;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface GamesRepository {
    CompletionStage<Games> list();

    CompletionStage<Games> create(Games game);

    CompletionStage<Optional<Games>> get(Long id);

    CompletionStage<Optional<Games>> update(Long id, Games game);
}
