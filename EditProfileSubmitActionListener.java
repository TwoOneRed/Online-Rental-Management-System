import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/***********************************************************************************************************
Class's Name    : EditProfileSubmitActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the users click submit in edit profile page.
************************************************************************************************************/ 
public class EditProfileSubmitActionListener implements ActionListener{
    ArrayList<JTextField> txtField;
    ArrayList<File> profilePictureFileArraylist;
    JTextArea descriptionTextArea;
    JPasswordField userPassword;
    ArrayList<JTextField> DOB;
    JComboBox<String> comboBox;
    JFrame editProfileFrame;
    String username;
    String role;
    String change;

    String Admin[] = {"Username","Password","Profile Picture","Phone Number","Date Of Birth","Email","Security Question", "Answers"};
    String Agent[] = {"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Agency", "Agency Registration Number", "REN/REA License Number", "Agency Contact Number"};
    String Owner[] = {"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Contact Number"};
    String Tenant[] = {"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email"};

    public EditProfileSubmitActionListener (ArrayList<JTextField> txtFieldArraylist, ArrayList<File> profilePictureFileArraylist, JTextArea descriptionTextArea, JPasswordField userPassword, ArrayList<JTextField> DOB ,JComboBox<String> comboBox ,JFrame editProfileFrame, String username, String role, String change){
        this.txtField = txtFieldArraylist; 
        this.profilePictureFileArraylist = profilePictureFileArraylist;
        this.descriptionTextArea = descriptionTextArea;
        this.userPassword = userPassword;
        this.editProfileFrame = editProfileFrame;
        this.username = username;
        this.role = role;
        this.DOB = DOB;
        this.comboBox = comboBox;
        this.change = change;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to save the edited details into file.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent evt){
        ArrayList<User> userDetailsArrayList = loadUserDetails();
        //GET THE OLD FILE EXTENSION AND NEW FILE EXTENSION
        String[] extensionArray = {"jpeg","jpg","tiff","tif","png"};
        String extension = "";
        String oldProfilePicExtension = "";

        for(String s: extensionArray){
            if (profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getName().endsWith(s))
                extension = s;
            if (profilePictureFileArraylist.get(0).getName().endsWith(s))
                oldProfilePicExtension = s;
        }

        // CHECK INVALID INPUT
        Boolean input = true;
        if(role.equals("Admin")){
            int count = 0;
            for(int i = 0 ; i < 8 ;i++){
                if(i == 1 && String.valueOf(userPassword.getPassword()).equals("")){
                    input = false;
                    break;
                }else if ( i== 1 ||i == 2 || i == 4 || i == 6){
                }else{
                    if(txtField.get(count).getText().equals("")){
                        input = false;
                        break;
                    }
                    count++;
                }
            }
        }else{
            int count = 0;
            int loopCounter = 0;
            if(role.equals("Agent"))
                loopCounter = Agent.length; //11
            else if(role.equals("Owner"))
                loopCounter = Owner.length;
            else //tenant
                loopCounter = Tenant.length;
                                    
            for(int i = 0 ; i < loopCounter ;i++){
                if(i == 1 && String.valueOf(userPassword.getPassword()).equals("")){
                    input = false;
                    break;
                }else if (i == 1 || i == 2 || i == 4){
                }else{
                    if(txtField.get(count).getText().equals("")){
                        input = false;
                        break;
                    }
                    count++;
                }
            }
        }

        // CHECK AGENT DESCRIPTION
        if (descriptionTextArea.getText().equals("") && role.equals("Agent"))
            input = false;

        // CHECK USERNAME
        Boolean checkUsername = true;
        for (User u: userDetailsArrayList){
            if (txtField.get(0).getText().equals(username)){}
            else if (u.getUsername().equals(txtField.get(0).getText())){
                checkUsername = false;
                break;
            }
        } 

        if(input && checkUsername){
            String profilePicFileName = txtField.get(0).getText() + "ProfilePic." + extension;
            String registrationDetails = writeEditedDetailIntoString(txtField, profilePicFileName, userPassword, DOB, comboBox, role);
            try{
                // CHECK IF THE USERNAME IS CHANGED THEN THE FILENAME ALSO CHANGE
                if(!username.equals(txtField.get(0).getText())){
                    BufferedImage profileImage = ImageIO.read(new File(profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getAbsolutePath()));
                    ImageIO.write(profileImage, extension, new File(profilePicFileName));
                    File deleteFile = new File(username + "ProfilePic." + oldProfilePicExtension);
                    deleteFile.delete();
                }
                else{
                    if (profilePictureFileArraylist.size() > 1){
                        BufferedImage profileImage = ImageIO.read(new File(profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getAbsolutePath()));
                        ImageIO.write(profileImage, extension, new File(profilePicFileName));
                    }
                }
                
                registrationDetails = role + ",true," + registrationDetails;
                updateUserDetailsToFile(txtField, registrationDetails, username, role);
                updateUsernameToPropertyFile(username, txtField.get(0).getText());
                JOptionPane.showMessageDialog(new JFrame(), "Profile Is Updated Successfully","Edit Profile", JOptionPane.INFORMATION_MESSAGE);
                if(change.equals("Frame")){
                    if(role.equals("Admin")){
                        new AdminMenu(txtField.get(0).getText(), profilePicFileName);
                    }
                    else if(role.equals("Agent")){
                        new AgentMenu(txtField.get(0).getText(), profilePicFileName);
                    }
                    else if(role.equals("Owner")){
                        new OwnerMenu(txtField.get(0).getText(), profilePicFileName);
                    }
                    else if(role.equals("Tenant")){
                        new TenantMenu(txtField.get(0).getText(), profilePicFileName);
                    }
                }
                editProfileFrame.dispose();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(input == false){
            JOptionPane.showMessageDialog(new JFrame(),"Invalid Input","Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        else if(checkUsername == false){
            JOptionPane.showMessageDialog(new JFrame(),"This Username Already Exists!","Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : writeEditedDetailIntoString
    Purpose         : This method is to write Edited Detail Into String.
    ************************************************************************************************************/
    public String writeEditedDetailIntoString(ArrayList<JTextField> txtField, String profilePictureFileName, JPasswordField userPassword, ArrayList<JTextField> DOB, JComboBox<String> comboBox , String role){
        String detail = "";
        if(role.equals("Admin")){
            int count = 0;
            for(int i = 0 ; i < 8 ;i++){
                if(i == 1){
                    detail += new String(userPassword.getPassword()) + ",";
                }else if (i == 2){
                    detail += profilePictureFileName + ",";
                }else if(i == 4){
                    detail += DOB.get(0).getText() + "-" + DOB.get(1).getText() + "-" + DOB.get(2).getText() + ",";
                }else if(i == 6){
                    detail += comboBox.getSelectedItem() + ",";
                }else if(i == 7){
                    detail += txtField.get(count).getText();
                    count++;
                }else{
                    detail += txtField.get(count).getText() +",";
                    count++;
                }
            }
        }else if(role.equals("Agent")){
            int count = 0;
            for(int i = 0 ; i < Agent.length ;i++){
                if(i == 1){
                    detail += new String(userPassword.getPassword()) + ",";
                }else if (i == 2){
                    detail += profilePictureFileName + ",";
                }else if(i == 4){
                    detail += DOB.get(0).getText() + "-" + DOB.get(1).getText() + "-" + DOB.get(2).getText() + ",";
                }else{
                    detail += txtField.get(count).getText() +",";
                    count++;
                }
            }
            detail += txtField.get(0).getText()+"Description.txt";

        }else{
            int count = 0;
            int loopCounter = 0;
            if(role.equals("Owner"))
                loopCounter = Owner.length;
            else //tenant
                loopCounter = Tenant.length;
                                    
            for(int i = 0 ; i < loopCounter ;i++){
                if(i == 1){
                    detail += new String(userPassword.getPassword()) + ",";
                }else if (i == 2){
                    detail += profilePictureFileName + ",";
                }else if(i == 4){
                    detail += DOB.get(0).getText() + "-" + DOB.get(1).getText() + "-" + DOB.get(2).getText() + ",";
                }else if(i == (loopCounter-1)){
                    detail += txtField.get(count).getText();
                }else{
                    detail += txtField.get(count).getText() +",";
                    count++;
                }
            }
        }
        return detail;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : updateUserDetailsToFile
    Purpose         : This method is to update the edited detail into user csv file.
    ************************************************************************************************************/
    public void updateUserDetailsToFile(ArrayList<JTextField> txtField, String registrationDetails, String username, String role){  
        ArrayList<String> detailsArraylist = new ArrayList<>();
        //String[] registrationDetailsArray = registrationDetails.split(",");
        if(role.equals("Agent")){
            String descriptionFileName = txtField.get(0).getText()+"Description.txt";
            try{
                // SAVE NEW DESCRIPTION FILE
                File deleteOldDesciptionFile = new File(username + "Description.txt");
                deleteOldDesciptionFile.delete();
                File descriptionFile = new File(descriptionFileName);
                descriptionFile.createNewFile();
                FileWriter descriptionWriter = new FileWriter(descriptionFile);
                descriptionWriter.write(descriptionTextArea.getText());
                descriptionWriter.close();

                // SAVE NEW DETAILS
                File myFile = new File("userDetails.csv");
                FileWriter myFileWriter = new FileWriter(myFile, true);
                FileReader fileReader = new FileReader(myFile);
                BufferedReader br = new BufferedReader(fileReader);
                while(br.ready()){
                    String[] detailsArray = br.readLine().split(","); 
                    if (detailsArray[2].equals(username)){
                        detailsArray[2] = txtField.get(0).getText();
                        detailsArraylist.add(registrationDetails);
                    }
                    else{
                        String details = String.join(",",detailsArray);
                        detailsArraylist.add(details);
                    }
                }          
                FileWriter tempFileWriter = new FileWriter(myFile, false);
                br.close();
                for(String s : detailsArraylist){
                    tempFileWriter.append(s+"\n");
                }
                myFileWriter.close();
                tempFileWriter.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            try{
                File myFile = new File("userDetails.csv");
                FileWriter myFileWriter = new FileWriter(myFile, true);
                FileReader fileReader = new FileReader(myFile);
                BufferedReader br = new BufferedReader(fileReader);
                while(br.ready()){
                    String[] detailsArray = br.readLine().split(","); 
                    if (detailsArray[2].equals(username)){
                        detailsArray[2] = txtField.get(0).getText();
                        detailsArraylist.add(registrationDetails);
                    }
                    else{
                        String details = String.join(",",detailsArray);
                        detailsArraylist.add(details);
                    }
                }          
                FileWriter tempFileWriter = new FileWriter(myFile, false);
                br.close();
                for(String s : detailsArraylist){
                    tempFileWriter.append(s+"\n");
                }
                myFileWriter.close();
                tempFileWriter.close();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : updateUsernameToPropertyFile
    Purpose         : This method is to update the username to property csv file.
    ************************************************************************************************************/
    public void updateUsernameToPropertyFile(String oldUsername, String newUsername){  
        File myFile = new File("propertyDetails.csv");
        ArrayList<String> detailsArraylist = new ArrayList<>();
        try{
            FileWriter myFileWriter = new FileWriter(myFile, true);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] detailsArray = br.readLine().split(","); 
                if (detailsArray[1].equals(oldUsername)){
                    detailsArray[1] = newUsername;      
                }
                String details = String.join(",",detailsArray);
                detailsArraylist.add(details);
            }          
            FileWriter tempFileWriter = new FileWriter(myFile, false);
            br.close();
            for(String s : detailsArraylist){
                tempFileWriter.append(s+"\n");
            }
            myFileWriter.close();
            tempFileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadUserDetails
    Purpose         : This method is to load user data from csv file and return as arraylist.
    ************************************************************************************************************/  
    public ArrayList<User> loadUserDetails(){
        File myFile = new File("userDetails.csv");
        ArrayList<User> userDetailsArrayList = new ArrayList<User>();
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                userDetailsArrayList.add(new User(details[0],details[1],details[2],details[3],details[4],details[5],details[6], details[7])); // add user data into arraylist      
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return userDetailsArrayList;
    }
}