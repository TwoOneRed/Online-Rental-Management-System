import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : AdminRegisterSubmitActionListener 
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the admin add a new admin 
                registration and press the submit button.
************************************************************************************************************/ 
public class AdminRegisterSubmitActionListener implements ActionListener{
    ArrayList<JTextField> txtFieldArraylist;
    ArrayList<JComboBox<String>> DOB;
    ArrayList<File> profilePictureFileArraylist;
    JPasswordField userPassword;
    JFrame frame;
    String adminUsername;
    String adminDetails[];
    String securityQuestion[];
    JComboBox<String> comboBox;
    public AdminRegisterSubmitActionListener (String adminDetails[], String securityQuestion[], ArrayList<JTextField> txtFieldArraylist,ArrayList<JComboBox<String>> DOB, JComboBox<String> comboBox ,ArrayList<File> profilePictureFileArraylist, JPasswordField userPassword,JFrame frame){
        this.txtFieldArraylist = txtFieldArraylist; 
        this.profilePictureFileArraylist = profilePictureFileArraylist;
        this.userPassword = userPassword;
        this.frame = frame;
        this.adminDetails = adminDetails;
        this.securityQuestion = securityQuestion;
        this.DOB = DOB;
        this.comboBox = comboBox;
        System.out.println(":)");
    }  
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : actionPerformed
    Purpose         : This method is to save the new admin registration details in the csv file after it triggered.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent evt){
        String registrationDetails = "";
        String[] extensionArray = {"jpeg","jpg","tiff","tif","png"};
        String extension = "";
        int count = 0;

        // GET THE UPLOADED PROFILE PICTURE FILE EXTENSION
        for(String s: extensionArray){
            if (profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getName().endsWith(s))
                extension = s;
        }
        String profilePicFileName = txtFieldArraylist.get(0).getText() + "ProfilePic." + extension;

        Boolean input = true;
        for(int i = 0 ; i < 8 ;i++){
            if(i == 1 && String.valueOf(userPassword.getPassword()).equals("")){
                input = false;
                break;
            }else if ( i== 1 ||i == 2 || i == 4 || i == 6){
            }else{
                if(txtFieldArraylist.get(count).getText().equals("")){
                    input = false;
                    break;
                }
                count++;
            }
        }
        count = 0;

        // SAVE ALL THE DETAILS AS A STRING
        if(input){
            for(int i = 0 ; i < adminDetails.length;i++){
                if(i == 1){
                    registrationDetails += new String(userPassword.getPassword()) + ",";
                }else if(i == 2){
                    registrationDetails += profilePicFileName + ",";
                }else if(i == 4){
                    registrationDetails += DOB.get(0).getSelectedItem() + "-" + DOB.get(1).getSelectedItem() + "-" + DOB.get(2).getSelectedItem() + ",";
                }else if(i == 6){
                    registrationDetails += comboBox.getSelectedItem() + ",";
                }else if(i == 7){
                    registrationDetails += txtFieldArraylist.get(count).getText();
                    count++;
                }else{
                    registrationDetails += txtFieldArraylist.get(count).getText() +",";
                    count++;
                }
            }
            // SAVE THE DETAILS INTO FILE
            try{
                FileWriter myFileWriter = new FileWriter("userDetails.csv", true);
                BufferedImage profileImage = ImageIO.read(new File(profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getAbsolutePath()));
                ImageIO.write(profileImage, extension, new File(profilePicFileName));  
                registrationDetails = "Admin,true," + registrationDetails;
                myFileWriter.append(registrationDetails + "\n");
                frame.dispose();
                myFileWriter.close();
                JOptionPane.showMessageDialog(new JFrame(), " Your Account is Created Successfully","Admin Sign Up", JOptionPane.INFORMATION_MESSAGE);    
                new ManageUser(adminUsername, profilePicFileName);

            } catch(IOException e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Invalid Input","Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}