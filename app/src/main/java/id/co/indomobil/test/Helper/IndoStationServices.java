package id.co.indomobil.test.Helper;

public class IndoStationServices {

    private static String ServiceIp_IndoStation_Prod = "http://indostation.indomobil.co.id";
    private static String ServiceIp_IndoStation_Testing = "http://testing-pos.indomobil.co.id/";
    public static String ServiceIp_IndoStation = ServiceIp_IndoStation_Testing;

    /* Change Password*/
    public static String ChangePassword = ServiceIp_IndoStation + "/WebServices/Upload/SynchUploadDataMasterSvc.ashx";

    /*Upload*/
    public static String VoucherDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataVoucherSvc.ashx";

}
