/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author asus
 */
public class ImageLoader {
    static ArrayList<String> fileName = new ArrayList<>();
    
    public static ArrayList<BufferedImage> loadImagesFromFolder(String folderPath) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && isImageFile(file)) {
                    try {
                        BufferedImage image = ImageIO.read(file);
                        if (image != null) {
                            images.add(image);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    String name = file.getName();
                    name = fixName(name);
                    fileName.add(name);
                }
            }
        }
        return images;
    }
    
    private static String fixName(String name){
        String extension = ".png";
        int extensionWordLength = extension.length();
        
        name = name.replaceAll("Portraits", "");
        name = name.trim();
        name = name.substring(0, name.length()-extensionWordLength);
        name = name.trim();
        
        return name;
    }
    
    public ArrayList<String> returnFileNames(){
        return fileName;
    }

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
    
    
}
