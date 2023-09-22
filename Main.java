import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/***********************************************************************************************************
Class's Name    : Main
Design Pattern  : Singleton
Purpose         : This class is the interface of a main menu. 
************************************************************************************************************/ 
public class Main extends JFrame{
    JPanel northPanel = new JPanel();    
    JPanel centerPanel = new JPanel(); 
    JPanel southPanel = new JPanel(new GridLayout(1,5));

    public Main(){
        super("Cyberjaya Online Rental Management System");
        Integer[] index = new Integer[10];
        Arrays.fill(index,0);
        Boolean[] facilitiesCheck = new Boolean[15];
        Arrays.fill(facilitiesCheck, false);
        northPanelFunct();

        //CENTER PANEL //mainly property
        centerPanel.setBackground(new Color(188, 186, 190));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollFrame = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerPanel.setAutoscrolls(true);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        new CenterPanelListing(this, index, facilitiesCheck ,centerPanel , "");
        
        add(northPanel,BorderLayout.NORTH);
        add(scrollFrame,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);
        setSize(1000,800);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : northPanelFunct
    Purpose         : This method display the filter panel and all the listed property in the north panel of the
                    main menu.
    ************************************************************************************************************/
    public void northPanelFunct(){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        //titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("Cyberjaya Online Rental System");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        titlePanel.add(title, BorderLayout.WEST);

        //LOGIN AND SIGN UP BUTTON
        JPanel loginSignUpPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                new Login();
            }
        });
        signUpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                new Register();
            }
        });
        loginButton.setPreferredSize(new Dimension(100, 40));
        signUpButton.setPreferredSize(new Dimension(100, 40));
        loginSignUpPanel.add(loginButton);
        loginSignUpPanel.add(signUpButton);
        loginSignUpPanel.setBackground(new Color(25, 149, 173));
        northPanel.add(titlePanel);
        titlePanel.add(Box.createRigidArea(new Dimension(935, 0)));
        titlePanel.add(loginSignUpPanel);

        titlePanel.setBackground(new Color(25, 149, 173));
        northPanel.setBackground(new Color(25, 149, 173));
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
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
    Programmer      : Leong Yi Hong
    Method's Name   : loadUserDetails
    Purpose         : This method is to load the details of users and return as arraylist.
    ************************************************************************************************************/ 
    public ArrayList<User> loadUserDetails(){ // load user data from csv file and return the arraylist
        File myFile = new File("userDetails.csv");
        ArrayList<User> userDetailsArrayList = new ArrayList<User>();
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                userDetailsArrayList.add(new User(details[0],details[1],details[2],details[3],details[4],details[5],details[6],details[7])); // add user data into arraylist      
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return userDetailsArrayList;
    }

    public static void main(String[] args){
         new Main();
    }
}