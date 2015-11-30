package org.peonsson.com.Database;

import org.peonsson.com.Entity.PrivateMessage;
import org.peonsson.com.Entity.User;
import org.peonsson.com.ViewModels.PrivateMessageViewModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Peonsson on 19/11/15.
 */
public class PrivateMessageDB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleCommunity");

    /**
     * DB method for submitting a new pm.
     *
     * @param pm    the pm to submit
     * @return      true if succeeded, false if failed
     */
    public static boolean submit(PrivateMessage pm) {
        EntityManager em = emf.createEntityManager();

        pm.setTimestamp(new Date());
        try {
            em.getTransaction().begin();
            em.persist(pm);
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
     * DB method for fetching pms meant for a specific user.
     *
     * @param user  the user
     * @return      a list of pms meant for the user
     */
    public static List<PrivateMessageViewModel> fetchPrivateMessages(User user) {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("from PrivateMessage where receiver = :rec");
        query.setParameter("rec", user);

        List<PrivateMessage> list = query.getResultList();
        List<PrivateMessageViewModel> myList = new ArrayList<>();

        for(PrivateMessage m: list) {
            myList.add(new PrivateMessageViewModel(m.getSender().getUsername(), m.getReceiver().getUsername(), m.getSubject(), m.getMessage(), m.getTimestamp()));
        }

        return myList;
    }
}
