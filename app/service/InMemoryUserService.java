package service;

import models.User;
import play.Logger;
import securesocial.core.BasicProfile;
import securesocial.core.PasswordInfo;
import securesocial.core.services.SaveMode;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;
import securesocial.core.providers.UsernamePasswordProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class InMemoryUserService extends BaseUserService<User> {
    public Logger.ALogger logger = play.Logger.of("application.service.InMemoryUserService");

    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, Token> tokens = new HashMap<>();

    @Override
    public CompletionStage<User> doSave(BasicProfile profile, SaveMode mode) {
        User result = null;
        if (mode == SaveMode.SignUp()) {
            result = new User(profile);
            users.put(profile.providerId() + profile.userId(), result);
        } else if (mode == SaveMode.LoggedIn()) {
            for (Iterator<User> it =  users.values().iterator() ; it.hasNext() && result == null ; ) {
                User user = it.next();
                for ( BasicProfile p : user.identities) {
                    if ( p.userId().equals(profile.userId()) && p.providerId().equals(profile.providerId())) {
                        user.identities.remove(p);
                        user.identities.add(profile);
                        result = user;
                        break;
                    }
                }
            }
        } else if (mode == SaveMode.PasswordChange()) {
            for (Iterator<User> it =  users.values().iterator() ; it.hasNext() && result == null ; ) {
                User user = it.next();
                for (BasicProfile p : user.identities) {
                    if (p.userId().equals(profile.userId()) && p.providerId().equals(UsernamePasswordProvider.UsernamePassword())) {
                        user.identities.remove(p);
                        user.identities.add(profile);
                        result = user;
                        break;
                    }
                }
            }
        } else {
            throw new RuntimeException("Unknown mode");
        }
        return new CompletableFuture().completedFuture(result);
    }

    @Override
    public CompletionStage<User> doLink(User current, BasicProfile to) {
        User target = null;

        for ( User u: users.values() ) {
            if ( u.main.providerId().equals(current.main.providerId()) && u.main.userId().equals(current.main.userId()) ) {
                target = u;
                break;
            }
        }

        if ( target == null ) {
            // this should not happen
            throw new RuntimeException("Can't find user : " + current.main.userId());
        }

        boolean alreadyLinked = false;
        for ( BasicProfile p : target.identities) {
            if ( p.userId().equals(to.userId()) && p.providerId().equals(to.providerId())) {
                alreadyLinked = true;
                break;
            }
        }
        if (!alreadyLinked) target.identities.add(to);
        return new CompletableFuture().completedFuture(target);
    }

    @Override
    public CompletionStage<Token> doSaveToken(Token token) {
        tokens.put(token.uuid, token);
        return new CompletableFuture().completedFuture(token);
    }

    @Override
    public CompletionStage<BasicProfile> doFind(String providerId, String userId) {
        if(logger.isDebugEnabled()){
            logger.debug("Finding user " + userId);
        }
        BasicProfile found = null;

        for ( User u: users.values() ) {
            for ( BasicProfile i : u.identities ) {
                if ( i.providerId().equals(providerId) && i.userId().equals(userId) ) {
                    found = i;
                    break;
                }
            }
        }

        return new CompletableFuture().completedFuture(found);
    }

    @Override
    public CompletionStage<PasswordInfo> doPasswordInfoFor(User user) {
        throw new RuntimeException("doPasswordInfoFor is not implemented yet in sample app");
    }

    @Override
    public CompletionStage<BasicProfile> doUpdatePasswordInfo(User user, PasswordInfo info) {
        throw new RuntimeException("doUpdatePasswordInfo is not implemented yet in sample app");
    }

    @Override
    public CompletionStage<Token> doFindToken(String tokenId) {
        return new CompletableFuture().completedFuture(tokens.get(tokenId));
    }


    @Override
    public CompletionStage<BasicProfile> doFindByEmailAndProvider(String email, String providerId) {
        BasicProfile found = null;

        for ( User u: users.values() ) {
            for ( BasicProfile i : u.identities ) {
                if ( i.providerId().equals(providerId) && i.email().isDefined() && i.email().get().equals(email) ) {
                    found = i;
                    break;
                }
            }
        }

        return new CompletableFuture().completedFuture(found);
    }

    @Override
    public CompletionStage<Token> doDeleteToken(String uuid) {
        return new CompletableFuture().completedFuture(tokens.remove(uuid));
    }

    @Override
    public void doDeleteExpiredTokens() {
        Iterator<Map.Entry<String,Token>> iterator = tokens.entrySet().iterator();
        while ( iterator.hasNext() ) {
            Map.Entry<String, Token> entry = iterator.next();
            if ( entry.getValue().isExpired() ) {
                iterator.remove();
            }
        }
    }
}