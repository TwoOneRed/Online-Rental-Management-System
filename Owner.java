/***********************************************************************************************************
Class Name      : Owner
Subclassing     : extend from User.java
Design Pattern  : Singleton
Purpose         : This class is extends from user and record the owner's data.
************************************************************************************************************/
public class Owner extends User{
    String contactNumber;  
    //Polymorphism    : Overloading
    public Owner(){}
    public Owner(String status,String profilePic,String username, String password, String phoneNumber, String DOB, String email, String contactNumber){
        super("Owner", status ,profilePic, username, password, phoneNumber, DOB, email);
        this.contactNumber = contactNumber;
    }
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : getContactNumber
    Purpose         : This method is use to get the contact number of owner. 
    ************************************************************************************************************/
    public String getContactNumber(){
        return contactNumber;
    }
    
    /***********************************************************************************************************
    Programmer      : Hooi Thing Hong
    Method's Name   : getArray
    Polymorphism    : Overriding
    Purpose         : This method to return the user details array.
    ************************************************************************************************************/
    public String[] getArray(){
        String[] array = new String[13];
        for(int i = 0 ; i < 8 ; i++)
            array[i] = super.getArray()[i];
        array[8]= getContactNumber();
        return array;
    }
}
