package controllers;

import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import models.dao.Game;
import models.dao.User;
import models.dao.Wishlist;
import models.dto.WishlistDto;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.GameService;
import service.UserService;
import service.WishlistService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "Wishlist Controller", produces = "application/json")
public class WishlistController extends Controller {

    @Inject
    private WishlistService wishlistService;

    @Inject
    private GameService gameService;

    @Inject
    private UserService userService;

    @Inject
    private PlaySessionStore playSessionStore;

    @ApiOperation(value = "Add Wishlist", notes = "Add new wishlist from json")
    public Result addWishlist() {
        JsonNode json = request().body().asJson();
        Wishlist wishlist = Json.fromJson(json, Wishlist.class);
        String profileId = getProfileId();
        User user = userService.getUserByUsername(profileId);
        wishlist.setUserId(user.getId());
        wishlistService.createWishlist(wishlist);
        return ok();
    }

    @ApiOperation(value = "Delete Wishlist", notes = "Delete wishlist by Id")
    @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)
    public Result deleteWishlist(@ApiParam(value = "Game Id", name = "id") long id) {
        wishlistService.deleteWishlist(id);
        return ok();
    }

    @ApiOperation(value = "Update wishlist", notes = "Update game in list from json")
    @ApiResponses({
            @ApiResponse(code = 404, message = "wishlist Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result updateWishlist(@ApiParam(value = "Game Id", name = "id") long id) {
        JsonNode json = request().body().asJson();
        Wishlist wishlist = Json.fromJson(json, Wishlist.class);
        wishlistService.updateWishlist(wishlist);
        return ok();
    }

    @ApiOperation(value = "Get All Wishlists", notes = "Get list of wishlists", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Wishlists Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result getWishlists() {
        List<Wishlist> wishlists = wishlistService.getWishlists();
        List<WishlistDto> wishlistDtos = wishlists.stream().map(x->
        {
            Game gameById = gameService.getGameById(x.getGameId());
            WishlistDto wishlistDto = new WishlistDto(x.getId(), gameById.getName());
            return wishlistDto;
        }).collect(Collectors.toList());
        JsonNode jsonNode = Json.toJson(wishlistDtos);
        return ok(jsonNode).as("application/json");
    }

    @ApiOperation(value = "Get Wishlist By Id", notes = "Get the wishlist by it's Id", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Wishlist Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result getWishlistById(@ApiParam(value = "Game Id", name = "id") long id) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        JsonNode jsonNode = Json.toJson(wishlist);
        return ok(jsonNode).as("application/json");
    }

    private String getProfileId() {
        PlayWebContext webContext = new PlayWebContext(ctx(), playSessionStore);
        ProfileManager<CommonProfile> profileManager = new ProfileManager(webContext);
        Optional<CommonProfile> profileOptional = profileManager.get(true);
        if (!profileOptional.isPresent()) {
            //return exception
        }
        CommonProfile profile = profileOptional.get();
        return profile.getId();
    }

}
