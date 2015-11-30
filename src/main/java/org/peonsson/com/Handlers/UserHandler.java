package org.peonsson.com.Handlers;

import org.peonsson.com.Entity.User;
import org.peonsson.com.ViewModels.LoginViewModel;
import org.peonsson.com.ViewModels.ReturnCodeViewModel;
import org.peonsson.com.ViewModels.UserViewModel;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by robin on 9/11/15.
 */
@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserHandler {

    /**
     * REST method for registering a new user.
     *
     * @param user  the user to register
     * @return      status code indicating success or failure
     */
    @POST
    @Path("/register")
    public static Response registerUser(User user) {
        if(User.register(user)) {
            return Response.status(Response.Status.CREATED).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * REST method for login in.
     *
     * @param model view model holding username and password
     * @return      a return code indicating success or failure
     */
    @POST
    @Path("/login")
    public static ReturnCodeViewModel loginUser(LoginViewModel model) {
        int id = User.login(model);
        return new ReturnCodeViewModel(id);
    }

    /**
     * REST method for getting a list of all users.
     *
     * @return  a list with users
     */
    @GET
    public static List<UserViewModel> getUsers() {
        return User.getUsers();
    }

    /**
     * REST method for getting information about a specific user.
     *
     * @param id    the id of the user
     * @return      a json with information about the user
     */
    @GET
    @Path("/{id}")
    public static UserViewModel getUser(@PathParam("id") int id) {
        return User.getUserViewModel(id);
    }
}
