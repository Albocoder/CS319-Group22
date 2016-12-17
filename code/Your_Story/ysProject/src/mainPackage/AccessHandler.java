package mainPackage;

public class AccessHandler {
    public static long userID = -1;
    public static String username = "";

    public static boolean accessGame(String username, String password, boolean newUser) {
        if (newUser) {
            return createAccount(username, password);
        } else {
            return logIn(username, password);
        }
    }

    public static void logOut() {
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
        AccessHandler.username = username;
        return true;
    }
}
