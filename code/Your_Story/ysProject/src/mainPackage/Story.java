package mainPackage;
import java.util.ArrayList;



/**
 *
 * @author kaxell
 * 
 * Constructor class for story.
 * Requirement: Every story has at least 2 characters
 */
public class Story {
    
    private String description;
    private String timeline;
    private long ID;
    private ArrayList<Character> charList; 
    
    /**
     *
     * @param description
     * @param timeline
     * @param ID
     */
    public Story (String description, String timeline, long ID)
    {
        this.description = description ;
        this.timeline = timeline ;
        this.ID = ID; 
        charList = LobbyConnection.getCharactersByStory(ID);
    }
    
    /**
     *
     * @param id
     */
    public Story(long id){
        Story s = LobbyConnection.getStory(id);
        this.description = s.description;
        this.timeline = s.timeline ;
        this.ID = s.ID; 
        charList = LobbyConnection.getCharactersByStory(ID);
    }
    
    /**
     *
     */
    public Story ()
    {
        this.description = "Sample" ;
        this.timeline = "Sample" ;
        this.ID = 0; 
        charList = new ArrayList<Character>();
    }
    
    
    //getters

    /**
     *
     * @return
     */
    public String getDescription(){return description;} 

    /**
     *
     * @return
     */
    public String getTimeline(){return timeline;} 

    /**
     *
     * @return
     */
    public long getID(){return ID;} 

    /**
     *
     * @return
     */
    public ArrayList<Character> getCharList(){return charList;}
    
    //setters

    /**
     *
     * @param description
     */
    public void setDescription(String description)
    {this.description = description;}

    /**
     *
     * @param timeline
     */
    public void setTimeline(String timeline)
    {this.timeline = timeline;}

    /**
     *
     * @param storyID
     */
    public void setID(long storyID)
    {ID = storyID;}

    /**
     *
     * @param charList
     */
    public void setCharList(ArrayList<Character> charList)
    {this.charList=charList;}
    
  

    /**
     * Returns the stories if user is authenticated
     * or an empty stoy arraylist if encounters any error
     * @return
     */
    public static ArrayList<Story> getStories(){
        try{
            return LobbyConnection.getStories(HomePage.getPlayer().getPlayerID());
        }
        catch(Exception e){
            return new ArrayList<Story>(0);
        }
    }
    //methods
}
