/***********************************************************************************************************
Class's Name    : User
Design Pattern  : Singleton
Purpose         : This class record all users details and for other users file to extends
************************************************************************************************************/ 
public class User {
    String username;
    String password;
    String phoneNumber;
    String DOB;
    String email;
    String role;
    String profilePic; //profile pic name
    Boolean status;

    //Polymorphism    : Overloading
    public User(){}
    public User(String role, String status,String username, String password, String profilePic, String phoneNumber, String DOB, String email){
        this.role = role;
        this.username = username;
        this.password = password;
        this.profilePic = profilePic;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
        this.email = email;
        this.status = Boolean.valueOf(status);
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : setStatus
    Purpose         : This method to set user status from inactive to active.
    ************************************************************************************************************/
    public void setStatus(){
        this.status = true;
    }
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getStatus
    Purpose         : This method to get the status
    ************************************************************************************************************/
    public Boolean getStatus(){
        return status;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getRole
    Purpose         : This method to get the role
    ************************************************************************************************************/
    public String getRole(){
        return role;
    }
    
    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getprofilePic
    Purpose         : This method to get the profile pictures.
    ************************************************************************************************************/
    public String getprofilePic(){
        return profilePic;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getUsername
    Purpose         : This method to get the username.
    ************************************************************************************************************/
    public String getUsername(){
        return username;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getPassword
    Purpose         : This method to get the password.
    ************************************************************************************************************/
    public String getPassword(){
        return password;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getPhoneNumber
    Purpose         : This method to get the PhoneNumber.
    ************************************************************************************************************/
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getDOB
    Purpose         : This method to get the Date Of Birth.
    ************************************************************************************************************/
    public String getDOB(){
        return DOB;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getEmail
    Purpose         : This method to get the Email.
    ************************************************************************************************************/
    public String getEmail(){
        return email;
    }

    /***********************************************************************************************************
    Programmer      : Leong Yi Hong
    Method's Name   : getArray
    Purpose         : This method to return the user details array.
    ************************************************************************************************************/
    public String[] getArray(){
        String[] array = new String[8];
        array[0] = getRole();
        array[1] = String.valueOf(getStatus());
        array[2] = getUsername();
        array[3] = getPassword();
        array[4] = getprofilePic();
        array[5] = getPhoneNumber();
        array[6] = getDOB();
        array[7] = getEmail();
        return array;
    }
}
