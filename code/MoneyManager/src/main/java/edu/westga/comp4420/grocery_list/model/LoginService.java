package edu.westga.comp4420.grocery_list.model;

/**
 * Validates the demonstration user's login credentials.
 *
 * @author Levi Rukundo
 * @version Fall 2026
 */
public final class LoginService {

    /**
     * Checks the provided credentials.
     *
     * @param username the username
     * @param password the password
     * @return true when both credentials are correct
     */
    public boolean authenticate(String username, String password) {
        return "user".equals(username) && "Money123!".equals(password);
    }
}