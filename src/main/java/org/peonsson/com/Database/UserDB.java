package org.peonsson.com.Database;

import org.peonsson.com.Entity.User;
import org.peonsson.com.ViewModels.LoginViewModel;
import org.peonsson.com.ViewModels.UserViewModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robin on 11/11/15.
 */
public class UserDB {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleCommunity");

    /**
     * DB method for registering a new user
     *
     * @param user  the user to register
     * @return      true if succeeded, false if failed
     */
    public static boolean registerUser(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleCommunity");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.close();
            return false;
        }

        return true;
    }

    /**
     * DB method for logging in a user
     *
     * @param model the information user typed in form
     * @return      a user object matching the username that was entered
     */
    public static User loginUser(LoginViewModel model) {
        EntityManager em = emf.createEntityManager();

        String username = model.getUsername();
        String password = model.getPassword();

        User user;
        try {
            Query query = em.createQuery("from User where username = :username");
            query.setParameter("username", username);
            List list = query.getResultList();
            user = (User) list.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return user;
    }

    /**
     * DB method for getting a list of all users.
     *
     * @return  a list of all users
     */
    public static List<UserViewModel> getUsers() {
        List<UserViewModel> users = new ArrayList<UserViewModel>();

        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("from User");
            List list = query.getResultList();

            for (int i = 0; i < list.size(); i++) {
                User current = (User) list.get(i);
                int id = current.getUserId();
                String email = current.getEmail();
                String username = current.getUsername();
                String firstname = current.getFirstname();
                String lastname = current.getLastname();
                String country = current.getCountry();
                String city = current.getCity();

                users.add(new UserViewModel(id, email, username, firstname, lastname, country, city));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }

        return users;
    }

    /**
     * DB method for getting a specific user
     *
     * @param id    the id of the user
     * @return      a user object with information about the user
     */
    public static User getUser(int id) {
        EntityManager em = emf.createEntityManager();

        User user;
        try {
            Query query = em.createQuery("from User where userId = :id");
            query.setParameter("id", id);
            List list = query.getResultList();

            user = (User) list.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return user;
    }

    /**
     * DB method for getting a specific user
     *
     * @param id    the id of the user
     * @return      a view model object with information about the user
     */
    public static UserViewModel getUserViewModel(int id) {
        EntityManager em = emf.createEntityManager();

        UserViewModel newUser = new UserViewModel();
        try {
            Query query = em.createQuery("from User where userId = :id");
            query.setParameter("id", id);
            List list = query.getResultList();

            User user = (User) list.get(0);

            newUser.setId(user.getUserId());
            newUser.setEmail(user.getEmail());
            newUser.setCity(user.getCity());
            newUser.setCountry(user.getCountry());
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setUsername(user.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
        return newUser;
    }
}