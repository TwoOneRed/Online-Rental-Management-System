import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/***********************************************************************************************************
Class's Name    : TenantMenu
Design Pattern  : Singleton
Purpose         : This class is to display the tenant menu.
************************************************************************************************************/ 
public class TenantMenu extends JFrame{
    JPanel northPanel = new JPanel();   
    JPanel centerPanel = new JPanel(); 

    public TenantMenu(String tenantName, String tenantPic){
        super("Tenant");
        Integer[] index = new Integer[10];
        Arrays.fill(index,0);
        Boolean[] facilitiesCheck = new Boolean[15];
        Arrays.fill(facilitiesCheck, false);
        northPanelFunct(tenantName, tenantPic);

        //CENTER PANEL //mainly property
        centerPanel.setBackground(new Color(188, 186, 190));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollFrame = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.setAutoscrolls(true);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        new CenterPanelListing(this, index, facilitiesCheck ,centerPanel , tenantName);
        
        add(northPanel,BorderLayout.NORTH);
        add(scrollFrame,BorderLayout.CENTER);
        setSize(1000,800);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /***********************************************************************************************************
    Programmer      : Alvin
    Method's Name   : northPanelFunct
    Purpose         : This method display the title panel and the admin profile picture Jmenu in the north panel
                    of tenant menu.
    ************************************************************************************************************/ 
    public void northPanelFunct(String tenantName,String profilePicFileName){
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        titlePanel.add(title);

        titlePanel.add(Box.createRigidArea(new Dimension(1100, 0)));

        //AGENT PROFILE PIC Menu
        JMenu userMenu =  new JMenu();
        userMenu.setIcon(loadImage(profilePicFileName, 50 , 50));
        JMenuItem viewProfile = new JMenuItem("View Profile");
        JMenuItem editProfile =  new JMenuItem("Edit Profile");
        JMenuItem logout = new JMenuItem("Log Out");
        viewProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                new ViewProfile(tenantName);
            }
        });
        editProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                JButton button = new JButton("Submit");           
                new EditProfile(loadTenantDetails(tenantName).getArray(), button, "Frame");
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

        //filterPanel.add(Box.createRigidArea(new Dimension(300, 0)));
        titlePanel.setBackground(new Color(25, 149, 173));
        northPanel.setBackground(new Color(25, 149, 173));
    }
    
    /***********************************************************************************************************
    Programmer      : Alvin
    Method's Name   : loadTenantDetails
    Purpose         : This method is to load tenant's details from csv file and return as Tenant.
    ************************************************************************************************************/  
    public Tenant loadTenantDetails(String username){ // load user data from csv file and return the arraylist
        System.out.println("LOADTENANT");
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(username)){
                    br.close();
                    return new Tenant(details[1], details[2] , details[3], details[4], details[5], details[6], details[7]);
                }
                    // add user data into arraylist      
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /***********************************************************************************************************
    Programmer      : Alvin
    Method's Name   : loadImage
    Purpose         : This method is to load images.
    ************************************************************************************************************/  
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}