/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class PrepareGeneratorSpiralAbyss {
    private HashMap<String, String> chamberHalfAndEnemies = new LinkedHashMap<>();
    private HashMap<String, ArrayList<String>> teamsFinal = new LinkedHashMap<>();
    private String floor;
    private String chamber;
    private App.WrappedLabel elementsLabel;
    private App.WrappedLabel weaponsLabel;
    private ArrayList<String> bannedName;
    private ArrayList<String> charOwnedList;
    

    public PrepareGeneratorSpiralAbyss(String floor, String chamber, WrappedLabel elementsLabel, WrappedLabel weaponsLabel, ArrayList<String> bannedName, ArrayList<String> charOwnedList) {
        this.floor = floor;
        this.chamber = chamber;
        this.elementsLabel = elementsLabel;
        this.weaponsLabel = weaponsLabel;
        this.bannedName = bannedName;
        this.charOwnedList = charOwnedList;
    }
    
    public void extractEnemyFromText(){
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
            
            firstHalf = firstHalf.substring(0, firstHalf.length()-2);
            secondHalf = secondHalf.substring(0, secondHalf.length()-2);
            chamberHalfAndEnemies.put("First Half", firstHalf);
            chamberHalfAndEnemies.put("Second Half", secondHalf);
        }
        
    }
    
    
    private ArrayList<String> checkOtherTeam(ArrayList<String> generatedTeam, String charRedeem){
        ArrayList<String> charCompleteHalf = new ArrayList<>(generatedTeam);
        ArrayList<String> charUncompleteHalf = new ArrayList<>(charOwnedList);
        for(String i:generatedTeam){
            charUncompleteHalf.remove(i);
        }
        if(!(charRedeem.isEmpty())){
            charUncompleteHalf.add(charRedeem);
        }
        
        ArrayList<String>generatedTeam2 = new ArrayList<>();
        try{
            App.PrepareGenerator app2 = new App.PrepareGenerator(chamberHalfAndEnemies.get("Second Half"), elementsLabel.getText(), weaponsLabel.getText(), bannedName, charUncompleteHalf);
            generatedTeam2 = app2.getGeneratedTeam();
        }catch(Exception e){
            ArrayList<String> returned = new ArrayList<>();
            returned.add(charCompleteHalf.get(2));
            return returned;
        }
        return generatedTeam2;
    }
    
    private HashMap<ArrayList<String>, ArrayList<String>> checkInTextTeams(){
        HashMap<ArrayList<String>, ArrayList<String>> firstHalfSecondHalf = new LinkedHashMap<>();
        ArrayList<String> firstHalf = new ArrayList<>();
        ArrayList<String> secondHalf = new ArrayList<>();
        try {
            File myObj = new File("src/App/text/floor12 First Half.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(", ");
                
                if(charOwnedList.contains(parts[0]) && charOwnedList.contains(parts[1]) &&charOwnedList.contains(parts[2]) &&charOwnedList.contains(parts[3])){
                    firstHalf.add(data);
                }
            }
            
            File myObj2 = new File("src/App/text/floor12 Second Half.txt");
            myReader = new Scanner(myObj2);
            while(myReader.hasNextLine()){
                String data2 = myReader.nextLine();
                String[] parts2 = data2.split(", ");
                if(charOwnedList.contains(parts2[0]) && charOwnedList.contains(parts2[1]) &&charOwnedList.contains(parts2[2]) &&charOwnedList.contains(parts2[3])){
                    secondHalf.add(data2);
                }
            }
            
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println(firstHalf +" ditambah" + secondHalf);
        ArrayList<String> temp = new ArrayList<>();
        temp.add("null");
        
        for(String first: firstHalf){
            String[] firstTeam = first.split(", ");
            ArrayList<String> al = new ArrayList<>(Arrays.asList(firstTeam));
            firstHalfSecondHalf.put(al, temp);
        }
        
        for(String second: secondHalf){
            String[] secondTeam = second.split(", ");
            ArrayList<String> al = new ArrayList<>(Arrays.asList(secondTeam));
            firstHalfSecondHalf.put(temp, al);
        }
        
        for(String first: firstHalf){
            String[] firstTeam = first.split(", ");
            for(String second: secondHalf){
                String[]secondTeam = second.split(", ");
                ArrayList<String> al1 = new ArrayList<>(Arrays.asList(firstTeam));
                ArrayList<String> al2 = new ArrayList<>(Arrays.asList(secondTeam));
                boolean noCommon = Collections.disjoint(Arrays.asList(firstTeam), Arrays.asList(secondTeam));
                if(noCommon){
                    firstHalfSecondHalf.put(al1, al2);
                }
            }
        }
        return firstHalfSecondHalf;
    }
    
   
    public void checkFloor(){
        if(floor.equals("12")){
              HashMap<ArrayList<String>, ArrayList<String>> firstHalfSecondHalf = checkInTextTeams();
              System.out.println("TEAMSSSSSSSS" + firstHalfSecondHalf);
              
              ArrayList<ArrayList<String>> teamListFirstHalf = new ArrayList<>();
              ArrayList<ArrayList<String>> teamListSecondHalf = new ArrayList<>();
              
              boolean success = false;  
              
              for(ArrayList<String> firstHalf: firstHalfSecondHalf.keySet()){
                  if(!(firstHalf.contains("null"))){
                      teamListFirstHalf.add(firstHalf);
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
              while(success == false && index<teamListFirstHalf.size()){
                  success = generateOtherTeam(teamListFirstHalf.get(index), "team2Generate");
                  index++;
              }
              
              index=0;
              while(success == false && index<teamListSecondHalf.size()){
                  success = generateOtherTeam(teamListSecondHalf.get(index), "team1Generate");
                  index++;
              }
              
              index=0;
              while(success == false){
                  success = generateTeamSpiralAbyss();
              }
              
        }
        else{
            generateTeamSpiralAbyss();
        }
    }
    
    private boolean generateOtherTeam(ArrayList<String> generatedTeam, String type){
        ArrayList<String> tempCharOwned = new ArrayList<>(charOwnedList);
        ArrayList<String> returnedTeam = checkOtherTeam(generatedTeam, "");

        ArrayList<String>generatedTeam2 = generatedTeam;
        while(returnedTeam.size()==1){
            tempCharOwned.remove(returnedTeam.get(0));

            System.out.println("TEMP CHAR OWNEDDDDDDDDDDDD : " + tempCharOwned);
            App.PrepareGenerator app1 = new App.PrepareGenerator(chamberHalfAndEnemies.get("First Half"), elementsLabel.getText(), weaponsLabel.getText(), bannedName, tempCharOwned);
            generatedTeam2 = app1.getGeneratedTeam();
            returnedTeam = checkOtherTeam(generatedTeam2, returnedTeam.get(0));

        }
        
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
        
        System.out.println("GENERATED TEAM 2" + generatedTeam2);
        System.out.println("RETURNED TEAM" +returnedTeam);
        return false;
    }
    
    private boolean generateTeamSpiralAbyss(){
        App.PrepareGenerator app = new App.PrepareGenerator(chamberHalfAndEnemies.get("First Half"), elementsLabel.getText(), weaponsLabel.getText(), bannedName, charOwnedList);
        ArrayList<String>generatedTeam = app.getGeneratedTeam();
        System.out.println("GENERATED 1" + generatedTeam);

        boolean result = generateOtherTeam(generatedTeam, "allGenerate");
        return result;
    }
    
    public HashMap<String, ArrayList<String>> getFinalTeams(){
        return teamsFinal;
    }
}
