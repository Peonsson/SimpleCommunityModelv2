import org.junit.Test;
import org.peonsson.com.BusinessLogic.LogHandler;
import org.peonsson.com.BusinessLogic.PrivateMessageHandler;
import org.peonsson.com.Entity.PrivateMessage;
import org.peonsson.com.ViewModels.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by robin on 24/11/15.
 */
public class PrivateMessageTest {
    @Test
    public void testCreateNewPMSucceed() throws Exception {
        PrivateMessageViewModel pm = new PrivateMessageViewModel("1", "2", "TEST SUBJ", "TEST MESS", new Date());

        ReturnBooleanViewModel returnVal = PrivateMessageHandler.submit(pm);

        assert(returnVal.isBool());
    }

    @Test
    public void testCreateNewPMFail() throws Exception {
        PrivateMessageViewModel pm = new PrivateMessageViewModel("-1", "-1", "TEST SUBJ", "TEST MESS", new Date());

        ReturnBooleanViewModel returnVal = PrivateMessageHandler.submit(pm);

        assert(!returnVal.isBool());
    }

    @Test
    public void testFetchPMsForUserSucceed() throws Exception {
        List<PrivateMessageViewModel> pms = PrivateMessageHandler.fetchPrivateMessages(1);

        assert(pms.size() > 0);
    }

    @Test
    public void testFetchPMsForUserFail() throws Exception {
        List<PrivateMessageViewModel> pms = PrivateMessageHandler.fetchPrivateMessages(-1);

        assert(pms.size() == 0);
    }
}
