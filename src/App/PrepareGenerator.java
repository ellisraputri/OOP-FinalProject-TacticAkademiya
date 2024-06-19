package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PrepareGenerator {
    //attributes 
    private String enemies;
    private String preferredElements;
    private String preferredWeapons;
    private ArrayList<String> bannedNames;
    private ArrayList<String> charOwned;
    
    private ArrayList<String> charName = new ArrayList<>();
    private ArrayList<String> charElement = new ArrayList<>();
    private ArrayList<String> charTier = new ArrayList<>();
    private ArrayList<String> charWeapon = new ArrayList<>();
    private ArrayList<Boolean> charPneuma = new ArrayList<>();
    private ArrayList<Boolean> charOusia = new ArrayList<>();
    
    private ArrayList<String> bestElements = new ArrayList<>();
    private ArrayList<String> avoidElements = new ArrayList<>();
    private ArrayList<String> bestWeapons = new ArrayList<>();
    private boolean bestPneuma=false;
    private boolean bestOusia=false;
    
    private String[][] teams;   
    private ArrayList<String> generatedTeam = new ArrayList<>();

    public PrepareGenerator(String enemies, String preferredElements, String preferredWeapons, ArrayList<String> bannedNames, ArrayList<String>charOwned) {
        this.enemies = enemies;
        this.preferredElements = preferredElements;
        this.preferredWeapons = preferredWeapons;
        this.bannedNames = bannedNames;
        this.charOwned = charOwned;
        
        //prepare all things
        prepareCharacters();
        prepareEnemies();
        preparePreference();
        prepareActivation();
    }
    
    private void prepareCharacters(){
        //retrieve character details from txt file
        try {
            File myObj = new File("src/App/text/character.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] partData = data.split("#");
                if(charOwned.contains(partData[0]) && !(bannedNames.contains(partData[0]))){
                    charName.add(partData[0]);
                    charElement.add(partData[1]);
                    charTier.add(partData[2]);
                    charWeapon.add(partData[4]);
                    charPneuma.add(Boolean.parseBoolean(partData[5]));
                    charOusia.add(Boolean.parseBoolean(partData[6]));
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    //the string input is in form with parentheses, such as "Automatons(Ruin Guard)"
    //so, we extract the content, so we can get the "Ruin Guard" as the output
    private String extractContentEnemy(String input) {
        // Define the pattern to match text within parentheses
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder();

        // Iterate over all matches and append the content to the result
        while (matcher.find()) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(matcher.group(1));
        }

        return result.toString();
    }
    
    //retrieve enemy details from txt file
    private void findEnemyFromText(String enemyName){       
        try {
            File myObj = new File("src/App/text/enemy.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] partData = data.split("#");
                if(enemyName.contains(partData[0])){
                    if(!(partData[1].equals("null"))){
                        avoidElements.add(partData[1]);                        
                    }
                    if(!(partData[2].equals("null"))){
                        bestElements.add(partData[2]);                        
                    }
                    if(!(partData[3].equals("null"))){
                        bestElements.add(partData[3]);                        
                    }
                    boolean pneuma = Boolean.parseBoolean(partData[4]);
                    if(pneuma){
                        bestPneuma = pneuma;
                    }
                    boolean ousia = Boolean.parseBoolean(partData[5]);
                    if(ousia){
                        bestOusia = ousia;
                    }
                }
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //extract the enemy name and retrieve enemy details
    private void prepareEnemies(){
        enemies = extractContentEnemy(enemies);
        String[] enemyNames = enemies.split(", ");
        
        for(String name: enemyNames){
            findEnemyFromText(name);
        }
    }
    
    //set up the user preferences on elements and weapons
    private void preparePreference(){
        if(!(preferredElements.equals("-")) && !(bestElements.contains(preferredElements)) && !(avoidElements.contains(preferredElements))){
            bestElements.add(preferredElements);
        }
        if(!(preferredWeapons.equals("-")) && !(bestWeapons.contains(preferredWeapons)) ){
            bestWeapons.add(preferredWeapons);
        }
    }
    
    //prepare to activate the generator
    private void prepareActivation(){
        //setting all attributes 
        CharGenerator demo = new CharGenerator();
        demo.setBestElements(bestElements);
        demo.setAvoidElements(avoidElements);
        demo.setBestWeapon(bestWeapons);
        demo.setBestPneuma(bestPneuma);
        demo.setBestOusia(bestOusia);
        
        //execute and loop until find the team
        boolean state = execute();
        while(state==false){
            state = execute();
        }
        
        //extract the result to an ArrayList
        for(int i=0; i<teams.length; i++){
            for(String role: teams[i]){
                generatedTeam.add(role);
            }
        }
    }
    
    //activate the generator
    public void activateGenerator(){
        CharGenerator demo = new CharGenerator();
        teams = demo.generator(charName, charElement, charTier, charWeapon, charPneuma, charOusia);
    }

    //check whether the team is valid or not
    //if not, then return false
    public boolean check(){
        for(int i=0; i<teams.length; i++){
            for(int j=0; j<teams[i].length; j++){
                if(teams[i][j].contains("Team unavailable")){
                    String nameToRemove = teams[i][j+1];
                    charElement.remove(charName.indexOf(nameToRemove));
                    charTier.remove(charName.indexOf(nameToRemove));
                    charWeapon.remove(charName.indexOf(nameToRemove));
                    charPneuma.remove(charName.indexOf(nameToRemove));
                    charOusia.remove(charName.indexOf(nameToRemove));
                    charName.remove(nameToRemove);
                    return false;
                }
            }
        }
        return true;
    }

    //execute the generator and check 
    public boolean execute(){
        activateGenerator();
        boolean state = check();
        return state;
    }
    
    //return the final team result
    public ArrayList<String> getGeneratedTeam(){
        return generatedTeam;
    }

    
}
