/***********************************************************************************************************
Class's Name    : Tenant
Subclassing     : extend from User.java
Design Pattern  : Singleton
Purpose         : This class is extends from user and record the tenant's data.
************************************************************************************************************/ 
public class Tenant extends User{

    public Tenant(String status,String profilePic,String username, String password, String phoneNumber, String DOB, String email){
        super("Tenant", status ,profilePic, username, password, phoneNumber, DOB, email);
    }

    /***********************************************************************************************************
    Programmer      : Alvin
    Method's Name   : getArray
    Polymorphism    : Overriding
    Purpose         : This method to return the tenant details array.
    ************************************************************************************************************/
    public String[] getArray(){
        return super.getArray();
    }
}
