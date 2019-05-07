package controllers;

import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import models.dao.Claim;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ClaimService;

import javax.inject.Inject;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Api(value = "Claim Controller", produces = "application/json")
public class ClaimController extends Controller {

    @Inject
    private ClaimService claimService;

    @ApiOperation(value = "Add Claim", notes = "Add new claim from json")
    public Result addClaim() {
        JsonNode json = request().body().asJson();
        Claim message = Json.fromJson(json, Claim.class);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        Timestamp empts = new Timestamp(0);
        Claim claim = new Claim(message.getId(), message.getUser_id(), ts, false, empts, message.getComment(), message.getTheme());
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
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result updateClaim(@ApiParam(value = "Game Id", name = "id") long id) {
        JsonNode json = request().body().asJson();
        Claim message = Json.fromJson(json, Claim.class);
        if (message.isSolved()) {
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            Claim claim = new Claim(message.getId(), message.getUser_id(), message.getCreated_at(), true, ts, message.getComment(), message.getTheme());
            claimService.updateClaim(claim);
        } else {
            claimService.updateClaim(message);
        }
        return ok();
    }

    @ApiOperation(value = "Get All Claims", notes = "Get list of claims", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Claims Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result getClaims() {
        List<Claim> claims = claimService.getClaims();
        JsonNode jsonNode = Json.toJson(claims);
        return ok(jsonNode).as("application/json");
    }

    @ApiOperation(value = "Get Claim By Id", notes = "Get the claim by it's Id", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Claim Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result getClaimById(@ApiParam(value = "Game Id", name = "id") long id) {
        Claim claim = claimService.getClaimById(id);
        JsonNode jsonNode = Json.toJson(claim);
        return ok(jsonNode).as("application/json");
    }

}
