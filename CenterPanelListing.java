import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/***********************************************************************************************************
Class's Name    : CenterPanelListing
Design Pattern  : Singleton
Purpose         : This class is to display all the property listed.
************************************************************************************************************/ 
public class CenterPanelListing {
    JFrame frame;
    Integer[] index; 
    Boolean[] facilitiesCheck;
    JPanel centerPanel;
    String loginName;
    //Aggregation from Property.
    ArrayList<Property>propertyDetailsArrayList = new ArrayList<Property>();

    public CenterPanelListing(JFrame frame, Integer[] index, Boolean[] facilitiesCheck, JPanel centerPanel, String loginName){
        this.frame = frame;
        this.index = index;
        this.facilitiesCheck = facilitiesCheck;
        this.centerPanel = centerPanel;
        this.loginName = loginName;

        loadPropertyData();

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

        centerPanel(tamanProject);
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : centerPanel
    Purpose         : This method display the filter panel and all the listed property.
    ************************************************************************************************************/
    public void centerPanel(String tamanProject[]){
        centerPanel.removeAll();
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
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
        centerPanel.add(filterPanel);
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
                    filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB, carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                    centerPanel(tamanProject);
                    frame.setVisible(true);
                }
            });         
            facilitiesPanel.add(facilitiesCheckBox.get(i));

        }
        centerPanel.add(label);
        centerPanel.add(facilitiesPanel);

        //SORTING
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setPreferredSize(new Dimension(1500,35));
        sortPanel.setMaximumSize(new Dimension(1500,35));
        sortPanel.add(new JLabel("  Property Status = > "));
        sortPanel.add(activity);
        centerPanel.add(sortPanel);

        
        activity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        propertyTypeCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        priceCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        rentalRateCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        bedroomCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        bathroomCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        carParkCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        furnishedCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        sortByCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });
        townshipDeveloperCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterFunction(tamanProject, facilitiesCheckBox, propertyTypeCB, priceCB, bedroomCB, bathroomCB,
                        carParkCB, furnishedCB, sortByCB, activity, townshipDeveloperCB, rentalRateCB);
                centerPanel(tamanProject);
                frame.setVisible(true);
            }
        });

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        //DISPLAY ALL THE LISTED PROPERTY
        for(int i = propertyDetailsArrayList.size()-1 ; i >= 0 ; i--){
            Property current = propertyDetailsArrayList.get(i);
            User propertyManager = loadUser(current.getUsername());
            JButton btn = new JButton();
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);

            JPanel panel = new JPanel(); //Big Box
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            //Agent
            JPanel agentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel agentPic = new JLabel(loadImage(propertyManager.getprofilePic(),60,60));
            JLabel agentName = new JLabel("  "+propertyManager.getUsername());
            agentName.setFont(new Font("Comic Sans MS", Font.PLAIN,  25));
            agentPanel.add(agentPic);
            agentPanel.add(agentName);
            //PROPERTY
            JPanel propertyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            //IMAGE
            String[] propertyImage = current.getPropertyImages().split("\\|");
            JLabel image = new JLabel(loadImage(propertyImage[0],380,250));
            //PROPERTY DETAIL
            JPanel propertyDetail = new JPanel();
            propertyDetail.setBackground(Color.WHITE);
            propertyDetail.setLayout(new BoxLayout(propertyDetail, BoxLayout.LINE_AXIS));
            JPanel detailLeft = new JPanel();
            detailLeft.setLayout(new BoxLayout(detailLeft, BoxLayout.PAGE_AXIS));
            JPanel detailRight = new JPanel(new GridLayout(3,2));

            //LEFT
            JLabel name = new JLabel(current.getBuildingName());
            JLabel price = new JLabel("RM"+current.getRentalPrice()+" per Month");
            JLabel location = new JLabel(current.getRoadName());
            JLabel detail = new JLabel(current.getBuiltUpArea()+" sqft    RM " + current.getRentalRate() + " psf");
            JLabel type = new JLabel(">" + current.getPropertyType() + "  >" + current.getFurnishing());
            name.setFont(new Font("Comic Sans MS", Font.BOLD,  27));
            location.setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
            price.setFont(new Font("Comic Sans MS", Font.PLAIN,  17));
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
            if(Boolean.valueOf(current.getPropertyStatus())){
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

            JLabel bedroom = new JLabel("  "+current.getNumberOfBedroom());
            JLabel bathroom = new JLabel("  "+current.getNumberOfBathroom());
            JLabel parking = new JLabel("  "+current.getNumberOfParking());
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

            if(Boolean.valueOf(current.getPropertyStatus())){
                agentPanel.setBackground(new Color(127, 197, 255));
                propertyPanel.setBackground(new Color(206,233,255));
                propertyDetail.setBackground(new Color(206,233,255));
                detailLeft.setBackground(new Color(206,233,255));
                detailRight.setBackground(new Color(206,233,255));
            }
            else{
                agentPanel.setBackground(new Color(221,133,134));
                propertyPanel.setBackground(new Color(229,208,208));
                propertyDetail.setBackground(new Color(229,208,208));
                detailLeft.setBackground(new Color(229,208,208));
                detailRight.setBackground(new Color(229,208,208));
            }
            
            panel.add(agentPanel);
            panel.add(propertyPanel);
            btn.add(panel);
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent evt){
                    new PropertyDetail(current, loginName);
                }
            });

            btn.setMaximumSize(new Dimension(1300, Short.MAX_VALUE));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerPanel.add(btn);
        }
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : loadPropertyData
    Purpose         : This method is to load all the property details from csv file and save as arraylist
    ************************************************************************************************************/    
    public void loadPropertyData(){
        propertyDetailsArrayList.clear();
        File myFile = new File("propertyDetails.csv");
        try{
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
    Programmer      : Leong Yi Hong
    Method's Name   : loadUser
    Purpose         : This method is to load all the specific user data and return as User
    ************************************************************************************************************/    
    public User loadUser(String name){
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
    Programmer      : Leong Yi Hong
    Method's Name   : loadImage
    Purpose         : This method is to load images.
    ************************************************************************************************************/ 
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : filterFunction
    Purpose         : This method is process the filter function of all the property.
    ************************************************************************************************************/ 
    //FILTER
    public void filterFunction(String tamanProject[], ArrayList<JCheckBox>facilitiesCheckBox, JComboBox<String> propertyTypeCB, JComboBox<String> priceCB, JComboBox<String> bedroomCB, JComboBox<String> bathroomCB, JComboBox<String> carParkCB, JComboBox<String> furnishedCB,JComboBox<String> sortByCB, JComboBox<String>activityCB ,  JComboBox<String>townDevCB , JComboBox<String> rentalRateCB){
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
        loadPropertyData();
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
    Programmer      : Leong Yi Hong
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
}
