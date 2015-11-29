package org.peonsson.com.BusinessLogic;

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

    @POST
    public static ReturnBooleanViewModel submit(SubmitNewLogViewModel log) {
        boolean bool = UserLog.submit(log);
        return new ReturnBooleanViewModel(bool);
    }

//    @GET
//    public static List<LogViewModel> getLogs() {
//        List<LogViewModel> logs = UserLog.getLogs();
//        System.out.println(logs.toString());
//        return logs;
//    }

    @GET
    @Path("/{id}")
    public static List<LogViewModel> getLogs(@PathParam("id") int id) {
        return UserLog.getLogs(id);
    }
}