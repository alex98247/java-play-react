package controllers;

import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import models.dao.Claim;
import models.dao.History;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ClaimService;
import service.HistoryService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Api(value = "Claim Controller", produces = "application/json")
public class ClaimController extends Controller {

    @Inject
    private ClaimService claimService;
    @Inject
    private PlaySessionStore playSessionStore;
    @Inject
    private HistoryService historyService;

    @ApiOperation(value = "Add Claim", notes = "Add new claim from json")
    public Result addClaim() {
        JsonNode json = request().body().asJson();
        Claim claim = Json.fromJson(json, Claim.class);
        String profileId = getProfileId();
        History history = new History(profileId, "Create claim");
        historyService.addHistory(history);

        claimService.createClaim(claim);
        return ok();
    }

    @ApiOperation(value = "Delete Claim", notes = "Delete claim by Id")
    @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)
    public Result deleteClaim(@ApiParam(value = "Game Id", name = "id") long id) {
        claimService.deleteClaim(id);
        return ok();
    }

    @ApiOperation(value = "Update Claim", notes = "Update game in list from json")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Claim Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result updateClaim(@ApiParam(value = "Game Id", name = "id") long id) {
        JsonNode json = request().body().asJson();
        Claim claim = Json.fromJson(json, Claim.class);
        claimService.updateClaim(claim);

        return ok();
    }

    @ApiOperation(value = "Get All Claims", notes = "Get list of claims", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Claims Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result getClaims() {
        List<Claim> claims = claimService.getClaims();
        JsonNode jsonNode = Json.toJson(claims);
        return ok(jsonNode).as("application/json");
    }

    @ApiOperation(value = "Get Claim By Id", notes = "Get the claim by it's Id", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Claim Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result getClaimById(@ApiParam(value = "Game Id", name = "id") long id) {
        Claim claim = claimService.getClaimById(id);
        JsonNode jsonNode = Json.toJson(claim);
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
