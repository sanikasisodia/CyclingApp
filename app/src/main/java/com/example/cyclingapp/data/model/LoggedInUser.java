package com.example.cyclingapp.data.model;

import androidx.room.Room;
import java.util.List;
import com.example.cyclingapp.App;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String role;

    public LoggedInUser(String email, String password) {
        AppDatabase db = Room.databaseBuilder(App.getAppContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();

        List<User> users = userDao.getAll().blockingGet();
        for (User user : users) {
            if (user.email.equals(email) && BCrypt.checkpw(password, user.passwordHash)) {
                userId = user.id;
                displayName = user.firstName + " " + user.lastName;
                role = String.valueOf(user.role);
            }
        }

        if (userId == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        db.close();
    }

    public String getUserId() {
        return userId.toString();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRole() {
        return role;
    }
}