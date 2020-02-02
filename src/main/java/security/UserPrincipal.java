package security;

import entity.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipal implements Principal {

    private String id;
    private String username;
    private List<String> roles = new ArrayList<>();

    /* Create a UserPrincipal, given the Entity class User*/
    public UserPrincipal(User user) {
        this.id = user.getId().toString();
        this.username = user.getUserName();
        this.roles = user.getRolesAsStrings();
    }

    public UserPrincipal(String id, String username, String... roles) {
        super();
        this.id = id;
        this.username = username;
        this.roles = Arrays.asList(roles);
    }
    
    public String getId() {
        return id;
    }

    public String getRoles() {
        return roles.get(0);
    }

    @Override
    public String getName() {
        return username;
    }

    public boolean isUserInRole(String role) {
        return this.roles.contains(role);
    }


    

}
