import java.awt.event.*;
import javax.swing.*;
/***********************************************************************************************************
Class's Name    : LogOutActionListener
Design Pattern  : Singleton
Purpose         : This class is a action listener and it is triggered after user click log out.
************************************************************************************************************/ 
public class LogOutActionListener implements ActionListener{
    JFrame frame;
    public LogOutActionListener(JFrame frame){
        this.frame = frame;
    }
     /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : actionPerformed
    Purpose         : This method is to do process for log out.
    ************************************************************************************************************/
    public void actionPerformed(ActionEvent e){
        frame.dispose();
        new Main();
    }
}