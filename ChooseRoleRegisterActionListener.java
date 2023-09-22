import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/***********************************************************************************************************
Class's Name    : ChooseRoleRegisterActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the users click the sign up button
************************************************************************************************************/ 
public class ChooseRoleRegisterActionListener implements ActionListener{
    String Agent[] ={"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Agency", "Agency Registration Number", "REN/REA License Number","Agency Contact Number","Description"};
    String Owner[] ={"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email", "Contact Number"};
    String Tenant[]={"Username", "Password", "Profile Picture", "Phone Number", "Date of Birth", "Email"};

    JFrame frame;
    String role;
    public ChooseRoleRegisterActionListener(String role, JFrame frame){
        this.role = role;
        this.frame = frame;
    }
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : actionPerformed
    Purpose         : This method is to perform registration process.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent ev){
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        frame.dispose();
        JButton source = (JButton)ev.getSource();
        JFrame frame = new JFrame("Sign Up Account");
        // BACKGROUND 
        JPanel backgroundPanel = new JPanel(); 
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBackground(new Color(175,238,238));

        JPanel northPanel = new JPanel();
        northPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
        northPanel.setBackground(new Color(175,238,238));  

        JPanel southPanel = new JPanel();
        southPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
        southPanel.setBackground(new Color(175,238,238)); 

        //MAIN CONTENT
        JPanel registerMenu = new JPanel();
        registerMenu.setLayout(new BoxLayout(registerMenu, BoxLayout.Y_AXIS));
        registerMenu.setPreferredSize(new Dimension(500,600));
        registerMenu.setMaximumSize(new Dimension(500,600));

        JPanel topRegister = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel registerTitle = new JLabel("Register account ");
        registerTitle.setFont(new Font("Trebuchet MS", Font.BOLD,  26));
        topRegister.setPreferredSize(new Dimension(500,50));
        topRegister.setMaximumSize(new Dimension(500,50));
        topRegister.setBackground(Color.WHITE); 
        topRegister.add(Box.createRigidArea(new Dimension(15, 0)));
        topRegister.add(registerTitle);
        registerMenu.add(topRegister);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,500));
        panel.setMaximumSize(new Dimension(500,500));
        JPasswordField userPassword = new JPasswordField();
        ArrayList<JTextField> txtFieldArraylist = new ArrayList<JTextField>();
        ArrayList<File> profilePictureFileArraylist = new ArrayList<File>();
        JTextArea descriptionTextArea = new JTextArea();
        ArrayList<JComboBox<String>> DOB = new ArrayList<JComboBox<String>>();
        String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] year = new String[100]; 
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int index = year.length-1; index >= 0; index--){
            year[index] = Integer.toString(currentYear - index);
        }
        profilePictureFileArraylist.add(new File("defaultProfilePic.jpg"));
        String[] sArr = source.getText().split(" ");
        role = sArr[3];
        int counter = 0;
        if(role.equals("Agent")){ // user click Agent registration button
            panel = new JPanel(new GridLayout(Agent.length,2));
            for(int i = 0; i < Agent.length; i++){
                JLabel agentLabel = new JLabel(Agent[i]);
                agentLabel.setFont(new Font("Comic Sans MS", Font.PLAIN,  16));
                panel.add(agentLabel);
                if (i == 1){ //add jpasswordfield
                    panel.add(userPassword);
                }
                else if(i == 2){ //add jfilechooser / browser image file function
                    JButton profilePictureButton = new JButton("Browse");
                    profilePictureButton.addActionListener(new ProfilePictureButtonActionListener(profilePictureFileArraylist,profilePictureButton));
                    panel.add(profilePictureButton);
                }
                else if(i == 4){ //DOB
                    JPanel dobPanel = new JPanel();
                    dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
                    DOB.add(new JComboBox<String>(days)); 
                    DOB.add(new JComboBox<String>(months)); 
                    DOB.add(new JComboBox<String>(year)); 
                    dobPanel.add(DOB.get(0));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(1));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(2));
                    dobPanel.setBackground(Color.WHITE); 
                    panel.add(dobPanel);
                }
                else if(i == Agent.length-1){
                     // DESCRIPTION PANEL
                    JPanel descriptionPanel = new JPanel();
                    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

                    descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));
                    descriptionTextArea.setLineWrap(true);
                    descriptionPanel.add(descriptionScrollPane);
                    panel.add(descriptionPanel);
                }
                else{
                    txtFieldArraylist.add(new JTextField());
                    panel.add(txtFieldArraylist.get(counter));
                    counter++;
                }
            }

        }else if(role.equals("Owner")){ // user click Agent registration button
            panel = new JPanel(new GridLayout(Owner.length,2));
            for(int i = 0; i < Owner.length; i++){
                JLabel ownerLabel = new JLabel(Owner[i]);
                ownerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN,  16));
                panel.add(ownerLabel);
                if (i == 1){
                    panel.add(userPassword);
                }
                else if(i == 2){ //add jfilechooser / browser image file function
                    JButton profilePictureButton = new JButton("Browse");
                    profilePictureButton.addActionListener(new ProfilePictureButtonActionListener(profilePictureFileArraylist,profilePictureButton));
                    panel.add(profilePictureButton);
                }
                else if(i == 4){ //DOB
                    JPanel dobPanel = new JPanel();
                    dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
                    DOB.add(new JComboBox<String>(days)); 
                    DOB.add(new JComboBox<String>(months)); 
                    DOB.add(new JComboBox<String>(year)); 
                    dobPanel.add(DOB.get(0));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(1));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(2));
                    dobPanel.setBackground(Color.WHITE); 
                    panel.add(dobPanel);
                }
                else{
                    txtFieldArraylist.add(new JTextField());
                    panel.add(txtFieldArraylist.get(counter));
                    counter++;
                }
            }

        }else{ // user click tenant registration button
            panel = new JPanel(new GridLayout(Tenant.length,2));
            for(int i = 0; i < Tenant.length; i++){
                JLabel tenantLabel = new JLabel(Tenant[i]);
                tenantLabel.setFont(new Font("Comic Sans MS", Font.PLAIN,  16));
                panel.add(tenantLabel);
                if (i == 1){
                    panel.add(userPassword);
                }
                else if(i == 2){ //add jfilechooser / browser image file function
                    JButton profilePictureButton = new JButton("Browse");
                    profilePictureButton.addActionListener(new ProfilePictureButtonActionListener(profilePictureFileArraylist,profilePictureButton));
                    panel.add(profilePictureButton);
                }
                else if(i == 4){ //DOB
                    JPanel dobPanel = new JPanel();
                    dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
                    DOB.add(new JComboBox<String>(days)); 
                    DOB.add(new JComboBox<String>(months)); 
                    DOB.add(new JComboBox<String>(year)); 
                    dobPanel.add(DOB.get(0));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(1));
                    dobPanel.add(new JLabel("  -  "));
                    dobPanel.add(DOB.get(2));
                    dobPanel.setBackground(Color.WHITE); 
                    panel.add(dobPanel);
                }
                else{
                    txtFieldArraylist.add(new JTextField());
                    panel.add(txtFieldArraylist.get(counter));
                    counter++;
                }
            }
        }
        panel.setBackground(Color.WHITE); 
        registerMenu.add(panel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setPreferredSize(new Dimension(500,50));
        bottomPanel.setMaximumSize(new Dimension(500,50));
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(120, 40)); 
        submitButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        submitButton.addActionListener(new RegisterSubmitActionListener(txtFieldArraylist, profilePictureFileArraylist, descriptionTextArea, userPassword, DOB, frame, role));
        bottomPanel.setBackground(Color.WHITE); 
        bottomPanel.add(submitButton);
        registerMenu.add(bottomPanel);

        registerMenu.setBorder(border);
        topRegister.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        backgroundPanel.add(northPanel, BorderLayout.NORTH);
        backgroundPanel.add(registerMenu,BorderLayout.CENTER);
        backgroundPanel.add(southPanel,BorderLayout.SOUTH);
        frame.add(backgroundPanel);
        frame.setSize(1000,800);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}