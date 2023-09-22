import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
/***********************************************************************************************************
Class's Name    : Register
Design Pattern  : Singleton
Purpose         : This class is to display the Register menu.
************************************************************************************************************/ 
public class Register extends JFrame{
    //role that user choose to register
    String role;
    
    public Register(){
        super("Sign Up");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Border emptyBorder = BorderFactory.createEmptyBorder();

        JPanel signUpBackground = new JPanel();
        signUpBackground.setLayout(new BoxLayout(signUpBackground, BoxLayout.Y_AXIS));
        signUpBackground.setBackground(new Color(175,238,238));

        JPanel topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,150));
        topPanel.setBackground(new Color(175,238,238));  

        JPanel southPanel = new JPanel();
        southPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 150));
        southPanel.setBackground(new Color(175,238,238));  

        JPanel roleMenu = new JPanel();
        roleMenu.setLayout(new BoxLayout(roleMenu, BoxLayout.Y_AXIS));
        roleMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        roleMenu.setPreferredSize(new Dimension(500,500));
        roleMenu.setMaximumSize(new Dimension(500,500));

        JPanel signUpTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel signUpWords = new JLabel("Sign Up ");
        signUpWords.setFont(new Font("Trebuchet MS", Font.BOLD,  26));

        signUpTitle.setPreferredSize(new Dimension(500,50));
        signUpTitle.setMaximumSize(new Dimension(500,50));
        signUpTitle.setBackground(Color.WHITE); 
        signUpTitle.add(Box.createRigidArea(new Dimension(15, 0)));
        signUpTitle.add(signUpWords);
        roleMenu.add(signUpTitle);

        JPanel signUpContent = new JPanel();
        signUpContent.setPreferredSize(new Dimension(500,450));
        signUpContent.setMaximumSize(new Dimension(500,450));
        signUpContent.setBackground(Color.WHITE);

        JPanel contentTop = new JPanel();
        contentTop.setLayout(new BoxLayout(contentTop, BoxLayout.Y_AXIS));
        contentTop.setMaximumSize(new Dimension(500,400));
        contentTop.setBackground(Color.WHITE);

        JButton agentButton = new JButton ("Sign up as Agent");
        agentButton.setMinimumSize(new Dimension(430,65));
        agentButton.setPreferredSize(new Dimension(430,65));
        agentButton.setMaximumSize(new Dimension(430,65));
        agentButton.setFont(new Font("Comic Sans MS", Font.PLAIN,  24));
        agentButton.setAlignmentX( Component.CENTER_ALIGNMENT );
        agentButton.setBackground(new Color(135,206,250));
        agentButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        agentButton.addActionListener(new ChooseRoleRegisterActionListener(role, this));

        JButton ownerButton = new JButton ("Sign up as Owner");
        ownerButton.setMinimumSize(new Dimension(430,65));
        ownerButton.setPreferredSize(new Dimension(430,65));
        ownerButton.setMaximumSize(new Dimension(430,65));
        ownerButton.setFont(new Font("Comic Sans MS", Font.PLAIN,  24));
        ownerButton.setAlignmentX( Component.CENTER_ALIGNMENT );
        ownerButton.setBackground(new Color(135,206,250));
        ownerButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        ownerButton.addActionListener(new ChooseRoleRegisterActionListener(role, this));

        JButton tenantButton = new JButton ("Sign up as Tenant");
        tenantButton.setMinimumSize(new Dimension(430,65));
        tenantButton.setPreferredSize(new Dimension(430,65));
        tenantButton.setMaximumSize(new Dimension(430,65));
        tenantButton.setFont(new Font("Comic Sans MS", Font.PLAIN,  24));
        tenantButton.setAlignmentX( Component.CENTER_ALIGNMENT );
        tenantButton.setBackground(new Color(135,206,250));
        tenantButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        tenantButton.addActionListener(new ChooseRoleRegisterActionListener(role, this));

        contentTop.add(Box.createRigidArea(new Dimension(0, 40)));
        contentTop.add(agentButton);
        contentTop.add(Box.createRigidArea(new Dimension(0, 50)));
        contentTop.add(ownerButton);
        contentTop.add(Box.createRigidArea(new Dimension(0, 50)));
        contentTop.add(tenantButton);
        contentTop.add(Box.createRigidArea(new Dimension(0, 60)));
        signUpContent.add(contentTop);
        
        JPanel southContent = new JPanel();
        southContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        southContent.setMaximumSize(new Dimension(500,Short.MAX_VALUE));
        southContent.setBackground(Color.WHITE);

        JPanel southContent2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southContent2.setMaximumSize(new Dimension(350,30));
        southContent2.setBackground(Color.WHITE);

        JLabel loginBait = new JLabel("Already have an account? ");
        loginBait.setFont(new Font("Verdana", Font.PLAIN, 15));

        JButton loginButton = new JButton("<html><u>Log in now<u>!<html>"); // create sign up button
        loginButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        loginButton.setBackground(Color.WHITE);
        loginButton.setBorder(emptyBorder);
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                dispose();
                new Login();
            }
        });
        southContent2.add(loginBait);
        southContent2.add(loginButton);
        signUpContent.add(southContent2);

        roleMenu.add(signUpContent);
        roleMenu.setBorder(border);
        signUpTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        signUpBackground.add(topPanel, BorderLayout.NORTH);
        signUpBackground.add(roleMenu, BorderLayout.CENTER);
        signUpBackground.add(southPanel, BorderLayout.SOUTH);
        add(signUpBackground);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}