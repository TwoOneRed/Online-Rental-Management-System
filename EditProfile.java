import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/***********************************************************************************************************
Class's Name    : EditProfile
Design Pattern  : Singleton
Purpose         : This class is to edit the user profile. 
************************************************************************************************************/ 
public class EditProfile extends JFrame{
    //declaration for the input for user
    String Admin[] = {"Username","Password","Profile Picture","Phone Number","Date Of Birth","Email","Security Question", "Answers"};
    String Agent[] = {"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Agency", "Agency Registration Number", "REN/REA License Number","Agency Contact Number", "Description"};
    String Owner[] = {"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Contact Number"};
    String Tenant[] = {"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email"};

    public EditProfile(String[] userDetailsArray, JButton submitButton, String change){
        super("Edit Profile");
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        ArrayList<JTextField> txtFieldArraylist = new ArrayList<JTextField>();
        ArrayList<JTextField> DOB = new ArrayList<JTextField>();
        JPasswordField userPassword = new JPasswordField(userDetailsArray[3]);
        ArrayList<File> profilePictureFileArraylist = new ArrayList<File>();
        String role = userDetailsArray[0];
        String username = userDetailsArray[2];
        String dobArray[] = userDetailsArray[6].split("-");
        String securityQuestion[] = {"In what city were you born?"," What is the name of your favorite pet?"," What is your mother's maiden name?","What high school did you attend?","What is the name of your first school?","What was the make of your first car?","What was your favorite food as a child?"," Where did you meet your spouse?"};
        JComboBox<String> comboBox = new JComboBox<String>(securityQuestion);
        JTextArea descriptionTextArea = new JTextArea();
        profilePictureFileArraylist.add(new File(userDetailsArray[4]));

        centerPanel(txtFieldArraylist, DOB, userPassword, profilePictureFileArraylist, descriptionTextArea, role, username, dobArray, comboBox, userDetailsArray, northPanel, centerPanel, bottomPanel);

        submitButton.setBackground(new Color(30,144,255));
        submitButton.setForeground(Color.white);
        submitButton.setPreferredSize(new Dimension(150,60));
        submitButton.addActionListener(new EditProfileSubmitActionListener(txtFieldArraylist, profilePictureFileArraylist, descriptionTextArea, userPassword, DOB, comboBox, this, username, role, change));
        bottomPanel.add(submitButton);
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong, Tan Sin Zhung
    Method's Name   : centerPanel
    Purpose         : This method is use to get the role of the user and continue to the following edit profile form.
    ************************************************************************************************************/ 
    public void centerPanel( ArrayList<JTextField> txtFieldArraylist, ArrayList<JTextField> DOB, JPasswordField userPassword, ArrayList<File> profilePictureFileArraylist, JTextArea descriptionTextArea, 
                        String role, String username,  String dobArray[], JComboBox<String> comboBox, String[] userDetailsArray, JPanel northPanel,JPanel centerPanel,JPanel bottomPanel){
        // NORTH PANEL
        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        northPanel.add(title, BorderLayout.WEST);
        northPanel.setBackground(new Color(25, 149, 173));
        northPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        // CENTER PANEL
        if(role.equals("Agent")){
            centerPanel.setLayout(new GridLayout(Agent.length,2));
            editProfileForm(centerPanel, userPassword, txtFieldArraylist, DOB, profilePictureFileArraylist, descriptionTextArea, userDetailsArray, Agent, dobArray, comboBox);
        
        }else if(role.equals("Owner")){ // user click Agent registration button
            centerPanel.setLayout(new GridLayout(Owner.length,2));
            editProfileForm(centerPanel, userPassword, txtFieldArraylist, DOB, profilePictureFileArraylist, new JTextArea(), userDetailsArray, Owner, dobArray, comboBox);
        }else if (role.equals("Tenant")){ // user click tenant registration button
            centerPanel.setLayout(new GridLayout(Tenant.length,2));
            editProfileForm(centerPanel, userPassword, txtFieldArraylist, DOB, profilePictureFileArraylist, new JTextArea(), userDetailsArray, Tenant, dobArray, comboBox);
        }
        else if(role.equals("Admin")){
            centerPanel.setLayout(new GridLayout(Admin.length,2));
            editProfileForm(centerPanel, userPassword, txtFieldArraylist, DOB, profilePictureFileArraylist, new JTextArea(), userDetailsArray, Admin, dobArray, comboBox);
        }
        // BOTTOM PANEL
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 80)));

        add(northPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        setSize(500,500);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong, Tan Sin Zhung
    Method's Name   : centerPanel
    Purpose         : This method is to display the edit profile form and prompt user to edit the data.
    ************************************************************************************************************/ 
    public void editProfileForm(JPanel centerPanel,JPasswordField userPassword,ArrayList<JTextField> txtFieldArraylist, ArrayList<JTextField> DOB, ArrayList<File> profilePictureFileArraylist, JTextArea descriptionTextArea, String[] userDetailsArray, String[] roleArray, String[] dobArray ,JComboBox<String>comboBox){
        int counter = 0;
        JTextField day = new JTextField(dobArray[0]); day.setEditable(false); day.setBackground(Color.white); day.setForeground(Color.LIGHT_GRAY);
        JTextField month = new JTextField(dobArray[1]); month.setEditable(false); month.setBackground(Color.white); month.setForeground(Color.LIGHT_GRAY);
        JTextField year = new JTextField(dobArray[2]); year.setEditable(false); year.setBackground(Color.white); year.setForeground(Color.LIGHT_GRAY);
        if(userDetailsArray[0].equals("Agent")){
            for(int i = 0; i < roleArray.length; i++){
                centerPanel.add(Box.createRigidArea(new Dimension(300,50)));
                centerPanel.add(new JLabel(roleArray[i]));
                if (i == 1){ //add jpasswordfield
                    centerPanel.add(userPassword);
                }
                else if(i == 2){ //add jfilechooser / browser image file function
                    JButton profilePictureButton = new JButton(profilePictureFileArraylist.get(0).getName());
                    profilePictureButton.addActionListener(new ProfilePictureButtonActionListener(profilePictureFileArraylist,profilePictureButton));
                    centerPanel.add(profilePictureButton);
                }
                else if(i == 4){ //DOB
                    JPanel dobPanel = new JPanel();
                    dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
                    DOB.add(day); 
                    DOB.add(month); 
                    DOB.add(year); 
                    dobPanel.add(DOB.get(0));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(1));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(2));
                    centerPanel.add(dobPanel);
                }
                else if (i == 10){
                    // DESCRIPTION PANEL
                    JPanel descriptionPanel = new JPanel();
                    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

                    descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));
                    descriptionTextArea.setLineWrap(true);
                    try {
                        File descFile = new File(userDetailsArray[11]);
                        String descWords;
                        BufferedReader br = new BufferedReader(new FileReader(descFile));
                        while ((descWords = br.readLine()) != null){
                            descriptionTextArea.append(descWords+"\n");
                            descriptionTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN,  14));
                            
                        }
                        br.close();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    descriptionPanel.add(descriptionScrollPane);
                    centerPanel.add(descriptionPanel);
                }
                else if (i == 9){
                    txtFieldArraylist.add(new JTextField(userDetailsArray[userDetailsArray.length-1]));
                    centerPanel.add(txtFieldArraylist.get(counter));
                    counter++;
                }
                else{
                    txtFieldArraylist.add(new JTextField(userDetailsArray[i+2]));
                    centerPanel.add(txtFieldArraylist.get(counter));
                    counter++;
                }
                centerPanel.add(Box.createRigidArea(new Dimension(300,20)));
            }
        }
        else{
            for(int i = 0; i < roleArray.length; i++){
                centerPanel.add(Box.createRigidArea(new Dimension(300,50)));
                centerPanel.add(new JLabel(roleArray[i]));
                if (i == 1){ //add jpasswordfield
                    centerPanel.add(userPassword);
                }
                else if(i == 2){ //add jfilechooser / browser image file function
                    JButton profilePictureButton = new JButton(profilePictureFileArraylist.get(0).getName());
                    profilePictureButton.addActionListener(new ProfilePictureButtonActionListener(profilePictureFileArraylist,profilePictureButton));
                    centerPanel.add(profilePictureButton);
                }
                else if(i == 4){ //DOB
                    JPanel dobPanel = new JPanel();
                    dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
                    DOB.add(day); 
                    DOB.add(month); 
                    DOB.add(year); 
                    dobPanel.add(DOB.get(0));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(1));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(2));
                    centerPanel.add(dobPanel);
                }

                else if(userDetailsArray[0].equals("Admin") && i == 6){
                    String securityQuestion[] = {"In what city were you born?"," What is the name of your favorite pet?"," What is your mother's maiden name?","What high school did you attend?","What is the name of your first school?","What was the make of your first car?","What was your favorite food as a child?"," Where did you meet your spouse?"};
                    for(int j = 0; j < securityQuestion.length ; j++){
                        if(userDetailsArray[8].equals(securityQuestion[j])){
                            comboBox.setSelectedIndex(j);
                            break;
                        }
                    }
                    centerPanel.add(comboBox);
                }
                else{
                    txtFieldArraylist.add(new JTextField(userDetailsArray[i+2]));
                    centerPanel.add(txtFieldArraylist.get(counter));
                    counter++;
                }
                centerPanel.add(Box.createRigidArea(new Dimension(300,20)));
            }
        }
    }
}