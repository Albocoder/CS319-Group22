/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

/**
 *
 * @author Asus
 */
public class VotingHandler {
    
    private long lobby;
    
    public VotingHandler(long lobby){
        this.lobby = lobby;
    }
    
    public long startVoting(int type){
    //    return VoteConnection.startVoting(lobby, type);
    return -1;
    }
    
    public long startVoting(int type, /*Player*/Object target){
    //    return VoteConnection.startVoting(lobby, type, target);
    return -1;
    }
    
    public long sendVote(int voting, boolean vote){
        return VoteConnection.sendVote(voting, vote ? 1 : 0);
    }
    
    public boolean getResult(long voteID){
        return VoteConnection.getResult(voteID);
    }
}