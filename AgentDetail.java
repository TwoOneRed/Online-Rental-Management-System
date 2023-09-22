import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.border.Border;
import java.awt.event.*;

/***********************************************************************************************************
Class's Name    : AgentDetail 
Design Pattern  : Singleton
Purpose         : This class is to display the agent detail.
************************************************************************************************************/ 
public class AgentDetail extends JFrame{
    //Aggregation from Property.
    ArrayList<Property>agentPropertyArrayList = new ArrayList<Property>();
    
    public AgentDetail(String agent , String loginName){ 
        super("Agent Details");
        loadAgentPropertyData(agent);
        setSize(1000,800);

        Agent currentAgent = new Agent();
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[0].equals("Agent") && details[2].equals(agent)){
                    currentAgent = new Agent(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] ,details[9],details[10],details[11],details[12]);
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }

        //TITLE
        JPanel northPanel = new JPanel(new GridLayout(1,1));
        northPanel.setPreferredSize(new Dimension(Short.MAX_VALUE, 65));
        northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.setBackground(new Color(25, 149, 173));
        
        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        title.setForeground(Color.white);
        northPanel.add(title);

        //AGENT
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //AGENT PHOTO
        JPanel agentImagePanel = new JPanel();
        agentImagePanel.setBackground(Color.white);
        agentImagePanel.setLayout(new BoxLayout(agentImagePanel, BoxLayout.X_AXIS));
        JLabel agentImage = new JLabel(loadImage(currentAgent.getprofilePic(), 150, 150));
        agentImagePanel.add(Box.createRigidArea(new Dimension(550, 0)));
        agentImagePanel.add(agentImage);
        agentImagePanel.add(Box.createRigidArea(new Dimension(550, 20)));
        centerPanel.add(agentImagePanel);

        //CENTER PANEL FIRST GRID
        JPanel firstGridPanel = new JPanel();
        firstGridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        firstGridPanel.setPreferredSize(new Dimension(700,350));
        firstGridPanel.setMaximumSize(new Dimension(700,350));
        firstGridPanel.setLayout(new GridLayout(6,2));
        firstGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String Agent[] = {"Username", "Contact Number", "Email", "REN/REA License Number", "Agency", " Agency Registration Number"};

        firstGridPanel.setLayout(new GridLayout(6,2));
        JLabel[] Label = new JLabel[6]; //LEFT
        JLabel[] Label2 = new JLabel[6]; //RIGHT
        for(int i = 0 ; i < 6 ; i++){
            Label[i] = new JLabel(Agent[i]);
        }
        Label2[0]= new JLabel(currentAgent.getUsername());    Label2[1]= new JLabel(currentAgent.getContactNumber());
        Label2[2]= new JLabel(currentAgent.getEmail()); Label2[3]= new JLabel(currentAgent.getAgentLicenseNum());
        Label2[4]= new JLabel(currentAgent.getAgency());       Label2[5]= new JLabel(currentAgent.getAgencyRegisNum());
        for(int i = 0 ; i < 6 ; i++){
            Label[i].setHorizontalAlignment(SwingConstants.CENTER);
            Label2[i].setHorizontalAlignment(SwingConstants.CENTER);
            Label[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            Label2[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            firstGridPanel.add(Label[i]);
            firstGridPanel.add(Label2[i]);
        }
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(firstGridPanel);


        //CENTER PANEL SECOND GRID
        JPanel secondGridPanel = new JPanel();
        secondGridPanel.setLayout(new BoxLayout(secondGridPanel, BoxLayout.X_AXIS));
        Border blacklineBorder = BorderFactory.createLineBorder(Color.black);
        secondGridPanel.setBorder(blacklineBorder);
        JPanel propPanel = new JPanel();
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));

        propPanel.setPreferredSize(new Dimension(700,150));
        propPanel.setMaximumSize(new Dimension(700,150));
        propPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // AGENT DESCRIPTION PANEL
        JPanel descriptionPanel = new JPanel();
        JTextArea descriptionTextArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionPanel.setBackground(new Color(127,197,255));
        descriptionTextArea.setEditable(false);
        descriptionTextArea.append("DESCRIPTION \n \n");
        try {
            File descFile = new File(currentAgent.getDescription());
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
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));
        descriptionTextArea.setLineWrap(true);
        descriptionPanel.add(descriptionScrollPane);

        propPanel.add(descriptionPanel);
        secondGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondGridPanel.add(propPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(secondGridPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //CENTER PANEL THIRD GRID
        JPanel thirdGridPanel = new JPanel();
        JPanel propertyPanel1 = new JPanel(); //LEFT
        JPanel propertyPanel2 = new JPanel(); //RIGHT
        thirdGridPanel.setLayout(new BoxLayout(thirdGridPanel, BoxLayout.X_AXIS));
        thirdGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        propertyPanel1.setLayout(new BoxLayout(propertyPanel1, BoxLayout.Y_AXIS));
        propertyPanel2.setLayout(new BoxLayout(propertyPanel2, BoxLayout.Y_AXIS));
        
        int totalProperty = agentPropertyArrayList.size();
        JLabel propertyDesc = new JLabel("Properties");
        JLabel propertyDesc2 = new JLabel(totalProperty + " properties by " + currentAgent.getUsername());
        JLabel forRent = new JLabel("For Rent");
        JLabel total = new JLabel(totalProperty + "");

        propertyDesc.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        propertyDesc.setForeground(Color.white);
        propertyDesc2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        propertyDesc2.setForeground(Color.white);
        forRent.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        forRent.setForeground(Color.white);
        total.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
        total.setForeground(Color.white);
        propertyPanel1.add(propertyDesc);
        propertyPanel1.add(propertyDesc2);
        propertyPanel2.add(forRent);
        propertyPanel2.add(total);
        thirdGridPanel.add(Box.createRigidArea(new Dimension(252, 0)));
        thirdGridPanel.add(propertyPanel1);
        thirdGridPanel.add(Box.createRigidArea(new Dimension(790, 0)));
        thirdGridPanel.add(propertyPanel2);
        thirdGridPanel.add(Box.createRigidArea(new Dimension(252, 0)));

        propertyPanel1.setBackground(new Color(30,144,255));
        propertyPanel2.setBackground(new Color(30,144,255));
        thirdGridPanel.setBackground(new Color(30,144,255));
        centerPanel.add(thirdGridPanel);

        //SOUTH PANEL
        JPanel fourthGridPanel = new JPanel();
        fourthGridPanel.setLayout(new BoxLayout(fourthGridPanel, BoxLayout.Y_AXIS));
        fourthGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fourthGridPanel.setBackground(Color.white);
        Border blackline = BorderFactory.createLineBorder(Color.black);

        double totalAgentProperty = Math.ceil(agentPropertyArrayList.size()/3.0);
        int valueInt = (int)totalAgentProperty;
        int counter = (agentPropertyArrayList.size()-1);
        for(int i = 0; i < valueInt ; i++){
            JPanel panel = new JPanel(); // BIG BOX TO THE RIGHT
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            for(int j = 0; j < 3; j++){
                if(counter == -1) 
                    break;
                Property current = agentPropertyArrayList.get(counter);
                JPanel verticalPanel = new JPanel();
                JPanel horizontalPanel = new JPanel();
                verticalPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
                verticalPanel.setBorder(blackline);
                horizontalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
                
                JButton button = new JButton();
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);

                //PROPERTY
                String[] image = current.getPropertyImages().split("\\|");
                JLabel propertyImage = new JLabel(loadImage(image[0],300,130));
                JLabel propertyPrice = new JLabel("RM" + current.getRentalPrice());
                JLabel propertyName = new JLabel(current.getBuildingName());
                JLabel propertyLocation = new JLabel(current.getCity() + ", " + current.getState());
                JLabel propertyDetails = new JLabel(current.getBuiltUpArea() + " sqft    RM " + current.getRentalRate() + " psf");
                JLabel propertyBedroom = new JLabel(loadImage("bedroom.jpg" , 40, 40));
                JLabel totalBedroom = new JLabel(current.getNumberOfBedroom());
                JLabel propertyBathroom = new JLabel(loadImage("bathroom.jpg" , 40, 40));
                JLabel totalBathroom = new JLabel(current.getNumberOfBathroom());
                JLabel propertyParking = new JLabel(loadImage("parking.jpg" , 40, 40));
                JLabel totalParking = new JLabel(current.getNumberOfParking());
                propertyPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
                propertyName.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
                propertyLocation.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
                propertyDetails.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));

                //VERTICAL PANEL
                verticalPanel.add(propertyImage);
                verticalPanel.add(propertyPrice);
                verticalPanel.add(propertyName);
                verticalPanel.add(propertyLocation);
                verticalPanel.add(propertyDetails);

                //HORIZONTAL PANEL
                horizontalPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                horizontalPanel.add(propertyBedroom);
                horizontalPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                horizontalPanel.add(totalBedroom);
                horizontalPanel.add(Box.createRigidArea(new Dimension(40, 0)));
                horizontalPanel.add(propertyBathroom);
                horizontalPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                horizontalPanel.add(totalBathroom);
                horizontalPanel.add(Box.createRigidArea(new Dimension(40, 0)));
                horizontalPanel.add(propertyParking);
                horizontalPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                horizontalPanel.add(totalParking);

                verticalPanel.add(horizontalPanel);
                button.add(verticalPanel);
                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new PropertyDetail(current, loginName);
                    }
                });
                panel.add(button);
                counter--;
            }
            fourthGridPanel.add(panel);
        }
        centerPanel.add(fourthGridPanel);
        centerPanel.setBackground(Color.white);
        JScrollPane scrollFrame = new JScrollPane(centerPanel);
        centerPanel.setAutoscrolls(true);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        scrollFrame.setBackground(new Color(255,255,255));

        add(northPanel, BorderLayout.NORTH);
        add(scrollFrame, BorderLayout.CENTER);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    
    /***********************************************************************************************************
    Programmer      : Alvin, Tan Sin Zhung
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
    Programmer      : Alvin, Tan Sin Zhung
    Method's Name   : loadAgentPropertyData
    Purpose         : This method is to load all the Agent's Property Data and written in arraylist.
    ************************************************************************************************************/ 
    public void loadAgentPropertyData(String agent){
        File myFile = new File("propertyDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[1].equals(agent)){
                            agentPropertyArrayList.add(new Property(details[0],details[1],details[2],details[3],details[4],details[5], 
                                                        details[6],details[7],details[8],details[9],details[10],details[11],details[12],
                                                        details[13],details[14],details[15],details[16],details[17],details[18],
                                                        details[19],details[20],details[21],details[22],details[23],details[24],details[25],details[26],details[27])); // add property data into arraylist
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

}