import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
/***********************************************************************************************************
Class's Name    : ManageUser
Design Pattern  : Singleton
Purpose         : This class is to display the ManageUser page in the admin menu.
************************************************************************************************************/ 
public class ManageUser extends JFrame{
    //Aggregation from Admin, Agent, Owner, Tenant.
    ArrayList<Admin>adminArrayList = new ArrayList<Admin>();
    ArrayList<Agent>agentArrayList = new ArrayList<Agent>();
    ArrayList<Owner>ownerArrayList = new ArrayList<Owner>();
    ArrayList<Tenant>tenantArrayList = new ArrayList<Tenant>();
    int adminCount = 0;
    
    JPanel topPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel middlePan = new JPanel();
    JPanel admin = new JPanel();
    JPanel agent = new JPanel();
    JPanel owner = new JPanel();
    JPanel tenant = new JPanel();

    public ManageUser(String adminUsername, String profilePicFileName){
        super("Manage user");        
        JLabel title = new JLabel("Manage User");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        topPan.setBackground(new Color(25, 149, 173));
        JButton back = new JButton("BACK TO MENU");
        back.setPreferredSize(new Dimension(150,40));
        loadUser();
        for(int i = 0 ; i < adminArrayList.size() ; i++){
            if(adminArrayList.get(i).getUsername().equals(adminUsername))
                adminCount = i;
        }

        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new AdminMenu(adminArrayList.get(adminCount).getUsername(), adminArrayList.get(adminCount).getprofilePic());
            }
        });
        topPan.add(title);
        topPan.add(Box.createRigidArea(new Dimension(1215, 0)));
        topPan.add(back);
        middlePan.setLayout(new BoxLayout(middlePan, BoxLayout.Y_AXIS));
        middlePan.setBackground(new Color(25, 149, 173));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(25, 149, 173));
        tabbedPane.setForeground(Color.WHITE);
        
        //MANAGE ADMIN
        admin.setBackground(new Color(188, 186, 190));
        admin.setLayout(new BoxLayout(admin, BoxLayout.PAGE_AXIS));
        admin.add(Box.createRigidArea(new Dimension(0, 10)));
        //MANAGE AGENT
        agent.setBackground(new Color(188, 186, 190));
        agent.setLayout(new BoxLayout(agent, BoxLayout.PAGE_AXIS));
        agent.add(Box.createRigidArea(new Dimension(0, 20)));
        //MANAGE OWNER
        owner.setBackground(new Color(188, 186, 190));
        owner.setLayout(new BoxLayout(owner, BoxLayout.PAGE_AXIS));
        owner.add(Box.createRigidArea(new Dimension(0, 20)));
        //MANAGE TENANT
        tenant.setBackground(new Color(188, 186, 190));
        tenant.setLayout(new BoxLayout(tenant, BoxLayout.PAGE_AXIS));
        tenant.add(Box.createRigidArea(new Dimension(0, 20)));
        adminListing(this);
        agentListing(this);
        ownerListing(this);
        tenantListing(this);
        tabbedPane.addTab("Admin", admin);
        tabbedPane.addTab("Agent", agent);
        tabbedPane.addTab("Owner", owner);
        tabbedPane.addTab("Tenant", tenant);
        middlePan.add(tabbedPane);

        JScrollPane sF = new JScrollPane(middlePan);
        middlePan.setAutoscrolls(true);
        sF.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        sF.getVerticalScrollBar().setUnitIncrement(16);

        this.add(topPan,BorderLayout.NORTH);
        this.add(sF,BorderLayout.CENTER);
        this.setSize(1000,800);
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : adminListing
    Purpose         : This method is to display admin listing.
    ************************************************************************************************************/ 
    public void adminListing(JFrame user){
        admin.removeAll();
        loadUser();
        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.setPreferredSize(new Dimension(1300, 50));
        top.setMaximumSize(new Dimension(1300, 50));
        JButton addAdmin = new JButton("ADD ADMIN");
        top.setForeground(Color.white);
        top.setBackground(new Color(25, 149, 173));
        addAdmin.setPreferredSize(new Dimension(150,40));
        top.add(addAdmin);
        admin.add(top);
        admin.add(Box.createRigidArea(new Dimension(0, 10)));
        addAdmin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                registerAdmin(admin,user);
                user.dispose();
            }
        });
        for(int i = 0 ; i < adminArrayList.size();i++){
            Admin current = adminArrayList.get(i);
            JPanel userDisplay = new JPanel();
            userDisplay.setPreferredSize(new Dimension(1300, 200));
            userDisplay.setMaximumSize(new Dimension(1300, 200));
            userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
            userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
            JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
            userDisplay.add(profilePicture);
            userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

            JPanel profileInfo = new JPanel();
            profileInfo.setPreferredSize(new Dimension(990,200));
            profileInfo.setMaximumSize(new Dimension(990,200));
            profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
            JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
            JLabel profilePhone = new JLabel(current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
            JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
            JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
            profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
            profileInfo.add(profileName);
            profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
            profileInfo.add(profilePhone);
            profileInfo.add(profileDOB);
            profileInfo.add(profileEmail);
            userDisplay.add(profileInfo);
            
            JPanel function = new JPanel(new GridLayout(2,1));
            function.setPreferredSize(new Dimension(100,100));
            function.setMaximumSize(new Dimension(100,100));
            JButton edit = new JButton("Edit");
            JButton delete = new JButton("Delete");
            edit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    JButton button = new JButton("Submit");                    
                    button.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            adminListing(user);
                            user.setVisible(true);
                        }
                    });
                    new EditProfile(current.getArray(),button , "Panel");
                }
            });
            delete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    deleteUserInFile(current.getUsername());
                    adminListing(user);
                    user.setVisible(true);
                }
            });

            function.add(edit);
            function.add(delete);
            userDisplay.add(function);

            JButton viewProfile = new JButton();
            viewProfile.setBorderPainted(false);
            viewProfile.setContentAreaFilled(false);
            viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
            viewProfile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    new ViewProfile(current.getUsername());
                }
            });
            viewProfile.add(userDisplay);
            admin.add(viewProfile);

            admin.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : agentListing
    Purpose         : This method is to display agent listing.
    ************************************************************************************************************/ 
    public void agentListing(JFrame user){
        agent.removeAll();
        loadUser();
        ArrayList<Agent>activeAgent = new ArrayList<Agent>();
        ArrayList<Agent>inActiveAgent = new ArrayList<Agent>();
        for(int i = 0 ; i < agentArrayList.size();i++){
            if(agentArrayList.get(i).getStatus())
                activeAgent.add(agentArrayList.get(i));
            else
                inActiveAgent.add(agentArrayList.get(i));
        }
        JPanel inactivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel inactiveTitle = new JLabel("Agent Registration");
        inactiveTitle.setFont(new Font("Comic Sans Ms", Font.BOLD,  30));
        inactiveTitle.setForeground(Color.white);
        inactivePanel.setBackground(new Color(25, 149, 173));
        inactivePanel.setPreferredSize(new Dimension(1300, 60));
        inactivePanel.setMaximumSize(new Dimension(1300, 60));
        inactivePanel.add(inactiveTitle);
        agent.add(inactivePanel);
        agent.add(Box.createRigidArea(new Dimension(0, 20)));
        //AGENT WAITING FOR PERMISSION
        if(inActiveAgent.size() == 0){
            JPanel pane = new JPanel(new GridLayout(1,1));
            pane.setPreferredSize(new Dimension(1300, 200));
            pane.setMaximumSize(new Dimension(1300, 200));
            JLabel inactiveInfo = new JLabel("Currently No Agent Registration To Accept / Reject");
            inactiveInfo.setHorizontalAlignment(SwingConstants.CENTER);
            inactiveInfo.setFont(new Font("Comic Sans Ms", Font.BOLD,  40));
            pane.add(inactiveInfo);
            agent.add(pane);
        }else{
            for(int i = 0 ; i < inActiveAgent.size();i++){
                Agent current = inActiveAgent.get(i);
                JPanel userDisplay = new JPanel();
                userDisplay.setPreferredSize(new Dimension(1300, 200));
                userDisplay.setMaximumSize(new Dimension(1300, 200));
                userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
                userDisplay.add(profilePicture);
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

                JPanel profileInfo = new JPanel();
                profileInfo.setPreferredSize(new Dimension(490,200));
                profileInfo.setMaximumSize(new Dimension(490,200));
                profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
                JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
                JLabel profilePhone = new JLabel(current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
                profileInfo.add(profileName);
                profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
                profileInfo.add(profilePhone);
                profileInfo.add(profileDOB);
                profileInfo.add(profileEmail);
                userDisplay.add(profileInfo);

                JPanel profileInfoPlus = new JPanel();
                profileInfoPlus.setPreferredSize(new Dimension(500,200));
                profileInfoPlus.setMaximumSize(new Dimension(500,200));
                profileInfoPlus.setLayout(new BoxLayout(profileInfoPlus, BoxLayout.PAGE_AXIS));
                JLabel profileAgency  = new JLabel("Agency-> " + current.getAgency());            profileAgency.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileLicense = new JLabel("Agent License Number-> " +current.getAgentLicenseNum());        profileLicense.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileaAgencyRegistration = new JLabel("Agency Registration Number-> " +current.getAgencyRegisNum());    profileaAgencyRegistration.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfoPlus.add(Box.createRigidArea(new Dimension(0, 60)));
                profileInfoPlus.add(profileAgency);
                profileInfoPlus.add(profileLicense);
                profileInfoPlus.add(profileaAgencyRegistration);

                userDisplay.add(profileInfoPlus);
                
                JPanel function = new JPanel(new GridLayout(2,1));
                function.setPreferredSize(new Dimension(100,100));
                function.setMaximumSize(new Dimension(100,100));
                JButton accept = new JButton("Accept");
                accept.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        statusUpdateUserInFile(current.getUsername());
                        agentListing(user);
                        user.setVisible(true);
                    }
                });
                JButton reject = new JButton("Reject");
                reject.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        deleteUserInFile(current.getUsername());
                        agentListing(user);
                        user.setVisible(true);
                    }
                });

                function.add(accept);
                function.add(reject);
                userDisplay.add(function);
                
                JButton viewProfile = new JButton();
                viewProfile.setBorderPainted(false);
                viewProfile.setContentAreaFilled(false);
                viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
                viewProfile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new ViewProfile(current.getUsername());
                    }
                });
                viewProfile.add(userDisplay);
                agent.add(viewProfile);

                agent.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        agent.add(Box.createRigidArea(new Dimension(0, 20)));
        //AGENT IN SYSTEM
        JPanel activePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel activeTitle = new JLabel("Agent in System");
        activeTitle.setFont(new Font("Comic Sans Ms", Font.BOLD,  30));
        activeTitle.setForeground(Color.white);
        activePanel.setBackground(new Color(25, 149, 173));
        activePanel.setPreferredSize(new Dimension(1300, 60));
        activePanel.setMaximumSize(new Dimension(1300, 60));
        activePanel.add(activeTitle);
        agent.add(activePanel);
        agent.add(Box.createRigidArea(new Dimension(0, 20)));
        if(activeAgent.size() == 0){
            JPanel pane = new JPanel(new GridLayout(1,1));
            pane.setPreferredSize(new Dimension(1300, 200));
            pane.setMaximumSize(new Dimension(1300, 200));
            JLabel activeInfo = new JLabel("Currently No Agent In System");
            activeInfo.setHorizontalAlignment(SwingConstants.CENTER);
            activeInfo.setFont(new Font("Comic Sans Ms", Font.BOLD,  40));
            pane.add(activeInfo);
            agent.add(pane);
        }else{
            for(int i = 0 ; i < activeAgent.size();i++){
                Agent current = activeAgent.get(i);
                JPanel userDisplay = new JPanel();
                userDisplay.setPreferredSize(new Dimension(1300, 200));
                userDisplay.setMaximumSize(new Dimension(1300, 200));
                userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
                userDisplay.add(profilePicture);
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

                JPanel profileInfo = new JPanel();
                profileInfo.setPreferredSize(new Dimension(490,200));
                profileInfo.setMaximumSize(new Dimension(490,200));
                profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
                JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
                JLabel profilePhone = new JLabel(current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
                profileInfo.add(profileName);
                profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
                profileInfo.add(profilePhone);
                profileInfo.add(profileDOB);
                profileInfo.add(profileEmail);
                userDisplay.add(profileInfo);

                JPanel profileInfoPlus = new JPanel();
                profileInfoPlus.setPreferredSize(new Dimension(500,200));
                profileInfoPlus.setMaximumSize(new Dimension(500,200));
                profileInfoPlus.setLayout(new BoxLayout(profileInfoPlus, BoxLayout.PAGE_AXIS));
                JLabel profileAgency  = new JLabel("Agency-> " + current.getAgency());            profileAgency.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileLicense = new JLabel("REN/REA License Number-> " +current.getAgentLicenseNum());        profileLicense.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileaAgencyRegistration = new JLabel("Agency Registration Number-> " +current.getAgencyRegisNum());    profileaAgencyRegistration.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfoPlus.add(Box.createRigidArea(new Dimension(0, 60)));
                profileInfoPlus.add(profileAgency);
                profileInfoPlus.add(profileLicense);
                profileInfoPlus.add(profileaAgencyRegistration);

                userDisplay.add(profileInfoPlus);
                
                JPanel function = new JPanel(new GridLayout(2,1));
                function.setPreferredSize(new Dimension(100,100));
                function.setMaximumSize(new Dimension(100,100));
                JButton edit = new JButton("Edit");
                JButton delete = new JButton("Delete");
                
                edit.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        JButton button = new JButton("Submit");                    
                        button.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                agentListing(user);
                                user.setVisible(true);
                            }
                        });
                        new EditProfile(current.getArray(),button , "Panel");
                    }
                });
                delete.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        deleteUserInFile(current.getUsername());
                        agentListing(user);
                        user.setVisible(true);
                    }
    
                });
                function.add(edit);
                function.add(delete);
                userDisplay.add(function);

                JButton viewProfile = new JButton();
                viewProfile.setBorderPainted(false);
                viewProfile.setContentAreaFilled(false);
                viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
                viewProfile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new ViewProfile(current.getUsername());
                    }
                });
                viewProfile.add(userDisplay);
                agent.add(viewProfile);

                agent.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : ownerListing
    Purpose         : This method is to display owner listing.
    ************************************************************************************************************/ 
    public void ownerListing(JFrame user){
        owner.removeAll();
        loadUser();
        ArrayList<Owner>activeOwner = new ArrayList<Owner>();
        ArrayList<Owner>inActiveOwner = new ArrayList<Owner>();
        for(int i = 0 ; i < ownerArrayList.size();i++){
            if(ownerArrayList.get(i).getStatus())
                activeOwner.add(ownerArrayList.get(i));
            else
                inActiveOwner.add(ownerArrayList.get(i));
        }
        JPanel inactivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel inactiveTitle = new JLabel("Owner Registration");
        inactiveTitle.setFont(new Font("Comic Sans Ms", Font.BOLD,  30));
        inactiveTitle.setForeground(Color.white);
        inactivePanel.setBackground(new Color(25, 149, 173));
        inactivePanel.setPreferredSize(new Dimension(1300, 60));
        inactivePanel.setMaximumSize(new Dimension(1300, 60));
        inactivePanel.add(inactiveTitle);
        owner.add(inactivePanel);
        owner.add(Box.createRigidArea(new Dimension(0, 20)));
        //AGENT WAITING FOR PERMISSION
        if(inActiveOwner.size() == 0){
            JPanel pane = new JPanel(new GridLayout(1,1));
            pane.setPreferredSize(new Dimension(1300, 200));
            pane.setMaximumSize(new Dimension(1300, 200));
            JLabel inactiveInfo = new JLabel("Currently No Owner Registration To Accept / Reject");
            inactiveInfo.setHorizontalAlignment(SwingConstants.CENTER);
            inactiveInfo.setFont(new Font("Comic Sans Ms", Font.BOLD,  40));
            pane.add(inactiveInfo);
            owner.add(pane);
        }else{
            for(int i = 0 ; i < inActiveOwner.size();i++){
                Owner current = inActiveOwner.get(i);
                JPanel userDisplay = new JPanel();
                userDisplay.setPreferredSize(new Dimension(1300, 200));
                userDisplay.setMaximumSize(new Dimension(1300, 200));
                userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
                userDisplay.add(profilePicture);
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

                JPanel profileInfo = new JPanel();
                profileInfo.setPreferredSize(new Dimension(990,200));
                profileInfo.setMaximumSize(new Dimension(990,200));
                profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
                JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
                JLabel profileContactPhone = new JLabel("Contact Number -> " + current.getPhoneNumber());   profileContactPhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profilePhone = new JLabel("Personal Number -> " + current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
                profileInfo.add(profileName);
                profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
                profileInfo.add(profilePhone);
                profileInfo.add(profileDOB);
                profileInfo.add(profileEmail);
                userDisplay.add(profileInfo);
                
                JPanel function = new JPanel(new GridLayout(2,1));
                function.setPreferredSize(new Dimension(100,100));
                function.setMaximumSize(new Dimension(100,100));
                JButton accept = new JButton("Accept");
                accept.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        statusUpdateUserInFile(current.getUsername());
                        ownerListing(user);
                        user.setVisible(true);
                    }
                });
                JButton reject = new JButton("Reject");
                reject.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        deleteUserInFile(current.getUsername());
                        ownerListing(user);
                        user.setVisible(true);
                    }
                });
                function.add(accept);
                function.add(reject);
                userDisplay.add(function);
                
                JButton viewProfile = new JButton();
                viewProfile.setBorderPainted(false);
                viewProfile.setContentAreaFilled(false);
                viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
                viewProfile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new ViewProfile(current.getUsername());
                    }
                });
                viewProfile.add(userDisplay);
                owner.add(viewProfile);
                owner.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        owner.add(Box.createRigidArea(new Dimension(0, 20)));
        //AGENT IN SYSTEM
        JPanel activePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel activeTitle = new JLabel("Owner in System");
        activeTitle.setFont(new Font("Comic Sans Ms", Font.BOLD,  30));
        activeTitle.setForeground(Color.white);
        activePanel.setBackground(new Color(25, 149, 173));
        activePanel.setPreferredSize(new Dimension(1300, 60));
        activePanel.setMaximumSize(new Dimension(1300, 60));
        activePanel.add(activeTitle);
        owner.add(activePanel);
        owner.add(Box.createRigidArea(new Dimension(0, 20)));
        if(activeOwner.size() == 0){
            JPanel pane = new JPanel(new GridLayout(1,1));
            pane.setPreferredSize(new Dimension(1300, 200));
            pane.setMaximumSize(new Dimension(1300, 200));
            JLabel activeInfo = new JLabel("Currently No Owner In System");
            activeInfo.setHorizontalAlignment(SwingConstants.CENTER);
            activeInfo.setFont(new Font("Comic Sans Ms", Font.BOLD,  40));
            pane.add(activeInfo);
            owner.add(pane);
        }else{
            for(int i = 0 ; i < activeOwner.size();i++){
                Owner current = activeOwner.get(i);
                JPanel userDisplay = new JPanel();
                userDisplay.setPreferredSize(new Dimension(1300, 200));
                userDisplay.setMaximumSize(new Dimension(1300, 200));
                userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
                userDisplay.add(profilePicture);
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

                JPanel profileInfo = new JPanel();
                profileInfo.setPreferredSize(new Dimension(990,200));
                profileInfo.setMaximumSize(new Dimension(990,200));
                profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
                JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
                JLabel profileContactPhone = new JLabel("Contact Number -> " + current.getPhoneNumber());   profileContactPhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profilePhone = new JLabel("Personal Number -> " + current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
                profileInfo.add(profileName);
                profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
                profileInfo.add(profilePhone);
                profileInfo.add(profileDOB);
                profileInfo.add(profileEmail);
                userDisplay.add(profileInfo);
                
                JPanel function = new JPanel(new GridLayout(2,1));
                function.setPreferredSize(new Dimension(100,100));
                function.setMaximumSize(new Dimension(100,100));
                JButton edit = new JButton("Edit");
                JButton delete = new JButton("Delete");
                edit.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        JButton button = new JButton("Submit");                    
                        button.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                ownerListing(user);
                                user.setVisible(true);
                            }
                        });
                        new EditProfile(current.getArray() ,button , "Panel");
                    }
                });
                delete.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        deleteUserInFile(current.getUsername());
                        ownerListing(user);
                        user.setVisible(true);
                    }
    
                });
                function.add(edit);
                function.add(delete);
                userDisplay.add(function);
                
                JButton viewProfile = new JButton();
                viewProfile.setBorderPainted(false);
                viewProfile.setContentAreaFilled(false);
                viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
                viewProfile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new ViewProfile(current.getUsername());
                    }
                });
                viewProfile.add(userDisplay);
                owner.add(viewProfile);
                owner.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : tenantListing
    Purpose         : This method is to display tenant listing.
    ************************************************************************************************************/ 
    public void tenantListing(JFrame user){
        tenant.removeAll();
        loadUser();
        ArrayList<Tenant>activeTenant = new ArrayList<Tenant>();
        ArrayList<Tenant>inActiveTenant = new ArrayList<Tenant>();
        for(int i = 0 ; i < tenantArrayList.size();i++){
            if(tenantArrayList.get(i).getStatus())
                activeTenant.add(tenantArrayList.get(i));
            else
                inActiveTenant.add(tenantArrayList.get(i));
        }
        JPanel inactivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel inactiveTitle = new JLabel("Tenant Registration");
        inactiveTitle.setFont(new Font("Comic Sans Ms", Font.BOLD,  30));
        inactiveTitle.setForeground(Color.white);
        inactivePanel.setBackground(new Color(25, 149, 173));
        inactivePanel.setPreferredSize(new Dimension(1300, 60));
        inactivePanel.setMaximumSize(new Dimension(1300, 60));
        inactivePanel.add(inactiveTitle);
        tenant.add(inactivePanel);
        tenant.add(Box.createRigidArea(new Dimension(0, 20)));
        //AGENT WAITING FOR PERMISSION
        if(inActiveTenant.size() == 0){
            JPanel pane = new JPanel(new GridLayout(1,1));
            pane.setPreferredSize(new Dimension(1300, 200));
            pane.setMaximumSize(new Dimension(1300, 200));
            JLabel inactiveInfo = new JLabel("Currently No Tenant Registration To Accept / Reject");
            inactiveInfo.setHorizontalAlignment(SwingConstants.CENTER);
            inactiveInfo.setFont(new Font("Comic Sans Ms", Font.BOLD,  40));
            pane.add(inactiveInfo);
            tenant.add(pane);
        }else{
            for(int i = 0 ; i < inActiveTenant.size();i++){
                Tenant current = inActiveTenant.get(i);
                JPanel userDisplay = new JPanel();
                userDisplay.setPreferredSize(new Dimension(1300, 200));
                userDisplay.setMaximumSize(new Dimension(1300, 200));
                userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
                userDisplay.add(profilePicture);
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

                JPanel profileInfo = new JPanel();
                profileInfo.setPreferredSize(new Dimension(990,200));
                profileInfo.setMaximumSize(new Dimension(990,200));
                profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
                JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
                JLabel profilePhone = new JLabel(current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
                profileInfo.add(profileName);
                profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
                profileInfo.add(profilePhone);
                profileInfo.add(profileDOB);
                profileInfo.add(profileEmail);
                userDisplay.add(profileInfo);
                
                JPanel function = new JPanel(new GridLayout(2,1));
                function.setPreferredSize(new Dimension(100,100));
                function.setMaximumSize(new Dimension(100,100));
                JButton accept = new JButton("Accept");
                accept.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        statusUpdateUserInFile(current.getUsername());
                        tenantListing(user);
                        user.setVisible(true);
                    }
                });
                JButton reject = new JButton("Reject");
                reject.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        deleteUserInFile(current.getUsername());
                        tenantListing(user);
                        user.setVisible(true);
                    }
                });
                function.add(accept);
                function.add(reject);
                userDisplay.add(function);
                
                JButton viewProfile = new JButton();
                viewProfile.setBorderPainted(false);
                viewProfile.setContentAreaFilled(false);
                viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
                viewProfile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new ViewProfile(current.getUsername());
                    }
                });
                viewProfile.add(userDisplay);
                tenant.add(viewProfile);
                tenant.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        tenant.add(Box.createRigidArea(new Dimension(0, 20)));
        //TENANT IN SYSTEM
        JPanel activePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel activeTitle = new JLabel("Tenant in System");
        activeTitle.setFont(new Font("Comic Sans Ms", Font.BOLD,  30));
        activeTitle.setForeground(Color.white);
        activePanel.setBackground(new Color(25, 149, 173));
        activePanel.setPreferredSize(new Dimension(1300, 60));
        activePanel.setMaximumSize(new Dimension(1300, 60));
        activePanel.add(activeTitle);
        tenant.add(activePanel);
        tenant.add(Box.createRigidArea(new Dimension(0, 20)));
        if(activeTenant.size() == 0){
            JPanel pane = new JPanel(new GridLayout(1,1));
            pane.setPreferredSize(new Dimension(1300, 200));
            pane.setMaximumSize(new Dimension(1300, 200));
            JLabel activeInfo = new JLabel("Currently No Tenant In System");
            activeInfo.setHorizontalAlignment(SwingConstants.CENTER);
            activeInfo.setFont(new Font("Comic Sans Ms", Font.BOLD,  40));
            pane.add(activeInfo);
            tenant.add(pane);
        }else{
            for(int i = 0 ; i < activeTenant.size();i++){
                Tenant current = activeTenant.get(i);
                JPanel userDisplay = new JPanel();
                userDisplay.setPreferredSize(new Dimension(1300, 200));
                userDisplay.setMaximumSize(new Dimension(1300, 200));
                userDisplay.setLayout(new BoxLayout(userDisplay, BoxLayout.LINE_AXIS));
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel profilePicture = new JLabel(loadImage(current.getprofilePic(), 160, 160));
                userDisplay.add(profilePicture);
                userDisplay.add(Box.createRigidArea(new Dimension(15, 0)));

                JPanel profileInfo = new JPanel();
                profileInfo.setPreferredSize(new Dimension(990,200));
                profileInfo.setMaximumSize(new Dimension(990,200));
                profileInfo.setLayout(new BoxLayout(profileInfo, BoxLayout.PAGE_AXIS));
                JLabel profileName = new JLabel(current.getUsername());       profileName.setFont(new Font("Comic Sans Ms", Font.BOLD,  25));
                JLabel profilePhone = new JLabel(current.getPhoneNumber());   profilePhone.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileDOB = new JLabel(current.getDOB());             profileDOB.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                JLabel profileEmail = new JLabel(current.getEmail());         profileEmail.setFont(new Font("Comic Sans Ms", Font.PLAIN,  20));
                profileInfo.add(Box.createRigidArea(new Dimension(0, 25)));
                profileInfo.add(profileName);
                profileInfo.add(Box.createRigidArea(new Dimension(0, 20)));
                profileInfo.add(profilePhone);
                profileInfo.add(profileDOB);
                profileInfo.add(profileEmail);
                userDisplay.add(profileInfo);
                
                JPanel function = new JPanel(new GridLayout(2,1));
                function.setPreferredSize(new Dimension(100,100));
                function.setMaximumSize(new Dimension(100,100));
                JButton edit = new JButton("Edit");
                JButton delete = new JButton("Delete");
                function.add(edit);
                function.add(delete);
                edit.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        JButton button = new JButton("Submit");                    
                        button.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                tenantListing(user);
                                user.setVisible(true);
                            }
                        });
                        new EditProfile(current.getArray(),button , "Panel");
                    }
                });
                delete.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        deleteUserInFile(current.getUsername());
                        tenantListing(user);
                        user.setVisible(true);
                    }
    
                });
                userDisplay.add(function);

                JButton viewProfile = new JButton();
                viewProfile.setBorderPainted(false);
                viewProfile.setContentAreaFilled(false);
                viewProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
                viewProfile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ev){
                        new ViewProfile(current.getUsername());
                    }
                });
                viewProfile.add(userDisplay);
                tenant.add(viewProfile);
                tenant.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : registerAdmin
    Purpose         : This method is to register more new admin account.
    ************************************************************************************************************/ 
    public void registerAdmin(JPanel admin, JFrame user){
        JFrame registerAdmin = new JFrame();
        JPanel topPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel title = new JLabel("Admin Registration");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  25));
        title.setForeground(Color.white);
        topPan.setBackground(new Color(25, 149, 173));
        topPan.add(title);
        ArrayList<JTextField> txtFieldArraylist = new ArrayList<JTextField>();
        ArrayList<JComboBox<String>> DOB = new ArrayList<JComboBox<String>>();
        String[] days = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] year = new String[100]; 
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        for (int index = year.length-1; index >= 0; index--){
            year[index] = Integer.toString(currentYear - index);
        }

        JPasswordField userPassword = new JPasswordField();
        ArrayList<File> profilePictureFileArraylist = new ArrayList<File>();
        profilePictureFileArraylist.add(new File("defaultProfilePic.jpg"));

        String adminDetails[] = {"Username","Password","Profile Picture","Phone Number","Date Of Birth","Email","Security Question", "Answers"};
        String securityQuestion[] = {"In what city were you born?"," What is the name of your favorite pet?"," What is your mother's maiden name?","What high school did you attend?","What is the name of your first school?","What was the make of your first car?","What was your favorite food as a child?"," Where did you meet your spouse?"};
        JComboBox<String> comboBox = new JComboBox<String>(securityQuestion);
        
        JPanel centerPan = new JPanel();
        centerPan.setLayout(new BoxLayout(centerPan, BoxLayout.Y_AXIS));
        centerPan.setBackground(new Color(188, 186, 190));
        JPanel adminInput = new JPanel(new GridLayout(8,2));
        adminInput.setBackground(Color.WHITE);
        adminInput.setPreferredSize(new Dimension(1000,500));
        adminInput.setMaximumSize(new Dimension(1000,500));
        
        int counter=0;
        for(int i = 0; i < adminDetails.length; i++){
            adminInput.add(new JLabel(adminDetails[i]));
            if (i == 1){ //add jpasswordfield
                adminInput.add(userPassword);
            }
            else if(i == 2){ //add jfilechooser / browser image file function
                JButton profilePictureButton = new JButton("Browse");
                profilePictureButton.addActionListener(new ProfilePictureButtonActionListener(profilePictureFileArraylist,profilePictureButton));
                adminInput.add(profilePictureButton);
            }
            else if(i == 4){ //DOB
                JPanel dobPanel = new JPanel();
                dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
                DOB.add(new JComboBox<String>(days)); 
                DOB.add(new JComboBox<String>(months)); 
                DOB.add(new JComboBox<String>(year)); 
                dobPanel.add(DOB.get(0));
                dobPanel.add(new JLabel("  -  "));
                dobPanel.add(DOB.get(1));
                dobPanel.add(new JLabel("  -  "));
                dobPanel.add(DOB.get(2));
                dobPanel.setBackground(Color.WHITE); 
                adminInput.add(dobPanel);
            }
            else if (i == 6){
                adminInput.add(comboBox);
            }
            else{
                txtFieldArraylist.add(new JTextField());
                adminInput.add(txtFieldArraylist.get(counter));
                counter++;
            }
        }
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submit = new JButton("Submit");
        submit.addActionListener(new AdminRegisterSubmitActionListener(adminDetails, securityQuestion, txtFieldArraylist, DOB, comboBox, profilePictureFileArraylist, userPassword, registerAdmin));
        submitPanel.add(submit);
        submitPanel.setBackground(Color.WHITE);
        submitPanel.setPreferredSize(new Dimension(1000,40));
        submitPanel.setMaximumSize(new Dimension(1000,40));
    
        centerPan.add(Box.createRigidArea(new Dimension(0, 100)));
        centerPan.add(adminInput);
        centerPan.add(submitPanel);
        registerAdmin.add(topPan,BorderLayout.NORTH);
        registerAdmin.add(centerPan,BorderLayout.CENTER);
        registerAdmin.setSize(1000,800);
        registerAdmin.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        registerAdmin.setVisible(true);
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
    Method's Name   : deleteUserInFile
    Purpose         : This method is to delete user in file.
    ************************************************************************************************************/ 
    public void deleteUserInFile(String username){  
        File myFile = new File("userDetails.csv");
        ArrayList<String> detailsArraylist = new ArrayList<>();
        try{
            FileWriter myFileWriter = new FileWriter(myFile, true);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] detailsArray = br.readLine().split(","); 
                if (detailsArray[2].equals(username)){}
                if(!detailsArray[2].equals(username)){
                    String details = String.join(",",detailsArray);
                    detailsArraylist.add(details);
                }
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

        File propertyFile = new File("propertyDetails.csv");
        ArrayList<String> prodetailsArraylist = new ArrayList<>();
        try{
            FileWriter myFileWriter = new FileWriter(propertyFile, true);
            FileReader fileReader = new FileReader(propertyFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] detailsArray = br.readLine().split(","); 
                if (detailsArray[1].equals(username)){}
                if(!detailsArray[1].equals(username)){
                    String details = String.join(",",detailsArray);
                    prodetailsArraylist.add(details);
                }
            }          
            FileWriter tempFileWriter = new FileWriter(propertyFile, false);
            br.close();
            for(String s : prodetailsArraylist){
                tempFileWriter.append(s+"\n");
            }
            myFileWriter.close();
            tempFileWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : statusUpdateUserInFile
    Purpose         : This method is to change user's registration status to true.
    ************************************************************************************************************/
    public void statusUpdateUserInFile(String username){  
        File myFile = new File("userDetails.csv");
        ArrayList<String> detailsArraylist = new ArrayList<>();
        try{
            FileWriter myFileWriter = new FileWriter(myFile, true);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] detailsArray = br.readLine().split(","); 
                if (detailsArray[2].equals(username)){
                    detailsArray[1] = "true";
                    String details = String.join(",",detailsArray);
                    detailsArraylist.add(details);
                }
                if(!detailsArray[2].equals(username)){
                    String details = String.join(",",detailsArray);
                    detailsArraylist.add(details);
                }
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
    Programmer      : Leong Yi Hong
    Method's Name   : loadAdminDetails
    Purpose         : This method is to load Admin Detail.
    ************************************************************************************************************/
    public void loadAdminDetails(String[] adminDetailsArray, String username){ // load user data from csv file and return the arraylist
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[2].equals(username))
                    System.arraycopy(details, 0, adminDetailsArray, 0, details.length);
                    // add user data into arraylist      
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : loadUser
    Purpose         : This method is to load all user data into their own array.
    ************************************************************************************************************/
    public void loadUser(){
        adminArrayList.clear();
        tenantArrayList.clear();
        ownerArrayList.clear();
        agentArrayList.clear();
        File myFile = new File("userDetails.csv");
        try{
            FileReader fileReader = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fileReader);
            while(br.ready()){
                String[] details = br.readLine().split(","); 
                if(details[0].equals("Admin")){
                    adminArrayList.add(new Admin(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] , details[9]));
                }else if(details[0].equals("Tenant")){
                    tenantArrayList.add(new Tenant(details[1], details[2] , details[3], details[4], details[5], details[6] , details[7]));
                }else if(details[0].equals("Owner")){
                    ownerArrayList.add(new Owner(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8]));
                }else if(details[0].equals("Agent")){
                    agentArrayList.add(new Agent(details[1], details[2] , details[3], details[4], details[5], details[6], details[7], details[8] ,details[9],details[10],details[11],details[12]));
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
