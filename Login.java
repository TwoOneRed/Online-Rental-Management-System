import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
/***********************************************************************************************************
Class's Name    : Login
Design Pattern  : Singleton
Purpose         : This class is the login page.
************************************************************************************************************/ 
public class Login extends JFrame{
    public Login (){
    super("Cyberjaya Online Rental Management System");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Border emptyBorder = BorderFactory.createEmptyBorder();

        JPanel loginBackground = new JPanel();
        loginBackground.setLayout(new BoxLayout(loginBackground, BoxLayout.Y_AXIS));
        loginBackground.setBackground(new Color(175,238,238));

        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,150));
        topPanel.setBackground(new Color(175,238,238));  

        JPanel southPanel = new JPanel();
        southPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));
        southPanel.setBackground(new Color(175,238,238));  

        JPanel loginMenu = new JPanel();
        loginMenu.setLayout(new BoxLayout(loginMenu, BoxLayout.Y_AXIS));
        loginMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginMenu.setPreferredSize(new Dimension(500,500));
        loginMenu.setMaximumSize(new Dimension(500,500));

        JPanel loginTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel loginWords = new JLabel("Log in ");
        loginWords.setFont(new Font("Trebuchet MS", Font.BOLD,  26));
        
        loginTitle.setPreferredSize(new Dimension(500,50));
        loginTitle.setMaximumSize(new Dimension(500,50));
        loginTitle.setBackground(Color.WHITE); 
        loginTitle.add(Box.createRigidArea(new Dimension(15, 0)));
        loginTitle.add(loginWords);

        JPanel loginContent = new JPanel();
        loginContent.setPreferredSize(new Dimension(500,350));
        loginContent.setMaximumSize(new Dimension(500,350));
        loginContent.setBackground(Color.WHITE);

        JPanel contentTop = new JPanel();
        contentTop.setLayout(new BoxLayout(contentTop, BoxLayout.Y_AXIS));
        contentTop.setMaximumSize(new Dimension(500,320));
        contentTop.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username : ");
        usernameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN,  22));

        JLabel passwordlabel = new JLabel("Password :");
        passwordlabel.setFont(new Font("Comic Sans MS", Font.PLAIN,  22));

        JTextField usernameTxtField = new JTextField(30);
        usernameTxtField.setFont(new Font("Comic Sans MS", Font.PLAIN,  18));
        JPasswordField passwordTxtField = new JPasswordField(30);
        passwordTxtField.setFont(new Font("Comic Sans MS", Font.PLAIN,  18));

        contentTop.add(Box.createRigidArea(new Dimension(0, 60)));
        contentTop.add(usernameLabel);
        contentTop.add(usernameTxtField);
        contentTop.add(Box.createRigidArea(new Dimension(0, 40)));
        contentTop.add(passwordlabel);
        contentTop.add(passwordTxtField);
        loginContent.add(contentTop);

        JPanel contentBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contentBottom.setMaximumSize(new Dimension(500,30));
        contentBottom.setBackground(Color.WHITE);

        JButton loginButton = new JButton("Log in"); 
        loginButton.setPreferredSize(new Dimension(120, 40)); 
        loginButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                ArrayList<User> userDetailsArrayList = loadUserDetails();
                String strUsername = usernameTxtField.getText();
                String strPasswordString = new String(passwordTxtField.getPassword());
                Boolean invalid = false;
                for(User u : userDetailsArrayList){
                    // check whether the username and password is correct
                    if (u.getUsername().equals(strUsername) && u.getPassword().equals(strPasswordString)){
                        if(Boolean.valueOf(u.getStatus())){
                            dispose();
                            if (u.getRole().equals("Admin"))
                            {
                                Admin admin = loadAdminDetails(u.getUsername());
                                JFrame security = new JFrame("Security Question");
                                Border border = BorderFactory.createLineBorder(Color.BLACK);

                                JPanel securityBackground = new JPanel();
                                securityBackground.setLayout(new BoxLayout(securityBackground, BoxLayout.Y_AXIS));
                                securityBackground.setBackground(new Color(175,238,238));

                                JPanel topPanel = new JPanel();
                                topPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
                                topPanel.setBackground(new Color(175,238,238));  

                                JPanel southPanel = new JPanel();
                                southPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));
                                southPanel.setBackground(new Color(175,238,238));  

                                JPanel securityMenu = new JPanel();
                                securityMenu.setLayout(new BoxLayout(securityMenu, BoxLayout.Y_AXIS));
                                securityMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
                                securityMenu.setPreferredSize(new Dimension(600,600));
                                securityMenu.setMaximumSize(new Dimension(600,600));
                                securityMenu.setBackground(Color.WHITE);
                                securityMenu.setBorder(border);
                            
                                
                                JPanel contentTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
                                contentTop.setPreferredSize(new Dimension(600,50));
                                contentTop.setMaximumSize(new Dimension(600,50));
                                contentTop.setBackground(Color.WHITE);
                                JLabel title = new JLabel ("Security Question");
                                title.setFont(new Font("Trebuchet MS", Font.BOLD,  26));
                                contentTop.add(Box.createRigidArea(new Dimension(15, 0)));
                                contentTop.add(title);
                                contentTop.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                                securityMenu.add(contentTop);

                                JPanel contentMid = new JPanel();
                                contentMid.setLayout(new BoxLayout(contentMid, BoxLayout.Y_AXIS));
                                contentMid.setAlignmentX(Component.CENTER_ALIGNMENT);
                                contentMid.setMinimumSize(new Dimension(550,500));
                                contentMid.setPreferredSize(new Dimension(550,500));
                                contentMid.setMaximumSize(new Dimension(550,500));
                                contentMid.setBackground(Color.WHITE);

                                JLabel securityTitle = new JLabel (" Security Question : ");
                                JLabel securityQues = new JLabel (admin.getSecurityQues());
                                JLabel securityAnsTitle = new JLabel (" Answer :");
                                JTextField securityAns = new JTextField();
                                securityAns.setPreferredSize(new Dimension(1120,65));
                                securityAns.setMaximumSize(new Dimension(1120,65));

                                securityTitle.setFont(new Font("Comic Sans MS", Font.BOLD,  30));
                                securityQues.setFont(new Font("Comic Sans MS", Font.PLAIN,  28));
                                securityAnsTitle.setFont(new Font("Comic Sans MS", Font.BOLD,  30));
                                securityAns.setFont(new Font("Comic Sans MS", Font.PLAIN,  24));

                                
                                contentMid.add(Box.createRigidArea(new Dimension(0, 50)));
                                contentMid.add(securityTitle);
                                contentMid.add(securityQues);
                                contentMid.add(Box.createRigidArea(new Dimension(0, 150)));
                                contentMid.add(securityAnsTitle);
                                contentMid.add(securityAns);
                                securityMenu.add(contentMid);


                                JPanel contentBot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                                contentBot.setPreferredSize(new Dimension(Short.MAX_VALUE,50));
                                contentBot.setMaximumSize(new Dimension(Short.MAX_VALUE,50));
                                contentBot.setBackground(Color.WHITE);

                                JButton submit = new JButton("submit");
                                submit.setPreferredSize(new Dimension(120, 40)); 
                                submit.setFont(new Font("Verdana", Font.PLAIN, 15));
                                submit.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent ev){
                                        security.dispose();
                                        if(admin.getSecurityAns().equals(securityAns.getText())){
                                            new AdminMenu(u.getUsername(), u.getprofilePic());
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(new JFrame(),"Invalid Answer","Invalid Answer", JOptionPane.ERROR_MESSAGE);
                                            new Main();
                                        }
                                    }
                                });
                                contentBot.add(submit);
                                securityMenu.add(contentBot); 
                                securityBackground.add(topPanel, BorderLayout.NORTH);
                                securityBackground.add(securityMenu, BorderLayout.CENTER);
                                securityBackground.add(southPanel, BorderLayout.SOUTH);
                                security.add(securityBackground);
                                security.setSize(1000,800);  
                                security.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		                        security.setVisible(true);
                                security.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            }
                            else if (u.getRole().equals("Agent"))
                                new AgentMenu(u.getUsername(), u.getprofilePic());
                            else if (u.getRole().equals("Owner"))
                                new OwnerMenu(u.getUsername(), u.getprofilePic());
                            else
                                new TenantMenu(u.getUsername(), u.getprofilePic());
                        }
                        else{
                            dispose();
                            JOptionPane.showMessageDialog(new JFrame(),"Please Wait Until Admin Approve Your Registration","Waiting Approval", JOptionPane.ERROR_MESSAGE);
                            new Main();
                        }
                        invalid = false;
                        break;
                    }
                    else{
                        invalid = true;
                    }
                }
                if(invalid){
                    dispose();
                    JOptionPane.showMessageDialog(new JFrame(),"Username or Password is Incorrect","Incorrect Username/Password", JOptionPane.ERROR_MESSAGE);
                    new Main();
                }  
            }
        });
        contentBottom.add(loginButton);
        loginContent.add(contentBottom);
        loginMenu.add(loginTitle);
        loginMenu.add(loginContent);

        JPanel southLogin = new JPanel();
        southLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        southLogin.setMaximumSize(new Dimension(500,Short.MAX_VALUE));
        southLogin.setBackground(Color.WHITE);
    
        JPanel southContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southContent.setMaximumSize(new Dimension(350,30));
        southContent.setBackground(Color.WHITE);

        JLabel signUpBait = new JLabel("Don't have an account yet? ");
        signUpBait.setFont(new Font("Verdana", Font.PLAIN, 15));

        JButton signUpButton = new JButton("<html><u>Sign Up now<u>!<html>"); // create sign up button
        signUpButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        signUpButton.setBackground(Color.WHITE);
        signUpButton.setBorder(emptyBorder);
        signUpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                new Register();
            }
        });

        southContent.add(signUpBait);
        southContent.add(signUpButton);
        southLogin.add(southContent);
        loginMenu.add(southLogin);
        loginMenu.setBorder(border);
        loginTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        loginBackground.add(topPanel, BorderLayout.NORTH);
        loginBackground.add(loginMenu, BorderLayout.CENTER);
        loginBackground.add(southPanel, BorderLayout.SOUTH);

        add(loginBackground);
        setSize(1000,800);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadAdminDetails
    Purpose         : This method is to load all Admin's detail and return as admin
    ************************************************************************************************************/ 
    public Admin loadAdminDetails(String name){
        Admin admin = new Admin();
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(name))
                    admin = new Admin(details[1],details[2],details[3],details[4],details[5],details[6], details[7],details[8],details[9]);
                    
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return admin;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadUserDetails
    Purpose         : This method is to load all the users' detail and return as arraylist.
    ************************************************************************************************************/ 
    // LOAD THE USER DATA FROM CSV FILE AND RETURN AS ARRAYLIST
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