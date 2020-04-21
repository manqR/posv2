package id.co.indomobil.test.Helper;

public class Api {

    private static String ServiceIp_IndoStation_Prod = "http://indostation.indomobil.co.id";
    private static String serviceIP_IndostationTest = "http://testing-pos.indomobil.co.id";
    public static String ServiceIp_IndoStation = serviceIP_IndostationTest;

    /*Checker*/

    /* Download Data Master Section*/
    //public static String MetaDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/Services/MobDeviceAPI/MobDevDB.ashx?Type=DbMetadata&LatestVersionNo=";
    public static String MasterDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataMasterSvc.ashx";

    /* Download Data Transaction Section*/
    public static String SalesDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataSalesSvc.ashx";
    public static String PODataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataPOSvc.ashx";
    public static String DODataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataDOSvc.ashx";
    public static String GRDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataGRSvc.ashx";
    public static String AdjustmentDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataAdjustmentSvc.ashx";
    public static String ShiftLogDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataShiftLogSvc.ashx";
    public static String StockDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataStockSvc.ashx";
    public static String SrcDocDataAPI_DOWNLOAD = ServiceIp_IndoStation+"/WebServices/Download/SynchDownloadDataSrcDocSvc.ashx";

    /*Upload Data Master Section*/
    public static String ChangePassword = ServiceIp_IndoStation + "/WebServices/Upload/SynchUploadDataMasterSvc.ashx";
    /*Upload Data Transaction Section*/
    public static String SalesDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataSalesSvc.ashx";
    public static String PODataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataPOSvc.ashx";
    public static String GRDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataGRSvc.ashx";
    public static String ShiftLogDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataShiftLogSvc.ashx";
    public static String CCDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataCCSvc.ashx";
    public static String AdjustmentDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataAdjustmentSvc.ashx";
    public static String StockDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataStockSvc.ashx";
    public static String SrcDocDataAPI_UPLOAD = ServiceIp_IndoStation+"/WebServices/Upload/SynchUploadDataSrcDocSvc.ashx";
    public static String UploadDB = ServiceIp_IndoStation+"/WebServices/Upload/UploadDB.ashx";

}

