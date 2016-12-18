/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;
import java.util.ArrayList;
/**
 *
 * @author kaxell
 */
public class Lobby {
    private String name;
    private long ID;
    private int quota;
    private int state;
    private ArrayList<Long> voteID;
    private Story story;
    private ArrayList<Seat> seats;
    private VotingHandler votingHandler;
    private Chat chat;
    
    //Other Variables
    public static final int LOBBY_INGAME = 1;
    public static final int LOBBY_WAITING = 0;
    public static final int VOTE_START = 0;
    public static final int VOTE_KICK = 1;
    public static final int VOTE_END = 2;
    
    
    
    public Lobby(String name,long ID, int quota, int state, ArrayList<Long> voteID,long storyID){
        this.name = name;
        this.ID = ID;
        this.quota = quota;
        this.state = state;
        this.voteID = voteID;
        this.story = LobbyConnection.getStory(storyID);
        chat = new Chat(ID, AccessHandler.userID);
        votingHandler = new VotingHandler(ID);
        
    }
    
    //public Lobby(long ID, long storyID){
      //  this.ID = ID;
       // this.story = new Story(storyID);
    //}
            
            
     public Lobby(){
        this.name = "Sample";
        this.ID = 0;
        this.quota = 10;
        this.state = 00;
        this.voteID = new ArrayList<Long>();
     //   this.story = story;
    }
     
     
    //getters 
     public String getName(){return name;}
     public long getID(){return ID;}
     public int getQuota(){return quota;}
     public int getState(){return state;}
     public ArrayList<Long> getVoteID() {return voteID;}
     public Story getStory() {return story;}
     public ArrayList<Seat> getSeats() {return seats;}
     public Chat getChat() {return chat;}
     
    //setters 
     public void setName(String name) {this.name = name;}
     public void setID(long ID) {this.ID = ID;}
     public void setQuota(int quota) {this.quota = quota;}
     public void setState(int state) {this.state = state;}
     public void setVoteID(ArrayList<Long> voteID) {this.voteID = voteID;}
     public void setStory(Story story) {this.story = story;}
     public void setSeats(ArrayList<Seat> seats) {this.seats = seats;}
     public void setChat(Chat chat) {this.chat = chat;}
    
     //other methods 
    
     public void updateQuota(){
         quota = LobbyConnection.getQuota(ID);
     }
          
     
     
     //Precondition​ : It cannot be done if there is no empty seats, or the game has already started.
     public void addPlayer(Player aPlayer){
         LobbyConnection.addPlayerToLobby(aPlayer.getPlayerID(), ID);
     }
     
     public void finishGame(){
         setState(2);
     }
     
     public void updateView(){}
     
     public void startVoting(int voteType, Player targetPlayer){
         long id = votingHandler.startVoting(voteType, targetPlayer);
         voteID.add(id);
     }    
     
     public void startVoting(int voteType){
         votingHandler.startVoting(voteType);
     }
     
     public void sendVote(long id, boolean vote){
        votingHandler.sendVote(id, vote);
     }
    @Override
    public String toString(){
        return name;
    }
}