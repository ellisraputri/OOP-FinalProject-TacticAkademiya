package App;


public class EnemyPanel extends ClonePanel{
    private String name;    //enemy name
    private String type;    //enemy type
    
    public EnemyPanel(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    //get enemy name
    public String getName(){
        return this.name;
    }
    
    //get enemy type
    public String getType() {
        return type;
    }
    
    
    
    
}
