import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import service.GameService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.route;

public class GameControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void test_app_summary_from_a_new_instance() {
        GameService repositoryMock = mock(GameService.class);
        Result result = new controllers.GameController().addGames();
        assertEquals(OK, result.status());
        //assertEquals("application/json", result.contentType().get());
        //assertEquals(contentAsString(result), "{\"content\":\"Java Play React Seed\"}");
    }

    @Test
    public void test_app_summary_from_route() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/api/summary");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
        assertEquals(contentAsString(result), "{\"content\":\"Java Play React Seed\"}");
    }
}
