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

    public static boolean registerUser(User user) {
        System.out.println("UserDB: registerUser");

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

    public static User loginUser(LoginViewModel model) {
        System.out.println("UserDB: GOT loginUser");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SimpleCommunity");
        EntityManager em = emf.createEntityManager();

        String username = model.getUsername();
        String password = model.getPassword();

        User user;
        try {

            Query query = em.createQuery("from User where username = :username");
            query.setParameter("username", username);
            List list = query.getResultList();
            user = (User) list.get(0);

//            System.out.println("UserDB user: " + user.getUsername());
//            System.out.println("UserDB pass: " + user.getPassword());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
        return user;
    }

    public static List<UserViewModel> getUsers() {
        System.out.println("UserDB: GOT browse");
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
            System.err.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }

        System.out.println(users.toString());
        return users;
    }

    public static User getUser(int id) {
        System.out.println("UserDB: GOT getUser");

        EntityManager em = emf.createEntityManager();

        User user;
        try {
            Query query = em.createQuery("from User where userId = :id");
            query.setParameter("id", id);
            List list = query.getResultList();

            user = (User) list.get(0);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
        return user;
    }

    public static UserViewModel getUserViewModel(int id) {
        System.out.println("UserDB: GOT getUser");

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
            System.err.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
        return newUser;
    }

    public static void addFriend(User user, User otherUser) {
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("from User where userId = :id");
            query.setParameter("id", user.getUserId());
            User thisUser = (User) query.getResultList().get(0);

            List<User> friends = thisUser.getFriends();
            System.out.println("UserDB: friends: " + friends.toString());

            for (User current : friends) {
                System.out.println("USerDB: otherUser : " + otherUser.toString());
                System.out.println("UserDB: current User: " + current.toString());
                if (current.getUserId() == otherUser.getUserId()) {
                    // If user already exists in list, don't add again
                    return;
                }
            }

            friends.add(otherUser);

            em.getTransaction().begin();
            em.persist(thisUser);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}