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
    
    //Other Variables
    public static final int LOBBY_INGAME = 1;
    public static final int LOBBY_WAITING = 0;
    public static final int VOTE_START = 0;
    public static final int VOTE_KICK = 1;
    public static final int VOTE_END = 2;
    
    
    
    public Lobby(String name,long ID, int quota, int state, ArrayList<Long> voteID,Story story){
        this.name = name;
        this.ID = ID;
        this.quota = quota;
        this.state = state;
        this.voteID = voteID;
        this.story = story;
    }
     public Lobby(){
        this.name = "Sample";
        this.ID = 0;
        this.quota = 10;
        this.state = 00;
        this.voteID = new ArrayList<Long>();
        this.story = new Story();
    }
     
     
    //getters 
     public String getName(){return name;}
     public long getID(){return ID;}
     public int getQuota(){return quota;}
     public int getState(){return state;}
     public ArrayList<Long> getVoteID() {return voteID;}
     public Story getStory() {return story;}
     
    //setters 
     public void setName(String name) {this.name = name;}
     public void setID(long ID) {this.ID = ID;}
     public void setQuota(int quota) {this.quota = quota;}
     public void setState(int state) {this.state = state;}
     public void setVoteID(ArrayList<Long> voteID) {this.voteID = voteID;}
     public void setStory(Story story) {this.story = story;}
     
    
     //other methods 
    
     public void updateQuota(){}
     
     public void updateCharacter(Character aCharacter){}
     
     //Precondition​ : It cannot be done if there is no empty seats, or the game has already started.
     public void addPlayer(Player aPlayer){}
     
     public void finishGame(){}
     
     public void updateView(){}
     
     public void startVoting(int voteType, /*Player*/Object targetPlayer){}
     
     public void startVoting(int voteType){}
     
     public void sendVote(int voteType, boolean vote){}
     
    }