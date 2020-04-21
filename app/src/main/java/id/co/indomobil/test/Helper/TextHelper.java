package id.co.indomobil.test.Helper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.co.indomobil.test.DBEntity.tGRPO0Entity;
import id.co.indomobil.test.DBEntity.tPO0Entity;
import id.co.indomobil.test.DBEntity.tSalesEntity;
import id.co.indomobil.test.DBHelper.tGRPO0Helper;
import id.co.indomobil.test.DBHelper.tPO0Helper;
import id.co.indomobil.test.DBHelper.tSalesHelper;

public class TextHelper {
    public TextHelper() {
    }

    public static String convertToMoneyFormat_IDR(String originalValue) {
        String retValue = "";
        int value = (int) Math.round(Double.parseDouble(originalValue));

        DecimalFormat mataUangIndonesia =  (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.ENGLISH);
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        retValue = mataUangIndonesia.format(value);

        return retValue;
    }

    public static String convertToMoneyFormat_IDR(String originalValue, boolean isWithDecimalValue) {
        String retValue = "";
        int value = (int) Math.round(Double.parseDouble(originalValue));

        DecimalFormat mataUangIndonesia =  (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.ENGLISH);
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        retValue = mataUangIndonesia.format(value);
        if (!isWithDecimalValue) {
            int panjangFormat = retValue.length();
            retValue = retValue.substring(0, panjangFormat - 3);
        }
        return retValue;
    }

    public static String convertToMoneyFormat_IDR(String originalValue, String currencySymbol) {
        String retValue = "";
        int value = (int) Math.round(Double.parseDouble(originalValue));

        DecimalFormat mataUangIndonesia =  (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.ENGLISH);
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol(currencySymbol + " ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        retValue = mataUangIndonesia.format(value);
        return retValue;
    }

    public static String convertToMoneyFormat_IDR(String originalValue, String currencySymbol, boolean isWithDecimalValue) {
        String retValue = "";
        int value = (int) Math.round(Double.parseDouble(originalValue));

        DecimalFormat mataUangIndonesia =  (DecimalFormat) DecimalFormat.getCurrencyInstance(Locale.ENGLISH);
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol(currencySymbol + " ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        mataUangIndonesia.setDecimalFormatSymbols(formatRp);
        retValue = mataUangIndonesia.format(value);
        if (!isWithDecimalValue) {
            int panjangFormat = retValue.length();
            retValue = retValue.substring(0, panjangFormat - 3);
        }

        return retValue;
    }

    public static double getValueFromMoneyFormat_IDR(String originalValue) {
        double retValue = 0.0d;
        int divider = 1;
        String cleanString = originalValue.toString().replaceAll("[Rp$,.]", "");

        if (originalValue.toString().contains(",")) {
            StringBuffer s = new StringBuffer(originalValue.substring(originalValue.toString().indexOf(",")+1,originalValue.length()).length());
            for (int i = 0; i < s.capacity(); i++) {
                s.append(0);
            }
            divider = Integer.parseInt ("1" + s);
            //retValue = Double.parseDouble(cleanString.trim()) / 100;
            retValue = Double.parseDouble(cleanString.trim()) / divider;
        }
        else
        {
            retValue = Double.parseDouble(cleanString.trim());
        }
        //System.out.println(parse.intValue());
        return retValue;
    }

    public static double getValueFromMoneyFormat_IDR(String originalValue, boolean isWithCommasValue) {
        double retValue = 0.0d;
        String cleanString = originalValue.toString().replaceAll("[Rp$,.]", "");

        if (!isWithCommasValue) {
            retValue = Double.parseDouble(cleanString.trim());
        }


        //System.out.println(parse.intValue());
        return retValue;
    }

    public static String formatDateToString_DD_MMMM_YYYY_Hour_Minute(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "dd" + dateSeparator + "MMMM" + dateSeparator + "yyyy";
        if (isWithTime)
            pattern = pattern + " HH:mm";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }

        return retValue;
    }



    public static String formatDateToString_DD_MMM_YYYY(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "dd" + dateSeparator + "MMM" + dateSeparator + "yyyy";
        if (isWithTime)
            pattern = pattern + "  HH:mm:ss";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }

        return retValue;
    }

    public static String formatDateToString_Hour_Minute(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "dd" + dateSeparator + "MMM" + dateSeparator + "yyyy";
        if (isWithTime)
            pattern ="  HH:mm";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }

