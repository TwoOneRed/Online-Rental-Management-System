import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/***********************************************************************************************************
Class's Name    : YourListingSaveAndContinueActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the owner/agent click the
                  save and continue button in list a new property page.
************************************************************************************************************/ 
public class YourListingSaveAndContinueActionListener implements ActionListener{
    JTabbedPane listPropertyTabbedPane;
    JPanel panel;
    ArrayList<JComboBox<String>>comboBoxArrayList;
    ArrayList<JTextField>textFieldArray;
    ArrayList<JCheckBox> facilitiesCheckBoxArrayList;
    ArrayList<JCheckBox> featuresCheckBoxArrayList;
    ArrayList<String> listingDetailsArrayList;
    String[] propertyArray;
    int index;
    public  YourListingSaveAndContinueActionListener(JTabbedPane listPropertyTabbedPane, JPanel panel, ArrayList<JComboBox<String>>comboBoxArrayList, ArrayList<JTextField>textFieldArray, ArrayList<JCheckBox> facilitiesCheckBoxArrayList, ArrayList<JCheckBox> featuresCheckBoxArrayList, ArrayList<String>listingDetailsArrayList,String[] propertyArray, int index){
        this.listPropertyTabbedPane = listPropertyTabbedPane;
        this.panel = panel;
        this.comboBoxArrayList = comboBoxArrayList;
        this.textFieldArray = textFieldArray;
        this.facilitiesCheckBoxArrayList = facilitiesCheckBoxArrayList;
        this.featuresCheckBoxArrayList = featuresCheckBoxArrayList;
        this.listingDetailsArrayList = listingDetailsArrayList;
        this.propertyArray = propertyArray;
        this.index = index;
    }

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to check the current tabbedpane index and change the tabbedpane to selected index.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent e){
        if (index == 1){
            int errorCount = 0;    int formatErrorCount = 0;
            // CHECK THE BLANK INPUT
            for (int i = 0; i < textFieldArray.size(); i++){
                if (textFieldArray.get(i).getText().equals(""))
                    errorCount++;
            }
            // CHECK THE INPUT FORMAT
            if (!textFieldArray.get(1).getText().equals("")){
                try{
                    Integer.parseInt(textFieldArray.get(1).getText());
                }catch(NumberFormatException ev){
                    formatErrorCount++;
                }
            }

            if(errorCount > 0 && formatErrorCount == 0)
                JOptionPane.showMessageDialog(new JFrame(),"INPUT CANNOT BE BLANK!","Input Error", JOptionPane.ERROR_MESSAGE);
            else if(errorCount == 0 && formatErrorCount > 0)
                JOptionPane.showMessageDialog(new JFrame(),"LISTING REFERENCE NUMBER MUST BE NUMBER!","Input Error", JOptionPane.ERROR_MESSAGE);
            else{
                for(JComboBox<String> c : comboBoxArrayList)
                    listingDetailsArrayList.add(c.getSelectedItem().toString());
                for (JTextField t : textFieldArray){
                    listingDetailsArrayList.add(t.getText());
                }
                locationPanelMethod(panel, listPropertyTabbedPane, propertyArray);
                listPropertyTabbedPane.setSelectedIndex(1);
                listPropertyTabbedPane.setEnabledAt(0, false);
                listPropertyTabbedPane.setEnabledAt(1, true);
                listPropertyTabbedPane.setEnabledAt(2, false);
                listPropertyTabbedPane.setEnabledAt(3, false);
                listPropertyTabbedPane.setEnabledAt(4, false);
            }
        }
        else if (index == 2){
            int errorCount = 0;    int formatErrorCount = 0;
            // CHECK THE BLANK INPUT
            if (textFieldArray.get(0).getText().equals("")){
                errorCount++;
            }
            else{
                if (textFieldArray.get(0).getText().equals("Apartment") || textFieldArray.get(0).getText().equals("Condominium") || textFieldArray.get(0).getText().equals("Flat") || textFieldArray.get(0).getText().equals("Studio")){
                    for(int i = 1; i < textFieldArray.size(); i++){
                        if(textFieldArray.get(i).getText().equals("")){
                            errorCount++;
                        }
                    }
                     // CHECK THE INPUT FORMAT
                    if (!textFieldArray.get(3).getText().equals("") && !textFieldArray.get(6).getText().equals("") && !textFieldArray.get(7).getText().equals("")){
                        try{
                            Integer.parseInt(textFieldArray.get(3).getText());
                            Integer.parseInt(textFieldArray.get(6).getText());
                            Integer.parseInt(textFieldArray.get(7).getText());
                        }catch(NumberFormatException ev){
                            formatErrorCount = 1;
                        }
                    }
                }
                else{
                    for(int i = 1; i < textFieldArray.size(); i++){
                        if(i != 5 && i != 7){
                            if(textFieldArray.get(i).getText().equals("")){
                                errorCount++;
                            }
                        }
                    }
                     // CHECK THE INPUT FORMAT
                    if (!textFieldArray.get(3).getText().equals("") && !textFieldArray.get(6).getText().equals("")){
                        try{
                            Integer.parseInt(textFieldArray.get(3).getText());
                            Integer.parseInt(textFieldArray.get(6).getText());
                        }catch(NumberFormatException ev){
                            formatErrorCount = 2;
                        }
                    }
                }
            }

            if(errorCount > 0 && formatErrorCount == 0)
                JOptionPane.showMessageDialog(new JFrame(),"INPUT CANNOT BE BLANK!","Input Error", JOptionPane.ERROR_MESSAGE);
            else if(errorCount == 0 && formatErrorCount == 1)
                JOptionPane.showMessageDialog(new JFrame(),"(POST CODE / (UNIT/HOUSE NO) / FLOOR) MUST BE NUMBER!","Input Error", JOptionPane.ERROR_MESSAGE);
            else if(errorCount == 0 && formatErrorCount == 2)
                JOptionPane.showMessageDialog(new JFrame(),"(POST CODE / (UNIT/HOUSE NO)) MUST BE NUMBER!","Input Error", JOptionPane.ERROR_MESSAGE);
            else{
                for (int i = 0; i < textFieldArray.size(); i++){
                    if (i == 4)
                        listingDetailsArrayList.add(textFieldArray.get(i).getText().replace(",", "|"));
                    else
                        listingDetailsArrayList.add(textFieldArray.get(i).getText());
                }
                listPropertyTabbedPane.setSelectedIndex(2);
                listPropertyTabbedPane.setEnabledAt(0, false);
                listPropertyTabbedPane.setEnabledAt(1, false);
                listPropertyTabbedPane.setEnabledAt(2, true);
                listPropertyTabbedPane.setEnabledAt(3, false);
                listPropertyTabbedPane.setEnabledAt(4, false);
            }
        }
        else if (index == 3){
            int errorCount = 0;    int formatErrorCount = 0;
            String strRentalRate = "";
            // CHECK THE BLANK INPUT
            for (int i = 0; i < textFieldArray.size()-1; i++){
                if (textFieldArray.get(i).getText().equals(""))
                    errorCount++;
            }
            // CHECK THE INPUT FORMAT
            if (!textFieldArray.get(0).getText().equals("") && !textFieldArray.get(1).getText().equals("")){
                try{
                    int rentalPrice = Integer.parseInt(textFieldArray.get(0).getText());
                    int builtUpArea = Integer.parseInt(textFieldArray.get(1).getText());
                    Float rentalRate = (float) rentalPrice/builtUpArea;
                    strRentalRate = String.format("%.02f", rentalRate);
                    textFieldArray.get(2).setText(strRentalRate);
                }catch(NumberFormatException ev){
                    formatErrorCount++;
                }
            }

            if(errorCount > 0 && formatErrorCount == 0)
                JOptionPane.showMessageDialog(new JFrame(),"INPUT CANNOT BE BLANK!","Input Error", JOptionPane.ERROR_MESSAGE);
            else if(errorCount == 0 && formatErrorCount > 0)
                JOptionPane.showMessageDialog(new JFrame(),"(RENTAL PRICE / BUILT-UP AREA) MUST BE NUMBER!","Input Error", JOptionPane.ERROR_MESSAGE);
            else{
                for (JTextField t: textFieldArray){
                    listingDetailsArrayList.add(t.getText());
                }

                for(JComboBox<String> c : comboBoxArrayList)
                    listingDetailsArrayList.add(c.getSelectedItem().toString());

                listPropertyTabbedPane.setSelectedIndex(3);
                listPropertyTabbedPane.setEnabledAt(0, false);
                listPropertyTabbedPane.setEnabledAt(1, false);
                listPropertyTabbedPane.setEnabledAt(2, false);
                listPropertyTabbedPane.setEnabledAt(3, true);
                listPropertyTabbedPane.setEnabledAt(4, false);
            }
        }
        else{
            String facilities = "";
            if (facilitiesCheckBoxArrayList.isEmpty())
                facilities = "No Property Facilities";
            else{
                for (int i = 0 ; i < facilitiesCheckBoxArrayList.size(); i++){
                    if(facilitiesCheckBoxArrayList.get(i).isSelected()){
                        if (i == facilitiesCheckBoxArrayList.size()-1)
                            facilities = facilities + facilitiesCheckBoxArrayList.get(i).getText();
                        else
                            facilities = facilities + facilitiesCheckBoxArrayList.get(i).getText() + "|";
                    }
                }
            }
            String features = "";
            if (featuresCheckBoxArrayList.isEmpty())
                features = "No Property Features";
            else{
                for (int i = 0 ; i < featuresCheckBoxArrayList.size(); i++){
                    if(featuresCheckBoxArrayList.get(i).isSelected()){
                        if (i == featuresCheckBoxArrayList.size()-1)
                            features = features + featuresCheckBoxArrayList.get(i).getText();
                        else
                            features = features + featuresCheckBoxArrayList.get(i).getText() + "|";
                    }
                }
            }
            listingDetailsArrayList.add(facilities);
            listingDetailsArrayList.add(features);
            listPropertyTabbedPane.setSelectedIndex(4);
            listPropertyTabbedPane.setEnabledAt(0, false);
            listPropertyTabbedPane.setEnabledAt(1, false);
            listPropertyTabbedPane.setEnabledAt(2, false);
            listPropertyTabbedPane.setEnabledAt(3, false);
            listPropertyTabbedPane.setEnabledAt(4, true);
        }
    };

    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : locationPanelMethod
    Purpose         : This method let the owner/agent to input location details after 
                    they want to list a new property.
    ************************************************************************************************************/
    public void locationPanelMethod(JPanel locationPanel, JTabbedPane listPropertyTabbedPane, String[] propertyArray){
        ArrayList<JTextField>textFieldArray = new ArrayList<JTextField>();
        ArrayList<JComboBox<String>> comboBoxArrayList = new ArrayList<JComboBox<String>>();
        String propertyType = listingDetailsArrayList.get(0);
        
        //TITLE PANEL
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Where is your property?");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 150)));
        GridLayout layout;

        // CENTER PANEL
        JPanel centerPanel = new JPanel();
        if(propertyType.equals("Apartment") || propertyType.equals("Condominium") || propertyType.equals("Flat") || propertyType.equals("Studio"))
            layout = new GridLayout(8,2); 
        else
            layout = new GridLayout(6,2); 

        layout.setVgap(8);
        layout.setHgap(18);
        centerPanel.setLayout(layout);  
        int textFieldCount = 0;
        for(int i=0; i<propertyArray.length; i++){
            JLabel label = new JLabel(propertyArray[i]);
            if (i == 3 || i == 7 || i == 9){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField());
                centerPanel.add(textFieldArray.get(textFieldCount));
                textFieldCount++;
                centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
            }
            if ( i == 8 || i == 10 ){
                if(propertyType.equals("Apartment") || propertyType.equals("Condominium") || propertyType.equals("Flat") || propertyType.equals("Studio")){
                    centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                    centerPanel.add(label);
                    textFieldArray.add(new JTextField());
                    centerPanel.add(textFieldArray.get(textFieldCount));
                    textFieldCount++;
                    centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
                }
                else{
                    textFieldArray.add(new JTextField("-"));
                    textFieldCount++;
                }
            }
            if (i == 4){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField("Selangor"));
                textFieldArray.get(textFieldCount).setEnabled(false);
                centerPanel.add(textFieldArray.get(textFieldCount));
                textFieldCount++;
                centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
            }
            if (i == 5){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField("Cyberjaya"));
                textFieldArray.get(textFieldCount).setEnabled(false);
                centerPanel.add(textFieldArray.get(textFieldCount));
                textFieldCount++;
                centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
            }
            if (i == 6){
                centerPanel.add(Box.createRigidArea(new Dimension(363, 35)));
                centerPanel.add(label);
                textFieldArray.add(new JTextField("63000"));
                textFieldArray.get(textFieldCount).setEnabled(false);
                centerPanel.add(textFieldArray.get(textFieldCount));
                textFieldCount++;
                centerPanel.add(Box.createRigidArea(new Dimension(360, 35)));
            }
        }

        //SOUTH PANEL
        JPanel southPanel = new JPanel();
        JButton saveAndContinueButton = new JButton("Save and Continue");
        saveAndContinueButton.setPreferredSize(new Dimension(150,60));
        saveAndContinueButton.setBackground(new Color(30,144,255));
        saveAndContinueButton.setForeground(Color.white);
        saveAndContinueButton.addActionListener(new YourListingSaveAndContinueActionListener(listPropertyTabbedPane, new JPanel(), comboBoxArrayList,textFieldArray, new ArrayList<JCheckBox>(), new ArrayList<JCheckBox>(), listingDetailsArrayList, propertyArray, 2));
        southPanel.add(saveAndContinueButton);
        southPanel.add(Box.createRigidArea(new Dimension(0,280)));

        locationPanel.add(titlePanel, BorderLayout.NORTH);
        locationPanel.add(centerPanel, BorderLayout.CENTER);
        locationPanel.add(southPanel, BorderLayout.SOUTH);
    }
}