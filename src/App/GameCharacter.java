package App;

public class GameCharacter {
    private String name;        //character name
    private String element;     //character element
    private String tier;        //character tier
    private String weapon;      //character weapon
    private boolean pneuma;     //do the character have pneuma
    private boolean ousia;      //do the character have ousia
    private int stars;          //character rarity

    
    public GameCharacter(){}

    public GameCharacter(String name, String element, String tier, String weapon, boolean pneuma, boolean ousia){
        this.name = name;
        this.element =  element;
        this.tier = tier;
        this.weapon = weapon;  
        this.pneuma = pneuma;
        this.ousia = ousia; 
    }
    
    //get character name
    public String getName(){
        return this.name;
    }
    
    //get character element
    public String getElement(){
        return this.element;
    }
    
    //get character tier
    public String getTier(){
        return this.tier;
    }
    
    //get character weapon
    public String getWeapon(){
        return this.weapon;
    }
    
    //get character pneuma
    public boolean getPneuma(){
        return this.pneuma;
    }
    
    //get character ousia
    public boolean getOusia(){
        return this.ousia;
    }
        
    //get character rarity
    public int getStars() {
        return stars;
    }
    
    //set character rarity
    public void setStars(int stars) {
        this.stars = stars;
    }
    
}
