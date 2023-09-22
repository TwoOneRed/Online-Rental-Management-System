import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.border.Border;
import java.awt.event.*;
/***********************************************************************************************************
Class's Name    : OwnerDetail
Design Pattern  : Singleton
Purpose         : This class is to display the Owner Detail.
************************************************************************************************************/ 
public class OwnerDetail extends JFrame{
    //Aggregation from Property.
    ArrayList<Property>ownerPropertyArrayList = new ArrayList<Property>();
    public OwnerDetail(String owner , String loginName){
        super("Owner Details");
        loadOwnerPropertyData(owner);
        setSize(1000,800);

        Owner currentOwner = new Owner();
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[0].equals("Owner") && details[2].equals(owner)){
                    currentOwner = new Owner(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8]);
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
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        northPanel.add(title);

        //OWNER
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setBackground(Color.white);
        
        //OWNER PHOTO
        JPanel ownerImagePanel = new JPanel();
        ownerImagePanel.setBackground(Color.white);
        ownerImagePanel.setLayout(new BoxLayout(ownerImagePanel, BoxLayout.X_AXIS));
        JLabel ownerImage = new JLabel(loadImage(currentOwner.getprofilePic(), 200, 200));
        ownerImagePanel.add(Box.createRigidArea(new Dimension(550, 0)));
        ownerImagePanel.add(ownerImage);
        ownerImagePanel.add(Box.createRigidArea(new Dimension(550, 20)));
        centerPanel.add(ownerImagePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,20)));

        //CENTER PANEL FIRST GRID
        JPanel firstGridPanel = new JPanel();
        firstGridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        firstGridPanel.setPreferredSize(new Dimension(400,250));
        firstGridPanel.setMaximumSize(new Dimension(400,250));
        firstGridPanel.setLayout(new GridLayout(3,2));
        firstGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String Owner[] = {"Name", "Contact Number", "Date of Birth"};

        firstGridPanel.setLayout(new GridLayout(3,2));
        JLabel[] Label = new JLabel[3]; //LEFT
        JLabel[] Label2 = new JLabel[3]; //RIGHT
        for(int i = 0 ; i < 3 ; i++){
            Label[i] = new JLabel(Owner[i]);
        }
        Label2[0]= new JLabel(currentOwner.getUsername());    
        Label2[1]= new JLabel(currentOwner.getContactNumber());
        Label2[2]= new JLabel(currentOwner.getDOB()); 
        for(int i = 0 ; i < 3 ; i++){
            Label[i].setHorizontalAlignment(SwingConstants.CENTER);
            Label2[i].setHorizontalAlignment(SwingConstants.CENTER);
            Label[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            Label2[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            firstGridPanel.add(Label[i]);
            firstGridPanel.add(Label2[i]);
        }
        centerPanel.add(firstGridPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,20)));

        //CENTER PANEL SECOND GRID
        JPanel secondGridPanel = new JPanel();
        JPanel propertyPanel1 = new JPanel(); //LEFT
        JPanel propertyPanel2 = new JPanel(); //RIGHT
        secondGridPanel.setLayout(new BoxLayout(secondGridPanel, BoxLayout.X_AXIS));
        secondGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        propertyPanel1.setLayout(new BoxLayout(propertyPanel1, BoxLayout.Y_AXIS));
        propertyPanel2.setLayout(new BoxLayout(propertyPanel2, BoxLayout.Y_AXIS));
        
        int totalProperty = ownerPropertyArrayList.size();
        JLabel propertyDesc = new JLabel("Properties");
        JLabel propertyDesc2 = new JLabel(totalProperty + " properties by " + currentOwner.getUsername());
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
        secondGridPanel.add(Box.createRigidArea(new Dimension(252, 0)));
        secondGridPanel.add(propertyPanel1);
        secondGridPanel.add(Box.createRigidArea(new Dimension(790, 0)));
        secondGridPanel.add(propertyPanel2);
        secondGridPanel.add(Box.createRigidArea(new Dimension(252, 0)));

        propertyPanel1.setBackground(new Color(30,144,255));
        propertyPanel2.setBackground(new Color(30,144,255));
        secondGridPanel.setBackground(new Color(30,144,255));
        centerPanel.add(secondGridPanel);
        //SOUTH PANEL
        JPanel fourthGridPanel = new JPanel();
        fourthGridPanel.setLayout(new BoxLayout(fourthGridPanel, BoxLayout.Y_AXIS));
        fourthGridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border blackline = BorderFactory.createLineBorder(Color.black);

        double totalOwnerProperty = Math.ceil(ownerPropertyArrayList.size()/3.0);
        int valueInt = (int)totalOwnerProperty;
        int counter = (ownerPropertyArrayList.size()-1);
        for(int i = 0; i < valueInt; i++){
            JPanel panel = new JPanel(); // BIG BOX TO THE RIGHT
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setBackground(new Color(255,255,255));
            for(int j = 0; j < 3; j++){
                if(counter == -1) 
                    break;
                Property current = ownerPropertyArrayList.get(counter);
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

                //PROPERTY--
                String[] image = current.getPropertyImages().split("\\|");
                JLabel propertyImage = new JLabel(loadImage(image[0],300,130));
                JLabel propertyPrice = new JLabel(current.getRentalPrice());
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
                horizontalPanel.add(Box.createRigidArea(new Dimension(20, 0)));
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

                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new PropertyDetail(current, loginName);
                    }
                });
                verticalPanel.add(horizontalPanel);
                button.add(verticalPanel);
                panel.add(button);
                counter--;
            }
            fourthGridPanel.add(panel);
        }
        centerPanel.add(fourthGridPanel);

        JScrollPane scrollFrame = new JScrollPane(centerPanel);
        centerPanel.setAutoscrolls(true);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        scrollFrame.setBackground(new Color(255,255,255));

        add(northPanel, BorderLayout.NORTH);
        //add(centerPanel, BorderLayout.CENTER);
        add(scrollFrame, BorderLayout.CENTER);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /***********************************************************************************************************
    Programmer      : Alvin, Hooi Thing Hong
    Method's Name   : loadImage
    Purpose         : This method is to load images.
    ************************************************************************************************************/ 
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /***********************************************************************************************************
    Programmer      : Alvin, Hooi Thing Hong
    Method's Name   : loadOwnerPropertyData
    Purpose         : This method is to load owner property data and save as arraylist.
    ************************************************************************************************************/ 
    public void loadOwnerPropertyData(String owner){
        File myFile = new File("propertyDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[1].equals(owner)){
                    ownerPropertyArrayList.add(new Property(details[0],details[1],details[2],details[3],details[4],details[5], 
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