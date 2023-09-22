import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.border.Border;
import java.util.*;
import java.io.*;
import java.util.List;
/***********************************************************************************************************
Class's Name    : PropertyDetail
Design Pattern  : Singleton
Purpose         : This class is use to display the detail of a property.
************************************************************************************************************/ 
public class PropertyDetail extends JFrame{
    //Aggregation from owner,agent.
    Owner owner = new Owner();
    Agent agent = new Agent();

    public PropertyDetail(Property property, String loginName){
        super("Property Details");
        //TITLE //*
        JPanel northPanel = new JPanel(new GridLayout(1,1));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        titlePanel.add(title);
        titlePanel.setBackground(new Color(25, 149, 173));
        northPanel.add(titlePanel);
        northPanel.setPreferredSize(new Dimension(Short.MAX_VALUE, 85));
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centalPanelFunc(this, centerPanel, property, loginName);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JScrollPane scrollFrame = new JScrollPane(centerPanel);
        centerPanel.setAutoscrolls(true);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollFrame.getVerticalScrollBar().setUnitIncrement(18);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        add(northPanel,BorderLayout.NORTH);
        add(scrollFrame,BorderLayout.CENTER);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1000,800);
        setVisible(true);
    }
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : centalPanelFunc
    Purpose         : This method is use to display overall information of a property. 
    ************************************************************************************************************/ 
    public void centalPanelFunc(JFrame frame, JPanel centerPanel, Property property, String loginName){
        centerPanel.removeAll();

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        Border emptyBorder = BorderFactory.createEmptyBorder();

        //PROPERTY DETAIL ( IMAGE )
        JPanel propertyPanel = new JPanel(new FlowLayout((FlowLayout.CENTER)));
        propertyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        String picture[] = property.getPropertyImages().split("\\|");
        List<String> printPic = Arrays.asList(picture);
        ArrayList<String> listPicture = new ArrayList<String>(printPic);
        JButton image = new JButton(loadImage(listPicture.get(0),450,300));
        image.addActionListener(new ImageActionListener(property));
        image.setBackground(new Color(105,105,105));
        propertyPanel.add(image);
        image.setBorder(emptyBorder);
        propertyPanel.setBackground(new Color(105,105,105));
        centerPanel.add(propertyPanel);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        //UP
        if (property.getPropertyStatus().equals("false")){
            JPanel introPanel = new JPanel();
            introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
            introPanel.setPreferredSize(new Dimension(1518,200));
            introPanel.setMinimumSize(new Dimension(1518,200));
            introPanel.setMaximumSize(new Dimension(1518,200));
            introPanel.setBackground(new Color(229,208,208));

            JPanel detailUp = new JPanel();
            detailUp.setLayout(new BoxLayout(detailUp, BoxLayout.Y_AXIS));
            detailUp.setBackground(new Color(229,208,208));
            detailUp.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel price = new JLabel("Rent for RM"+property.getRentalPrice()+" / Month");
            JLabel name = new JLabel(property.getBuildingName());
            JLabel location = new JLabel(property.getRoadName()+ ", "+property.getCity()+", " + property.getState());
            JLabel detail = new JLabel(property.getBuiltUpArea()+" sqft    RM " + property.getRentalRate() + " psf");
            JLabel status = new JLabel("This property is Inactive");
            

            price.setFont(new Font("Trebuchet MS", Font.BOLD,  26));
            name.setFont(new Font("Tahoma", Font.PLAIN,  20));
            name.setForeground(new Color(21,22,23));
            location.setFont(new Font("Tahoma", Font.PLAIN,  20));
            location.setForeground(new Color(105,105,105));
            detail.setFont(new Font("Tahoma", Font.PLAIN,  19));
            detail.setForeground(new Color(96,96,96));
            status.setFont(new Font("Trebuchet MS", Font.BOLD,22));

            detailUp.add(price);
            detailUp.add(name);
            detailUp.add(location);
            detailUp.add(detail);
            detailUp.add(Box.createRigidArea(new Dimension(0, 15)));
            detailUp.add(status);

            
            //DOWN
            JPanel detailDown = new JPanel(new GridLayout(1,3));
            detailDown.setPreferredSize(new Dimension(500,70));
            detailDown.setMinimumSize(new Dimension(500,70));
            detailDown.setMaximumSize(new Dimension(500,70));
            detailDown.setBackground(new Color(229,208,208));
            detailDown.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel bedroomLogo = new JLabel(loadImage("inactiveBed.jpg" , 30,30));
            JLabel bedroom = new JLabel(property.getNumberOfBedroom());
            JLabel bathroomLogo = new JLabel(loadImage("inactiveBath.jpg",30,30));
            JLabel bathroom = new JLabel(property.getNumberOfBathroom());
            JLabel parkingLogo = new JLabel(loadImage("inactiveCP.jpg",30,30));
            JLabel parking = new JLabel(property.getNumberOfParking());
            bedroom.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  14));
            bathroom.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            parking.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  14));

            detailDown.add(bedroomLogo);
            detailDown.add(bedroom);
            detailDown.add(bathroomLogo);
            detailDown.add(bathroom);
            detailDown.add(parkingLogo);
            detailDown.add(parking);
            
            introPanel.add(detailUp);
            introPanel.add(detailDown);
            centerPanel.add(introPanel);
        } else {
            JPanel introPanel = new JPanel();
            introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
            introPanel.setPreferredSize(new Dimension(1518,200));
            introPanel.setMinimumSize(new Dimension(1518,200));
            introPanel.setMaximumSize(new Dimension(1518,200));
            introPanel.setBackground(new Color(206,233,255));

            JPanel detailUp = new JPanel();
            detailUp.setLayout(new BoxLayout(detailUp, BoxLayout.Y_AXIS));
            detailUp.setBackground(new Color(206,233,255));
            detailUp.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel price = new JLabel("Rent for RM"+property.getRentalPrice()+" / Month");
            JLabel name = new JLabel(property.getBuildingName());
            JLabel location = new JLabel(property.getRoadName()+ ", "+property.getCity()+", " + property.getState());
            JLabel detail = new JLabel(property.getBuiltUpArea()+" sqft    RM " + property.getRentalRate() + " psf");
            JLabel status = new JLabel("This property is Active");
            

            price.setFont(new Font("Trebuchet MS", Font.BOLD,  26));
            name.setFont(new Font("Tahoma", Font.PLAIN,  20));
            name.setForeground(new Color(21,22,23));
            location.setFont(new Font("Tahoma", Font.PLAIN,  20));
            location.setForeground(new Color(105,105,105));
            detail.setFont(new Font("Tahoma", Font.PLAIN,  19));
            detail.setForeground(new Color(96,96,96));
            status.setFont(new Font("Trebuchet MS", Font.BOLD,22));

            detailUp.add(price);
            detailUp.add(name);
            detailUp.add(location);
            detailUp.add(detail);
            detailUp.add(Box.createRigidArea(new Dimension(0, 15)));
            detailUp.add(status);

            
            //DOWN
            JPanel detailDown = new JPanel(new GridLayout(1,3));
            detailDown.setPreferredSize(new Dimension(500,70));
            detailDown.setMinimumSize(new Dimension(500,70));
            detailDown.setMaximumSize(new Dimension(500,70));
            detailDown.setBackground(new Color(206,233,255));
            detailDown.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel bedroomLogo = new JLabel(loadImage("activeBed.jpg" , 30,30));
            JLabel bedroom = new JLabel(property.getNumberOfBedroom());
            JLabel bathroomLogo = new JLabel(loadImage("activeBath.jpg",30,30));
            JLabel bathroom = new JLabel(property.getNumberOfBathroom());
            JLabel parkingLogo = new JLabel(loadImage("activeCP.jpg",30,30));
            JLabel parking = new JLabel(property.getNumberOfParking());
            bedroom.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  14));
            bathroom.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            parking.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  14));

            detailDown.add(bedroomLogo);
            detailDown.add(bedroom);
            detailDown.add(bathroomLogo);
            detailDown.add(bathroom);
            detailDown.add(parkingLogo);
            detailDown.add(parking);
            
            introPanel.add(detailUp);
            introPanel.add(detailDown);
            centerPanel.add(introPanel);
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // DETAILS OF THE BUILDING
        JPanel building = new JPanel();
        building.setLayout(new BoxLayout(building,BoxLayout.Y_AXIS));
        building.setPreferredSize(new Dimension(1518,400));
        building.setMinimumSize(new Dimension(1518,400));
        building.setMaximumSize(new Dimension(1518,400));

        JPanel buildingTitle = new JPanel();
        buildingTitle.setBackground(Color.WHITE);
        buildingTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        buildingTitle.setPreferredSize(new Dimension(900,30));
        buildingTitle.setMaximumSize(new Dimension(900,30));


        JPanel buildingDetails = new JPanel(new GridLayout(5,2,20,10));
        buildingDetails.setBackground(Color.WHITE);
        buildingDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
        buildingDetails.setPreferredSize(new Dimension(900,300));
        buildingDetails.setMinimumSize(new Dimension(900,300));
        buildingDetails.setMaximumSize(new Dimension(900,300));
        JLabel buildingLabel = new JLabel("Details of the building");
        buildingLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  20));
        buildingTitle.add(buildingLabel);

        JLabel propertyType = new JLabel ("<html><b> Property Type :</b><p>  "+property.getPropertyType()+ " </p></html>");
        JLabel tenure = new JLabel ("<html><b> Tenure :</b><p>  "+property.getTenure()+ " </p></html>");
        JLabel townshipDeveloper = new JLabel ("<html><b> Township / Developer :</b><p>  "+property.getTownshipDeveloper()+ " </p></html>");
        JLabel builtUpSize = new JLabel ("<html><b> Built-up Size(sq.ft) : </b><p>"+property.getBuiltUpArea()+ "sq.ft </p></html>");
        JLabel buildUpPrice = new JLabel ("<html><b> Rental Rate(price per sq.ft) : </b><p>  RM"+ property.getRentalRate()+ "per sq.ft </p></html>");
        JLabel furnishing = new JLabel ("<html><b> Furnishing : </b><p>  "+property.getFurnishing()+ " </p></html>");
        JLabel block = new JLabel ("<html><b>  Block : </b><p> "+property.getBlock()+ "</p></html>");
        JLabel floor = new JLabel ("<html><b> Floor : </b><p> "+property.getFloor()+ "  </p></html>");
        JLabel buildingName = new JLabel ("<html><b> Building Name : </b><p> "+property.getBuildingName()+ " </p></html>");
        JLabel listingRefNumber = new JLabel ("<html><b> Listing Reference Number : </b><p> "+property.getListingReferenceNumber()+ "</p></html>");

        buildingDetails.add(propertyType);
        buildingDetails.add(tenure);
        buildingDetails.add(townshipDeveloper);
        buildingDetails.add(builtUpSize);
        buildingDetails.add(buildUpPrice);
        buildingDetails.add(furnishing);
        buildingDetails.add(block);
        buildingDetails.add(floor);
        buildingDetails.add(buildingName);
        buildingDetails.add(listingRefNumber);

        propertyType.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        tenure.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        townshipDeveloper.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        builtUpSize.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        block.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        floor.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        buildUpPrice.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        buildingName.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        listingRefNumber.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
        furnishing.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));

        building.add(Box.createRigidArea(new Dimension(0, 40)));
        building.add(buildingTitle);
        building.add(buildingDetails);
        buildingTitle.setBorder(border);
        buildingDetails.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));
        centerPanel.add(building);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //DESCRIPTION
        JPanel descriptionBackground = new JPanel();
        descriptionBackground.setLayout(new BoxLayout(descriptionBackground,BoxLayout.Y_AXIS));
        descriptionBackground.setPreferredSize(new Dimension(1518,450));
        descriptionBackground.setMinimumSize(new Dimension(1518,450));
        descriptionBackground.setMaximumSize(new Dimension(1518,450));

        JPanel descriptionTitle = new JPanel();
        descriptionTitle.setBackground(Color.WHITE);
        descriptionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionTitle.setPreferredSize(new Dimension(900,30));
        descriptionTitle.setMaximumSize(new Dimension(900,30));

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel,BoxLayout.Y_AXIS));
        descriptionPanel.setBackground(Color.WHITE);
        descriptionPanel.setAlignmentX( Component.CENTER_ALIGNMENT );

        JLabel descriptionLabel = new JLabel ("Description");
        descriptionLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  20));
        descriptionTitle.add(descriptionLabel);
        try {
            File descFile = new File(property.getPropertyDescription());
            String descWords;
            BufferedReader br = new BufferedReader(new FileReader(descFile));
            JTextArea desc = new JTextArea ();
            desc.setEditable(false);
            while ((descWords = br.readLine()) != null){
                desc.append(descWords+"\n");
                desc.setFont(new Font("Comic Sans MS", Font.PLAIN,  14));
                
            }
            br.close();
            descriptionPanel.add(desc);
        }catch(Exception e){
            System.out.println(e);
        }

        JScrollPane scrollDesc = new JScrollPane(descriptionPanel);
        descriptionPanel.setAutoscrolls(true);
        scrollDesc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollDesc.getVerticalScrollBar().setUnitIncrement(18);
        scrollDesc.setPreferredSize(new Dimension(900,400));
        scrollDesc.setMinimumSize(new Dimension(900,400));
        scrollDesc.setMaximumSize(new Dimension(900,400));


        descriptionBackground.add(descriptionTitle);
        descriptionBackground.add(scrollDesc);
        descriptionTitle.setBorder(border);
        descriptionPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));
        centerPanel.add(descriptionBackground);
    

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //facilities
        JPanel facilitiesBackground = new JPanel();
        facilitiesBackground.setLayout(new BoxLayout(facilitiesBackground,BoxLayout.Y_AXIS));
        facilitiesBackground.setPreferredSize(new Dimension(1518,450));
        facilitiesBackground.setMinimumSize(new Dimension(1518,450));
        facilitiesBackground.setMaximumSize(new Dimension(1518,450));

        JPanel facilitiesTitle = new JPanel();
        facilitiesTitle.setBackground(Color.WHITE);
        facilitiesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        facilitiesTitle.setPreferredSize(new Dimension(900,30));
        facilitiesTitle.setMaximumSize(new Dimension(900,30));

        JPanel facilitiesPanel = new JPanel();
        facilitiesPanel.setLayout(new BoxLayout(facilitiesPanel,BoxLayout.Y_AXIS));
        facilitiesPanel.setBackground(Color.WHITE);
        facilitiesPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        facilitiesPanel.setPreferredSize(new Dimension(900,400));
        facilitiesPanel.setMinimumSize(new Dimension(900,400));
        facilitiesPanel.setMaximumSize(new Dimension(900,400));
        

        JLabel facilitiesLabel = new JLabel ("Available Facilities");
        facilitiesLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  20));
        facilitiesTitle.add(facilitiesLabel);
        String facilities[] = property.getPropertyFacilities().split("\\|");
        List<String> printFac = Arrays.asList(facilities);
        ArrayList<String> listString = new ArrayList<String>(printFac);
        for (String str : listString){
            JLabel facilitiesDesc = new JLabel ("<html> <p>- "+str+"</p></html>");
            facilitiesDesc.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            facilitiesPanel.add(facilitiesDesc);
        }
        facilitiesBackground.add(facilitiesTitle);
        facilitiesBackground.add(facilitiesPanel);
        facilitiesTitle.setBorder(border);
        facilitiesPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));

        centerPanel.add(facilitiesBackground);
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //FEATURES
        JPanel featuresBackground = new JPanel();
        featuresBackground.setLayout(new BoxLayout(featuresBackground,BoxLayout.Y_AXIS));
        featuresBackground.setPreferredSize(new Dimension(1518,450));
        featuresBackground.setMinimumSize(new Dimension(1518,450));
        featuresBackground.setMaximumSize(new Dimension(1518,450));

        JPanel featuresTitle = new JPanel();
        featuresTitle.setBackground(Color.WHITE);
        featuresTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        featuresTitle.setPreferredSize(new Dimension(900,30));
        featuresTitle.setMaximumSize(new Dimension(900,30));

        JPanel featuresPanel = new JPanel();
        featuresPanel.setLayout(new BoxLayout(featuresPanel,BoxLayout.Y_AXIS));
        featuresPanel.setBackground(Color.WHITE);
        featuresPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
        featuresPanel.setPreferredSize(new Dimension(900,400));
        featuresPanel.setMinimumSize(new Dimension(900,400));
        featuresPanel.setMaximumSize(new Dimension(900,400));
        

        JLabel featuresLabel = new JLabel ("Available Features");
        featuresLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  20));
        featuresTitle.add(featuresLabel);
        String features[] = property.getPropertyFeatures().split("\\|");
        List<String> printFea = Arrays.asList(features);
        ArrayList<String> listFeature = new ArrayList<String>(printFea);
        for (String str : listFeature){
            JLabel featuresDesc = new JLabel ("<html> <p>- "+str+"</p></html>");
            featuresDesc.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            featuresPanel.add(featuresDesc);
        }
        featuresBackground.add(featuresTitle);
        featuresBackground.add(featuresPanel);
        featuresTitle.setBorder(border);
        featuresPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY));

        centerPanel.add(featuresBackground);

        loadUser(property.getUsername());
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (property.getRole().equals("Owner")){
            //OWNER PANEL 
            JPanel ownerBackground = new JPanel();
            ownerBackground.setLayout(new BoxLayout(ownerBackground,BoxLayout.Y_AXIS));
            ownerBackground.setPreferredSize(new Dimension(1518,350));
            ownerBackground.setMinimumSize(new Dimension(1518,350));
            ownerBackground.setMaximumSize(new Dimension(1518,350));

            JPanel ownerPanel = new JPanel();
            ownerPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
            ownerPanel.setBackground(new Color(240,230,140));
            ownerPanel.setPreferredSize(new Dimension(700,300));
            ownerPanel.setMinimumSize(new Dimension(700,300));
            ownerPanel.setMaximumSize(new Dimension(700,300));

            JPanel ownerDetailPanel = new JPanel();
            ownerDetailPanel.setLayout(new BoxLayout(ownerDetailPanel,BoxLayout.Y_AXIS));
            ownerDetailPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
            ownerDetailPanel.setBackground(new Color(240,230,140));
            ownerPanel.setPreferredSize(new Dimension(600,300));
            ownerPanel.setMinimumSize(new Dimension(600,300));
            ownerPanel.setMaximumSize(new Dimension(600,300));

            JLabel manageTitle = new JLabel ("This property is owned by : ");
            JLabel ownerProfile = new JLabel (loadImage(owner.getprofilePic(),150,150));
            ownerProfile.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel manageBy = new JLabel ("<html><p> Name : "+ owner.getUsername() +"</p><p> Email : " +owner.getEmail()+ "</p><p>Contact Number : "+owner.getContactNumber() +"</p></html>");
            JButton contactOwner = new JButton ("More Information");

            contactOwner.setBackground(new Color(255,99,71));
            contactOwner.setPreferredSize(new Dimension(180, 50));  
            contactOwner.setMinimumSize(new Dimension(180, 50));
            contactOwner.setMaximumSize(new Dimension(180, 50)); 
            contactOwner.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    new OwnerDetail(property.getUsername(),loginName);
                }
            });

            manageTitle.setFont(new Font(Font.DIALOG, Font.BOLD,  18));
            manageBy.setFont(new Font(Font.DIALOG, Font.PLAIN,  15));
            contactOwner.setFont(new Font("Verdana", Font.PLAIN, 16));

            ownerDetailPanel.add(manageTitle);
            ownerDetailPanel.add(ownerProfile);
            ownerDetailPanel.add(manageBy);
            ownerDetailPanel.add(contactOwner);
            ownerPanel.add(ownerDetailPanel);
            contactOwner.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

            ownerPanel.setBorder(border);
            ownerBackground.add(ownerPanel);
            centerPanel.add(ownerBackground);

        }else{
            //AGENT PANEL 
            JPanel agentBackground = new JPanel();
            agentBackground.setLayout(new BoxLayout(agentBackground,BoxLayout.Y_AXIS));
            agentBackground.setPreferredSize(new Dimension(1518,350));
            agentBackground.setMinimumSize(new Dimension(1518,350));
            agentBackground.setMaximumSize(new Dimension(1518,350));

            JPanel agentPanel = new JPanel();
            agentPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
            agentPanel.setBackground(new Color(240,230,140));
            agentPanel.setPreferredSize(new Dimension(700,300));
            agentPanel.setMinimumSize(new Dimension(700,300));
            agentPanel.setMaximumSize(new Dimension(700,300));

            JPanel agentDetailPanel = new JPanel();
            agentDetailPanel.setLayout(new BoxLayout(agentDetailPanel,BoxLayout.Y_AXIS));
            agentDetailPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
            agentDetailPanel.setBackground(new Color(240,230,140));
            agentPanel.setPreferredSize(new Dimension(600,300));
            agentPanel.setMinimumSize(new Dimension(600,300));
            agentPanel.setMaximumSize(new Dimension(600,300));

            JLabel manageTitle = new JLabel("This property is managed by :");
            JLabel agentProfile = new JLabel (loadImage(agent.getprofilePic(),150,150));
            agentProfile.setAlignmentX(Component.LEFT_ALIGNMENT);
            JLabel manageBy = new JLabel ("<html><p> Agent : "+ agent.getUsername() +"</p><p> Agency : " +agent.getAgency()+ "</p><p>Contact Number : "+agent.getContactNumber() +"</p></html>");
            JButton contactAgent = new JButton ("More Information");

            contactAgent.setBackground(new Color(255,99,71));
            contactAgent.setPreferredSize(new Dimension(180, 50));  
            contactAgent.setMinimumSize(new Dimension(180, 50));
            contactAgent.setMaximumSize(new Dimension(180, 50)); 
            contactAgent.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    new AgentDetail(property.getUsername(), loginName);
                }
            });

            manageTitle.setFont(new Font(Font.DIALOG, Font.BOLD,  18));
            manageBy.setFont(new Font(Font.DIALOG, Font.PLAIN,  15));
            contactAgent.setFont(new Font("Verdana", Font.PLAIN, 16));

            agentDetailPanel.add(manageTitle);
            agentDetailPanel.add(agentProfile);
            agentDetailPanel.add(manageBy);
            agentDetailPanel.add(contactAgent);
            agentPanel.add(agentDetailPanel);
            contactAgent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

            agentPanel.setBorder(border);
            agentBackground.add(agentPanel);
            centerPanel.add(agentBackground);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //COMMENT
        Property updatedComment = loadAgentOwnerPropertyData(property.getPropertyID());
        String[] commentArray = updatedComment.getComment().split("\\|");

        // COMMENT BACKGROUND
        JPanel commentBackground = new JPanel(new BorderLayout());
        commentBackground.setBackground(new Color(214,214,214));
        commentBackground.setPreferredSize(new Dimension(1500,400));
        commentBackground.setMinimumSize(new Dimension(1500,400));

        // COMMENTS CONTENT TEXT AREA
        JTextArea commentsTextArea = new JTextArea();
        commentsTextArea.setEditable(false);
        commentsTextArea.setLayout(new BoxLayout(commentsTextArea,BoxLayout.Y_AXIS));
        commentsTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        commentsTextArea.setBackground(Color.WHITE);
        commentsTextArea.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        JScrollPane commentsTextAreaScrollPane = new JScrollPane(commentsTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // DROP COMMENT PANEL
        JPanel dropCommentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dropCommentPanel.setBackground(Color.WHITE);
        dropCommentPanel.setPreferredSize(new Dimension(1500,40));

        // COMMENT TITLE
        JLabel commentTitle = new JLabel (commentArray[0]);
        commentTitle.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        commentsTextArea.add(commentTitle);

        // COMMENT CONTENT
        for(int i = 1; i < commentArray.length; i++){
                commentsTextArea.append("\n"+commentArray[i]);
            commentsTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  15));
        }

        if (loginName.equals("")){
            JLabel loginFirst = new JLabel ("Login to drop comment here !");
            loginFirst.setFont(new Font(Font.DIALOG, Font.BOLD,  20));
            dropCommentPanel.add(loginFirst);

        }else{
            JLabel dropCommentLabel = new JLabel("Drop your comment here : ");
            JTextField dropCommentTxtField = new JTextField(45);
            JButton commentSubmitButton = new JButton ("Submit");
            commentSubmitButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    updatePropertyComment(property, dropCommentTxtField, loginName);
                    centalPanelFunc(frame, centerPanel, property, loginName);
                    frame.setVisible(true);
                }
            });
            dropCommentPanel.add(dropCommentLabel);
            dropCommentPanel.add(dropCommentTxtField);
            dropCommentPanel.add(commentSubmitButton);
            dropCommentLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  16));
        }
        commentBackground.add(commentsTextAreaScrollPane,BorderLayout.CENTER); 
        commentBackground.add(dropCommentPanel,BorderLayout.SOUTH);  
        centerPanel.add(commentBackground);
    }

    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong, Tan Sin Zhung
    Method's Name   : updatePropertyComment
    Purpose         : This method is used to update the comment of a property after user commented.
    ************************************************************************************************************/ 
    public void updatePropertyComment(Property property,  JTextField dropCommentTxtField, String loginName){
        String comment = loginName + ": " + dropCommentTxtField.getText();
        ArrayList<String> detailsArraylist = new ArrayList<>();
        try{
            File myFile = new File("propertyDetails.csv");
            FileWriter myFileWriter = new FileWriter(myFile, true);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] detailsArray = br.readLine().split(","); 
                if (detailsArray[2].equals(property.getPropertyID())){   
                    detailsArray[27] += comment + "|";  
                }
                String details = String.join(",",detailsArray);
                detailsArraylist.add(details);
            }          
            FileWriter tempFileWriter = new FileWriter(myFile, false);
            br.close();
            for(String s : detailsArraylist){
                tempFileWriter.append(s+"\n");
            }
            myFileWriter.close();
            tempFileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
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
    
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : loadAgentOwnerPropertyData
    Purpose         : This method is to load the data of property listed by owner and agent and return as Property.
    ************************************************************************************************************/    
    //LOAD LOGIN AGENT/OWNER'S PROPERTY
    public Property loadAgentOwnerPropertyData(String propertyID){
        try{
            File myFile = new File("propertyDetails.csv");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(",");
                if(details[2].equals(propertyID)){
                    br.close();
                    return new Property(details[0],details[1],details[2],details[3],details[4],details[5], 
                                                    details[6],details[7],details[8],details[9],details[10],details[11],details[12],
                                                    details[13],details[14],details[15],details[16],details[17],details[18],
                                                    details[19],details[20],details[21],details[22],details[23],details[24],details[25],details[26],details[27]); // add property data into arraylist
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
    Method's Name   : loadUser
    Purpose         : This method is to load the data of owner/agent and save as owner/agent.
    ************************************************************************************************************/  
    public void loadUser(String name){
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(name.equals(details[2])){ //find user
                    if(details[0].equals("Owner")) {//see owner
                        owner = new Owner(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8]);
                    }
                    else if(details[0].equals("Agent")){
                        agent = new Agent(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] ,details[9],details[10],details[11],details[12]);
                    }
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
