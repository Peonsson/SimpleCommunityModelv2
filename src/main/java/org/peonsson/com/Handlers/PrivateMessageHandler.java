package org.peonsson.com.Handlers;

import org.peonsson.com.Entity.PrivateMessage;
import org.peonsson.com.ViewModels.PrivateMessageViewModel;
import org.peonsson.com.ViewModels.ReturnBooleanViewModel;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Peonsson on 19/11/15.
 */
@Path("/PrivateMessages")
@Produces("application/json")
@Consumes("application/json")
public class PrivateMessageHandler {

    /**
     * Submit new PM REST method
     *
     * @param privateMessage    the private message (pm) to submit
     * @return                  a json indicating success or failure
     */
    @POST
    public static ReturnBooleanViewModel submit(PrivateMessageViewModel privateMessage) {
        boolean bool = PrivateMessage.submit(privateMessage);
        return new ReturnBooleanViewModel(bool);
    }

    /**
     * Get all PMs for a specific user
     *
     * @param id    the id of the user
     * @return      a list of pms meant for the user
     */
    @GET
    @Path("/{id}")
    public static List<PrivateMessageViewModel> fetchPrivateMessages(@PathParam("id") int id) {
        return PrivateMessage.fetchPrivateMessages(id);
    }
}
