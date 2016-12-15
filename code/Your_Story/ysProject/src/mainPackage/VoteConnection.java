/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

/**
 *
 * @author Cevat
 */
public class VoteConnection {
    private final static String VOTING_DATA = "voting";
    private final static String VOTE_DATA = "vote";
    
    public static int startVoting(int lobby, int type){
        DBInterface.getConnection().executeStuff("INSERT INTO " + VOTING_DATA + "(lobby, type, target) VALUES(" + lobby + ", " + type + ", 0)");
        int[] temp = DBInterface.selectIntArray(VOTING_DATA, "id", "lobby", lobby);
        return temp[temp.length - 1];
    }
    
    public static int startVoting(int lobby, int type, int target){
        DBInterface.getConnection().executeStuff("INSERT INTO " + VOTING_DATA + "(lobby, type, target) VALUES(" + lobby + ", " + type + ", " + target + ")");
        int[] temp = DBInterface.selectIntArray(VOTING_DATA, "id", "lobby", lobby);
        return temp[temp.length - 1];
    }
    
    public static int sendVote(int voting, int vote){
        DBInterface.getConnection().executeStuff("INSERT INTO " + VOTE_DATA + "(voting, value) VALUES(" + voting + ", " + vote + ")");
        int[] temp = DBInterface.selectIntArray(VOTE_DATA, "id", "voting", voting);
        return temp[temp.length - 1];
    }
    public static boolean getResult(int voting){
        int[] temp = DBInterface.selectIntArray(VOTE_DATA, "value", "voting", voting);
        int counter = 0;
        for(int index = 0; index < temp.length; index++)
            counter += temp[index];
        return counter * 2 > temp.length;
    }
}