        return retValue;
    }

    public static String formatDateToString_DD_MM_YYYY_Hour_Minute(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "dd" + dateSeparator + "MM" + dateSeparator + "yyyy";
        if (isWithTime)
            pattern = pattern + " HH:mm";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }

        return retValue;
    }

    public static String formatDateToString_DD_MMM_YYYY_Hour_Minute(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "dd" + dateSeparator + "MMM" + dateSeparator + "yyyy";
        if (isWithTime)
            pattern = pattern + " HH:mm";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }

        return retValue;
    }

    public static String formatDateToString_DD_MM_YYYY(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "dd" + dateSeparator + "MM" + dateSeparator + "yyyy";
        if (isWithTime)
            pattern = pattern + "  HH:mm:ss";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }

        return retValue;
    }

    public static String formatDateToString_YYYY_MM_DD(java.util.Date date, String dateSeparator, boolean isWithTime) {
        String retValue = "";
        String pattern = "yyyy" + dateSeparator + "MM" + dateSeparator + "dd";
        if (isWithTime)
            pattern = pattern + "  HH:mm:ss";
        if (date != null) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
            retValue = DATE_FORMAT.format(date);
        }
        return retValue;
    }

    public static String CompareDate(String sdate1, String sdate2) {

        String retValue = "";
        try {

            sdate1 = formatStringDate(sdate1, "dd-MMM-yyyy", "dd/MM/yyyy");
            sdate2 = formatStringDate(sdate2, "dd-MMM-yyyy", "dd/MM/yyyy");

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            //String str1 = "12/10/2013";
            Date date1 = formatter.parse(sdate1);

            //String str2 = "13/10/2013";
            Date date2 = formatter.parse(sdate2);

            if (date1.compareTo(date2) <= 0) {
                retValue = "true";
            }else {
                retValue = "false";
            }

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return retValue;
    }

    public static String formatStringDate(String sdate, String SinputFormat, String SoutputFormat) {
        DateFormat inputFormat = new SimpleDateFormat(SinputFormat);
        DateFormat outputFormat = new SimpleDateFormat(SoutputFormat);

        String inputDateStr = sdate;
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        return outputDateStr;
    }

    public static Date formatStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String getCalculatedDate(String date, String dateFormat, int days) {
        String result;
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(s.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
        result = sdf1.format(c.getTime());

        /*Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DATE, days);
        return s.format(new Date(String.valueOf(cal.getTime())));*/
        return result;
    }

    public static Date formatDateIn_YYYY_MM_DD(java.util.Date date, String dateSeparator, boolean isWithTime) {
        Date retValue = date;

        String pattern = "yyyy" + dateSeparator + "MM" + dateSeparator + "dd";
        if (isWithTime)
            pattern = pattern + "  HH:mm:ss";
        if (date != null) {
            try {
                String DateInString = formatDateToString_YYYY_MM_DD(date, dateSeparator, isWithTime);
                SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
                retValue = DATE_FORMAT.parse(DateInString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return retValue;
    }

    public static <T> String getNextDocNoFormat(Class<T> Document, Context ctx) {
        String retValue = "";
        SQLiteDatabase db;
        if (Document.getName() == tSalesEntity.class.getName()) { //dokumen Sales Order
            tSalesHelper dbSales = new tSalesHelper(ctx);
            String nextSysNo = dbSales.GetNextSalesSysNO().toString();
            String dateFormat = formatDateToString_YYYY_MM_DD(new java.util.Date(), "", false);
            retValue = "SO-" + dateFormat + "-" + nextSysNo;
        } else if (Document.getName() == tPO0Entity.class.getName()) { //dokumen Purchase Order
            tPO0Helper currentDB = new tPO0Helper(ctx);
            String nextDocNo = currentDB.GetNextPODocNO().toString();
            String dateFormat = formatDateToString_YYYY_MM_DD(new java.util.Date(), "", false);
            retValue = "PO-" + nextDocNo;
            //retValue = nextDocNo;
        } else if (Document.getName() == tGRPO0Entity.class.getName()) { //dokumen Purchase Order
            tGRPO0Helper currentDB = new tGRPO0Helper(ctx);
            String nextDocNo = currentDB.GetNextPODocNO().toString();
            String dateFormat = formatDateToString_YYYY_MM_DD(new java.util.Date(), "", false);
            retValue = "GRPO-" + nextDocNo;
            //retValue = nextDocNo;
        } else
            retValue = "";
        return retValue;
    }

    public static  String ConvertJsonDate(String jsondate)
    {

        //String jsondate="\/Date(1427959670000)\/";
        jsondate=jsondate.replace("/Date(", "").replace(")/", "");
        long time = Long.parseLong(jsondate);
        Date d= new Date(time);
        //Log.d("Convertd date is:"+new SimpleDateFormat("dd/MM/yyyy").format(d).toString());

        return new SimpleDateFormat("dd-MMM-yyyy").format(d).toString();
    }
}
