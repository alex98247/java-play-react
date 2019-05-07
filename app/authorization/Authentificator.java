package authorization;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.util.CommonHelper;

import java.util.HashSet;
import java.util.Set;

public class Authentificator implements Authenticator<UsernamePasswordCredentials> {

    public void validate(UsernamePasswordCredentials credentials, WebContext context) {
        if (credentials == null) {
            throw new CredentialsException("No credential");
        } else {
            String username = credentials.getUsername();
            String password = credentials.getPassword();
            //User user = User.find.query().where().eq("username", username).findOne();
            //String userPassword = user.getPassword_hash().toString();
            if (CommonHelper.isBlank(username)) {
                throw new CredentialsException("Username cannot be blank");
            } else if (CommonHelper.isBlank(password)) {
                throw new CredentialsException("Password cannot be blank");
            } else if (CommonHelper.areNotEquals(password, password)) {
                throw new CredentialsException("Username : '" + username + "' does not match password");
            } else {
                CommonProfile profile = new CommonProfile();
                Set<String> roles = new HashSet<>();
                //roles.add(user.getRole().name());
                roles.add(Roles.ADMIN.name());
                profile.setId(username);
                profile.setRoles(roles);
                profile.addAttribute("username", username);
                credentials.setUserProfile(profile);
            }
        }
    }
}
