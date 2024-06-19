package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class CharGenerator {
    private static ArrayList<GameCharacter> charSS = new ArrayList<>(); //list for characters tier SS
    private static ArrayList<GameCharacter> charS = new ArrayList<>();  //list for characters tier S
    private static ArrayList<GameCharacter> charA = new ArrayList<>();  //list for characters tier A
    private static ArrayList<GameCharacter> charB = new ArrayList<>();  //list for characters tier B
    private static ArrayList<GameCharacter> charC = new ArrayList<>();  //list for characters tier C
    private static ArrayList<GameCharacter> charD = new ArrayList<>();  //list for characters tier D
    private static ArrayList<GameCharacter> sortedChar = new ArrayList<>();     //user's characters
    private static ArrayList<String> sortedCharName = new ArrayList<>();        // user's characters name
    
    //all Genshin DPS
    private static String[] dpsList = {"Xiao", "Wanderer", "Shikanoin Heizou", "Chongyun", "Ganyu", "Kamisato Ayaka", "Kaeya", "Rosaria", "Aloy", "Wriothesley"
                    , "Tighnari", "Alhaitham", "Kaveh", "Beidou", "Fischl", "Keqing", "Raiden Shogun", "Yae Miko", "Cyno"
                    , "Ningguang", "Noelle", "Arataki Itto", "Navia", "Xingqiu", "Tartaglia", "Ayato", "Yelan", "Nilou","Furina", "Neuvillette"
                    , "Razor", "Xinyan", "Eula", "Freminet", "Xiangling", "Hu Tao", "Arlecchino", "Diluc", "Lyney", "Gaming", "Yoimiya"
                    ,"Klee", "Yanfei", "Dehya"};
    
    private static ArrayList<String> bestElements = new ArrayList<>();  //best element based on enemies or preferences
    private static ArrayList<String> avoidElements = new ArrayList<>();     //elements that need to be avoid
    private static ArrayList<String> bestWeapon = new ArrayList<>();        //best weapon based on enemies or preferences
    private static boolean bestPneuma;      //is the enemy is easier to defeat when bringing a Pneuma character
    private static boolean bestOusia;       //is the enemy is easier to defeat when bringing an Ousia character
    
    //clear all list because they are all static
    private void emptyAll(){
        charSS.clear();
        charS.clear();
        charA.clear();
        charB.clear();
        charC.clear();
        charD.clear();
        sortedCharName.clear();
        sortedChar.clear();
    }
    
    //set best elements based on input
    public void setBestElements(ArrayList<String> bestElementInput){
        bestElements = bestElementInput;
    }
    
    //set avoid elements based on input
    public void setAvoidElements(ArrayList<String> avoidElementInput){
        avoidElements = avoidElementInput;
    }
    
    //set best weapons based on input
    public void setBestWeapon(ArrayList<String> bestWeaponInput){
        bestWeapon = bestWeaponInput;
    }
    
    //set best pneuma based on input
    public void setBestPneuma(boolean bestPneumaInput){
        bestPneuma = bestPneumaInput;
    }
    
    //set best ousia based on input
    public void setBestOusia(boolean bestOusiaInput){
        bestOusia = bestOusiaInput;
    }
    
    //add all characters name to sortedCharName
    public void addAllNames(){
        for(GameCharacter c:sortedChar){
            sortedCharName.add(c.getName());
        } 
    }
    
    //filter the characters based on their tiers
    public void filterCharacter(ArrayList<String> userName, ArrayList<String>userElement, ArrayList<String>userTier, ArrayList<String>userWeapon, ArrayList<Boolean>userPneuma, ArrayList<Boolean>userOusia){
        for(int i=0; i<userName.size(); i++){
            GameCharacter c = new GameCharacter(userName.get(i), userElement.get(i), userTier.get(i), userWeapon.get(i), userPneuma.get(i), userOusia.get(i));
            String tier = c.getTier();
            switch (tier) {
                case "SS":
                    charSS.add(c);
                    break;
                case "S":
                    charS.add(c);
                    break;
                case "A":
                    charA.add(c);
                    break;
                case "B":
                    charB.add(c);
                    break;
                case "C":
                    charC.add(c);
                    break;
                case "D":
                    charD.add(c);
                    break;
            }     
        }
        sortedChar.addAll(charSS);
        sortedChar.addAll(charS);
        sortedChar.addAll(charA);
        sortedChar.addAll(charB);
        sortedChar.addAll(charC);
        sortedChar.addAll(charD);
    }
    
    //find the best character for the scenario
    public int findBestCharacter(GameCharacter character, String bestElement){
        int num=0;      //the score: the higher it is, the best character it is
        
        //if no best element, then just make sure the element is not one of the avoidElements
        if(bestElement==null){
            if(!(avoidElements.contains(character.getElement()))){
                num += 3;
            }
        }
        //if the best element is not null, then find characters with that best element
        else{
            if(character.getElement().equals(bestElement) && !(avoidElements.contains(character.getElement()))){
                num += 3;
            }
        }
        
        //if the character's weapon match the best weapon
        if(bestWeapon.contains(character.getWeapon()) && !(bestWeapon.isEmpty())){
            num += 2;
        }
        
        //if the character pneuma match the best pneuma
        if(bestPneuma){
            if(character.getPneuma() == bestPneuma){
                num += 1;
            }
        }
        
        //if the character ousia match the best ousia
        if(bestOusia){
            if(character.getOusia() == bestOusia){
                num += 1;
            }
        }
        return num;     //return score
    }
    
    //find the suitable character
    public GameCharacter findCharacter(String bestElement){
        ArrayList<Integer> number = new ArrayList<>();
        
        //find the best character
        for(GameCharacter c: sortedChar){
            int num = findBestCharacter(c, bestElement);
            number.add(num);
            if(num==6){
                return c;   //if there is a character with full marks, then immediately returns
            }
        }
        
        //find the character with the highest score
        int max = 0;
        int index = 0;
        for(int n: number){
            if(n>max){
                max = n;
                index = number.indexOf(n);
            } 
        }
        GameCharacter c = sortedChar.get(index);
        return c;
    }
    
    //check whether the character is DPS or not
    public boolean checkDps(String name){
        if(Arrays.asList(dpsList).contains(name)){
            return true;
        }
        return false;
    }

    //find DPS from support character
    public String findDps(String nameSupport1, String nameSupport2){
        String[] roles1 = findRoleBasedOnChar(nameSupport1).split(",");     //find the first support role
        String[] elementsArray = {"Pyro", "Cryo", "Hydro", "Dendro", "Electro", "Anemo", "Geo", "Physical"};
        ArrayList<String> elements = new ArrayList<>(Arrays.asList(elementsArray));
        for(String avoidElement: avoidElements){
            elements.remove(avoidElement);      //removing the avoidElements from the elements for the dps
        }
        
        //if only one support name
        if(nameSupport2==null){
            String str;
            for(String element: elements){
                try {
                    File myObj = new File("src/App/text/DPS_Element/"+ element + ".txt");
                    Scanner myReader = new Scanner(myObj);
                    
                    //check whether the dps has the support role
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if(data.contains("#")){
                            String[] parts = data.split("#");
                            if(sortedCharName.contains(parts[0])){  //make sure the user has this character
                                for(String role:roles1){
                                    if(parts[1].contains(role) || parts[2].contains(role) || parts[3].contains(role)){
                                        str = parts[0] + "_" + element;
                                        return str;
                                    }
                                }
                            }
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
        //if two support names
        else{
            String str=null;
            String[] roles2 = findRoleBasedOnChar(nameSupport2).split(",");     //find the second support role
            for(String element: elements){
                try {
                    File myObj = new File("src/App/text/DPS_Element/"+ element + ".txt");
                    Scanner myReader = new Scanner(myObj);
                    
                    //find the dps that accomodates the support1 and support2
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if(data.contains("#")){
                            String[] parts = data.split("#");

                            if(sortedCharName.contains(parts[0])){      //make sure user has the character
                                for(String role1:roles1){
                                    for(String role2: roles2){
                                       // try to find dps that can accomodate both supports
                                        if(parts[1].contains(role1) || parts[2].contains(role1) || parts[3].contains(role1)){
                                            if(parts[1].contains(role2) || parts[2].contains(role2) || parts[3].contains(role2)){
                                                str = parts[0] + "_" + element;
                                                return str;
                                            }
                                        }
                                    }
                                }
                                
                                //if no dps can accomodate both support, just search for dps that can accomodate support1
                                if(str==null){
                                    for(String role:roles1){
                                        if(parts[1].contains(role) || parts[2].contains(role) || parts[3].contains(role)){
                                            str = parts[0] + "_" + element;
                                        }
                                    }
                                }
                                
                            }
                        }
                    }
                    myReader.close();
                    return str;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        return "Character unavailable";
    }

    
    //finding the team comp
    public String[] findTeamComp(String nameDps, String name2, String name3, String element){
        int n=0;
        try {
            //search the dps line position, then record it in n
            File myObj = new File("src/App/text/DPS_Element/"+ element + ".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                n++;
                if(data.trim().equals(nameDps)){
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String[] temp = null;
        
        //loop until we find the available team comp for the dps
        while(temp==null){
            if(name2==null){
                temp = findAvailableTeamComp(n, name2, name3, element, "dps");
            }
            else if(name2!=null && name3==null){
                temp = findAvailableTeamComp(n, name2, name3, element, "dpscombined");
            }
            else{
                temp = findAvailableTeamComp(n, name2, name3, element, "nondpscombined");
            }
            n++;
        }
        
        //if we loop for all possible team comp for that dps and still cant find the team, return team unavailable
        if(Arrays.asList(temp).contains("Team unavailable")){
            String[] newTemp = {"Team unavailable",nameDps, name2, name3};
            return newTemp;
        }
        
        return temp;
    }
    
    //check whether the part contains the role
    public boolean checkRoleInPart(String onePart, String[]roleChar){
        for(String role: roleChar){
            if(onePart.contains(role)){
                return true;
            }
        }
        return false;
    }
    
    //check whether the part is a name or not
    public int checkNames(String onePart){
        String[]charNames = onePart.split(",");
        for(int i=0; i<charNames.length; i++){
            if(sortedCharName.contains(charNames[i])){
                return i;
            }
        }
        return -1;
    }

    //find available team comp
    public String[] findAvailableTeamComp(int n, String name2, String name3, String element, String state){
        String line;

        try(Stream<String> lines = Files.lines(Paths.get("src/App/text/DPS_Element/" + element + ".txt"))) {
            line = lines.skip(n).findFirst().get();     //skip lines based on n value
            String[] part = line.split("#");
            //if the line is empty
            if(part.length==1){
                String[] tempArray = {"Team unavailable"};
                return tempArray;
            }
            
            //if we have only the dps
            if(state.equals("dps")){
                ArrayList<String> duplicateNames = new ArrayList<>();   //make sure the name dont duplicate
                for(int i=0; i<4; i++){
                    int checkNameResult = checkNames(part[i]);      //check whether the part is a name or not
                    String[]charNames = part[i].split(",");         
                    
                    //if that part is a name and duplicateNames does not contains it
                    if(checkNameResult != -1 && !(duplicateNames.contains(charNames[checkNameResult]))){
                        part[i] = charNames[checkNameResult];   //add it as a part of a team
                        duplicateNames.add(part[i]);
                        continue;
                    }
                    else{
                        part[i] = findCharBasedOnRole(part[i], duplicateNames);  //find the character based on the role
                        duplicateNames.add(part[i]);
                    }
                }
            }
            
            //if we have a dps and another character
            else if(state.equals("dpscombined")){
                String[] roleChar2 = findRoleBasedOnChar(name2).split(",");     //check character2 role
                ArrayList<String> duplicateNames = new ArrayList<>();   //avoid duplicate names
                for(int i=0; i<4; i++){
                    int checkNameResult = checkNames(part[i]);
                    String[]charNames = part[i].split(",");
                    
                    //if that part is a name and duplicateNames doesn't contain it
                    if(checkNameResult != -1 && !(duplicateNames.contains(charNames[checkNameResult]))){
                        part[i] = charNames[checkNameResult];
                        duplicateNames.add(part[i]);
                        continue;
                    }
                    //if dps team have character2 role and duplicateNames doesn't contain it
                    else if(checkRoleInPart(part[i], roleChar2) && !(duplicateNames.contains(name2))){
                        part[i] = name2;
                        duplicateNames.add(part[i]);
                    }
                    //find suitable character based on the role
                    else{
                        part[i] = findCharBasedOnRole(part[i], duplicateNames);
                        duplicateNames.add(part[i]);
                    }
                }
                //if the team result doesnt have character2
                if(!(Arrays.asList(part).contains(name2))){
                    return null;
                }
            }
            
            //if we have a non dps with another character
            else if(state.equals("nondpscombined")){
                String[] roleChar2 = findRoleBasedOnChar(name2).split(",");     //check character2 role
                String[] roleChar3 = findRoleBasedOnChar(name3).split(",");     //check character3 role
                ArrayList<String> duplicateNames = new ArrayList<>();   //avoid duplicating name
                for(int i=0; i<4; i++){
                    int checkNameResult = checkNames(part[i]);      //check whether that part is a name
                    String[]charNames = part[i].split(",");
                    
                    //if the part is a name and duplicateNames doesn't contain it
                    if(checkNameResult != -1 && !(duplicateNames.contains(charNames[checkNameResult]))){
                        part[i] = charNames[checkNameResult];
                        duplicateNames.add(part[i]);
                        continue;
                    }
                    //if the part has character2 role and duplicateNames doesn't contain it
                    else if(checkRoleInPart(part[i], roleChar2) && !(duplicateNames.contains(name2))){
                        part[i] = name2;
                        duplicateNames.add(part[i]);
                    }
                    //if the part has character3 role and duplicateNames doesn't contain it
                    else if(checkRoleInPart(part[i], roleChar3) && !(duplicateNames.contains(name3))){
                        part[i] = name3;
                        duplicateNames.add(part[i]);
                    }
                    //find the suitable character based on role
                    else{
                        part[i] = findCharBasedOnRole(part[i], duplicateNames);
                        duplicateNames.add(part[i]);
                    }
                }
                
                //if the final team doesnt have character2 or character3
                if(!(Arrays.asList(part).contains(name2)) || !(Arrays.asList(part).contains(name3))){
                    return null;
                }
            }

            if(Arrays.asList(part).contains("Character unavailable")){
                return null;
            }
            
            
            lines.close();
            return part;
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    //find role based on character name
    public String findRoleBasedOnChar(String name){
        try {
            File myObj = new File("src/App/text/character.txt");    //check txt file
            Scanner myReader = new Scanner(myObj);

            //loop until we find the name
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] part = data.split("#");
                if(part[0].equals(name)){
                    return part[3];
                }
            }
            myReader.close();
        } catch (Exception e) {
            return "Role unavailable";
        }
        return "Role unavailable";
    }

    
    //find character based on role
    public String findCharBasedOnRole(String roleInput, ArrayList<String> duplicateNames){
        String[] roles = roleInput.split(",");

        for(String r: roles){
            r= r.trim();
            //if one part contains a character name
            if(sortedCharName.contains(r) && !(duplicateNames.contains(r))){
                return r;
            }
        }

        for(String r: roles){
            String role = r.replaceAll(" ", "");
            role = role.contains("On-FieldDPS")? r.replaceAll("On-FieldDPS", "On-Field DPS") : role;

            if(sortedCharName.contains(role)){
                continue;
            }
            
            //search based on role
            try {
                File myObj = new File("src/App/text/Role/"+ role.trim() + ".txt");
                Scanner myReader = new Scanner(myObj);
    
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] part = data.split("#");
                    if(sortedCharName.contains(part[0]) && !(duplicateNames.contains(part[0]))){
                        return part[0];
                    }
                }
                myReader.close();
            } catch (Exception e) {
                return "Character unavailable";
            }
        }

        return "Character unavailable";     
    }

    
    //find name to remove from the next generator activation
    public String findName(String nameDps, String name2, String name3){
        ArrayList <String> charTemp = new ArrayList<>();
        for(String name: sortedCharName){
            //add the dps name and support name
            if(name.equals(nameDps) || name.equals(name2) || name.equals(name3)){
                charTemp.add(name);
            }
        }

        String nameToReturn="";
        for(GameCharacter c: sortedChar){
            for(int index=0; index<charTemp.size(); index++){
                if(c.getName().equals(charTemp.get(index))){
                    //prioritize remove physical and geo since it is harder to construct their team
                    if(c.getElement().equals("Physical") || c.getElement().equals("Geo")){
                        nameToReturn = c.getName();
                    }
                }
            }
        }
        
        //if the dps and support not physical or geo, then remove the character that has lowest tier
        if(nameToReturn.equals("")){
            nameToReturn = charTemp.getLast();
        }

        return nameToReturn;
    }

    
    //the method to activate all things
    public String[][] generator(ArrayList<String>userName, ArrayList<String>userElement, ArrayList<String>userTier, ArrayList<String>userWeapon, ArrayList<Boolean>userPneuma, ArrayList<Boolean>userOusia){
        CharGenerator demo = new CharGenerator();
        emptyAll();     //empty list
        filterCharacter(userName, userElement, userTier, userWeapon, userPneuma, userOusia);       //filter characters
        addAllNames();     //add names to sortedCharName
        
        ArrayList<GameCharacter> resultDps = new ArrayList<>();             //if the best character is a dps
        ArrayList<GameCharacter> resultNonDps = new ArrayList<>();          //if the best character is not a dps
        ArrayList<GameCharacter> resultDpsCombined = new ArrayList<>();     //if the best characters are dps with another character
        ArrayList<GameCharacter> resultNonDpsCombined = new ArrayList<>();  //if the best characters are nondps with another character
        GameCharacter result = new GameCharacter();
        
        //finding the best character for the enemy
        if(bestElements.isEmpty()){
            result = demo.findCharacter(null);
            //if it is a dps, then add to resultDps
            if(demo.checkDps(result.getName())){
                resultDps.add(result);
            }
            else{
                resultNonDps.add(result);
            }
        }        
        else{
            for(int i=0; i<bestElements.size(); i++){
                
                //if the best element contains underscore, then it means two of the elements must be present
                //for example: pyro_electro means both pyro and electro need to be present
                if(bestElements.get(i).contains("_")){
                    String[] elements = bestElements.get(i).split("_");
                    GameCharacter result1 = demo.findCharacter(elements[0]);
                    GameCharacter result2 = demo.findCharacter(elements[1]);
                    
                    //if one of them is a dps, then add to resultDpsCombined
                    if(demo.checkDps(result1.getName())){
                        resultDpsCombined.add(result1);
                        resultDpsCombined.add(result2);
                    }
                    else if(demo.checkDps(result2.getName())){
                        resultDpsCombined.add(result2);
                        resultDpsCombined.add(result1);
                    }
                    
                    //if not, add to result nonDpsCombined
                    else{
                        resultNonDpsCombined.add(result1);
                        resultNonDpsCombined.add(result2);
                    }
                }
                
                //if the best element doesnt have underscore
                //then find the dps based on that element
                else{
                    result = demo.findCharacter(bestElements.get(i));
                    if(demo.checkDps(result.getName())){
                        resultDps.add(result);
                    }
                    else{
                        resultNonDps.add(result);
                    }
                }
            }
        }
        
        int len = bestElements.size()==0? 1 : bestElements.size();
        String[][] teams = new String[len][];
        int countTeam=0;

        //making the teams for the best character
        for(int i=0; i<resultDps.size(); i++){
            String[]team1 = new String[4];
            team1 = demo.findTeamComp(resultDps.get(i).getName(),null,null, resultDps.get(i).getElement());
            //if team is unavailable, then return the dps name to be removed
            if(Arrays.asList(team1).contains("Team unavailable")){
                String[][] newTemp = {{"Team unavailable", resultDps.get(i).getName()}};
                return newTemp;
            }
            teams[countTeam] = team1;
            countTeam++;
        }
        for(int j=0; j<resultDpsCombined.size(); j+=2){
            String[] team2 = new String[4];
            team2 = demo.findTeamComp(resultDpsCombined.get(j).getName(), resultDpsCombined.get(j+1).getName(),null, resultDpsCombined.get(j).getElement());
            //if team is unavailable
            if(Arrays.asList(team2).contains("Team unavailable")){
                String name = findName(team2[1], team2[2], team2[3]);   //find the name to return
                String[][] newTemp = {{"Team unavailable", name}};
                return newTemp;
            }
            teams[countTeam] = team2;
            countTeam++;
        }
        for(int k=0; k<resultNonDps.size(); k++){
            String[] team3 = new String[4];
            String[] dpsInfo = demo.findDps(resultNonDps.get(k).getName(), null).split("_");
            team3 = demo.findTeamComp(dpsInfo[0], resultNonDps.get(k).getName(),null, dpsInfo[1]);
            //if team is unavailable, then return dps name
            if(Arrays.asList(team3).contains("Team unavailable")){
                String[][] newTemp = {{"Team unavailable", dpsInfo[0]}};
                return newTemp;
            }
            teams[countTeam] = team3;
            countTeam++;
        }
        for(int l=0; l<resultNonDpsCombined.size()/2; l+=2){
            String[] team4 = new String[4];
            String[] dpsInfo = demo.findDps(resultNonDpsCombined.get(l).getName(), resultNonDpsCombined.get(l+1).getName()).split("_");            
            team4 = demo.findTeamComp(dpsInfo[0], resultNonDpsCombined.get(l).getName(),resultNonDpsCombined.get(l+1).getName(), dpsInfo[1]);
            //if team is unavailable
            if(Arrays.asList(team4).contains("Team unavailable")){
                String name = findName(team4[1], team4[2], team4[3]);   //find name to return
                String[][] newTemp = {{"Team unavailable", name}};
                return newTemp;
            }
            teams[countTeam] = team4;
            countTeam++;
        }

        
        //return final team
        return teams;

        
    }
}
