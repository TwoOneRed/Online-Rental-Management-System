import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : AgentMenu
Design Pattern  : Singleton
Purpose         : This class is to display the agent menu.
************************************************************************************************************/ 
public class AgentMenu extends JFrame{
    JPanel northPanel = new JPanel(new BorderLayout());   
    JPanel centerPanel = new JPanel(); 
    JPanel homePanel = new JPanel();
    JPanel listingPanel = new JPanel(); 

    public AgentMenu(String agentUsername, String profilePicFileName){
        super("Agent");
        northPanelFunct(this, agentUsername, profilePicFileName);
        JScrollPane centerScrollPane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerScrollPane.setAutoscrolls(true); 
        centerScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centerPanelFunc(this, centerScrollPane, agentUsername, "Agent");
        add(northPanel,BorderLayout.NORTH);
        add(centerScrollPane,BorderLayout.CENTER);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : northPanelFunct
    Purpose         : This method display the title panel, and the profile picture Jmenu in the north panel of the 
                    agent menu. 
    ************************************************************************************************************/
    public void northPanelFunct(JFrame agentMenuFrame, String agentUsername, String profilePicFileName){
        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        northPanel.add(title, BorderLayout.WEST);

        //AGENT PROFILE PIC JMENU
        JMenu userMenu =  new JMenu();
        JMenuItem viewProfile = new JMenuItem("View Profile");
        JMenuItem editProfile =  new JMenuItem("Edit Profile");
        JMenuItem logout = new JMenuItem("Log Out");
        userMenu.setIcon(loadImage(profilePicFileName, 60, 60));
        userMenu.add(viewProfile);  userMenu.add(editProfile); userMenu.add(logout);
        viewProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                new ViewProfile(agentUsername);
            }
        });
        editProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                agentMenuFrame.dispose();
                new EditProfile(loadAgentDetails(agentUsername).getArray(), new JButton("Submit"), "Frame");
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
    Programmer      : Tan Sin Zhung
    Method's Name   : centerPanelFunct
    Purpose         : This method display tabbedPane in the center panel of agent menu. In the tabbedpane
                    there are two panel in it, which are home page and your listing page.
    ************************************************************************************************************/
    public void centerPanelFunc(JFrame frame, JScrollPane centerScrollPane, String agentUsername, String role){
        // AGENT TABBED PANEL
        JTabbedPane agentTabbedPane = new JTabbedPane();
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
        agentTabbedPane.add(homePanel, "HOME");  
        new CenterPanelListing(this, homeIndex, homeFacilitiesCheck, homePanel, agentUsername);

        // LISTING PANEL
        agentTabbedPane.add(listingPanel, "LISTINGS");

        agentTabbedPane.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent evt){
                JTabbedPane sourceTabbedPane =  (JTabbedPane)evt.getSource();
                int count = sourceTabbedPane.getSelectedIndex();
                if(count  == 0){
                    listingPanel.removeAll();
                    new CenterPanelListing(frame, homeIndex, homeFacilitiesCheck, homePanel, agentUsername);
                }
                if (count == 1){
                    homePanel.removeAll();
                    new YourListingPanel(frame, listingIndex, listingFacilitiesCheck, listingPanel, centerScrollPane, agentUsername, role);
                }
            }
        });
        centerPanel.add(agentTabbedPane);
        centerPanel.setBackground(new Color(25, 149, 173));
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadAgentDetails
    Purpose         : This method is to load agent's details from csv file and return as Agent
    ************************************************************************************************************/    
    //LOAD AGENT DETAILS INTO ARRAY
    public Agent loadAgentDetails(String username){ // load agent data from csv file and return the arraylist
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(username)){
                    br.close();
                    return new Agent(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8], details[9], details[10], details[11], details[12]);
                    // add agent data into arraylist      
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadImage
    Purpose         : This method is to load images.
    ************************************************************************************************************/ 
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}