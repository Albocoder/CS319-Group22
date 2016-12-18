package mainPackage;
import java.util.ArrayList;



/**
 *
 * @author kaxell
 */
public class Story {
    
    private String description;
    private String timeline;
    private long ID;
    private ArrayList<Character> charList; // Every story has at least 2 characters
    

    public Story (String description, String timeline, long ID)
    {
        this.description = description ;
        this.timeline = timeline ;
        this.ID = ID; 
        charList = new ArrayList<Character>();
    }
    
    public Story(long id){
        Story s = LobbyConnection.getStory(id);
        this.description = s.description;
        this.timeline = s.timeline ;
        this.ID = s.ID; 
    }
    
    public Story ()
    {
        this.description = "Sample" ;
        this.timeline = "Sample" ;
        this.ID = 0; 
        charList = new ArrayList<Character>();
    }
    
    
    //getters
    public String getDescription(){return description;} 
    public String getTimeline(){return timeline;} 
    public long getID(){return ID;} 
    public ArrayList<Character> getCharList(){return charList;}
    
    //setters
    public void setDescription(String description)
    {this.description = description;}
    public void setTimeline(String timeline)
    {this.timeline = timeline;}
    public void setID(long storyID)
    {ID = storyID;}
    public void setCharList(ArrayList<Character> charList)
    {this.charList=charList;}
    
    
    //methods
}