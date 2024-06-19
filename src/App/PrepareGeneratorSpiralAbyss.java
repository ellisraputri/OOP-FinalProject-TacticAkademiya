package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class PrepareGeneratorSpiralAbyss {
    private HashMap<String, String> chamberHalfAndEnemies = new LinkedHashMap<>();
    private HashMap<String, ArrayList<String>> teamsFinal = new LinkedHashMap<>();
    private String floor;
    private String chamber;
    private App.WrappedLabel elementsLabel;
    private App.WrappedLabel weaponsLabel;
    private ArrayList<String> bannedName;
    private ArrayList<String> charOwnedList;
    

    //constructor that set attributes
    public PrepareGeneratorSpiralAbyss(String floor, String chamber, WrappedLabel elementsLabel, WrappedLabel weaponsLabel, ArrayList<String> bannedName, ArrayList<String> charOwnedList) {
        this.floor = floor;
        this.chamber = chamber;
        this.elementsLabel = elementsLabel;
        this.weaponsLabel = weaponsLabel;
        this.bannedName = bannedName;
        this.charOwnedList = charOwnedList;
    }
    
    //extract enemies from Spiral Abyss floor and chamber
    public void extractEnemyFromText(){
        //if chamber not equals to all,then we find the chamber until we find it
        //when we find it, the arrived will become true
        if(!(chamber.equals("All"))){
            boolean arrived=false;
            int halfCount=0;

            try {
                File myObj = new File("src/App/text/Floor/floor"+floor+".txt");
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(arrived && halfCount<2){
                        String[] parts = data.split("#");
                        chamberHalfAndEnemies.put(parts[0].trim(), parts[1].trim());
                        halfCount++;
                    }

                    if(data.trim().equals("Chamber"+chamber)){
                        arrived=true;
                    }

                    if(halfCount==2){
                        break;
                    }
                }
                myReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        //if the chamber is all
        else{
            String firstHalf="";
            String secondHalf="";
            
            try {
                File myObj = new File("src/App/text/Floor/floor"+floor+".txt");
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data.contains("#")){
                        String[] parts = data.split("#");
                        if(parts[0].trim().equals("First Half")){
                            firstHalf = firstHalf + parts[1] +", ";
                        }
                        else{
                            secondHalf = secondHalf + parts[1] +", ";
                        }
                    }
                }
                myReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            
            //we put the first half and also second half enemies
            firstHalf = firstHalf.substring(0, firstHalf.length()-2);
            secondHalf = secondHalf.substring(0, secondHalf.length()-2);
            chamberHalfAndEnemies.put("First Half", firstHalf);
            chamberHalfAndEnemies.put("Second Half", secondHalf);
        }
        
    }
    
    
    //check whether the other half team can be generated or not
    private ArrayList<String> checkOtherTeam(ArrayList<String> generatedTeam, String charRedeem){
        ArrayList<String> charCompleteHalf = new ArrayList<>(generatedTeam);
        ArrayList<String> charUncompleteHalf = new ArrayList<>(charOwnedList);
        for(String i:generatedTeam){
            charUncompleteHalf.remove(i);     //the incomplete half must not contain the character that is already in the complete half
        }
        if(!(charRedeem.isEmpty())){
            //char redeem is the character that previously in the complete half
            //but because the incomplete half cannot generate the team, then this character is sent 
            //with the hope that the incomplete half will be fulfilled
            charUncompleteHalf.add(charRedeem);     
        }
        
        ArrayList<String>generatedTeam2 = new ArrayList<>();
        try{
            //we test if the generator can generate the team or not
            App.PrepareGenerator app2 = new App.PrepareGenerator(chamberHalfAndEnemies.get("Second Half"), elementsLabel.getText(), weaponsLabel.getText(), bannedName, charUncompleteHalf);
            generatedTeam2 = app2.getGeneratedTeam();
        }catch(Exception e){
            //if not, then we randomly return a returned that contains the charRedeem for next iteration
            ArrayList<String> returned = new ArrayList<>();
            returned.add(charCompleteHalf.get(2));
            return returned;
        }
        return generatedTeam2;
    }
    
    //check whether the user have the characters to compose a team that is already proven to be good by player base
    private HashMap<ArrayList<String>, ArrayList<String>> checkInTextTeams(){
        HashMap<ArrayList<String>, ArrayList<String>> firstHalfSecondHalf = new LinkedHashMap<>();
        ArrayList<String> firstHalf = new ArrayList<>();
        ArrayList<String> secondHalf = new ArrayList<>();
        
        //retrieve the team data from txt file
        try {
            File myObj = new File("src/App/text/floor12 First Half.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(", ");
                
                //if there is a match in first half, then put it into firsthalf ArrayList
                if(charOwnedList.contains(parts[0]) && charOwnedList.contains(parts[1]) &&charOwnedList.contains(parts[2]) &&charOwnedList.contains(parts[3])){
                    firstHalf.add(data);
                }
            }
            
            File myObj2 = new File("src/App/text/floor12 Second Half.txt");
            myReader = new Scanner(myObj2);
            while(myReader.hasNextLine()){
                String data2 = myReader.nextLine();
                String[] parts2 = data2.split(", ");
                
                //if there is a match in second half, then put it into secondhalf ArrayList
                if(charOwnedList.contains(parts2[0]) && charOwnedList.contains(parts2[1]) &&charOwnedList.contains(parts2[2]) &&charOwnedList.contains(parts2[3])){
                    secondHalf.add(data2);
                }
            }
            
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        ArrayList<String> temp = new ArrayList<>();
        temp.add("null");
        
        //put the first half team into the final team
        for(String first: firstHalf){
            String[] firstTeam = first.split(", ");
            ArrayList<String> al = new ArrayList<>(Arrays.asList(firstTeam));
            firstHalfSecondHalf.put(al, temp);
        }
        
        //put the second half team into the final team
        for(String second: secondHalf){
            String[] secondTeam = second.split(", ");
            ArrayList<String> al = new ArrayList<>(Arrays.asList(secondTeam));
            firstHalfSecondHalf.put(temp, al);
        }
        
        //check whether the firsthalf and secondhalf team has different member or not
        for(String first: firstHalf){
            String[] firstTeam = first.split(", ");
            for(String second: secondHalf){
                String[]secondTeam = second.split(", ");
                ArrayList<String> al1 = new ArrayList<>(Arrays.asList(firstTeam));
                ArrayList<String> al2 = new ArrayList<>(Arrays.asList(secondTeam));
                boolean noCommon = Collections.disjoint(Arrays.asList(firstTeam), Arrays.asList(secondTeam));
                
                //if no common member between these two, then we put to the result
                if(noCommon){
                    firstHalfSecondHalf.put(al1, al2);
                }
            }
        }
        return firstHalfSecondHalf;
    }
    
   
    //check floor Spiral Abyss
    public void checkFloor(){
        //if floor 12
        if(floor.equals("12")){
              //then, we check if the user have the character based on the player base or not
              HashMap<ArrayList<String>, ArrayList<String>> firstHalfSecondHalf = checkInTextTeams();              
              ArrayList<ArrayList<String>> teamListFirstHalf = new ArrayList<>();
              ArrayList<ArrayList<String>> teamListSecondHalf = new ArrayList<>();
              
              boolean success = false;  
              
              for(ArrayList<String> firstHalf: firstHalfSecondHalf.keySet()){
                  if(!(firstHalf.contains("null"))){
                      teamListFirstHalf.add(firstHalf);
                      //if both first half and second half is not null, then we already success and get the final result
                      if(!(firstHalfSecondHalf.get(firstHalf).contains("null"))){
                          teamsFinal.put("First Half", firstHalf);
                          teamsFinal.put("Second Half", firstHalfSecondHalf.get(firstHalf));
                          success = true;
                          break;
                      }
                  }
                  if(!(firstHalfSecondHalf.get(firstHalf).contains("null"))){
                     teamListSecondHalf.add(firstHalfSecondHalf.get(firstHalf));
                  }
              }
                            
              
              int index =0;
              //if we didnt success, then we check whether need to generate what team
              //if the team first half is not empty, then we generate for second half
              while(success == false && index<teamListFirstHalf.size()){
                  success = generateOtherTeam(teamListFirstHalf.get(index), "team2Generate");
                  index++;
              }
              
              index=0;
              //if the team second half is not empty, we generate team for first half
              while(success == false && index<teamListSecondHalf.size()){
                  success = generateOtherTeam(teamListSecondHalf.get(index), "team1Generate");
                  index++;
              }
              
              index=0;
              //if both is empty, then we generate both
              while(success == false){
                  success = generateTeamSpiralAbyss();
              }  
        }
        else{
            //for floor 9 to 11, we just generate
            generateTeamSpiralAbyss();
        }
    }
    
    //generate the other team based on the generated team
    private boolean generateOtherTeam(ArrayList<String> generatedTeam, String type){
        ArrayList<String> tempCharOwned = new ArrayList<>(charOwnedList);
        ArrayList<String> returnedTeam = checkOtherTeam(generatedTeam, "");
        
        //calling the generator
        ArrayList<String>generatedTeam2 = generatedTeam;
        while(returnedTeam.size()==1){
            tempCharOwned.remove(returnedTeam.get(0));

            App.PrepareGenerator app1 = new App.PrepareGenerator(chamberHalfAndEnemies.get("First Half"), elementsLabel.getText(), weaponsLabel.getText(), bannedName, tempCharOwned);
            generatedTeam2 = app1.getGeneratedTeam();
            returnedTeam = checkOtherTeam(generatedTeam2, returnedTeam.get(0));

        }
        
        //putting the teams in the correct position in hashmap
        if(type.equals("allGenerate")){
            teamsFinal.put("First Half", generatedTeam2);
            teamsFinal.put("Second Half", returnedTeam);
            return true;
        }
        else if(type.equals("team1Generate")){
            teamsFinal.put("First Half", returnedTeam);
            teamsFinal.put("Second Half", generatedTeam2);
            return true;
        }
        else if(type.equals("team2Generate")){
            teamsFinal.put("First Half", generatedTeam2);
            teamsFinal.put("Second Half", returnedTeam);
            return true;
        }

        return false;
    }
    
    //calling the generator
    private boolean generateTeamSpiralAbyss(){
        App.PrepareGenerator app = new App.PrepareGenerator(chamberHalfAndEnemies.get("First Half"), elementsLabel.getText(), weaponsLabel.getText(), bannedName, charOwnedList);
        ArrayList<String>generatedTeam = app.getGeneratedTeam();
        boolean result = generateOtherTeam(generatedTeam, "allGenerate");
        return result;
    }
    
    //return final team result
    public HashMap<String, ArrayList<String>> getFinalTeams(){
        return teamsFinal;
    }
}
