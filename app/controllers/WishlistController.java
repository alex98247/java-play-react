package controllers;

import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import models.Wishlist;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.WishlistService;

import javax.inject.Inject;
import java.util.List;

@Api(value = "Wishlist Controller", produces = "application/json")
public class WishlistController extends Controller {

    @Inject
    private WishlistService wishlistService;

    @ApiOperation(value = "Add Wishlist", notes = "Add new wishlist from json")
    public Result addWishlist() {
        JsonNode json = request().body().asJson();
        Wishlist wishlist = Json.fromJson(json, Wishlist.class);
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
        JsonNode jsonNode = Json.toJson(wishlists);
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

}
