package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import java.security.Security;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RestrictController extends Controller
{

    public CompletionStage<Result> restrictOne()
    {
        return CompletableFuture.completedFuture(ok("rrrrr").as("application/json"));
    }
}
