import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : AdminMenu
Design Pattern  : Singleton
Purpose         : This class is to display the admin menu.
************************************************************************************************************/ 
public class AdminMenu extends JFrame{
    JPanel northPanel = new JPanel();   
    JPanel centerPanel = new JPanel(); 

    public AdminMenu(String adminUsername, String profilePicFileName){
        super("Admin");
        northPanelFunct(adminUsername, profilePicFileName);

        //CENTER PANEL //mainly property
        centerPanel.setBackground(new Color(188, 186, 190));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        JScrollPane scrollFrame = new JScrollPane(centerPanel);
        centerPanel.setAutoscrolls(true);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        Integer[] index = new Integer[10];
        Arrays.fill(index,0);
        Boolean[] facilitiesCheck = new Boolean[15];
        Arrays.fill(facilitiesCheck, false);
        new CenterPanelListing(this,index, facilitiesCheck ,centerPanel, adminUsername);

        JPanel manageUser = new JPanel(new GridLayout(1,1));
        manageUser.setMaximumSize(new Dimension(Short.MAX_VALUE,40));

        JButton manageUserButton =new JButton ("Manage User");
        manageUser.setPreferredSize(new Dimension(Short.MAX_VALUE,40));
        manageUserButton.setBackground(new Color(25, 149, 173));
        manageUserButton.setForeground(Color.white);
        manageUserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                new ManageUser(adminUsername,profilePicFileName);
            }
        });
        manageUser.add(manageUserButton);      
        add(northPanel,BorderLayout.NORTH);
        add(scrollFrame,BorderLayout.CENTER);
        add(manageUser,BorderLayout.SOUTH);
        setSize(1000,800);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : northPanelFunct
    Purpose         : This method display the title panel and the admin profile picture Jmenu in the north panel
                    of admin menu.
    ************************************************************************************************************/ 
    public void northPanelFunct(String adminName,String profilePicFileName){
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        //TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        titlePanel.add(title);

        titlePanel.add(Box.createRigidArea(new Dimension(1100, 0)));

        //ADMIN PROFILE PIC JMENU
        JMenu userMenu =  new JMenu();
        userMenu.setIcon(loadImage(profilePicFileName, 50 , 50));
        JMenuItem viewProfile = new JMenuItem("View Profile");
        JMenuItem editProfile =  new JMenuItem("Edit Profile");
        JMenuItem logout = new JMenuItem("Log Out");
        viewProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                new ViewProfile(adminName);
            }
        });
        editProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                JButton button = new JButton("Submit");           
                new EditProfile(loadAdminDetails(adminName).getArray(), button, "Frame");
            }
        });
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                new Main();
            }
        });
                
        userMenu.add(viewProfile);  userMenu.add(editProfile); userMenu.add(logout);
        JMenuBar userMenuBar = new JMenuBar();
        userMenuBar.setBackground(new Color(25, 149, 173));
        userMenuBar.add(userMenu);
        titlePanel.add(userMenuBar);
        northPanel.add(titlePanel);
        titlePanel.setBackground(new Color(25, 149, 173));
        northPanel.setBackground(new Color(25, 149, 173));
    }
    
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : loadImage
    Purpose         : This method is to load images.
    ************************************************************************************************************/ 
    //LOAD IMAGE
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : loadAdminDetails
    Purpose         : This method is to load admin's details from csv file and return as Admin
    ************************************************************************************************************/  
    public Admin loadAdminDetails(String username){ // load admin data from csv file and return the arraylist
        try{
            File myFile = new File("userDetails.csv");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(username)){
                    br.close();
                    return new Admin(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] , details[9]);
                }
                // add admin data into arraylist      
            }
            br.close();    
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}