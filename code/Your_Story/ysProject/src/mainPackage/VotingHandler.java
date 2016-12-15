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
    
    int lobby;
    
    public VotingHandler(int lobby){
        this.lobby = lobby;
    }
    
    public int startVoting(int type){
        return VoteConnection.startVoting(lobby, type);
    }
    
    public int startVoting(int type, int target){
        return VoteConnection.startVoting(lobby, type, target);
    }
    
    public int sendVote(int voting, boolean vote){
        return VoteConnection.sendVote(voting, vote ? 1 : 0);
    }
    
    public boolean getResult(int voting){
        return VoteConnection.getResult(voting);
    }
}
