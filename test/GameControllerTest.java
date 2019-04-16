import controllers.GameController;
import models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.Result;
import service.GameService;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    GameService gameService;

    @InjectMocks
    GameController gameController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getGames() {
        ArrayList<Game> games = new ArrayList<>();
        Game game = new Game(1, "lala", 1, new Timestamp(156780));
        games.add(game);

        when(gameService.getGames()).thenReturn(games);
        Result result = gameController.getGames();

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
    }

    @Test
    public void test_result_getGames() {
        ArrayList<Game> games = new ArrayList<>();
        Game game = new Game(1, "lala", 1, new Timestamp(156780));
        games.add(game);

        when(gameService.getGames()).thenReturn(games);
        Result result = gameController.getGames();

        assertEquals(contentAsString(result), "[{\"id\":1,\"name\":\"lala\",\"popularity\":1.0,\"created_at\":156780}]");
    }

    @Test
    public void test_deleteGames() {
        Game game = new Game(1, "lala", 1, new Timestamp(156780));
        doNothing().when(gameService).deleteGame(game.getId());
        Result result = gameController.deleteGame(game.getId());

        assertEquals(OK, result.status());
    }

    @Test
    public void test_getGameById() {
        Game game = new Game(1, "lala", 1, new Timestamp(156780));
        when(gameService.getGameById(game.getId())).thenReturn(game);
        Result result = gameController.getGameById(game.getId());

        assertEquals(OK, result.status());
        assertEquals(contentAsString(result), "{\"id\":1,\"name\":\"lala\",\"popularity\":1.0,\"created_at\":156780}");
    }

}
