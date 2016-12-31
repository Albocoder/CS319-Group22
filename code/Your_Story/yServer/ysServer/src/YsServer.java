import accepters.*;
/**
 *
 * @author Erin Avllazagaj
 * 
 * This main class is just calling the AcceptersManager with the maximum backlog
 */

public class YsServer {
    
    //atm the project is set to run at port 1337 with maximum of 20 threads
    public static void main(String[] args) {
        new AcceptersManager(Integer.parseInt(args[0]));
    }
    
}
