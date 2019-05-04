package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Claim;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.ClaimService;

import javax.inject.Inject;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class ClaimController extends Controller {

    @Inject
    private ClaimService claimService;

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

    public Result deleteClaim(long id) {
        claimService.deleteClaim(id);
        return ok();
    }

    public Result updateClaim(long id) {
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

    public Result getClaims() {
        List<Claim> claims = claimService.getClaims();
        JsonNode jsonNode = Json.toJson(claims);
        return ok(jsonNode).as("application/json");
    }

    public Result getClaimById(long id) {
        Claim claim = claimService.getClaimById(id);
        JsonNode jsonNode = Json.toJson(claim);
        return ok(jsonNode).as("application/json");
    }

}
