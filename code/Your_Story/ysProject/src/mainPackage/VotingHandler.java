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
    
    long lobby;
    
    public VotingHandler(long lobby){
        this.lobby = lobby;
    }
    
    public long startVoting(long type){
        return VoteConnection.startVoting(lobby, type);
    }
    
    public long startVoting(long type, long target){
        return VoteConnection.startVoting(lobby, type, target);
    }
    
    public long sendVote(long voting, boolean vote){
        return VoteConnection.sendVote(voting, vote ? 1 : 0);
    }
    
    public boolean getResult(long voting){
        return VoteConnection.getResult(voting);
    }
}
