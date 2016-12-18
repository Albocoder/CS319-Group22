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
    
    public long startVoting(int type){
        return VoteConnection.startVoting(lobby, type);
    }
    
    public long startVoting(int type, Player player){
        return VoteConnection.startVoting(lobby, type, player.getPlayerID());
    }
    
    public long sendVote(long voting, boolean vote){
        return VoteConnection.sendVote(voting, vote ? 1 : 0);
    }
    
    public boolean getResult(long voting){
        return VoteConnection.getResult(voting);
    }
}
