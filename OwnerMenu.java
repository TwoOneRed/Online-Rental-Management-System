import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : OwnerMenu
Design Pattern  : Singleton
Purpose         : This class is to display the owner menu.
************************************************************************************************************/ 
public class OwnerMenu extends JFrame{
    JPanel northPanel = new JPanel(new BorderLayout());   
    JPanel centerPanel = new JPanel(); 
    JPanel homePanel = new JPanel();
    JPanel listingPanel = new JPanel(); 

    public OwnerMenu(String ownerUsername, String profilePicFileName){
        super("Owner");
        northPanelFunct(this, ownerUsername, profilePicFileName);
        JScrollPane centerScrollPane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerScrollPane.setAutoscrolls(true); 
        centerScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanelFunc(this, centerScrollPane, ownerUsername, "Owner");
        add(northPanel,BorderLayout.NORTH);
        add(centerScrollPane,BorderLayout.CENTER);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
/***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : northPanelFunct
    Purpose         : This method display the title panel, and the profile picture Jmenu in the north panel of the 
                    owner menu. 
    ************************************************************************************************************/
    public void northPanelFunct(JFrame ownerMenuFrame,String ownerUsername, String profilePicFileName){
        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        northPanel.add(title, BorderLayout.WEST);

        //owner PROFILE PIC Menu
        JMenu userMenu =  new JMenu();
        JMenuItem viewProfile = new JMenuItem("View Profile");
        JMenuItem editProfile =  new JMenuItem("Edit Profile");
        JMenuItem logout = new JMenuItem("Log Out");
        userMenu.setIcon(loadImage(profilePicFileName, 60 , 60));
        userMenu.add(viewProfile);  userMenu.add(editProfile); userMenu.add(logout);
        viewProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                new ViewProfile(ownerUsername);
            }
        });
        editProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                ownerMenuFrame.dispose();
                new EditProfile(loadOwnerDetails(ownerUsername).getArray(), new JButton("Submit"), "Frame");
            }
        });
        logout.addActionListener(new LogOutActionListener(this));
        JMenuBar userMenuBar = new JMenuBar();
        userMenuBar.setBackground(new Color(25, 149, 173));
        userMenuBar.add(userMenu);
        northPanel.add(userMenuBar, BorderLayout.EAST);
        northPanel.setBackground(new Color(25, 149, 173));
    }
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : centerPanelFunct
    Purpose         : This method display tabbedPane in the center panel of owner menu. In the tabbedpane
                    there are two panel in it, which are home page and your listing page.
    ************************************************************************************************************/
    public void centerPanelFunc(JFrame frame, JScrollPane centerScrollPane, String ownerUsername, String role){
        // owner TABBED PANEL
        JTabbedPane ownerTabbedPane = new JTabbedPane();
        Integer[] homeIndex = new Integer[10];
        Arrays.fill(homeIndex,0);
        Integer[] listingIndex = new Integer[10];
        Arrays.fill(listingIndex,0);
        Boolean[] homeFacilitiesCheck = new Boolean[15];
        Arrays.fill(homeFacilitiesCheck, false);
        Boolean[] listingFacilitiesCheck = new Boolean[15];
        Arrays.fill(listingFacilitiesCheck, false);
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        listingPanel.setLayout(new BoxLayout(listingPanel, BoxLayout.Y_AXIS));
       
        // HOME PANEL
        ownerTabbedPane.add(homePanel, "HOME");  
        new CenterPanelListing(this, homeIndex, homeFacilitiesCheck, homePanel, ownerUsername);

        // LISTING PANEL
        ownerTabbedPane.add(listingPanel, "LISTINGS");

        ownerTabbedPane.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent evt){
                JTabbedPane sourceTabbedPane =  (JTabbedPane)evt.getSource();
                int count = sourceTabbedPane.getSelectedIndex();
                if(count  == 0){
                    listingPanel.removeAll();
                    new CenterPanelListing(frame, homeIndex, homeFacilitiesCheck, homePanel, ownerUsername);
                }
                if (count == 1){
                    homePanel.removeAll();
                    new YourListingPanel(frame, listingIndex, listingFacilitiesCheck, listingPanel, centerScrollPane, ownerUsername, role);
                }
            }
        });
        centerPanel.add(ownerTabbedPane);
        centerPanel.setBackground(new Color(25, 149, 173));
    }
        
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : loadOwnerDetails
    Purpose         : This method is to load owner's details from csv file and return as Owner
    ************************************************************************************************************/    
    public Owner loadOwnerDetails(String username){ // load user data from csv file and return the arraylist
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(username)){
                    br.close();
                    return new Owner(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8]);
                    // add user data into arraylist      
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : loadImage
    Purpose         : This method is to load images.
    ************************************************************************************************************/ 
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}