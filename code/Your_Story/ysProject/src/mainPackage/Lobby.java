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
    private ArrayList<Character> charList;
    
    //Other Variables

    /**
     *
     */
    public static final int LOBBY_INGAME = 1;

    /**
     *
     */
    public static final int LOBBY_WAITING = 0;

    /**
     *
     */
    public static final int VOTE_START = 0;

    /**
     *
     */
    public static final int VOTE_KICK = 1;

    /**
     *
     */
    public static final int VOTE_END = 2;
    
    /**
     *
     * @param name
     * @param ID
     * @param quota
     * @param state
     * @param voteID
     * @param storyID
     */
    public Lobby(String name,long ID, int quota, int state, ArrayList<Long> voteID,long storyID){
        this.name = name;
        this.ID = ID;
        this.quota = quota;
        this.state = state;
        this.voteID = voteID;
        this.story = LobbyConnection.getStory(storyID);
        chat = new Chat(ID, AccessHandler.userID);
        votingHandler = new VotingHandler(ID);
        charList = LobbyConnection.getCharacters(ID);
        System.out.println("In");
        if(state != 2)
            seats = LobbyConnection.getSeats(ID);
        System.out.println("Out");
    }
    
    /**
     * Constructor of Lobby
     * @param ID
     * @param storyID
     */
    public Lobby(long ID, long storyID){
      this.ID = ID;
     this.story = new Story(storyID);
      chat = new Chat(ID, AccessHandler.userID);
        votingHandler = new VotingHandler(ID);
        charList = LobbyConnection.getCharacters(ID);
        System.out.println("In");
        seats = LobbyConnection.getSeats(ID);
        System.out.println("Out");
    }
            
    /**
     * Sample Lobby Constructo, it is not used. 
     */
    public Lobby(){
        this.name = "Sample";
        this.ID = 0;
        this.quota = 10;
        this.state = 00;
        this.voteID = new ArrayList<Long>();
     //   this.story = story;
    }
     
     
    //getters 

    /**
     *
     * @return
     */
     public String getName(){return name;}

    /**
     *
     * @return
     */
    public long getID(){return ID;}

    /**
     *
     * @return
     */
    public int getQuota(){return quota;}

    /**
     *
     * @return
     */
    public int getState(){return state;}

    /**
     *
     * @return
     */
    public ArrayList<Long> getVoteID() {return voteID;}

    /**
     *
     * @return
     */
    public Story getStory() {return story;}

    /**
     *
     * @return
     */
    public ArrayList<Seat> getSeats() {return seats;}
    
    
    public void updateSeats() {seats = LobbyConnection.getSeats(ID);}

    /**
     *
     * @return
     */
    public Chat getChat() {return chat;}

    /**
     *
     * @return
     */
    public ArrayList<Character> getChars(){return charList;}
     
    //setters 

    /**
     *
     * @param name
     */
     public void setName(String name) {this.name = name;}

    /**
     *
     * @param ID
     */
    public void setID(long ID) {this.ID = ID;}

    /**
     *
     * @param quota
     */
    public void setQuota(int quota) {this.quota = quota;}

    /**
     *
     * @param state
     */
    public void setState(int state) {
            LobbyConnection.setLobbyState(this.ID,state);
            this.state = state;
    }

    /**
     *
     * @param voteID
     */
    public void setVoteID(ArrayList<Long> voteID) {this.voteID = voteID;}

    /**
     *
     * @param story
     */
    public void setStory(Story story) {this.story = story;}

    /**
     *
     * @param seats
     */
    public void setSeats(ArrayList<Seat> seats) {this.seats = seats;}

    /**
     *
     * @param chat
     */
    public void setChat(Chat chat) {this.chat = chat;}

    /**
     *
     * @param charList
     */
    public void setCharList(ArrayList<Character> charList){this.charList = charList;}
    
     //other methods 
    
    //methods to update data

    /**
     *
     */
    public void updateQuota(){
        quota = LobbyConnection.getQuota(ID);
    }

    /**
     *
     */
    public void updateChars(){
        charList = LobbyConnection.getCharacters(ID);
    }
          
     
     
     //Precondition​ : It cannot be done if there is no empty seats, or the game has already started.

    /**
     *
     * @param aPlayer
     */
     public void addPlayer(Player aPlayer){
         LobbyConnection.addPlayerToLobby(aPlayer.getPlayerID(), ID);
     }
     
    /**
     *
     */
    public void finishGame(){
         setState(2);
     }
     
    /**
     *
     */
    public void updateView(){}
     
    /**
     *
     * @param voteType
     * @param targetPlayer
     */
    public void startVoting(int voteType, Player targetPlayer){
         long id = votingHandler.startVoting(voteType, targetPlayer);
         voteID.add(id);
     }    
     
    /**
     *
     * @param voteType
     */
    public void startVoting(int voteType){
         votingHandler.startVoting(voteType);
     }
     
    /**
     *
     * @param id
     * @param vote
     */
    public void sendVote(long id, boolean vote){
        votingHandler.sendVote(id, vote);
     }
    
    /**
     *
     * @return returns list of not chosen characters in the story
     */
    public ArrayList<Character> getFreeChars(){
        ArrayList<Character> temp = new ArrayList<Character>();
        for(int i = 0; i < charList.size(); i++){
            if(!charList.get(i).getIsTaken())
                temp.add(charList.get(i));
        }
        return temp;
    }
    
    /**
     * Creates a new lobby, assigns the player executed this method in first seat. 
     * @param name name of story
     * @param s : story object
     * @return
     */
    public static Lobby createLobby(String name, Story s){
        return new Lobby(name, LobbyConnection.createLobby(name,s.getID()),1,
                Lobby.LOBBY_WAITING, null,s.getID());
    }
    
    public void kickPlayer(Player p){
        LobbyConnection.removePlayerFromLobby(p.getPlayerID(), this.getID());
    }
}
