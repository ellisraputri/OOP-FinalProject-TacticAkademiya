package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.ImageIcon;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GameCharacterDetail {
    private final GameCharacter gamechar;
    private String url;     //to find basic info
    private String url2;    //to find weapon, artifact, teams
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
    private String cnVoice;
    private String jpVoice;
    
    public GameCharacterDetail(GameCharacter gamechar){
        this.gamechar = gamechar;
        findUrl();
        findBasicInfo();
        findWeaponsArtifactsTeams();
        findNamecard();
    }
    
    //find the url for the character based on txt
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
    
    //find basic info
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
           cnVoice = doc.getElementsContainingText("Chinese:").last().text().replaceAll("Chinese:", "");
           jpVoice = doc.getElementsContainingText("Japanese:").last().text().replaceAll("Japanese:", "");
           
           //setting the ones that their va name is not showing right
           if(gamechar.getName().equals("Bennett")){
               jpVoice = " 逢坂良太/Osaka Ryota";
           }
           if(gamechar.getName().equals("Chongyun")){
               jpVoice = " 斉藤壮馬/Saito Soma";
           }
           if(gamechar.getName().equals("Diluc")){
               jpVoice = " 小野賢章/Ono Kensho";
           }
           if(gamechar.getName().equals("Tartaglia")){
               jpVoice = " 木村良平/Kimura Ryohei";
           }
           if(gamechar.getName().equals("Kaveh")){
               jpVoice = " 内田雄馬/Uchida Yuma";
           }
           if(gamechar.getName().equals("Keqing")){
               cnVoice = " 谢莹/Xie Ying";
           }
           if(gamechar.getName().equals("Lisa")){
               cnVoice = " 钟可/Zhong Ke";
           }
           if(gamechar.getName().equals("Kaedehara Kazuha")){
               jpVoice = " Shimazaki Nobunaga";
           }
           if(gamechar.getName().equals("Venti")){
               cnVoice=" 喵☆酱/Miao Jiang";
           }
           if(gamechar.getName().equals("Kaeya")){
               cnVoice = " 孙晔/Sun Ye";
               jpVoice = " 鳥海浩輔/Toriumi Kosuke";
           }
           if(gamechar.getName().equals("Mika")){
               jpVoice = " 三瓶由布子/Sanpei Yuko";
           }
           if(gamechar.getName().equals("Razor")){
               jpVoice = " 内山昂辉/Uchiyama Koki";
               cnVoice = " 周帅/Zhou Shuai";
           }
  
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    //find weapons, artifacts, and teams
    private void findWeaponsArtifactsTeams(){
        try{
           //connecting to the url
           Document doc = Jsoup.connect(url2).get();
           
           //get weapons, artifacts and filter it 
           Elements weaponsArtifacts = doc.getElementsByClass("character-build-weapon-name");
           int i=0;
           for(Element e: weaponsArtifacts){
                String str = e.text();
                //for Dehya, the weapon only four
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
           
           //find teams for Hydro Traveler from txt
           //this is because the web does not provide
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
           
           //find teams for Amber from txt
           //this is because the web does not provide
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
           
           //find other characters team from the url
           else{
                Elements charTeams = doc.getElementsByClass("character-portrait");
                for(Element e: charTeams){
                     String name = e.select("img").attr("alt");
                     
                     //set some character name, because the web uses nickname
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
    
    //find namecard from folder
    private void findNamecard(){
        namecard = new ImageIcon("src/App/image/CharacterCard/Namecard/" + gamechar.getName() +".png");
    }
    
    //get character constellation name
    public String getConstellation() {
        return constellation;
    }

    //get character affilliation
    public String getAffiliation() {
        return affiliation;
    }

    //get character birthday
    public String getBirthday() {
        return birthday;
    }
    
    //get weapon images based on the character best weapons
    public ArrayList<ImageIcon> getWeaponImages(){
        ArrayList<ImageIcon> weaponImageList = new ArrayList<>();
        for(String s: weapons){
            weaponImageList.add(new ImageIcon("src/App/image/Weapon/" + gamechar.getWeapon() + "/Portraits " + s + ".png"));
        }
        return weaponImageList;
    }
    
    //get the weapon names
    public ArrayList<String> getWeapons() {
        ArrayList<String> weapons2 = new ArrayList<>(weapons);
        return weapons2;
    }
    
    //get artifact images based on the character best artifact
    public ArrayList<ImageIcon> getArtifactImages(){
        ArrayList<ImageIcon> artifactImageList = new ArrayList<>();
        for(String s: artifacts){
            artifactImageList.add(new ImageIcon("src/App/image/Artifacts/Portraits " + s + ".png"));
        }
        return artifactImageList;
    }
    
    //get the artifact names
    public ArrayList<String> getArtifacts() {
        ArrayList<String> artifact2 = new ArrayList<>(artifacts);
        return artifact2;
    }
    
    //get artifacts sands stats
    public String getArtifactSands() {
        return artifactSands;
    }
    
    //get artifacts goblet stats
    public String getArtifactGoblet() {
        return artifactGoblet;
    }

    //get artifacts circlet stats
    public String getArtifactCirclet() {
        return artifactCirclet;
    }
    
    //get artifact substats
    public String getArtifactSubstats() {
        return artifactSubstats;
    }
    
    //get characters team comps
    public ArrayList<String> getTeams() {
        return teams;
    }

    //get character namecard
    public ImageIcon getNamecard() {
        return namecard;
    }

    //get character CN voice actor name
    public String getCnVoice() {
        return cnVoice;
    }

    //get character JP voice actor name
    public String getJpVoice() {
        return jpVoice;
    }
    
    
    
    
    
}
