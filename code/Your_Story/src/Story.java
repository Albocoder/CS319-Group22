
package Story_Line;

/**
 *
 * @author kaxell
 */
public class Story {
    
    private String description;
    private String timeline;
    private long ID;
    private Character[] charList;
    

    public Story (String description, String timeline, long ID)
    {
        this.description = description ;
        this.timeline = timeline ;
        this.ID = ID; 
    }
    
    public Story ()
    {
        this.description = "Sample" ;
        this.timeline = "Sample" ;
        this.ID = 0; 
    }
    
    
    //getters
    public String getDescription(){return description;} 
    public String getTimeline(){return timeline;} 
    public long getID(){return ID;} 
    public Character[] getCharList(){return charList;}
    
    //setters
    public void setDescription(String description)
    {this.description = description;}
    public void setTimeline(String timeline)
    {this.timeline = timeline;}
    public void setID(long storyID)
    {ID = storyID;}
    public void setCharList(Character[] charList)
    {System.arraycopy(charList, 0, this.charList,0, charList.length);}
}
