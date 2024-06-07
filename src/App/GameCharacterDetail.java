/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author asus
 */
public class GameCharacterDetail {
    private final GameCharacter gamechar;
    private String url;
    private String constellation;
    private String affiliation;
    private String birthday;
    
    public GameCharacterDetail(GameCharacter gamechar){
        this.gamechar = gamechar;
        findUrl();
        findInfo();
    }
    
    private void findUrl(){
        try {
            File myObj = new File("src/App/text/link.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("    ");
                if(parts[0].equals(gamechar.getName())){
                    this.url = parts[1];
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void findInfo(){
        try{
           //connecting to the url
           Document doc = Jsoup.connect(url).get();
           
           //get constellation, affiliation, birthday info
           constellation = doc.getElementsContainingText("Constellation:").last().text();
           affiliation = doc.getElementsContainingText("Affiliation:").last().text();
           birthday = doc.getElementsContainingText("Birthday:").last().text();
           

            System.out.println(constellation +"  " + affiliation + "   " + birthday);

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public String getConstellation() {
        return constellation;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getBirthday() {
        return birthday;
    }
    
    
    
}
