package id.co.indomobil.test.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.SyncDetailEntity;
import id.co.indomobil.test.DBHelper.SynDetailHelper;

public class NetworkUtil {
    Context context;

    public static String getConnectivityStatusString(final Context context) {


        SQLiteDatabase db = null;
        String status = null;
        String retvalue="{}";
        final List<SyncDetailEntity> detailSync = new ArrayList<>();

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {


            SynDetailHelper SE = new SynDetailHelper(context);

            List<SyncDetailEntity> P;
            P = SE.SelectDetailData("");
            db = SE.getReadableDatabase();

            for (SyncDetailEntity obj: P) {
                SyncDetailEntity objData = new SyncDetailEntity();
                objData.idtrans = obj.getTransID();
                objData.productID = obj.getProductID();
                objData.price = obj.getPrice();
                objData.qty = obj.getQty();
                detailSync.add(objData);
            }


//            xxx = detailSync.toString();
            JsonHelper.serializeObjectToJson(detailSync);


//            StringRequest stringRequest = new StringRequest(Request.Method.POST, "",
//                    new Response.Listener<String>() {
//
//                        @Override
//                        public void onResponse(String detailSync) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(detailSync.toString());
//                            } catch (JSONException e) {
//                            }
//                        }
//                    });
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, IndoStationServices.VoucherDataAPI_UPLOAD,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String detailSync) {
////                            String rps = response;
////                            Toast.makeText(context, "response: " + detailSync, Toast.LENGTH_LONG).show();
////                                status = detailSync.toString();
////
//                            try {
//                                JSONObject jsonObject = new JSONObject(detailSync.toString());
//                                Log.d("VoucherDataAPI_UPLOAD", "onResponse: "+detailSync.toString());
//                                status = "Internet is available";
//
//                            } catch (JSONException e) {
//
//
//                            }
//
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//
//                        }
//                    });


//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                status = "Wifi enabled";
//                return status;
//            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                status = "Mobile data enabled";
//                return status;
//            }

            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://18.139.0.190:10000/api/auth/login";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // your response
                            Log.d("Response ", "onResponse: "+response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error
                }
            }){
                @Override
                public byte[] getBody() throws AuthFailureError {
                    String your_string_json = detailSync.toString() ; // put your json
                    return your_string_json.getBytes();
                }
            };
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
            queue.start();

            status = "Internet is available";
        } else {
            status = "No internet is available";
            return status;
        }
        return status;
    }

//    public static String sendPostRequest(String requestUrl, String payload) {
//        try {
//            URL url = new URL(requestUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            Log.d("sendPostrequest", "sendPostRequest: " + writer);
//
////            writer.write(payload);
////            writer.close();
////            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////            StringBuffer jsonString = new StringBuffer();
////            String line;
////            while ((line = br.readLine()) != null) {
////                jsonString.append(line);
////            }
////            br.close();
////            connection.disconnect();
//            status = "Internet is available";
//            return status;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }

//    public StringRequest sendVoucherData() {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        final SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("message", "Starting upload voucher data..."); //InputString: from the EditText
//        editor.commit();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, IndoStationServices.VoucherDataAPI_UPLOAD,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //String rps = response;
//                        //.makeText(mCtx, "response: " + response, Toast.LENGTH_LONG).show();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.toString());
//                        } catch (JSONException e) {
//                            editor.putString("message", "Failed upload voucher data");
//                            editor.putBoolean("isFinishedUpload", true); //InputString: from the EditText
//                            editor.putBoolean("isFailedUpload", true); //InputString: from the EditText
//                            editor.commit();
//                            Intent intent = new Intent(context, MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.putExtra("isFailedSyncSend", true);
//                            context.startActivity(intent);
//                        }
//                        //Toast.makeText(mCtx, "Sync Sales Header selesai - IndoPOS", Toast.LENGTH_LONG).show();
//                        editor.putString("message", "Finished upload voucher data..."); //InputString: from the EditText
//                        editor.putBoolean("isFinishedUpload", true); //InputString: from the EditText
//                        editor.putBoolean("isFailedUpload", false); //InputString: from the EditText
//                        editor.commit();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Toast.makeText(mCtx, "error send Voucher Data : " + error.toString(), Toast.LENGTH_LONG).show();
//                        editor.putString("message", "Failed upload voucher data");
//                        editor.putBoolean("isFinishedUpload", true); //InputString: from the EditText
//                        editor.putBoolean("isFailedUpload", true); //InputString: from the EditText
//                        editor.commit();
//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("isFailedSyncSend", true);
//                        context.startActivity(intent);
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Type", "DbTransactionDataUpload");
//                params.put("ObjectType", "Voucher");
//                params.put("VoucherData", retvalue);
//                return params;
//            }
//        };
//
//        stringRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 100000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//
//        return stringRequest;
//    }


}






