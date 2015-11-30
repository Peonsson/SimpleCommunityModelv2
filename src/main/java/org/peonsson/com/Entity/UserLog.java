package org.peonsson.com.Entity;

import org.peonsson.com.Database.LogDB;
import org.peonsson.com.Database.UserDB;
import org.peonsson.com.ViewModels.LogViewModel;
import org.peonsson.com.ViewModels.SubmitNewLogViewModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by Peonsson and robin.
 *
 * This class represents a log entry that a user writes.
 */

@Entity
@Table(name = "UserLog")
@XmlRootElement
public class UserLog {
    @Column(name = "UserLogId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userLogId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    @Column(name = "Timestamp")
    @NotNull
    private Date timestamp;

    @Column(name = "Subject")
    @NotNull
    @Size(min = 3, max = 32, message = "Subject must be between 3 to 32 characters long.")
    private String subject;

    @Column(name = "Message")
    @NotNull
    @Size(min = 3, max = 128, message = "Message must be between 3 to 128 characters long.")
    private String message;

    public UserLog() {
        super();
        this.timestamp = new Date();
    }

    /**
     * Constructor used to create a new UserLog entry.
     *
     * @param user      the user who wrote the entry
     * @param subject   the subject of the entry
     * @param message   the message itself
     */
    public UserLog(User user, String subject, String message) {
        this.user = user;
        this.timestamp = new Date();
        this.subject = subject;
        this.message = message;
    }

    public int getUserLogId() {
        return userLogId;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User userId) {
        this.user = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Submit a new log.
     *
     * @param log   the log to submit
     * @return      boolean indicating success (true) or failure (false)
     */
    public static boolean submit(SubmitNewLogViewModel log) {
        User user = UserDB.getUser(log.getId());

        // TODO: Remove this code
        if (user != null) {
            System.out.println(user.toString());
        }
        else {
            return false;
        }

        return LogDB.submit(log);
    }

    /**
     * Return a list of logs belonging to a user.
     *
     * @param id    user id
     * @return      a list of logs belonging to the user
     */
    public static List<LogViewModel> getLogs(int id) {
        User user = UserDB.getUser(id);
        return LogDB.getLogs(user);
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "userLogId=" + userLogId +
                ", user=" + user +
                ", timestamp=" + timestamp +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}