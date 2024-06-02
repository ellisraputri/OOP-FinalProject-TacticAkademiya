package App;

public class GameCharacter {
    private String name;
    private String element;
    private String tier;
    private String weapon;
    private boolean pneuma;
    private boolean ousia;
    
    public GameCharacter(){}

    public GameCharacter(String name, String element, String tier, String weapon, boolean pneuma, boolean ousia){
        this.name = name;
        this.element =  element;
        this.tier = tier;
        this.weapon = weapon;  
        this.pneuma = pneuma;
        this.ousia = ousia; 
    }

    public String getName(){
        return this.name;
    }

    public String getElement(){
        return this.element;
    }

    public String getTier(){
        return this.tier;
    }

    public String getWeapon(){
        return this.weapon;
    }

    public boolean getPneuma(){
        return this.pneuma;
    }

    public boolean getOusia(){
        return this.ousia;
    }

}
