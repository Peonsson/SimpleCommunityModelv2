package org.peonsson.com.Handlers;

import org.peonsson.com.Entity.UserLog;
import org.peonsson.com.ViewModels.LogViewModel;
import org.peonsson.com.ViewModels.ReturnBooleanViewModel;
import org.peonsson.com.ViewModels.SubmitNewLogViewModel;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Peonsson on 11/11/15.
 */
@Path("/logs")
@Produces("application/json")
@Consumes("application/json")
public class LogHandler {

    /**
     * Submit a new log REST method.
     *
     * @param log   the log to be submitted
     * @return      json indicating success or failure
     */
    @POST
    public static ReturnBooleanViewModel submit(SubmitNewLogViewModel log) {
        boolean bool = UserLog.submit(log);
        return new ReturnBooleanViewModel(bool);
    }

    /**
     * Return all user logs REST method.
     *
     * @return      a list of all logs
     */
//    @GET
//    public static List<LogViewModel> getLogs() {
//        List<LogViewModel> logs = UserLog.getLogs();
//        System.out.println(logs.toString());
//        return logs;
//    }

    /**
     * Get logs by user id REST method.
     *
     * @param id    id of user
     * @return      list of logs belonging to user
     */
    @GET
    @Path("/{id}")
    public static List<LogViewModel> getLogs(@PathParam("id") int id) {
        return UserLog.getLogs(id);
    }
}