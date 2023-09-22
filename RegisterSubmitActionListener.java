import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/***********************************************************************************************************
Class's Name    : RegisterSubmitActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered when the user click the register button
************************************************************************************************************/ 
public class RegisterSubmitActionListener implements ActionListener{
    ArrayList<JTextField> txtField;
    ArrayList<File> profilePictureFileArraylist;
    JTextArea descriptionTextArea;
    JPasswordField userPassword;
    JFrame frame;
    String agentUsername;
    ArrayList<JComboBox<String>> DOB ;
    String Agent[] ={"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Agency", "Agency Registration Number", "REN/REA License Number", "Description","Agency Contact Number"};
    String Owner[] ={"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Contact Number"};
    String Tenant[] ={"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email"};
    String role;
    public RegisterSubmitActionListener (ArrayList<JTextField> txtFieldArraylist, ArrayList<File> profilePictureFileArraylist, JTextArea descriptionTextArea,JPasswordField userPassword, ArrayList<JComboBox<String>> DOB ,JFrame frame, String role){
        this.role= role;
        this.txtField = txtFieldArraylist; 
        this.profilePictureFileArraylist = profilePictureFileArraylist;
        this.descriptionTextArea = descriptionTextArea;
        this.userPassword = userPassword;
        this.frame = frame;
        this.DOB = DOB;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to save the registration details in csv file.
    ************************************************************************************************************/  
    public void actionPerformed(ActionEvent evt){
        ArrayList<User> userDetailsArrayList = loadUserDetails();
        String registrationDetails = "";
        String[] extensionArray = {"jpeg","jpg","tiff","tif","png"};
        String extension = "";
        int count = 0;
        int loopCounter = 0;
        if(role.equals("Agent"))
            loopCounter = Agent.length-1; //11
        else if(role.equals("Owner"))
            loopCounter = Owner.length;
        else //tenant
            loopCounter = Tenant.length;

        // GET THE UPLOADED PROFILE PICTURE FILE EXTENSION
        for(String s: extensionArray){
            if (profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getName().endsWith(s))
                extension = s;
        }
        String profilePicFileName = txtField.get(0).getText() + "ProfilePic." + extension;

        // CHECK INVALID INPUT
        Boolean input = true;
        for(int i = 0 ; i < loopCounter;i++){
            if(i == 1 && (String.valueOf(userPassword.getPassword()).equals(""))){
                input = false;
                break;
            }else if(i == 1|| i == 2 || i == 4){
            }else{
                if(txtField.get(count).getText().equals("")){
                    input = false;
                    break;
                }
                count++;
            }
        }
        count = 0;

        // CHECK AGENT DESCRIPTION
        if (descriptionTextArea.getText().equals("") && role.equals("Agent"))
            input = false;

        // CHECK USERNAME
        Boolean checkUsername = true;
        for (User u: userDetailsArrayList){
            if (u.getUsername().equals(txtField.get(0).getText())){
                checkUsername = false;
                break;
            }
        } 

        // SAVE ALL THE DETAILS AS A STRING
        if(input && checkUsername){
            for(int i = 0 ; i < loopCounter;i++){
                if(i == 1){
                    registrationDetails += new String(userPassword.getPassword()) + ",";
                }else if(i == 2){
                    registrationDetails += profilePicFileName + ",";
                }else if(i == 4){
                    registrationDetails += DOB.get(0).getSelectedItem() + "-" + DOB.get(1).getSelectedItem() + "-" + DOB.get(2).getSelectedItem() + ",";
                }else{
                    registrationDetails += txtField.get(count).getText() +",";
                    count++;
                }
            }

            // SAVE THE DETAILS INTO FILE
            if(role.equals("Agent")){
                // SAVE DESCRIPTION INTO TXT FILE
                String descriptionFileName = txtField.get(0).getText()+"Description.txt";
                registrationDetails += descriptionFileName;
                try{
                    File descriptionFile = new File(descriptionFileName);
                    descriptionFile.createNewFile();
                    FileWriter descriptionWriter = new FileWriter(descriptionFile);
                    descriptionWriter.write(descriptionTextArea.getText());
                    descriptionWriter.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            try{
                FileWriter myFileWriter = new FileWriter("userDetails.csv", true);
                BufferedImage profileImage = ImageIO.read(new File(profilePictureFileArraylist.get(profilePictureFileArraylist.size()-1).getAbsolutePath()));
                ImageIO.write(profileImage, extension, new File(profilePicFileName));  
                registrationDetails = role + ",false," + registrationDetails;
                myFileWriter.append(registrationDetails + "\n");
                frame.dispose();
                JOptionPane.showMessageDialog(new JFrame(), " Your Account is Created Successfully","Agent Sign Up", JOptionPane.INFORMATION_MESSAGE);    
                myFileWriter.close();
    
            } catch(IOException e){
                e.printStackTrace();
            }
            new Main();
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
