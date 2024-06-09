/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author asus
 */
public class GameCharacterDetail {
    private final GameCharacter gamechar;
    private String url;
    private String url2;
    private String constellation;
    private String affiliation;
    private String birthday;
    private Set<String> weapons = new LinkedHashSet<>();
    private Set<String> artifacts = new LinkedHashSet<>();
    private String artifactSands;
    private String artifactGoblet;
    private String artifactCirclet;
    private String artifactSubstats;
    private ArrayList<String> teams = new ArrayList<>();
    private ImageIcon namecard;
    
    public GameCharacterDetail(GameCharacter gamechar){
        this.gamechar = gamechar;
        findUrl();
        findBasicInfo();
        findWeaponsArtifactsTeams();
        findNamecard();
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
                    this.url2 = parts[2];
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void findBasicInfo(){
        try{
           //connecting to the url
           Document doc = Jsoup.connect(url).get();
           
           //get constellation, affiliation, birthday info
           if(gamechar.getName().contains("Traveler")){
               String consMale = doc.getElementsContainingText("Constellation:").get(21).text();
               String consFemale = doc.getElementsContainingText("Constellation:").last().text().replaceAll("Constellation: ", "");
               constellation = consMale +" (Aether), " + consFemale + " (Lumine)";
               affiliation = "-";
           }
           else{
                constellation = doc.getElementsContainingText("Constellation:").last().text();
                affiliation = doc.getElementsContainingText("Affiliation:").last().text().replaceAll("Affiliation: ", "");
           }
           birthday = doc.getElementsContainingText("Birthday:").last().text();
           
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    private void findWeaponsArtifactsTeams(){
        try{
           //connecting to the url
           Document doc = Jsoup.connect(url2).get();
           
           //get weapons, artifacts and filter it 
           Elements weaponsArtifacts = doc.getElementsByClass("character-build-weapon-name");
           int i=0;
           for(Element e: weaponsArtifacts){
                String str = e.text();
                if(gamechar.getName().equals("Dehya")){
                    if(i<4){
                        weapons.add(str);
                    }
                    else{
                        artifacts.add(str);
                    }
                }
                else{
                    if(i<5){
                        str=str.replaceAll("R\\d+", "").trim();
                        weapons.add(str);
                    }
                    else{
                        artifacts.add(str);
                    }
                }
                i++;
           }
           
           
           //get artifacts detailed info
           Elements artifactStats = doc.getElementsByClass("character-stats-item");
           artifactSands = artifactStats.get(0).text().replaceAll("Sands: ", "");
           artifactGoblet = artifactStats.get(1).text().replaceAll("Goblet: ", "");
           artifactCirclet = artifactStats.get(2).text().replaceAll("Circlet: ", "");
           artifactSubstats = artifactStats.get(3).text().replaceAll("Substats: ", "");
           
           //find teams
           if(gamechar.getName().equals("Hydro Traveler")){
                try {
                    File myObj = new File("src/App/text/hydroTravelerTeams.txt");
                    Scanner myReader = new Scanner(myObj);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        String[] parts = data.split("#");
                        teams.addAll(Arrays.asList(parts));
                    }
                    myReader.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
           }
           else if(gamechar.getName().equals("Amber")){
               try {
                    File myObj = new File("src/App/text/amberTeams.txt");
                    Scanner myReader = new Scanner(myObj);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        String[] parts = data.split("#");
                        teams.addAll(Arrays.asList(parts));
                    }
                    myReader.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
           }
           else{
                Elements charTeams = doc.getElementsByClass("character-portrait");
                for(Element e: charTeams){
                     String name = e.select("img").attr("alt");
                     name = (name.equals("Itto"))? "Arataki Itto" : name;
                     name = (name.equals("Kokomi"))? "Sangonomiya Kokomi" : name;
                     name = (name.equals("Ayaka"))? "Kamisato Ayaka" : name;
                     name = (name.equals("Childe"))? "Tartaglia" : name;
                     name = (name.equals("Kazuha"))? "Kaedehara Kazuha":name;
                     name = (name.equals("Ayato"))? "Kamisato Ayato" : name;
                     name = (name.equals("Heizou"))? "Shikanoin Heizou":name;
                     name = (name.equals("Raiden"))? "Raiden Shogun":name;
                     name = (name.equals("Sara"))? "Kujou Sara":name;
                     if(name.contains("Traveler")){
                         name = name.replaceAll("Traveler \\(([^)]+)\\)", "$1 Traveler");
                     }
                     teams.add(name);
                }
                teams.remove(0);
           }
           
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    private void findNamecard(){
        namecard = new ImageIcon("src/App/image/CharacterCard/Namecard/" + gamechar.getName() +".png");
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
    
    public ArrayList<ImageIcon> getWeaponImages(){
        ArrayList<ImageIcon> weaponImageList = new ArrayList<>();
        for(String s: weapons){
            weaponImageList.add(new ImageIcon("src/App/image/Weapon/" + gamechar.getWeapon() + "/Portraits " + s + ".png"));
        }
        return weaponImageList;
    }

    public ArrayList<String> getWeapons() {
        ArrayList<String> weapons2 = new ArrayList<>(weapons);
        return weapons2;
    }
    
    public ArrayList<ImageIcon> getArtifactImages(){
        ArrayList<ImageIcon> artifactImageList = new ArrayList<>();
        for(String s: artifacts){
            artifactImageList.add(new ImageIcon("src/App/image/Artifacts/Portraits " + s + ".png"));
        }
        System.out.println(artifactImageList.size());
        return artifactImageList;
    }

    public ArrayList<String> getArtifacts() {
        ArrayList<String> artifact2 = new ArrayList<>(artifacts);
        return artifact2;
    }

    public String getArtifactSands() {
        return artifactSands;
    }

    public String getArtifactGoblet() {
        return artifactGoblet;
    }

    public String getArtifactCirclet() {
        return artifactCirclet;
    }

    public String getArtifactSubstats() {
        return artifactSubstats;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public ImageIcon getNamecard() {
        return namecard;
    }
    
    
    
    
}
