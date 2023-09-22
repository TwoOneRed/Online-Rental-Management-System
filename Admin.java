
/***********************************************************************************************************
Class's Name    : Admin
Subclassing     : extend from User.java
Design Pattern  : Singleton 
Purpose         : This class is extends from user and record the admin's data.
************************************************************************************************************/ 
public class Admin extends User{
    String securityQues;
    String securityAns;
    
    //Polymorphism    : Overloading
    public Admin(){}
    public Admin(String status,String profilePic,String username, String password, String phoneNumber, String DOB, String email , String securityQues , String securityAns){
        super("Admin", status ,profilePic, username, password, phoneNumber, DOB, email);
        this.securityQues = securityQues;
        this.securityAns = securityAns;
    } 
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getSecurityQues
    Purpose         : This method to get the admin's security question
    ************************************************************************************************************/
    public String getSecurityQues(){
        return securityQues;
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getSecurityAns
    Purpose         : This method to get the admin's security question answer
    ************************************************************************************************************/
    public String getSecurityAns(){
        return securityAns;
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getArray
    Polymorphism    : Overriding
    Purpose         : This method to return the admin details array.
    ************************************************************************************************************/
    public String[] getArray(){
        String[] adminarray = new String[10];
        for(int i = 0 ; i < 8 ; i++)
            adminarray[i] = super.getArray()[i];

        adminarray[8]= getSecurityQues();
        adminarray[9]= getSecurityAns();
        return adminarray;
    }
}
