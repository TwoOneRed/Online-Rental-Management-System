import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/***********************************************************************************************************
Class's Name    : YourListingSubmitButtonActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the agent/owner click submit at
                the list a new property part.
************************************************************************************************************/ 
public class YourListingSubmitButtonActionListener implements ActionListener {     //SUBMIT BUTTON IN ADD PROPERTY
    // Aggregation from Property
    ArrayList<File>propertyImagesFileArrayList; 
    ArrayList<Property> propertyDetailsArrayList;
    ArrayList<String> listingDetailsArrayList;
    JFrame frame;
    JPanel listingPanel;
    JScrollPane scrollPane;
    JTextArea descriptionTextArea;
    Boolean[] facilitiesCheck;
    Integer[] index;
    String username;
    String role;
    public YourListingSubmitButtonActionListener(ArrayList<File>propertyImagesFileArrayList, ArrayList<Property> propertyDetailsArrayList, ArrayList<String> listingDetailsArrayList, JFrame frame, JPanel listingPanel, JScrollPane scrollPane, JTextArea descriptionTextArea, Boolean[] facilitiesCheck,Integer[] index, String username, String role){
        this.propertyImagesFileArrayList = propertyImagesFileArrayList;
        this.propertyDetailsArrayList = propertyDetailsArrayList;
        this.listingDetailsArrayList = listingDetailsArrayList;
        this.frame = frame;
        this.listingPanel = listingPanel;
        this.scrollPane = scrollPane;
        this.descriptionTextArea = descriptionTextArea;
        this.facilitiesCheck = facilitiesCheck;
        this.index = index;
        this.username = username;
        this.role = role;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to save the new property details when triggered.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent e){ 
        loadPropertyData();
        int propertyID = 1;
        int count = 1;  
        String listingDetails = "";
        String propertyFileName = "";
        String[] extensionArray = {"jpeg","jpg","tiff","tif","png"};

        if(descriptionTextArea.getText().equals("") || propertyImagesFileArrayList.isEmpty())
            JOptionPane.showMessageDialog(new JFrame(),"INPUT CANNOT BE BLANK!","Input Error", JOptionPane.ERROR_MESSAGE);
        else{
            // CHECK THE PREVIOUS PROPERTY'S ID
            if(!propertyDetailsArrayList.isEmpty()){
                propertyID = Integer.parseInt(propertyDetailsArrayList.get(propertyDetailsArrayList.size()-1).getPropertyID()) + 1;
            }       

            // SAVE DESCRIPTION INTO TXT FILE
            String descriptionFileName = Integer.toString(propertyID)+"Description.txt";
            listingDetailsArrayList.add(descriptionFileName);
            try{
                File descriptionFile = new File(descriptionFileName);
                descriptionFile.createNewFile();
                FileWriter descriptionWriter = new FileWriter(descriptionFile);
                descriptionWriter.write(descriptionTextArea.getText());
                descriptionWriter.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            
            //GET THE OLD FILE EXTENSION AND NEW FILE EXTENSION
            listingDetails = role + "," + username + "," + propertyID + ",true,";
            for (String s : listingDetailsArrayList){
                listingDetails = listingDetails + s + ",";
            }
            try{
                // RECEIVE THE IMAGE AND SAVE INTO PROPERTYDETAILS.CSV
                FileWriter propertyFileWriter = new FileWriter("propertyDetails.csv", true);
                for(File f : propertyImagesFileArrayList){
                    for(String extension: extensionArray){
                        if (f.getName().endsWith(extension)){
                            BufferedImage propertyImages = ImageIO.read(new File(f.getAbsolutePath()));
                            ImageIO.write(propertyImages, extension, new File(Integer.toString(propertyID) + "(" + Integer.toString(count) +")."+extension));
                            propertyFileName = propertyFileName + Integer.toString(propertyID) + "(" + Integer.toString(count) +")."+ extension + "|";
                            count++; 
                        }
                    }
                }
                listingDetails = listingDetails + propertyFileName + ",Comments|";
                propertyFileWriter.append(listingDetails + "\n");
                JOptionPane.showMessageDialog(new JFrame(), "Your Property Is Listed Successfully!","Property Listing", JOptionPane.INFORMATION_MESSAGE);    
                propertyFileWriter.close();

            }catch(IOException exp){
                exp.printStackTrace();
            }
            listingPanel.removeAll();
            listingPanel.setAutoscrolls(true);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            new YourListingPanel(frame, index, facilitiesCheck, listingPanel, scrollPane, username, role);
        }
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : loadPropertyData
    Purpose         : This method is to load property data into arrayList.
    ************************************************************************************************************/
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

}
