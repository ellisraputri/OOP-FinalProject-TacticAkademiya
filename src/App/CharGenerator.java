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
    private static ArrayList<GameCharacter> charSS = new ArrayList<>();
    private static ArrayList<GameCharacter> charS = new ArrayList<>();
    private static ArrayList<GameCharacter> charA = new ArrayList<>();
    private static ArrayList<GameCharacter> charB = new ArrayList<>();
    private static ArrayList<GameCharacter> charC = new ArrayList<>();
    private static ArrayList<GameCharacter> charD = new ArrayList<>();
    private static ArrayList<GameCharacter> sortedChar = new ArrayList<>();
    private static ArrayList<String> sortedCharName = new ArrayList<>();
    private static String[] dpsList = {"Xiao", "Wanderer", "Shikanoin Heizou", "Chongyun", "Ganyu", "Kamisato Ayaka", "Kaeya", "Rosaria", "Aloy", "Wriothesley"
                    , "Tighnari", "Alhaitham", "Kaveh", "Beidou", "Fischl", "Keqing", "Raiden Shogun", "Yae Miko", "Cyno"
                    , "Ningguang", "Noelle", "Arataki Itto", "Navia", "Xingqiu", "Tartaglia", "Ayato", "Yelan", "Nilou","Furina", "Neuvillette"
                    , "Razor", "Xinyan", "Eula", "Freminet", "Xiangling", "Hu Tao", "Arlecchino", "Diluc", "Lyney", "Gaming", "Yoimiya"
                    ,"Klee", "Yanfei", "Dehya"};
    private static ArrayList<String> bestElements = new ArrayList<>();
    private static ArrayList<String> avoidElements = new ArrayList<>();
    private static ArrayList<String> bestWeapon = new ArrayList<>();
    private static boolean bestPneuma;
    private static boolean bestOusia;

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

    public void setBestElements(ArrayList<String> bestElementInput){
        bestElements = bestElementInput;
    }

    public void setAvoidElements(ArrayList<String> avoidElementInput){
        avoidElements = avoidElementInput;
    }

    public void setBestWeapon(ArrayList<String> bestWeaponInput){
        bestWeapon = bestWeaponInput;
    }

    public void setBestPneuma(boolean bestPneumaInput){
        bestPneuma = bestPneumaInput;
    }

    public void setBestOusia(boolean bestOusiaInput){
        bestOusia = bestOusiaInput;
    }
    
    public void addAllNames(){
        for(GameCharacter c:sortedChar){
            sortedCharName.add(c.getName());
        } 
    }
    
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

    public int findBestCharacter(GameCharacter character, String bestElement){
        int num=0;
        if(bestElement==null){
            if(!(avoidElements.contains(character.getElement()))){
                num += 3;
            }
        }
        else{
            if(character.getElement().equals(bestElement) && !(avoidElements.contains(character.getElement()))){
                num += 3;
            }
        }

        if(bestWeapon.contains(character.getWeapon()) && !(bestWeapon.isEmpty())){
            num += 2;
        }
        if(character.getPneuma() == bestPneuma){
            num += 1;
        }
        if(character.getOusia() == bestOusia){
            num += 1;
        }
        
        return num;
    }

    public GameCharacter findCharacter(String bestElement){
        ArrayList<Integer> number = new ArrayList<>();
        for(GameCharacter c: sortedChar){
            int num = findBestCharacter(c, bestElement);
            number.add(num);
            if(num==6){
                return c;
            }
        }

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

    public boolean checkDps(String name){
        if(Arrays.asList(dpsList).contains(name)){
            return true;
        }
        return false;
    }


    public String findDps(String nameSupport1, String nameSupport2){
        String[] roles1 = findRoleBasedOnChar(nameSupport1).split(",");
        String[] elementsArray = {"Pyro", "Cryo", "Hydro", "Dendro", "Electro", "Anemo", "Geo", "Physical"};
        ArrayList<String> elements = new ArrayList<>(Arrays.asList(elementsArray));
        for(String avoidElement: avoidElements){
            elements.remove(avoidElement);
        }
        
        if(nameSupport2==null){
            String str;
            for(String element: elements){
                try {
                    File myObj = new File("src/App/text/DPS_Element/"+ element + ".txt");
                    Scanner myReader = new Scanner(myObj);
        
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if(data.contains("#")){
                            String[] parts = data.split("#");
                            if(sortedCharName.contains(parts[0])){
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
        
        else{
            String str=null;
            String[] roles2 = findRoleBasedOnChar(nameSupport2).split(",");
            for(String element: elements){
                try {
                    File myObj = new File("src/App/text/DPS_Element/"+ element + ".txt");
                    Scanner myReader = new Scanner(myObj);
        
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if(data.contains("#")){
                            String[] parts = data.split("#");

                            if(sortedCharName.contains(parts[0])){
                                for(String role1:roles1){
                                    for(String role2: roles2){
                                        if(parts[1].contains(role1) || parts[2].contains(role1) || parts[3].contains(role1)){
                                            if(parts[1].contains(role2) || parts[2].contains(role2) || parts[3].contains(role2)){
                                                str = parts[0] + "_" + element;
                                                return str;
                                            }
                                        }
                                    }
                                }
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


    public String[] findTeamComp(String nameDps, String name2, String name3, String element){
        int n=0;
        try {
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

        if(Arrays.asList(temp).contains("Team unavailable")){
            String[] newTemp = {"Team unavailable",nameDps, name2, name3};
            return newTemp;
        }
        
        return temp;
    }

    public boolean checkRoleInPart(String onePart, String[]roleChar){
        for(String role: roleChar){
            if(onePart.contains(role)){
                return true;
            }
        }
        return false;
    }

    public int checkNames(String onePart){
        String[]charNames = onePart.split(",");
        for(int i=0; i<charNames.length; i++){
            if(sortedCharName.contains(charNames[i])){
                return i;
            }
        }
        return -1;
    }

    
    public String[] findAvailableTeamComp(int n, String name2, String name3, String element, String state){
        String line;

        try(Stream<String> lines = Files.lines(Paths.get("src/App/text/DPS_Element/" + element + ".txt"))) {
            line = lines.skip(n).findFirst().get();
            String[] part = line.split("#");
            if(part.length==1){
                String[] tempArray = {"Team unavailable"};
                return tempArray;
            }

            if(state.equals("dps")){
                ArrayList<String> duplicateNames = new ArrayList<>();
                for(int i=0; i<4; i++){
                    int checkNameResult = checkNames(part[i]);
                    String[]charNames = part[i].split(",");

                    if(checkNameResult != -1 && !(duplicateNames.contains(charNames[checkNameResult]))){
                        part[i] = charNames[checkNameResult];
                        duplicateNames.add(part[i]);
                        continue;
                    }
                    else{
                        part[i] = findCharBasedOnRole(part[i], duplicateNames);
                        duplicateNames.add(part[i]);
                    }
                }
            }
            else if(state.equals("dpscombined")){
                String[] roleChar2 = findRoleBasedOnChar(name2).split(",");
                ArrayList<String> duplicateNames = new ArrayList<>();
                for(int i=0; i<4; i++){
                    int checkNameResult = checkNames(part[i]);
                    String[]charNames = part[i].split(",");

                    if(checkNameResult != -1 && !(duplicateNames.contains(charNames[checkNameResult]))){
                        part[i] = charNames[checkNameResult];
                        duplicateNames.add(part[i]);
                        continue;
                    }
                    else if(checkRoleInPart(part[i], roleChar2) && !(duplicateNames.contains(name2))){
                        part[i] = name2;
                        duplicateNames.add(part[i]);
                    }
                    else{
                        part[i] = findCharBasedOnRole(part[i], duplicateNames);
                        duplicateNames.add(part[i]);
                    }
                }
                if(!(Arrays.asList(part).contains(name2))){
                    return null;
                }
            }
            else if(state.equals("nondpscombined")){
                String[] roleChar2 = findRoleBasedOnChar(name2).split(",");
                String[] roleChar3 = findRoleBasedOnChar(name3).split(",");
                ArrayList<String> duplicateNames = new ArrayList<>();
                for(int i=0; i<4; i++){
                    System.out.println(part[i]);
                    int checkNameResult = checkNames(part[i]);
                    String[]charNames = part[i].split(",");

                    if(checkNameResult != -1 && !(duplicateNames.contains(charNames[checkNameResult]))){
                        part[i] = charNames[checkNameResult];
                        duplicateNames.add(part[i]);
                        continue;
                    }
                    else if(checkRoleInPart(part[i], roleChar2) && !(duplicateNames.contains(name2))){
                        part[i] = name2;
                        duplicateNames.add(part[i]);
                    }
                    else if(checkRoleInPart(part[i], roleChar3) && !(duplicateNames.contains(name3))){
                        part[i] = name3;
                        duplicateNames.add(part[i]);
                    }
                    else{
                        part[i] = findCharBasedOnRole(part[i], duplicateNames);
                        duplicateNames.add(part[i]);
                    }
                }
                if(!(Arrays.asList(part).contains(name2)) || !(Arrays.asList(part).contains(name3))){
                    return null;
                }
            }

            if(Arrays.asList(part).contains("Character unavailable")){
                System.out.println("char un");
                return null;
            }
            
            
            lines.close();
            return part;
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String findRoleBasedOnChar(String name){
        try {
            File myObj = new File("src/App/text/character.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] part = data.split("#");
                if(part[0].equals(name)){
                    System.out.println(part[3]);
                    return part[3];
                }
            }
            myReader.close();
        } catch (Exception e) {
            return "Role unavailable";
        }
        return "Role unavailable";
    }


    public String findCharBasedOnRole(String roleInput, ArrayList<String> duplicateNames){
        String[] roles = roleInput.split(",");

        for(String r: roles){
            r= r.trim();
            if(sortedCharName.contains(r) && !(duplicateNames.contains(r))){
                return r;
            }
        }

        for(String r: roles){
            String role = r.replaceAll(" ", "");
            role = role.contains("On-FieldDPS")? r.replaceAll("On-FieldDPS", "_OnFieldDPS") : role;

            if(sortedCharName.contains(role)){
                continue;
            }
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


    public String findName(String nameDps, String name2, String name3){
        ArrayList <String> charTemp = new ArrayList<>();
        for(String name: sortedCharName){
            if(name.equals(nameDps) || name.equals(name2) || name.equals(name3)){
                charTemp.add(name);
            }
        }

        String nameToReturn="";
        for(GameCharacter c: sortedChar){
            for(int index=0; index<charTemp.size(); index++){
                if(c.getName().equals(charTemp.get(index))){
                    if(c.getElement().equals("Physical") || c.getElement().equals("Geo")){
                        nameToReturn = c.getName();
                    }
                }
            }
        }
        if(nameToReturn.equals("")){
            nameToReturn = charTemp.getLast();
        }

        return nameToReturn;
    }


    public String[][] generator(ArrayList<String>userName, ArrayList<String>userElement, ArrayList<String>userTier, ArrayList<String>userWeapon, 
            ArrayList<Boolean>userPneuma, ArrayList<Boolean>userOusia){
        CharGenerator demo = new CharGenerator();
        emptyAll();
        filterCharacter(userName, userElement, userTier, userWeapon, userPneuma, userOusia);
        addAllNames();
        
        ArrayList<GameCharacter> resultDps = new ArrayList<>();
        ArrayList<GameCharacter> resultNonDps = new ArrayList<>();
        ArrayList<GameCharacter> resultDpsCombined = new ArrayList<>();
        ArrayList<GameCharacter> resultNonDpsCombined = new ArrayList<>();
        GameCharacter result = new GameCharacter();

        System.out.println(userName);
        
        //finding the best character for the enemy
        if(bestElements.isEmpty()){
            result = demo.findCharacter(null);
            if(demo.checkDps(result.getName())){
                resultDps.add(result);
            }
            else{
                resultNonDps.add(result);
            }
        }
        else{
            for(int i=0; i<bestElements.size(); i++){
                if(bestElements.get(i).contains("_")){
                    String[] elements = bestElements.get(i).split("_");
                    GameCharacter result1 = demo.findCharacter(elements[0]);
                    GameCharacter result2 = demo.findCharacter(elements[1]);

                    if(demo.checkDps(result1.getName())){
                        resultDpsCombined.add(result1);
                        resultDpsCombined.add(result2);
                    }
                    else if(demo.checkDps(result2.getName())){
                        resultDpsCombined.add(result2);
                        resultDpsCombined.add(result1);
                    }
                    else{
                        resultNonDpsCombined.add(result1);
                        resultNonDpsCombined.add(result2);
                    }
                }
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

        for(GameCharacter i: resultDpsCombined){
            System.out.println("dps" + i.getName());
        }
        
        int len = bestElements.size()==0? 1 : bestElements.size();
        String[][] teams = new String[len][];
        int countTeam=0;

        //making the teams for the best character
        for(int i=0; i<resultDps.size(); i++){
            String[]team1 = new String[4];
            team1 = demo.findTeamComp(resultDps.get(i).getName(),null,null, resultDps.get(i).getElement());
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
            if(Arrays.asList(team2).contains("Team unavailable")){
                String name = findName(team2[1], team2[2], team2[3]);
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
            if(Arrays.asList(team4).contains("Team unavailable")){
                String name = findName(team4[1], team4[2], team4[3]);
                String[][] newTemp = {{"Team unavailable", name}};
                return newTemp;
            }
            teams[countTeam] = team4;
            countTeam++;
        }

        

        return teams;

        
    }
}
