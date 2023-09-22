import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : YourListingPanel
Design Pattern  : Singleton
Purpose         : This class is to display your listing page in the owner/agent menu.
************************************************************************************************************/ 
public class YourListingPanel{
    // Aggregation from Property
    JFrame frame;
    Integer[] index; 
    Boolean[] facilitiesCheck;
    JPanel listingPanel;
    JScrollPane scrollPane;
    ArrayList<Property>propertyDetailsArrayList = new ArrayList<Property>();
    ArrayList<String> ListingDetailsArrayList = new ArrayList<String>();
    String username;
    String role;

    public YourListingPanel(JFrame frame , Integer[] index , Boolean[] facilitiesCheck, JPanel listingPanel, JScrollPane scrollPane, String username, String role){
        this.frame = frame;
        this.index = index;
        this.facilitiesCheck = facilitiesCheck;
        this.listingPanel = listingPanel;
        this.scrollPane = scrollPane;
        this.username = username;
        this.role = role;

        loadAgentOwnerPropertyData();
        
        ArrayList<String>temp = new ArrayList<>();
        temp.add("Any Developer");
        for(int i = 1 ; i <= propertyDetailsArrayList.size(); i++){
            temp.add(propertyDetailsArrayList.get(i-1).getTownshipDeveloper());
        }

        ArrayList<String>taman = new ArrayList<>();
        for(int i = 0 ; i < temp.size() ; i++){
            if(!taman.contains(temp.get(i))){
                taman.add(temp.get(i));
            }
        }
        Collections.sort(taman);
        String[] tamanProject = taman.toArray(new String[0]);

        listingPanel(tamanProject);
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : listingPanel
    Purpose         : This method to display all the owner/agent listed property details.
    ************************************************************************************************************/
    public void listingPanel(String tamanProject[]){
        listingPanel.removeAll();
        listingPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //TITLE PANEL   
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setPreferredSize(new Dimension(1400,50));
        JLabel listing = new JLabel("Listings");
        listing.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        titlePanel.add(listing);
        titlePanel.add(Box.createRigidArea(new Dimension(1220, 0)));

        //LIST PROPERTY BUTTON
        String propertyArray[] = {"Property Type", "Township/Project Name", "Listing Reference Number",    
        "Building Name", "State", "City", "Post Code", "Road Name", "Block", "Unit/House no", "Floor",
        "Rental Price (per month)", "Built-Up Area(sq.ft)","Tenure","Furnishing", "Number of Bedroom", "Number of Bathroom", "Number of Parking",  
        "Property Facilities", "Property Features",
        "Property Description", "Property Images"}; 
        JButton listPropertyButton = new JButton("List Property");
        listPropertyButton.setMaximumSize(new Dimension(140,50));
        listPropertyButton.setBackground(new Color(30,144,255));
        listPropertyButton.setForeground(Color.white);
        listPropertyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                listingPanel.removeAll();
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    
                JTabbedPane listPropertyTabbedPane = new JTabbedPane();
                JPanel listingDetailsPanel = new JPanel(new BorderLayout());
                JPanel locationPanel = new JPanel(new BorderLayout());
                JPanel propertyDetailsPanel = new JPanel(new BorderLayout());
                JPanel propertyFacilitiesAndFeaturesPanel = new JPanel(new BorderLayout());
                JPanel descriptionImagesPanel = new JPanel(new BorderLayout());
                listPropertyTabbedPane.add(listingDetailsPanel, "1.Listing Details");
                listPropertyTabbedPane.add(locationPanel, "2.Location");
                listPropertyTabbedPane.add(propertyDetailsPanel, "3.Property Details");
                listPropertyTabbedPane.add(propertyFacilitiesAndFeaturesPanel, "4.Property Facilities And Features");
                listPropertyTabbedPane.add(descriptionImagesPanel, "5.Description and Media");
                listingDetailsPanelMethod(listingDetailsPanel, locationPanel, listPropertyTabbedPane, propertyArray);
                propertyDetailsPanelMethod(propertyDetailsPanel, listPropertyTabbedPane, propertyArray);
                propertyFacilitiesAndFeaturesPanelMethod(propertyFacilitiesAndFeaturesPanel, listPropertyTabbedPane, propertyArray);
                descriptionImagesPanelMethod(frame,descriptionImagesPanel, listingPanel, scrollPane, listPropertyTabbedPane, index, propertyArray, username, role);
                listingPanel.add(listPropertyTabbedPane, BorderLayout.CENTER);
            }

        });
        titlePanel.add(listPropertyButton);
        listingPanel.add(titlePanel);
        listingPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //FILTER
        JPanel filterPanel = new JPanel(new GridLayout(1,8));
        filterPanel.setBackground(Color.WHITE);
        String propertyTypeList[] = {"Any Property Type", "Apartment" , "Condominium", "Flat", "Studio", "Terraced House", "Bangalow", "Semi Detached","Villa"};
        String priceList[] = {"Any Price","100 - 499","500 - 999", "1000 - 1499","1500 - 1999","2000 - 2499","2500 - 2999","3000 - 3499", "3500 - 3999", "4000 - 4499", "4500 - 4999","5000 - 5499","5500 - 5999"};
        String rentalRate[] = {"Any Rate","0.01 - 0.50","0.51 - 1.00","1.01 - 1.50","1.51 - 2.00","2.01 - 2.50","2.51 - 3.00","3.01 - 3.50","3.51 - 4.00","4.01 - 4.50","4.51 - 5.00","More than 5"};
        String bedroomList[] = {"Any Bedrooms","Studio","1 Bedroom","2 Bedroom","3 Bedroom","4 Bedroom","5+ Bedroom"};
        String bathroomList[] = {"Any Bathrooms","1 Bathroom","2 Bathroom","3 Bathroom","4 Bathroom","5+ Bathroom"};
        String carParkList[] = {"Any Car Parks","0","1","2","3","4+"};
        String furnishedList[] = {"Any Furnishing","Fully Furnished", "Partly Furnished","Unfurnished"};
        String sortByList[] = {"Sort By" , "Sort By Price High To Low","Sort By Price Low To High", "Sort By Rental Rate High To Low","Sort By Rental Rate Low To High"};
        
        JComboBox<String> propertyTypeCB = new JComboBox<String>(propertyTypeList);
        JComboBox<String> priceCB = new JComboBox<String>(priceList);
        JComboBox<String> rentalRateCB = new JComboBox<String>(rentalRate);
        JComboBox<String> bedroomCB = new JComboBox<String>(bedroomList);
        JComboBox<String> bathroomCB = new JComboBox<String>(bathroomList);
        JComboBox<String> carParkCB = new JComboBox<String>(carParkList);
        JComboBox<String> furnishedCB = new JComboBox<String>(furnishedList);
        JComboBox<String> sortByCB = new JComboBox<String>(sortByList);
        JComboBox<String> townshipDeveloperCB = new JComboBox<String>(tamanProject);
        propertyTypeCB.setSelectedIndex(index[0]);
        priceCB.setSelectedIndex(index[1]);
        rentalRateCB.setSelectedIndex(index[2]);
        bedroomCB.setSelectedIndex(index[3]);
        bathroomCB.setSelectedIndex(index[4]);
        carParkCB.setSelectedIndex(index[5]);
        furnishedCB.setSelectedIndex(index[6]);
        sortByCB.setSelectedIndex(index[7]);
        townshipDeveloperCB.setSelectedIndex(index[8]);        
        propertyTypeCB.setBackground(Color.WHITE);
        priceCB.setBackground(Color.WHITE);
        rentalRateCB.setBackground(Color.WHITE);
        bedroomCB.setBackground(Color.WHITE);
        bathroomCB.setBackground(Color.WHITE);
        carParkCB.setBackground(Color.WHITE);
        furnishedCB.setBackground(Color.WHITE);
        sortByCB.setBackground(Color.WHITE);
        townshipDeveloperCB.setBackground(Color.WHITE);
        filterPanel.add(propertyTypeCB);
        filterPanel.add(priceCB);
        filterPanel.add(rentalRateCB);
        filterPanel.add(bedroomCB);
        filterPanel.add(bathroomCB);
        filterPanel.add(carParkCB);
        filterPanel.add(furnishedCB);
        filterPanel.add(sortByCB);
        filterPanel.add(townshipDeveloperCB);
        String[] activityArr = {"All","Active","Inactive"};
        JComboBox<String> activity = new JComboBox<String>(activityArr);
        activity.setSelectedIndex(index[9]);
        listingPanel.add(filterPanel);
        filterPanel.setPreferredSize(new Dimension(1500,50));
        filterPanel.setMaximumSize(new Dimension(1500,50));
        
        JPanel label = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label.setBackground(new Color(214,214,214));
        JLabel facilitiesLabel = new JLabel(" Facilities");
        label.setPreferredSize(new Dimension(1500,25));
        label.setMaximumSize(new Dimension(1500,25));
        label.add(facilitiesLabel);
        //Filter By facilities
        JPanel facilitiesPanel = new JPanel(new GridLayout(2,8));
        facilitiesPanel.setBackground(new Color(214,214,214));
        facilitiesPanel.setPreferredSize(new Dimension(1500,60));
        facilitiesPanel.setMaximumSize(new Dimension(1500,60));
        ArrayList<JCheckBox>facilitiesCheckBox = new ArrayList<>();
        String[] facilities = {"24-Hours Security", "Parking", "Club House","BBQ", "Gymnasium", "Jogging Track", "Playground", 
        "Swimming Pool", "Wading Pool", "Jacuzzi", "Sauna", "Tennis Court", "Badminton Court", "Basketball Court", "Squash Court"};;
        for(int i = 0 ; i < facilities.length ; i ++){
            JCheckBox checkBox = new JCheckBox(facilities[i],facilitiesCheck[i]);
            checkBox.setBackground(new Color(214,214,214));
            facilitiesCheckBox.add(checkBox);
            checkBox.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB, carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB,rentalRateCB);
                    listingPanel(tamanProject);
                    frame.setVisible(true);
                }
            });         
            facilitiesPanel.add(facilitiesCheckBox.get(i));

        }
        listingPanel.add(label);
        listingPanel.add(facilitiesPanel);

        //SORTING
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setPreferredSize(new Dimension(1500,35));
        sortPanel.setMaximumSize(new Dimension(1500,35));
        sortPanel.add(new JLabel("  Property Status = > "));
        sortPanel.add(activity);
        listingPanel.add(sortPanel);

        activity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        propertyTypeCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        priceCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        rentalRateCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        bedroomCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        bathroomCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        carParkCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        furnishedCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        sortByCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        townshipDeveloperCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                listingPanel(tamanProject);
                frame.setVisible(true);
            }
        });

        listingPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        // LISTED PROPERTY
        for(int i = propertyDetailsArrayList.size()-1 ; i >= 0 ; i--){
            Property property = propertyDetailsArrayList.get(i);
            User propertyManager = loadUser(property.getUsername());
            JButton btn = new JButton();
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
        
            JPanel panel = new JPanel(); //Big Box
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            //Agent
            JPanel agentOwnerPanel = new JPanel();
            JLabel agentOwnerPic = new JLabel(loadImage(propertyManager.getprofilePic(),60,60));
            JLabel agentOwnerName = new JLabel("  "+propertyManager.getUsername());
            JButton activeInactiveButton = new JButton();
            agentOwnerPanel.setLayout(new BoxLayout(agentOwnerPanel, BoxLayout.X_AXIS));
            agentOwnerName.setFont(new Font("Comic Sans MS", Font.PLAIN,  25));
            agentOwnerPanel.add(Box.createRigidArea(new Dimension(8, 70)));
            agentOwnerPanel.add(agentOwnerPic);
            agentOwnerPanel.add(Box.createRigidArea(new Dimension(15, 0)));
            agentOwnerPanel.add(agentOwnerName);
            agentOwnerPanel.add(Box.createRigidArea(new Dimension(950, 0)));
            activeInactiveButton.setMaximumSize(new Dimension(140,50));
            if(property.getPropertyStatus().equals("true"))
                activeInactiveButton.setText("Active");
            else
                activeInactiveButton.setText("Inactive");

            activeInactiveButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (activeInactiveButton.getText().equals("Active")){
                        updatePropertyStatus(property,"false");
                        activeInactiveButton.setText("Inactive");
                    }
                    else{
                        updatePropertyStatus(property,"true");
                        activeInactiveButton.setText("Active");
                    }
                    new YourListingPanel(frame, index, facilitiesCheck, listingPanel, scrollPane, username, role);
                    frame.setVisible(true);
                }
            });
            agentOwnerPanel.add(activeInactiveButton);
            agentOwnerPanel.add(Box.createRigidArea(new Dimension(20, 0)));
            //PROPERTY
            JPanel propertyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            //IMAGE
            String[] propertyImage = property.getPropertyImages().split("\\|");
            JLabel image = new JLabel(loadImage(propertyImage[0],380,270));
            //PROPERTY DETAIL
            JPanel propertyDetail = new JPanel();
            propertyDetail.setBackground(Color.WHITE);
            propertyDetail.setLayout(new BoxLayout(propertyDetail, BoxLayout.LINE_AXIS));
            JPanel detailLeft = new JPanel();
            detailLeft.setLayout(new BoxLayout(detailLeft, BoxLayout.PAGE_AXIS));
            JPanel detailRight = new JPanel(new GridLayout(3,2));

            //LEFT
            JLabel name = new JLabel(property.getBuildingName());
            JLabel price = new JLabel("RM"+property.getRentalPrice()+" per Month");
            JLabel location = new JLabel(property.getRoadName());
            JLabel detail = new JLabel(property.getBuiltUpArea()+" sqft    RM " + property.getRentalRate() + " psf");
            JLabel type = new JLabel(">" + property.getPropertyType() + "  >" + property.getFurnishing());
            name.setFont(new Font("Comic Sans MS", Font.BOLD,  27));
            location.setFont(new Font("Comic Sans MS", Font.PLAIN,  17));
            price.setFont(new Font("Comic Sans MS", Font.PLAIN,  20));
            detail.setFont(new Font("Comic Sans MS", Font.PLAIN,  17));
            type.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            detailLeft.add(name);
            detailLeft.add(Box.createRigidArea(new Dimension(0, 10)));
            detailLeft.add(location);
            detailLeft.add(price);
            detailLeft.add(detail);
            detailLeft.add(Box.createRigidArea(new Dimension(0, 20)));
            detailLeft.add(type);
            
            //RIGHT
            String bed;
            String bath;
            String cPark;
            if(Boolean.valueOf(property.getPropertyStatus())){
                bed = "activeBed.jpg";
                bath = "activeBath.jpg";
                cPark = "activeCP.jpg";
            }else{
                bed = "inactiveBed.jpg";
                bath = "inactiveBath.jpg";
                cPark = "inactiveCP.jpg";
            }
            JLabel bedroomLogo = new JLabel(loadImage(bed, 40,40));
            JLabel bathroomLogo = new JLabel(loadImage(bath,40,40));
            JLabel parkingLogo = new JLabel(loadImage(cPark,40,40));

            JLabel bedroom = new JLabel("  "+property.getNumberOfBedroom());
            JLabel bathroom = new JLabel("  "+property.getNumberOfBathroom());
            JLabel parking = new JLabel("  "+property.getNumberOfParking());
            bedroom.setFont(new Font("Comic Sans MS", Font.PLAIN,  25));
            bathroom.setFont(new Font("Comic Sans MS", Font.PLAIN,  25));
            parking.setFont(new Font("Comic Sans MS", Font.PLAIN,  25));
            detailRight.add(bedroomLogo);
            detailRight.add(bedroom);
            detailRight.add(bathroomLogo);
            detailRight.add(bathroom);
            detailRight.add(parkingLogo);
            detailRight.add(parking);

            propertyDetail.add(Box.createRigidArea(new Dimension(80, 0)));
            propertyDetail.add(detailLeft);
            propertyDetail.add(Box.createRigidArea(new Dimension(80, 0)));
            propertyDetail.add(detailRight);
            propertyDetail.add(Box.createRigidArea(new Dimension(80, 0)));
            
            propertyPanel.add(image);
            propertyPanel.add(propertyDetail);

            if(Boolean.valueOf(property.getPropertyStatus())){
                agentOwnerPanel.setBackground(new Color(127, 197, 255));
                propertyPanel.setBackground(new Color(206,233,255));
                propertyDetail.setBackground(new Color(206,233,255));
                detailLeft.setBackground(new Color(206,233,255));
                detailRight.setBackground(new Color(206,233,255));
            }
            else{
                agentOwnerPanel.setBackground(new Color(221,133,134));
                propertyPanel.setBackground(new Color(229,208,208));
                propertyDetail.setBackground(new Color(229,208,208));
                detailLeft.setBackground(new Color(229,208,208));
                detailRight.setBackground(new Color(229,208,208));
            }
            
            panel.add(agentOwnerPanel);
            panel.add(propertyPanel);
            btn.add(panel);
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    new PropertyDetail(property, username);
                }
            });

            btn.setMaximumSize(new Dimension(1300, Short.MAX_VALUE));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            listingPanel.add(btn);
        }
    }
    
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : filterFunction
    Purpose         : This method is process the filter function of the owner/agent listed property.
    ************************************************************************************************************/
    //FILTER
    public void filterFunction(String tamanProject[], ArrayList<JCheckBox>facilitiesCheckBox, JComboBox<String> propertyTypeCB, JComboBox<String> priceCB, JComboBox<String> bedroomCB, JComboBox<String> bathroomCB, JComboBox<String> carParkCB, JComboBox<String> furnishedCB,JComboBox<String> sortByCB, JComboBox<String>activityCB ,  JComboBox<String>townDevCB,JComboBox<String> rentalRateCB){
        String propertyType = cbConvertToString(propertyTypeCB);    this.index[0] = propertyTypeCB.getSelectedIndex();
        String price = cbConvertToString(priceCB);                  this.index[1] = priceCB.getSelectedIndex();
        String rentalRate = cbConvertToString(rentalRateCB);        this.index[2] = rentalRateCB.getSelectedIndex();
        String bedroom = cbConvertToString(bedroomCB);              this.index[3] = bedroomCB.getSelectedIndex();
        String bathroom = cbConvertToString(bathroomCB);            this.index[4] = bathroomCB.getSelectedIndex();
        String carPark = cbConvertToString(carParkCB);              this.index[5] = carParkCB.getSelectedIndex();
        String furnished = cbConvertToString(furnishedCB);          this.index[6] = furnishedCB.getSelectedIndex();
        String sortBy = cbConvertToString(sortByCB);                this.index[7] = sortByCB.getSelectedIndex();
        String townDev = cbConvertToString(townDevCB);              this.index[8] = townDevCB.getSelectedIndex();
        String activity = (String)activityCB.getSelectedItem();     this.index[9] = activityCB.getSelectedIndex();
        for(int i = 0 ; i < facilitiesCheck.length; i++){
            if(facilitiesCheckBox.get(i).isSelected()){
                this.facilitiesCheck[i] = true;
            }
            else{
                this.facilitiesCheck[i] = false;
            }
        }
        int minPrice = 0;
        int maxPrice = 0;
        if(!price.equals("Any")){
            String[] priceArray = price.split(" "); 
            minPrice = Integer.valueOf(priceArray[0]);
            maxPrice = Integer.valueOf(priceArray[2]);
        }
        Float minRRate = 0f;
        Float maxRRate = 0f;
        if(!(rentalRate.equals("Any") || rentalRate.equals("More than 5"))){
            String[] priceArray = rentalRate.split(" "); 
            minRRate = Float.valueOf(priceArray[0]);
            maxRRate = Float.valueOf(priceArray[2]);
        }
        String numOfBed = "";
        if(!bedroom.equals("Any") || !bedroom.equals("5+ Bedroom")|| !bedroom.equals("Studio")){
            String[] array = bedroom.split(" "); 
            numOfBed = array[0];
        }
        String numOfBath = "";
        if(!bathroom.equals("Any") || !bathroom.equals("5+ Bathroom")){
            String[] array = bathroom.split(" "); 
            numOfBath = array[0];
        }
        String numOfCP = "";
        if(!carPark.equals("Any") || !carPark.equals("4+")){
            String[] array = carPark.split(" "); 
            numOfCP = array[0];
        }
        loadAgentOwnerPropertyData();
        ArrayList<Property>temp = new ArrayList<Property>();
        for(int i = 0 ; i < propertyDetailsArrayList.size(); i++){
            Boolean filter = true; //default is true
            Property currentProperty = propertyDetailsArrayList.get(i);

            //facilities
            String[] faci = currentProperty.getPropertyFacilities().split("\\|");
            for(int j = 0 ; j < facilitiesCheckBox.size(); j++){
                if(facilitiesCheckBox.get(j).isSelected()){
                    for(int k = 0 ;k < faci.length; k++){
                        if(facilitiesCheckBox.get(j).getText().equals(faci[k])){ filter = true; break;}
                        else {filter = false;}
                    }
                }
                if(!filter)
                    break;
            }
            
            if(propertyType.equals("Any")){} //Property Type
            else if (!currentProperty.getPropertyType().equals(propertyType))
                filter = false;
            if(price.equals("Any")){} //Price
            else if (!((Integer.valueOf(currentProperty.getRentalPrice()) >= minPrice) && (Integer.valueOf(currentProperty.getRentalPrice()) <= maxPrice)))
                filter = false;
            if(rentalRate.equals("Any")){} //Rental Rate
            else if (rentalRate.equals("More than 5")){
                if(!(Float.valueOf(currentProperty.getRentalRate()) >= 5))
                    filter = false;
            }
            else if (!((Float.valueOf(currentProperty.getRentalRate()) >= minRRate) && (Float.valueOf(currentProperty.getRentalRate()) <= maxRRate)))
                filter = false;
                
            if(bedroom.equals("Any")){} //Bedroom
            else if (bedroom.equals("5+ Bedroom")){
                if(currentProperty.getNumberOfBedroom().equals("Studio"))
                    filter = false;
                else if(!(Float.valueOf(currentProperty.getNumberOfBedroom()) >= 5))
                    filter = false;
            }
            else if (bedroom.equals("Studio")){
                if(!(currentProperty.getNumberOfBedroom()).equals("Studio"))
                    filter = false;
            }
            else if (!currentProperty.getNumberOfBedroom().equals(numOfBed))
                filter = false;

            if(bathroom.equals("Any")){} //Bathroom
            else if (bathroom.equals("5+ Bathroom")){
                if(!(Float.valueOf(currentProperty.getNumberOfBathroom()) >= 5))
                    filter = false;
            }
            else if (!currentProperty.getNumberOfBathroom().equals(numOfBath))
                filter = false;

            if(carPark.equals("Any")){} //Car Park
            else if(carPark.equals("4+")){
                if(!(Float.valueOf(currentProperty.getNumberOfParking()) >= 4))
                    filter = false;
            }
            else if(!currentProperty.getNumberOfParking().equals(numOfCP))
                filter = false;

            if(furnished.equals("Any")){} //Furinishing
            else if(!currentProperty.getFurnishing().equals(furnished))
                filter = false;
            if(townDev.equals("Any")){} //Dev
            else if(!currentProperty.getTownshipDeveloper().equals(townDev))
                filter = false;

            if(filter)
                temp.add(currentProperty);
        }
        if(sortBy.equals("Sort By Price High To Low")){
            int n = temp.size();
            for (int i = 0; i < n-1; i++){
                for (int j = 0; j < n-i-1; j++){
                    if (Integer.valueOf(temp.get(j).getRentalPrice()) > Integer.valueOf(temp.get(j+1).getRentalPrice()))
                    {
                        Property temporary = temp.get(j);
                        temp.set(j, temp.get(j+1));
                        temp.set(j+1, temporary);
                    }
                }
            }
        }else if(sortBy.equals("Sort By Price Low To High")){
            int n = temp.size();
            for (int i = 0; i < n-1; i++){
                for (int j = 0; j < n-i-1; j++){
                    if (Integer.valueOf(temp.get(j).getRentalPrice()) < Integer.valueOf(temp.get(j+1).getRentalPrice()))
                    {
                        Property temporary = temp.get(j);
                        temp.set(j, temp.get(j+1));
                        temp.set(j+1, temporary);
                    }
                }
            }
        }else if(sortBy.equals("Sort By Rental Rate High To Low")){
            int n = temp.size();
            for (int i = 0; i < n-1; i++){
                for (int j = 0; j < n-i-1; j++){
                    if (Float.valueOf(temp.get(j).getRentalRate()) > Float.valueOf(temp.get(j+1).getRentalRate()))
                    {
                        Property temporary = temp.get(j);
                        temp.set(j, temp.get(j+1));
                        temp.set(j+1, temporary);
                    }
                }
            }
        }
        else if(sortBy.equals("Sort By Rental Rate Low To High")){
            int n = temp.size();
            for (int i = 0; i < n-1; i++){
                for (int j = 0; j < n-i-1; j++){
                    if (Float.valueOf(temp.get(j).getRentalRate()) < Float.valueOf(temp.get(j+1).getRentalRate()))
                    {
                        Property temporary = temp.get(j);
                        temp.set(j, temp.get(j+1));
                        temp.set(j+1, temporary);
                    }
                }
            }
        }
        propertyDetailsArrayList.clear();
        for(int i = 0 ; i < temp.size(); i++){ propertyDetailsArrayList.add(temp.get(i)); }

        for(int i = propertyDetailsArrayList.size()-1 ; i >= 0 ; i--){ 
            if(activity.equals("Active")){
                if(Boolean.valueOf(propertyDetailsArrayList.get(i).getPropertyStatus())){}// remnove inACTIVE
                else{ propertyDetailsArrayList.remove(i); }
            }
            else if(activity.equals("Inactive")){
                if(Boolean.valueOf(propertyDetailsArrayList.get(i).getPropertyStatus())){// remove ACTIVE
                    propertyDetailsArrayList.remove(i);
                }
            }
        }
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : cbConvertToString
    Purpose         : This method is convert get ComboBox text to String.
    ************************************************************************************************************/ 
    public String cbConvertToString(JComboBox<String> cb){
        String text = (String)cb.getSelectedItem();
        String[] detailsArray = text.split(" "); 
        if(detailsArray[0].equals("Any")){
            return "Any";
        }
        else
            return text;
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : listingDetailsPanelMethod
    Purpose         : This method let the owner/agent to input listing details after they want to list a new property.
    ************************************************************************************************************/
    // LISTING DETAILS PANEL
    public void listingDetailsPanelMethod(JPanel listingDetailsPanel, JPanel locationPanel, JTabbedPane listPropertyTabbedPane, String[] propertyArray){
        ArrayList<JTextField>textFieldArray = new ArrayList<JTextField>();
        ArrayList<JComboBox<String>> comboBoxArrayList = new ArrayList<JComboBox<String>>();

        listPropertyTabbedPane.setEnabledAt(1, false);
        listPropertyTabbedPane.setEnabledAt(2, false);
        listPropertyTabbedPane.setEnabledAt(3, false);
        listPropertyTabbedPane.setEnabledAt(4, false);
        
        //TITLE PANEL
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Start Listing Your Property");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 150)));

        // CENTER PANEL
        JPanel centerPanel = new JPanel(new GridLayout(3,2,18,12));
        int textFieldCount = 0;
        String propertyTypeList[] = {"Apartment" , "Condominium", "Flat", "Studio", "Terraced House", "Bangalow", "Semi Detached","Villa",};

        for(int i=0; i<propertyArray.length; i++){
            JLabel label = new JLabel(propertyArray[i]);
            if (i == 0){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                comboBoxArrayList.add(new JComboBox<String>(propertyTypeList));
                comboBoxArrayList.get(0).setBackground(Color.WHITE);
                centerPanel.add(comboBoxArrayList.get(0));
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
            }
            if (i == 1 || i == 2){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField());
                centerPanel.add(textFieldArray.get(textFieldCount));
                textFieldCount++;
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
            }
        }

        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        JButton saveAndContinueButton = new JButton("Save and Continue");
        saveAndContinueButton.setPreferredSize(new Dimension(150,60));
        saveAndContinueButton.setBackground(new Color(30,144,255));
        saveAndContinueButton.setForeground(Color.white);
        saveAndContinueButton.addActionListener(new YourListingSaveAndContinueActionListener(listPropertyTabbedPane, locationPanel,comboBoxArrayList,textFieldArray, new ArrayList<JCheckBox>(), new ArrayList<JCheckBox>(), ListingDetailsArrayList, propertyArray,1));
        southPanel.add(saveAndContinueButton);
        southPanel.add(Box.createRigidArea(new Dimension(0,280)));

        listingDetailsPanel.add(titlePanel, BorderLayout.NORTH);
        listingDetailsPanel.add(centerPanel, BorderLayout.CENTER);
        listingDetailsPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : propertyDetailsPanelMethod
    Purpose         : This method let the owner/agent to input property details after they want to list a new property.
    ************************************************************************************************************/
    // PROPERTY DETAILS PANEL
    public void propertyDetailsPanelMethod( JPanel propertyDetailsPanel, JTabbedPane listPropertyTabbedPane, String[] propertyArray){
        ArrayList<JTextField>textFieldArray = new ArrayList<JTextField>();
        ArrayList<JComboBox<String>> comboBoxArrayList = new ArrayList<JComboBox<String>>();

        //TITLE PANEL
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Give Details About Your Property");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 120)));

        // CENTER PANEL
        JPanel centerPanel = new JPanel(new GridLayout(7,2,18,2));
        int textFieldCount = 0;
        int comboBoxCount = 0;

        String tenureList[] = {"Freehold", "Listhold"};
        String furnishedList[] = {"Fully Furnished","Partly Furnished", "Unfurnished"};
        String bedroomList[] = {"Studio","1","2","3","4","5","6","7","8","9","10"};
        String bathroomList[] = {"1","2","3","4","5","6","7","8","9","10"};
        String carParkList[] = {"0","1","2","3","4","5+"};

        for(int i=0; i<propertyArray.length; i++){
            JLabel label = new JLabel(propertyArray[i]);
            if (i == 11){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField());
                centerPanel.add(textFieldArray.get(textFieldCount));
                textFieldCount++;
                centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
            }
            if (i == 12){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField()); // BUILT-UP AREA(SQ,FT)
                textFieldArray.add(new JTextField()); // ADD A PLAIN TEXT FIELD FOR BUILT-UP PRICE(PER SQ.FT)
                centerPanel.add(textFieldArray.get(textFieldCount)); // BUILT-UP AREA(SQ,FT)
                centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
            }
            if (i == 13){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                comboBoxArrayList.add(new JComboBox<String>(tenureList));
                comboBoxArrayList.get(comboBoxCount).setBackground(Color.WHITE);
                centerPanel.add(comboBoxArrayList.get(comboBoxCount));
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
                comboBoxCount++;
            }
            if (i == 14){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                comboBoxArrayList.add(new JComboBox<String>(furnishedList));
                comboBoxArrayList.get(comboBoxCount).setBackground(Color.WHITE);
                centerPanel.add(comboBoxArrayList.get(comboBoxCount));
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
                comboBoxCount++;
            }
            if (i == 15){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                comboBoxArrayList.add(new JComboBox<String>(bedroomList));
                comboBoxArrayList.get(comboBoxCount).setBackground(Color.WHITE);
                centerPanel.add(comboBoxArrayList.get(comboBoxCount));
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
                comboBoxCount++;
            }
            if (i == 16){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                comboBoxArrayList.add(new JComboBox<String>(bathroomList));
                comboBoxArrayList.get(comboBoxCount).setBackground(Color.WHITE);
                centerPanel.add(comboBoxArrayList.get(comboBoxCount));
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
                comboBoxCount++;
            }
            if (i == 17){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 25)));
                centerPanel.add(label);
                comboBoxArrayList.add(new JComboBox<String>(carParkList));
                comboBoxArrayList.get(comboBoxCount).setBackground(Color.WHITE);
                centerPanel.add(comboBoxArrayList.get(comboBoxCount));
                centerPanel.add(Box.createRigidArea(new Dimension(360, 25)));
                comboBoxCount++;
            }
        }

        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        JButton saveAndContinueButton = new JButton("Save and Continue");
        saveAndContinueButton.setPreferredSize(new Dimension(150,60));
        saveAndContinueButton.setBackground(new Color(30,144,255));
        saveAndContinueButton.setForeground(Color.white);
        saveAndContinueButton.addActionListener(new YourListingSaveAndContinueActionListener(listPropertyTabbedPane, new JPanel(), comboBoxArrayList, textFieldArray, new ArrayList<JCheckBox>(), new ArrayList<JCheckBox>(), ListingDetailsArrayList, propertyArray,3));
        southPanel.add(saveAndContinueButton);
        southPanel.add(Box.createRigidArea(new Dimension(0,280)));

        propertyDetailsPanel.add(titlePanel, BorderLayout.NORTH);
        propertyDetailsPanel.add(centerPanel, BorderLayout.CENTER);
        propertyDetailsPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : propertyFacilitiesAndFeaturesPanelMethod
    Purpose         : This method let the owner/agent to input property facilities and features details after 
                    they want to list a new property.
    ************************************************************************************************************/
    // PROPERTY FACILITIES AND FEATURES PANEL
    public void propertyFacilitiesAndFeaturesPanelMethod(JPanel propertyFacilitiesAndFeaturesPanel, JTabbedPane listPropertyTabbedPane, String[] propertyArray){
        ArrayList<JCheckBox> facilitiesCheckBoxArrayList = new ArrayList<JCheckBox>();
        ArrayList<JCheckBox> featuresCheckBoxArrayList = new ArrayList<JCheckBox>();
        
        //TITLE PANEL
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Property Facilities And Features");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 170)));

        // CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        String propertyfacilitiesList[] = {"24-Hours Security", "Parking", "Club House","BBQ", "Gymnasium", "Jogging Track", "Playground", 
        "Swimming Pool", "Wading Pool", "Jacuzzi", "Sauna", "Tennis Court", "Badminton Court", "Basketball Court", "Squash Court"};
        String propertyFeatureList[] = {"Air Conditioner", "Kitchen Cabinet", "Balcony", "Garden", "Garage", "Bath Tub", "Fridge", "Washing Machine", "Dryer"};
    
        // FACILITIES PANEL
        JPanel facilitiesPanel = new JPanel();
        JLabel facilitieslabel = new JLabel(propertyArray[18]);
        JPanel facilitiesCheckBoxPanel = new JPanel(new GridLayout(3,5,3,3));
        int facilitiesCheckBoxCount = 0;

        facilitiesPanel.setLayout(new BoxLayout(facilitiesPanel, BoxLayout.X_AXIS));
        facilitiesPanel.add(Box.createRigidArea(new Dimension(120,0)));
        facilitiesPanel.add(facilitieslabel);
        facilitiesPanel.add(Box.createRigidArea(new Dimension(50,0)));
        facilitiesCheckBoxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        for(String s : propertyfacilitiesList){
            facilitiesCheckBoxArrayList.add(new JCheckBox(s));
            facilitiesCheckBoxPanel.add(facilitiesCheckBoxArrayList.get(facilitiesCheckBoxCount));
            facilitiesCheckBoxCount++;
        }
        facilitiesPanel.add(facilitiesCheckBoxPanel);
        facilitiesPanel.add(Box.createRigidArea(new Dimension(80,0)));
        centerPanel.add(facilitiesPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        // FEATURES PANEL
        JPanel featuresPanel = new JPanel();
        JLabel featureslabel = new JLabel(propertyArray[19]);
        JPanel featuresCheckBoxPanel = new JPanel(new GridLayout(3,3,3,3));
        int featuresCheckBoxCount = 0;

        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.X_AXIS));
        featuresPanel.add(Box.createRigidArea(new Dimension(120,0)));
        featuresPanel.add(featureslabel);
        featuresPanel.add(Box.createRigidArea(new Dimension(50,0)));
        featuresCheckBoxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        for(String s : propertyFeatureList){
            featuresCheckBoxArrayList.add(new JCheckBox(s));
            featuresCheckBoxPanel.add(featuresCheckBoxArrayList.get(featuresCheckBoxCount));
            featuresCheckBoxCount++;
        }
        featuresPanel.add(featuresCheckBoxPanel);
        featuresPanel.add(Box.createRigidArea(new Dimension(80,0)));
        centerPanel.add(featuresPanel);

        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        JButton saveAndContinueButton = new JButton("Save and Continue");
        saveAndContinueButton.setPreferredSize(new Dimension(150,60));
        saveAndContinueButton.setBackground(new Color(30,144,255));
        saveAndContinueButton.setForeground(Color.white);
        saveAndContinueButton.addActionListener(new YourListingSaveAndContinueActionListener(listPropertyTabbedPane, new JPanel(), new ArrayList<JComboBox<String>>(), new ArrayList<JTextField>(), facilitiesCheckBoxArrayList, featuresCheckBoxArrayList, ListingDetailsArrayList, propertyArray,4));
        southPanel.add(saveAndContinueButton);
        southPanel.add(Box.createRigidArea(new Dimension(0,280)));

        propertyFacilitiesAndFeaturesPanel.add(titlePanel, BorderLayout.NORTH);
        propertyFacilitiesAndFeaturesPanel.add(centerPanel, BorderLayout.CENTER);
        propertyFacilitiesAndFeaturesPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : descriptionImagesPanelMethod
    Purpose         : This method let the owner/agent to input property description and images details after 
                    they want to list a new property.
    ************************************************************************************************************/
    //DESCRIPTION AND IMAGES PANEL
    public void descriptionImagesPanelMethod(JFrame frame, JPanel descriptionImagesPanel, JPanel listingPanel, JScrollPane scrollPane, JTabbedPane listPropertyTabbedPane, Integer[] index, String[] propertyArray, String username, String role){
        ArrayList<File>propertyImagesFileArrayList = new ArrayList<File>();  

        //TITLE PANEL
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("What is your property like?");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 150)));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //DESCRIPTION PANEL
        JPanel descriptionPanel = new JPanel();
        JTextArea descriptionTextArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.X_AXIS));
        descriptionTextArea.setLineWrap(true);
        descriptionPanel.add(Box.createRigidArea(new Dimension(250, 25)));
        descriptionPanel.add(new JLabel(propertyArray[20]));
        descriptionPanel.add(Box.createRigidArea(new Dimension(125,0)));
        descriptionPanel.add(descriptionScrollPane);
        descriptionPanel.add(Box.createRigidArea(new Dimension(250,25)));

        // IMAGES PANEL
        JPanel imagesPanel = new JPanel();
        JButton browseImagesButton = new JButton("Add Photo");
        JTextArea imagesFileNameTextArea = new JTextArea();
        JScrollPane imagesFileNameScrollPane = new JScrollPane(imagesFileNameTextArea);
        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.X_AXIS));
        browseImagesButton.addActionListener(new YourListingBrowseImagesButtonActionListener(propertyImagesFileArrayList,imagesFileNameTextArea,browseImagesButton));
        imagesFileNameTextArea.setEditable(false);
        imagesFileNameTextArea.setLineWrap(true);

        imagesPanel.add(Box.createRigidArea(new Dimension(250, 25)));
        imagesPanel.add(new JLabel(propertyArray[21]));
        imagesPanel.add(Box.createRigidArea(new Dimension(30,0)));
        imagesPanel.add(browseImagesButton);
        imagesPanel.add(Box.createRigidArea(new Dimension(28,0)));
        imagesPanel.add(imagesFileNameScrollPane);
        imagesPanel.add(Box.createRigidArea(new Dimension(250, 25)));

        // ADD DESCRIPTON PANEL AND IMAGES PANEL TOGETHER   
        centerPanel.add(descriptionPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,15)));
        centerPanel.add(imagesPanel);

        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(150,60));
        submitButton.setBackground(new Color(30,144,255));
        submitButton.setForeground(Color.white);
        submitButton.addActionListener(new YourListingSubmitButtonActionListener(propertyImagesFileArrayList, propertyDetailsArrayList, ListingDetailsArrayList, frame, listingPanel, scrollPane, descriptionTextArea, facilitiesCheck, index, username, role));
        southPanel.add(submitButton);
        southPanel.add(Box.createRigidArea(new Dimension(0,280)));

        descriptionImagesPanel.add(titlePanel, BorderLayout.NORTH);
        descriptionImagesPanel.add(centerPanel, BorderLayout.CENTER);
        descriptionImagesPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadPropertyData
    Purpose         : This method is to load all the property Data and written in arraylist.
    ************************************************************************************************************/ 
    //LOAD ALL PROPERTY INTO ARRAYLIST
    public void loadPropertyData(){
        propertyDetailsArrayList.clear();
        try{
            File myFile = new File("propertyDetails.csv");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                propertyDetailsArrayList.add(new Property(details[0],details[1],details[2],details[3],details[4],details[5], 
                                                details[6],details[7],details[8],details[9],details[10],details[11],details[12],
                                                details[13],details[14],details[15],details[16],details[17],details[18],
                                                details[19],details[20],details[21],details[22],details[23],details[24],details[25],details[26],details[27])); // add property data into arraylist
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadAgentOwnerPropertyData
    Purpose         : This method is to load owner's/agent's property data and written in arraylist.
    ************************************************************************************************************/ 
    //LOAD LOGIN AGENT/OWNER'S PROPERTY INTO ARRAYLIST
    public void loadAgentOwnerPropertyData(){
        propertyDetailsArrayList.clear();
        try{
            File myFile = new File("propertyDetails.csv");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(",");
                if(details[1].equals(username)){
                    propertyDetailsArrayList.add(new Property(details[0],details[1],details[2],details[3],details[4],details[5], 
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

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : updatePropertyStatus
    Purpose         : This method is to update the property status after owner/agent clicked active/inactive.
    ************************************************************************************************************/ 
    //UPDATE PROPERTY STATUS TO FILE
    public void updatePropertyStatus(Property property, String propertyStatus){
        ArrayList<String> detailsArraylist = new ArrayList<>();
        try{
            File myFile = new File("propertyDetails.csv");
            FileWriter myFileWriter = new FileWriter(myFile, true);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] detailsArray = br.readLine().split(","); 
                if (detailsArray[2].equals(property.getPropertyID())){   
                    detailsArray[3] = propertyStatus;  
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
    Programmer      : Tan Sin Zhung
    Method's Name   : loadUser
    Purpose         : This method is to load the specific user data and return as User.
    ************************************************************************************************************/ 
    public User loadUser(String name){ // load user data from csv file and return the arraylist
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(name)){
                    br.close();
                    return new User(details[0],details[1],details[2],details[3],details[4],details[5],details[6], details[7]);
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
