package mainPackage;

/**
 *
 * @author kaxell
 */
public class AccessHandler {

    /**
     *
     */
    public static long userID = -1;

    /**
     *
     */
    public static String username = "";

    /**
     *
     * @param username
     * @param password
     * @param newUser
     * @return
     */
    public static boolean accessGame(String username, String password, boolean newUser) {
        if (newUser) {
            return createAccount(username, password);
        } else {
            return logIn(username, password);
        }
    }

    /**
     *
     */
    public static void logOut() {
        AccessConnection.logout(userID);
        userID = -1;
        username = "";
    }
    
    private static boolean logIn(String username, String password) {
        userID = AccessConnection.authenticate(username, password);
        if (userID == -1)
                return false;
        AccessHandler.username = username;
        return true;
    }

    private static boolean createAccount(String username, String password) {
        userID = AccessConnection.record(username, password);
        if (userID == -1) 
                return false;
        AccessConnection.authenticate(username, password);
        AccessHandler.username = username;
        return true;
    }
}
