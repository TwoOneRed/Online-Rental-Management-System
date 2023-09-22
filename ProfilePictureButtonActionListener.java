import javax.swing.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;
/***********************************************************************************************************
Class's Name    : ProfilePictureButtonActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the profile picture JMenu button is clicked
************************************************************************************************************/ 
public class ProfilePictureButtonActionListener implements ActionListener{
    ArrayList<File> profilePictureFileArraylist;
    JButton profilePictureButton;
    public ProfilePictureButtonActionListener(ArrayList<File> profilePictureFileArraylist, JButton profilePictureButton){
        this.profilePictureFileArraylist = profilePictureFileArraylist;
        this.profilePictureButton = profilePictureButton;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to process the browse images.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent evt){
        JFileChooser profilePictureChooser = new JFileChooser();
        profilePictureChooser.addChoosableFileFilter(new PictureFilter()); // Image file filter
        profilePictureChooser.setAcceptAllFileFilterUsed(false); 
        if(profilePictureChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File pictureFile = profilePictureChooser.getSelectedFile();
            profilePictureButton.setText(pictureFile.getName());
            profilePictureFileArraylist.add(pictureFile);
        }
    }
}
