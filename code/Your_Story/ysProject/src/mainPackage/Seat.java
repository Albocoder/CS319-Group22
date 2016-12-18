/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

/**
 *
 * @author kaxell
 */
public class Seat {
    
    private boolean vote;
    private int timer;
    private boolean isOccupied;
    private long ID;
    private Player player;
        
    public Seat(){
        ID = 00;
        timer = 0;
        isOccupied = false;
        vote = false;
        player = null;
    }
    public Seat(long ID){
        this.ID = ID;
    }//
    
    //getters / setters
    
    public boolean getVote(){return vote;}
    public int getTimer(){return timer;}
    public boolean getIsOccupied(){return isOccupied;}
    public long getID(){return ID;}
    public Player getPlayer(){return player;}
    
    public void setVote(boolean vote)
    {this.vote = vote;}
    public void setTimer(int timer)
    {this.timer = timer;}
    public void setIsOccupied(boolean isOccupied)
    {this.isOccupied = isOccupied;}
    public void setID(int ID)
    {this.ID = ID;}
    public void setPlayer(Player player) 
    {this.player = player;}
    
    //other methods 
    public void chooseChar(Character aChar){}
    //This method is called to choose a character. It will 
    //connect a player entry to the player table with a character entry in the character table.
    
    public void startVoting(int voteType){}
    //Sends a player’s intention to start a voting to the lobby.
    //Voting type depends on the value of typeofVote:
    
    public void resetTime(){
    timer = 0;
    }
    
    public void startTime(){
    timer = 120; //Depending on how the countdown is done. 
    }
    
    public void addPlayer(Player player){
    this.player = player;
    isOccupied = true;
    }
   
    public void removePlayer(){
    if (isOccupied){
        player = null;
        isOccupied = false;
        }
    }
    
    public void sendVote(int voteType, boolean vote){
    //This will send the vote of a player in the
    //database and will store it there to be calculated later.
    }
    
    public void startVoting(int voteType, Player targetPlayer){}
    //This method is used to start the
    //voting session. It will be called from a player to start the vote to kick another player.
   
}
