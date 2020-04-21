package id.co.indomobil.test.DBEntity;


import java.sql.Date;

public class mCityEntity {

    private String CITY_CODE;
    private String CITY_NAME;
    private String CREATION_USER_ID;
    private Date CREATION_DATETIME;
    private String CHANGE_USER_ID;
    private Date CHANGE_DATETIME;

    public mCityEntity(String city_name, String city_code) {
        this.CITY_CODE = city_code;
        this.CITY_NAME = city_name;
    }

    public mCityEntity() {

    }

    public String getCITY_CODE(){
        return CITY_CODE;
    }

    public void setCITY_CODE(String CITY_CODE){
        this.CITY_CODE = CITY_CODE;
    }

    public String getCITY_NAME(){
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME){
        this.CITY_NAME = CITY_NAME;
    }

    public String getCREATION_USER_ID(){
        return CREATION_USER_ID;
    }

    public void setCREATION_USER_ID(String CREATION_USER_ID){
        this.CREATION_USER_ID = CREATION_USER_ID;
    }

    public String getCHANGE_USER_ID(){
        return CHANGE_USER_ID;
    }

    public void setCHANGE_USER_ID(String CHANGE_USER_ID){
        this.CHANGE_USER_ID = CHANGE_USER_ID;
    }

    public Date getCREATION_DATETIME(){
        return CREATION_DATETIME;
    }
    public void setCREATION_DATETIME(Date CREATION_DATETIME){
        this.CREATION_DATETIME = CREATION_DATETIME;
    }

    public Date getCHANGE_DATETIME(){
        return CHANGE_DATETIME;
    }
    public void setCHANGE_DATETIME(Date CHANGE_DATETIME){
        this.CHANGE_DATETIME = CHANGE_DATETIME;
    }

}

