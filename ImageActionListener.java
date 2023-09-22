import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/***********************************************************************************************************
Class's Name    : ImageActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after the user clicked the property images
                in the property details page.
************************************************************************************************************/ 
public class ImageActionListener implements ActionListener{
    //Aggregation from Property.
    Property property;
    int counter = 0;
    public ImageActionListener(Property property){
        this.property = property;
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : actionPerformed
    Purpose         : This method is to display the property images.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame("Image of Property");
        JPanel topPanel = new JPanel ();
        topPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,100));
        topPanel.setBackground(new Color(0,191,255));


        JLabel topTitle = new JLabel("Image of Property " + property.getBuildingName());
        topTitle.setFont(new Font("Trebuchet MS", Font.BOLD,  26));
        topPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        topPanel.add(topTitle);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setMinimumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        centerPanel.setPreferredSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        centerPanel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        centerPanel.setBackground(Color.WHITE);

        JPanel picturePanel = new JPanel();
        picturePanel.setBackground(new Color(105,105,105));
        picturePanel.setMinimumSize(new Dimension(Short.MAX_VALUE, 600));
        picturePanel.setPreferredSize(new Dimension(Short.MAX_VALUE, 600));
        picturePanel.setMaximumSize(new Dimension(Short.MAX_VALUE,600));


        String picture[] = property.getPropertyImages().split("\\|");
        List<String> printPic = Arrays.asList(picture);
        ArrayList<String> listPicture = new ArrayList<String>(printPic);
        JLabel pic = new JLabel(loadImage(listPicture.get(0),750,550));
        picturePanel.add(pic);
        centerPanel.add(picturePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setMinimumSize(new Dimension(450,150));
        buttonPanel.setPreferredSize(new Dimension(450,150));
        buttonPanel.setMaximumSize(new Dimension(450,150));
        buttonPanel.setBackground(Color.WHITE);

        JButton nextPicture = new JButton("Next");
        nextPicture.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                counter+=1;
                if (counter == listPicture.size()){
                    counter = 0;
                }
                pic.setIcon(loadImage(listPicture.get(counter),750,550));
            }
        });
        nextPicture.setMinimumSize(new Dimension(120,50));
        nextPicture.setPreferredSize(new Dimension(120,50));
        nextPicture.setMaximumSize(new Dimension(120,50));
        nextPicture.setFont(new Font("Verdana", Font.PLAIN, 15));

        JButton prevPicture = new JButton("Previous");
        prevPicture.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                counter-=1;
                if (counter < 0){
                    counter = listPicture.size()-1;
                }
                pic.setIcon(loadImage(listPicture.get(counter),750,550));
            }
        });
        prevPicture.setMinimumSize(new Dimension(120,50));
        prevPicture.setPreferredSize(new Dimension(120,50));
        prevPicture.setMaximumSize(new Dimension(120,50));
        prevPicture.setFont(new Font("Verdana", Font.PLAIN, 15));


        buttonPanel.add(prevPicture);
        buttonPanel.add(nextPicture);
        centerPanel.add(buttonPanel);


        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setSize(1000,800);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
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
}