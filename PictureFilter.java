import javax.swing.filechooser.FileFilter;
import java.io.*;

/***********************************************************************************************************
Class's Name    : PictureFilter
Design Pattern  : Singleton
Purpose         : This class is to filter picture file type.
************************************************************************************************************/ 
public class PictureFilter extends FileFilter{   
    public boolean accept(File file){
        String fileName = file.getName();
        if (file.isDirectory()){
            return true;
        }
        else if(fileName.endsWith("jpeg") || fileName.endsWith("jpg") || fileName.endsWith("tiff") || fileName.endsWith("tif") || fileName.endsWith("png"))
            return true;
        else
            return false;
    }
    public String getDescription(){
        return ".jpeg, .jpg, .tiff, .tif, .png";
    }
}