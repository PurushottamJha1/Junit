package Authentication;

import java.util.HashMap;

public class ServiceReset {
    private ServiceMail mailService;
    private HashMap<String, User> users;

    public ServiceReset(ServiceMail mailService, HashMap<String, User> users) {
        this.mailService = mailService;
        this.users = users;
    }

    public boolean sendPasswordResetLink(String email) {
        if (!isValidEmail(email)) {
            return false; 
        }

        if (!users.containsKey(email)) {
            return false; 
        }

        User user = users.get(email);
        String resetLink = generateResetLink(user);
        return mailService.sendMail(email, "Password Reset Link", resetLink);
    }

    private String generateResetLink(User user) {
        // Generate a unique reset link for the user
        return "https://example.com/reset-password?user=" + user.getEmail();
    }

    private boolean isValidEmail(String email) {
        // Simple email validation
        return email != null && email.contains("@");
    }
}
