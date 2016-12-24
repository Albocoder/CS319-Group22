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
    private boolean isOccupied = false;
    private long seatID;
    private long playerID;
    private long lobbyID;
    private long charID;
    private Player player; 
    private Character character; 
    
    /**
     *
     */
    public Seat(){
        seatID = 00;
        timer = 0;
        isOccupied = false;
        vote = false;
        playerID= 0;
        charID= 0;
        //
    }

    /**
     *
     * @param seatID
     * @param lobbyID
     * @param charID
     */
    public Seat(long seatID,long lobbyID,long charID){
        this.seatID = seatID;
        this.lobbyID = lobbyID;
        this.charID = charID;
        if(charID > 0)
            character = new Character(charID);
    }//
    
    /**
     *
     * @param seatID
     * @param lobbyID
     * @param charID
     * @param playerID
     */
    public Seat(long seatID,long lobbyID,long charID, long playerID){
        this.seatID = seatID;
        this.lobbyID = lobbyID;
        this.charID = charID;
        this.playerID = playerID;
        if(charID > 0)
            character = new Character(charID);
        if(playerID > 0){
            player = new Player(playerID);
            isOccupied = true;
        }
    }//
    
    
    
    //getters / setters

    /**
     *
     * @return
     */
    
    public boolean getVote(){return vote;}

    /**
     *
     * @return
     */
    public int getTimer(){return timer;}

    /**
     *
     * @return
     */
    public boolean getIsOccupied(){return isOccupied;}

    /**
     *
     * @return
     */
    public long getSeatID(){return seatID;}

    /**
     *
     * @return
     */
    public Player getPlayer(){return player;}

    /**
     *
     * @return
     */
    public long getPlayerID(){return playerID;}

    /**
     *
     * @return
     */
    public long getLobbyID(){return lobbyID;}

    /**
     *
     * @return
     */
    public long getCharID(){return charID;}

    /**
     *
     * @return
     */
    public Character getCharacter(){return character;}
    
    /**
     *
     * @param vote
     */
    public void setVote(boolean vote)
    {this.vote = vote;}

    /**
     *
     * @param timer
     */
    public void setTimer(int timer)
    {this.timer = timer;}

    /**
     *
     * @param isOccupied
     */
    public void setIsOccupied(boolean isOccupied)
    {this.isOccupied = isOccupied;}

    /**
     *
     * @param seatID
     */
    public void setSeatID(int seatID)
    {this.seatID = seatID;}

    /**
     *
     * @param player
     */
    public void setPlayer(Player player) 
    {this.player = player;}

    /**
     *
     * @param lobbyID
     */
    public void setLobbyID(long lobbyID)
    {this.lobbyID = lobbyID;}

    /**
     *
     * @param charID
     */
    public void setCharID(long charID)
    {this.charID = charID;}

    /**
     *
     * @param character
     */
    public void setCharacter(Character character)
    {this.character = character;}
    
    //other methods 

    /**
     *
     * @param aChar
     */
    public void chooseChar(Character aChar){}
    //This method is called to choose a character. It will 
    //connect a player entry to the player table with a character entry in the character table.
    
    /**
     *
     * @param voteType
     */
    public void startVoting(int voteType){}
    //Sends a player’s intention to start a voting to the lobby.
    //Voting type depends on the value of typeofVote:
    
    /**
     *
     */
    public void resetTime(){
    timer = 0;
    }
    
    /**
     *
     */
    public void startTime(){
        timer = 120; //Depending on how the countdown is done. 
    }
    
    /**
     *
     * @param playerID
     */
    public void addPlayer(long playerID){
        this.playerID = playerID;
        player = new Player(playerID);
        isOccupied = true;
    }
   
    /**
     *
     */
    public void removePlayer(){
        if (isOccupied){
            playerID = 0;
            isOccupied = false;
            player = null;
            charID = 0;
            character = null;
        }
    }
    
    /**
     *
     * @param voteType
     * @param vote
     */
    public void sendVote(int voteType, boolean vote){
    //This will send the vote of a player in the
    //database and will store it there to be calculated later.
    }
    
    /**
     *
     * @param voteType
     * @param targetPlayer
     */
    public void startVoting(int voteType, Player targetPlayer){}
    //This method is used to start the
    //voting session. It will be called from a player to start the vote to kick another player.
   
}
