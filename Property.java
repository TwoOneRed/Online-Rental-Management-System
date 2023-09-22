/***********************************************************************************************************
Class's Name    : Property
Design Pattern  : Singleton
Purpose         : This class is to save the details of a property 
************************************************************************************************************/ 
public class Property {

    String role;
    String username;
    String propertyID;
    String propertyStatus; //property current on or of.

    // LISTING DETAILS
    String propertyType;
    String townshipDeveloper;
    String listingReferenceNumber;

    // LOCATION DETAILS
    String buildingName;
    String state;
    String city;
    String postcode;
    String roadName;
    String block;
    String unitHouseNumber;
    String floor;

    // PROPERTY DETAILS
    String rentalPrice;
    String builtUpArea;
    String rentalRate;
    String tenure;
    String furnishing;
    String numberOfBedroom;
    String numberOfBathroom;
    String numberOfParking;

    // PROPERTY FACILITIES AND FEATURES
    String propertyFacilities;
    String propertyFeatures;

    // DESCRIPTION AND IMAGES
    String propertyDescription;
    String propertyImages;
    String comment;

    public Property(String role, String username, String propertyID, String propertyStatus, String propertyType, String townshipDeveloper, String listingReferenceNumber, String buildingName,
    String state, String city, String postcode, String roadName, String block, String unitHouseNumber, String floor, String rentalPrice,
    String builtUpArea, String rentalRate, String tenure, String furnishing, String numberOfBedroom, String numberOfBathroom, String numberOfParking,
    String propertyFacilities,String propertyFeatures,String propertyDescription,String propertyImages, String comment){

        this.role = role;
        this.username = username;
        this.propertyID = propertyID;
        this.propertyStatus = propertyStatus;
        this.propertyType = propertyType;
        this.townshipDeveloper = townshipDeveloper;
        this.listingReferenceNumber = listingReferenceNumber;
        this.buildingName = buildingName;
        this.state = state;
        this.city = city;
        this.postcode = postcode;
        this.roadName = roadName;
        this.block = block;
        this.unitHouseNumber = unitHouseNumber;
        this.floor = floor;
        this.rentalPrice = rentalPrice;
        this.builtUpArea = builtUpArea;
        this.rentalRate = rentalRate;
        this.tenure = tenure;
        this.furnishing = furnishing;
        this.numberOfBedroom = numberOfBedroom;
        this.numberOfBathroom = numberOfBathroom;
        this.numberOfParking = numberOfParking;
        this.propertyFacilities = propertyFacilities;
        this.propertyFeatures = propertyFeatures;
        this.propertyDescription = propertyDescription;
        this.propertyImages = propertyImages;
        this.comment = comment;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getRole
    Purpose         : This method is used to get the role that registered this property.
    ************************************************************************************************************/
    public String getRole(){
        return role;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getUsername
    Purpose         : This method is used to get the username that registered this property.
    ************************************************************************************************************/
    public String getUsername(){
        return username;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyID
    Purpose         : This method is used to get the ID of a property.
    ************************************************************************************************************/
    public String getPropertyID(){
        return propertyID;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyStatus
    Purpose         : This method is used to get the status of property
    ************************************************************************************************************/
    public String getPropertyStatus(){
        return propertyStatus;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyType
    Purpose         : This method is used to get the type of property. 
    ************************************************************************************************************/
    public String getPropertyType(){
        return propertyType;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getTownshipDeveloper
    Purpose         : This method is used to get the Township Developer of property. 
    ************************************************************************************************************/
    public String getTownshipDeveloper(){
        return townshipDeveloper;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getListingReferenceNumber
    Purpose         : This method is used to get the reference number of the property. 
    ************************************************************************************************************/
    public String getListingReferenceNumber(){
        return listingReferenceNumber;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getBuildingName
    Purpose         : This method is used to get the name of property. 
    ************************************************************************************************************/
    public String getBuildingName(){
        return buildingName;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getState
    Purpose         : This method is used to get the state of property located. 
    ************************************************************************************************************/
    public String getState(){
        return state;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getCity
    Purpose         : This method is used to get the city of property located. 
    ************************************************************************************************************/
    public String getCity(){
        return city;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPostcode
    Purpose         : This method is used to get the postcode of property. 
    ************************************************************************************************************/
    public String getPostcode(){
        return postcode;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getAddress
    Purpose         : This method is used to get the address of property. 
    ************************************************************************************************************/
    public String getRoadName(){
        roadName = roadName.replace("|",",");
        return roadName;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getBlock
    Purpose         : This method is used to get the block number of property. 
    ************************************************************************************************************/
    public String getBlock(){
        return block;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getUnitHouseNumber
    Purpose         : This method is used to get the unit of house number of property. 
    ************************************************************************************************************/
    public String getUnitHouseNumber(){
        return unitHouseNumber;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getFloor
    Purpose         : This method is used to get the floor of property. 
    ************************************************************************************************************/
    public String getFloor(){
        return floor;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getRentalPrice
    Purpose         : This method is used to get the rental price of property. 
    ************************************************************************************************************/
    public String getRentalPrice(){
        return rentalPrice;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getBuiltUpArea
    Purpose         : This method is used to get the built up area of a property. 
    ************************************************************************************************************/
    public String getBuiltUpArea(){
        return builtUpArea;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getRentalRate
    Purpose         : This method is used to get the rental rate of the property. 
    ************************************************************************************************************/
    public String getRentalRate(){
        return rentalRate;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getTenure
    Purpose         : This method is used to get the tenure of property. 
    ************************************************************************************************************/
    public String getTenure(){
        return tenure;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getFurnishing
    Purpose         : This method is used to get the furnishing option of property. 
    ************************************************************************************************************/
    public String getFurnishing(){
        return furnishing;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getNumberOfBedroom
    Purpose         : This method is used to get number of bedrooms of property. 
    ************************************************************************************************************/
    public String getNumberOfBedroom(){
        return numberOfBedroom;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getNumberOfBathroom
    Purpose         : This method is used to get number of bathrooms of property. 
    ************************************************************************************************************/
    public String getNumberOfBathroom(){
        return numberOfBathroom;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getNumberOfParking
    Purpose         : This method is used to get number of parkings of property. 
    ************************************************************************************************************/
    public String getNumberOfParking(){
        return numberOfParking;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyFacilities
    Purpose         : This method is used to get the facilities that is available in property. 
    ************************************************************************************************************/
    public String getPropertyFacilities(){
        return propertyFacilities;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyFeatures
    Purpose         : This method is used to get the features that is available in property. 
    ************************************************************************************************************/
    public String getPropertyFeatures(){
        return propertyFeatures;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyDescription
    Purpose         : This method is used to get the description of property. 
    ************************************************************************************************************/
    public String getPropertyDescription(){
        return propertyDescription;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getPropertyImages
    Purpose         : This method is used to get the images of property. 
    ************************************************************************************************************/
    public String getPropertyImages(){
        return propertyImages;
    }
    /***********************************************************************************************************
    Programmer      : Tan Sin Zhung
    Method's Name   : getComment
    Purpose         : This method is used to get the comment that are inserted by users in the property detail page. 
    ************************************************************************************************************/
    public String getComment(){
        return comment;
    }
}
