/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

/**
 *
 * @author asus
 */
public class EnemyPanel extends ClonePanel{
    private String name;
    private String type;
    
    public EnemyPanel(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName(){
        return this.name;
    }

    public String getType() {
        return type;
    }
    
    
    
    
}
