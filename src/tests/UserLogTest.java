import org.junit.Test;
import org.peonsson.com.Handlers.LogHandler;
import org.peonsson.com.ViewModels.LogViewModel;
import org.peonsson.com.ViewModels.ReturnBooleanViewModel;
import org.peonsson.com.ViewModels.SubmitNewLogViewModel;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by robin on 24/11/15.
 */
public class UserLogTest {
    @Test
    public void testCreateNewLogEntrySuccess() throws Exception {
        SubmitNewLogViewModel log = new SubmitNewLogViewModel(1, "TEST MESSAGE", "TESTSUBJ");

        ReturnBooleanViewModel success = LogHandler.submit(log);

        assertTrue(success.isBool());
    }

    @Test
    public void testCreateNewLogEntryFail() throws Exception {
        SubmitNewLogViewModel log = new SubmitNewLogViewModel(-1, "TEST MESSAGE", "TESTSUBJ");

        ReturnBooleanViewModel success = LogHandler.submit(log);

        assertTrue(!success.isBool());
    }

    @Test
    public void testFetchUserLogsSuccess() throws Exception {
        List<LogViewModel> logs = LogHandler.getLogs(1);

        assertTrue(logs.size() > 0);
    }
}
