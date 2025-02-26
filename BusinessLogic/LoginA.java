package BusinessLogic;

public class LoginA {
    private static boolean isAuthenticated = false;

    public static boolean login(String username, String password) {
        if ("admin".equals(username) && "pass".equals(password)) {
            isAuthenticated = true;
            return true;
        }
        return false;
    }

    public static boolean isAuthenticated() {
        return isAuthenticated;
    }

    public static void logout() {
        isAuthenticated = false;
    }
}
