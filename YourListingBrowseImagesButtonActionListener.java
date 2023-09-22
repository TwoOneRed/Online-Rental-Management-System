import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : YourListingBrowseImagesButtonActionListener 
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered when the user click the browse button
                in list a new property page.
************************************************************************************************************/ 
public class YourListingBrowseImagesButtonActionListener implements ActionListener{
    ArrayList<File>propertyImagesFileArrayList; 
    JTextArea imagesFileNameTextArea;
    JButton browseImagesButton;
    public YourListingBrowseImagesButtonActionListener(ArrayList<File>propertyImagesFileArrayList, JTextArea imagesFileNameTextArea, JButton browseImagesButton){
        this.propertyImagesFileArrayList = propertyImagesFileArrayList;
        this.imagesFileNameTextArea = imagesFileNameTextArea;
        this.browseImagesButton = browseImagesButton;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to let the user to select the property picture file in list a new property page.
    ************************************************************************************************************/    
    public void actionPerformed(ActionEvent e){
        JFileChooser propertyImagesChooser = new JFileChooser();
        propertyImagesChooser.setMultiSelectionEnabled(true);
        propertyImagesChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        propertyImagesChooser.addChoosableFileFilter(new PictureFilter()); // Image file filter
        propertyImagesChooser.setAcceptAllFileFilterUsed(false); 
        if(propertyImagesChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File[] ImagesFile = propertyImagesChooser.getSelectedFiles();
            if (propertyImagesFileArrayList.isEmpty())
                imagesFileNameTextArea.append("Open(Maximum 20 images): \n" );
        
            for(File f : ImagesFile){
                if(propertyImagesFileArrayList.size() != 20){
                    propertyImagesFileArrayList.add(f);
                    imagesFileNameTextArea.append(f.getName() + "\n");
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(), "Images Uploaded More Than 20 Images","List Property", JOptionPane.INFORMATION_MESSAGE);   
                    break; 
                }
            }   
        }
    }
}
