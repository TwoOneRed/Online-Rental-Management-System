/***********************************************************************************************************
Class's Name    : Agent
Subclassing     : extend from User.java
Design Pattern  : Singleton
Purpose         : This class is extends from user and record the agent's data.
************************************************************************************************************/ 
public class Agent extends User{
    String agency, agentLicenseNum, agencyRegistrationNum, description, contactNumber;

    //Polymorphism    : Overloading
    public Agent(){}
    public Agent(String status,String profilePic,String username, String password, String phoneNumber, String DOB, String email, String agency, 
                 String agencyRegistrationNum, String agentLicenseNum, String contactNumber, String description){
        super("Agent",status,profilePic, username, password, phoneNumber, DOB, email);
        this.agency = agency;
        this.agencyRegistrationNum = agencyRegistrationNum;
        this.agentLicenseNum = agentLicenseNum;
        this.description = description;
        this.contactNumber = contactNumber;
    }  
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getAgency
    Purpose         : This method to get the agent's agency
    ************************************************************************************************************/
    public String getAgency(){
        return agency;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getAgentLicenseNum
    Purpose         : This method to get the agent's license Number
    ************************************************************************************************************/
    public String getAgentLicenseNum(){
        return agentLicenseNum;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getAgencyRegisNum
    Purpose         : This method to get the agent's agency registration number
    ************************************************************************************************************/
    public String getAgencyRegisNum(){
        return agencyRegistrationNum;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getDescription
    Purpose         : This method to get the agent's description
    ************************************************************************************************************/
    public String getDescription(){
        return description;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getContactNumber
    Purpose         : This method to get the agent's Contact Number
    ************************************************************************************************************/
    public String getContactNumber(){
        return contactNumber;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getArray
    Polymorphism    : Overriding
    Purpose         : This method to return the agent details array.
    ************************************************************************************************************/
    public String[] getArray(){
        String[] array = new String[13];
        for(int i = 0 ; i < 8 ; i++)
            array[i] = super.getArray()[i];
        array[8]= getAgency();
        array[9]= getAgencyRegisNum();
        array[10] = getAgentLicenseNum();
        array[11] = getDescription();
        array[12] = getContactNumber();
        return array;
    }
}
