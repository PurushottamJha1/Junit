package Authentication;

import java.util.HashMap;

public class ServiceAuth
{
    private HashMap<String, User> users;

    public ServiceAuth() {
        users = new HashMap<>();
        // Simulated user database
        users.put("example@example.com", new User("example@example.com", "password123"));
    }

    public String authenticateUser(String email, String password) {
        if (!users.containsKey(email)) {
            return "Invalid email";
        }

        User user = users.get(email);
        if (!password.equals(user.getPassword())) {
            return "Wrong password";
        }

        return "Login successful";
    }
}