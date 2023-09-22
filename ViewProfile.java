import java.awt.*;
import javax.swing.*;
import java.io.*;
/***********************************************************************************************************
Class's Name    : ViewProfile
Design Pattern  : Singleton
Purpose         : This class is to display view profile page.
************************************************************************************************************/ 
public class ViewProfile extends JFrame{
    //Aggregation from Admin, Agent, Owner, Tenant.
    Admin admin;
    Agent agent;
    Owner owner;
    Tenant tenant;
    String role;
    public ViewProfile(String name){
        super("View Profile");

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(Short.MAX_VALUE, 65));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        JLabel title = new JLabel("  View Profile");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  30));
        title.setForeground(Color.white);
        titlePanel.add(title);
        titlePanel.setBackground(new Color(25, 149, 173));

        loadUser(name);

        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.PAGE_AXIS));
        profilePanel.setBackground(new Color(127,197,255));
        JPanel profilePicture = new JPanel();
        profilePicture.setPreferredSize(new Dimension(150,150));
        profilePicture.setMaximumSize(new Dimension(150,150));
        profilePicture.setBorder(BorderFactory.createLineBorder(Color.black));
        profilePicture.setBackground(new Color(206,233,255));
        JLabel pic = new JLabel();
        profilePicture.add(pic);
        
        JPanel profiledesc = new JPanel();
        profiledesc.setBorder(BorderFactory.createLineBorder(Color.black));
        profiledesc.setPreferredSize(new Dimension(700,350));
        profiledesc.setMaximumSize(new Dimension(700,350));
        profiledesc.setBackground(new Color(206,233,255));

        profilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profilePanel.add(profilePicture);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profilePanel.add(profiledesc);
        String Agent[] ={"Username", "Password", "Phone Number", "Date of Birth", "Email", "Agency", "Agency Registration Number", "REN/REA License Number","Agency Contact Number"};
        String Owner[] ={"Username", "Password", "Phone Number", "Date of Birth", "Email", "Contact Number"};
        String Tenant[]={"Username", "Password", "Phone Number", "Date of Birth", "Email"};
        String Admin[] = {"Username","Password", "Phone Number","Date Of Birth","Email","Security Question", "Answers"};
        if(role.equals("Admin")){
            pic.setIcon(loadImage(admin.getprofilePic(), 150, 150));
            profiledesc.setLayout(new GridLayout(7,2));
            JLabel[] Label = new JLabel[7]; //LEFT
            JLabel[] Label2 = new JLabel[7]; //RIGHT
            for(int i = 0 ; i < 7 ; i++){
                Label[i] = new JLabel(Admin[i]);
            }
            Label2[0]= new JLabel(admin.getUsername());    Label2[1]= new JLabel(admin.getPassword());
            Label2[2]= new JLabel(admin.getPhoneNumber()); Label2[3]= new JLabel(admin.getDOB());
            Label2[4]= new JLabel(admin.getEmail());       Label2[5]= new JLabel(admin.getSecurityQues());
            Label2[6]= new JLabel(admin.getSecurityAns()); 
            for(int i = 0 ; i < 7 ; i++){
                Label[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label2[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                Label2[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                profiledesc.add(Label[i]);
                profiledesc.add(Label2[i]);
            }
        }
        else if(role.equals("Agent")){
            pic.setIcon(loadImage(agent.getprofilePic(), 150, 150));
            profiledesc.setLayout(new GridLayout(9,2));
            JLabel[] Label = new JLabel[10]; //LEFT
            JLabel[] Label2 = new JLabel[10]; //RIGHT
            for(int i = 0 ; i < 9 ; i++){
                Label[i] = new JLabel(Agent[i]);
            }
            Label2[0]= new JLabel(agent.getUsername());         Label2[1]= new JLabel(agent.getPassword());
            Label2[2]= new JLabel(agent.getPhoneNumber());      Label2[3]= new JLabel(agent.getDOB());
            Label2[4]= new JLabel(agent.getEmail());            Label2[5]= new JLabel(agent.getAgency());
            Label2[6]= new JLabel(agent.getAgencyRegisNum());   Label2[7]= new JLabel(agent.getAgentLicenseNum());
            Label2[8]= new JLabel(agent.getContactNumber());
            for(int i = 0 ; i < 9 ; i++){
                Label[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label2[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                Label2[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                profiledesc.add(Label[i]);
                profiledesc.add(Label2[i]);
            }

            // AGENT DESCRIPTION PANEL
            JPanel descriptionPanel = new JPanel();
            JTextArea descriptionTextArea = new JTextArea();
            JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
            descriptionPanel.setBackground(new Color(127,197,255));
            descriptionTextArea.setEditable(false);
            descriptionTextArea.append("DESCRIPTION \n \n");
            try {
                File descFile = new File(agent.getDescription());
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
            descriptionPanel.add(Box.createRigidArea(new Dimension(417,25)));
            descriptionPanel.add(descriptionScrollPane);
            descriptionPanel.add(Box.createRigidArea(new Dimension(417,25)));
    
            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));
            profilePanel.add(descriptionPanel);
            profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));
  
        }
        else if(role.equals("Owner")){
            pic.setIcon(loadImage(owner.getprofilePic(), 150, 150));
            profiledesc.setLayout(new GridLayout(6,2));
            JLabel[] Label = new JLabel[6]; //LEFT
            JLabel[] Label2 = new JLabel[6]; //RIGHT
            for(int i = 0 ; i < 6 ; i++){
                Label[i] = new JLabel(Owner[i]);
            }
            Label2[0]= new JLabel(owner.getUsername());         Label2[1]= new JLabel(owner.getPassword());
            Label2[2]= new JLabel(owner.getPhoneNumber());      Label2[3]= new JLabel(owner.getDOB());
            Label2[4]= new JLabel(owner.getEmail());            Label2[5]= new JLabel(owner.getContactNumber());
            for(int i = 0 ; i < 6 ; i++){
                Label[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label2[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                Label2[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                profiledesc.add(Label[i]);
                profiledesc.add(Label2[i]);
            }
        }
        else if(role.equals("Tenant")){
            pic.setIcon(loadImage(tenant.getprofilePic(), 150, 150));
            profiledesc.setLayout(new GridLayout(5,2));
            JLabel[] Label = new JLabel[5]; //LEFT
            JLabel[] Label2 = new JLabel[5]; //RIGHT
            for(int i = 0 ; i < 5 ; i++){
                Label[i] = new JLabel(Tenant[i]);
            }
            Label2[0]= new JLabel(tenant.getUsername());         Label2[1]= new JLabel(tenant.getPassword());
            Label2[2]= new JLabel(tenant.getPhoneNumber());      Label2[3]= new JLabel(tenant.getDOB());
            Label2[4]= new JLabel(tenant.getEmail());          
            for(int i = 0 ; i < 5 ; i++){
                Label[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label2[i].setHorizontalAlignment(SwingConstants.CENTER);
                Label[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                Label2[i].setFont(new Font("Comic Sans MS", Font.PLAIN,  15));
                profiledesc.add(Label[i]);
                profiledesc.add(Label2[i]);
            }
        }
        add(titlePanel,BorderLayout.NORTH);
        add(profilePanel,BorderLayout.CENTER);
        setSize(1000,800);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : loadUser
    Purpose         : This method is to load users details from csv file and return own declarations.
    ************************************************************************************************************/  
    public void loadUser(String name){
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(name)){
                    if(details[0].equals("Admin")){
                        role = "Admin";
                        admin = new Admin(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] , details[9]);
                    }else if(details[0].equals("Tenant")){
                        role = "Tenant";
                        tenant = new Tenant(details[1], details[2] , details[3], details[4], details[5], details[6] , details[7]);
                    }else if(details[0].equals("Owner")){
                        role = "Owner";
                        owner = new Owner(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8]);
                    }else if(details[0].equals("Agent")){
                        role = "Agent";
                        agent = new Agent(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] ,details[9],details[10],details[11],details[12]);
                    }
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : loadImage
    Purpose         : This method is to load Image from file.
    ************************************************************************************************************/  
    public ImageIcon loadImage(String path, int width, int height){
        Image image = new ImageIcon(path).getImage();
        Image scaledImage = image.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}