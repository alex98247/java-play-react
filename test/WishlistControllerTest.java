import authorization.Roles;
import controllers.WishlistController;
import models.Wishlist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.Result;
import service.WishlistService;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

@RunWith(MockitoJUnitRunner.class)
public class WishlistControllerTest {

    @Mock
    WishlistService wishlistService;

    @InjectMocks
    WishlistController wishlistController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getWishlist() {
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        Wishlist wishlist = new Wishlist(2, 3, 9, new Timestamp(156780),false);
        wishlists.add(wishlist);

        when(wishlistService.getWishlists()).thenReturn(wishlists);
        Result result = wishlistController.getWishlists();

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
    }

    @Test
    public void test_result_getWishlists() {
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        Wishlist wishlist = new Wishlist(2, 3, 9, new Timestamp(156780),false);
        wishlists.add(wishlist);

        when(wishlistService.getWishlists()).thenReturn(wishlists);
        Result result = wishlistController.getWishlists();

        assertEquals(contentAsString(result), "[{\"id\":2,\"user_id\":3,\"game_id\":9,\"created_at\":156780,\"is_deleted\":false}]");
    }

    @Test
    public void test_deleteWishlists() {
        Wishlist wishlist = new Wishlist(2, 3, 9, new Timestamp(156780),false);
        doNothing().when(wishlistService).deleteWishlist(wishlist.getId());
        Result result = wishlistController.deleteWishlist(wishlist.getId());

        assertEquals(OK, result.status());
    }

    @Test
    public void test_getWishlistById() {
        Wishlist wishlist = new Wishlist(2, 3, 9, new Timestamp(156780),false);
        when(wishlistService.getWishlistById(wishlist.getId())).thenReturn(wishlist);
        Result result = wishlistController.getWishlistById(wishlist.getId());

        assertEquals(OK, result.status());
        assertEquals(contentAsString(result), "{\"id\":2,\"user_id\":3,\"game_id\":9,\"created_at\":156780,\"is_deleted\":false}");
    }
}