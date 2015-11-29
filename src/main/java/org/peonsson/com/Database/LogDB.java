package org.peonsson.com.Database;

import org.peonsson.com.BusinessLogic.UserHandler;
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

    public static boolean submit(SubmitNewLogViewModel log) {
        EntityManager em = emf.createEntityManager();

        try {
            UserLog userLog = new UserLog();
            System.out.println("Log id: " + log.getId());
            User user = UserDB.getUser(log.getId());

            userLog.setUserId(user);
            userLog.setMessage(log.getMessage());
            userLog.setSubject(log.getSubject());

            em.getTransaction().begin();
            em.persist(userLog);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            em.close();
            return false;
        } finally {
            em.close();
            return true;
        }
    }

    public static List<LogViewModel> getLogs(User user) {

        System.out.println(user.toString());

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("from UserLog where user = :user");
        query.setParameter("user", user);
        List<UserLog> logs = query.getResultList();

        System.out.println(logs.size());

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
