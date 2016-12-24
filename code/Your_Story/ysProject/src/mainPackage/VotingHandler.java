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
    
    /**
     *
     * @param lobby
     */
    public VotingHandler(long lobby){
        this.lobby = lobby;
    }
    
    /**
     *
     * @param type
     * @return
     */
    public long startVoting(int type){
        return VoteConnection.startVoting(lobby, type);
    }
    
    /**
     *
     * @param type
     * @param player
     * @return
     */
    public long startVoting(int type, Player player){
        return VoteConnection.startVoting(lobby, type, player.getPlayerID());
    }
    
    /**
     *
     * @param voting
     * @param vote
     * @return
     */
    public long sendVote(long voting, boolean vote){
        return VoteConnection.sendVote(voting, vote ? 1 : 0);
    }
    
    /**
     *
     * @param voting
     * @return
     */
    public boolean getResult(long voting){
        return VoteConnection.getResult(voting);
    }
}
