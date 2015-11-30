package org.peonsson.com.Database;

import org.peonsson.com.Entity.User;
import org.peonsson.com.Entity.UserLog;
import org.peonsson.com.ViewModels.LogViewModel;
import org.peonsson.com.ViewModels.SubmitNewLogViewModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Peonsson on 11/11/15.
 */
public class LogDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleCommunity");

    /**
     * DB method for submitting new log post.
     *
     * @param log   log post to submit
     * @return      true if succeed, false if failure
     */
    public static boolean submit(SubmitNewLogViewModel log) {
        EntityManager em = emf.createEntityManager();

        try {
            UserLog userLog = new UserLog();
            User user = UserDB.getUser(log.getId());

            userLog.setUserId(user);
            userLog.setMessage(log.getMessage());
            userLog.setSubject(log.getSubject());

            em.getTransaction().begin();
            em.persist(userLog);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return false;
        } finally {
            em.close();
            return true;
        }
    }

    /**
     * DB method for getting logs belonging to a user
     *
     * @param user  the user
     * @return      list with logs belonging to the user
     */
    public static List<LogViewModel> getLogs(User user) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("from UserLog where user = :user");
        query.setParameter("user", user);
        List<UserLog> logs = query.getResultList();

        List<LogViewModel> returnList = new ArrayList<>();
        for (UserLog log : logs) {
            String username = log.getUserId().getUsername();
            String subject = log.getSubject();
            String message = log.getMessage();
            Date date = log.getTimestamp();

            returnList.add(new LogViewModel(message, subject, date, username));
        }

        return returnList;
    }
}
