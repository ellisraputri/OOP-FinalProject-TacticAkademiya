package App;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class ImageLoader {
    //store all file names
    private static ArrayList<String> fileName = new ArrayList<>();
    
    //load images from a folder path
    public static ArrayList<BufferedImage> loadImagesFromFolder(String folderPath) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();    //load all the files into array
        
        //if the folder is not empty
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                //if the file is valid and is an image file
                if (file.isFile() && isImageFile(file)) {
                    try {
                        BufferedImage image = ImageIO.read(file);
                        if (image != null) {
                            images.add(image);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    //fix the name before adding to list
                    String name = file.getName();
                    name = fixName(name);
                    fileName.add(name);
                }
            }
        }
        return images;
    }
    
    //fix name
    private static String fixName(String name){
        //remove the .png in the name
        String extension = ".png";
        int extensionWordLength = extension.length();
        
        //delete the Portraits word in the name
        name = name.replaceAll("Portraits", "");
        name = name.trim();
        name = name.substring(0, name.length()-extensionWordLength);
        name = name.trim();
        
        return name;
    }
    
    //getter for file names
    public ArrayList<String> returnFileNames(){
        return fileName;
    }

    //check whether it is an image file
    private static boolean isImageFile(File file) {
        String[] imageExtensions = { "jpg", "jpeg", "png", "gif", "bmp" };
        String fileName = file.getName().toLowerCase();
        for (String extension : imageExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
    
    //empty the list (since it is static)
    public void emptyFileName(){
        fileName.clear();
    }
    
    
}
