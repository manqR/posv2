package id.co.indomobil.test.ui.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.co.indomobil.test.DBHelper.DatabaseHelper;
import id.co.indomobil.test.R;
import id.co.indomobil.test.DBHelper.SynDetailHelper;

public class SyncFragment extends Fragment {

    private Context mContext = null;
    DatabaseHelper mDatabaseHelper;
    SynDetailHelper mDatabaseDetailHelper;
    private SyncViewModel SyncViewModel;
    private Button btnAdd, btnViewData;
    private EditText txtTrans;
    private EditText txtPrice;
    private EditText txtDate;
    private EditText txtProductID;
    private EditText txtQty;
    private EditText txtPurchase;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        SyncViewModel =
//                ViewModelProviders.of(this).get(SyncViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sync, container, false);
        mContext = getActivity();
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
//
//        if (connectivityManager.getActiveNetworkInfo() == null) {
//            toastMessage("Device Tidak Terhubung Jaringan Internet");
//        }

        mDatabaseHelper = new DatabaseHelper(mContext);
        mDatabaseDetailHelper = new SynDetailHelper(mContext);

        txtTrans = (EditText) root.findViewById(R.id.txtidtrans);
        txtPrice = (EditText) root.findViewById(R.id.txttotalPrice);
        txtDate = (EditText) root.findViewById(R.id.txtDate);
        txtProductID = (EditText) root.findViewById(R.id.txtProductID);
        txtPurchase = (EditText) root.findViewById(R.id.txtPurchase);
        txtQty = (EditText) root.findViewById(R.id.txtQty);

        btnAdd = (Button) root.findViewById(R.id.submitButton);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer newTrans = Integer.valueOf(txtTrans.getText().toString());
                Integer newPrice= Integer.valueOf(txtPrice.getText().toString());
                Integer newDate = Integer.valueOf(txtDate.getText().toString());
                Integer newProductID = Integer.valueOf(txtProductID.getText().toString());
                Integer newPurchase = Integer.valueOf(txtPurchase.getText().toString());
                Integer newQty = Integer.valueOf(txtQty.getText().toString());

                if (txtTrans.length() != 0) {
                    AddData(newTrans,newPrice,newDate, newProductID, newPurchase, newQty);
                    txtTrans.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });
        return root;
    }
    public void AddData(Integer newTrans, Integer newPrice, Integer newDate, Integer newProductID, Integer newPurchase, Integer newQty) {
        boolean insertData = mDatabaseHelper.addData(newTrans,newPrice,newDate,0);
        boolean insertDataDetail = mDatabaseDetailHelper.addDataDetail(newTrans,newProductID,newPurchase,newQty);

        if (insertData && insertDataDetail) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);

            if (connectivityManager.getActiveNetworkInfo() == null) {
                toastMessage("Data Berhasil di input, Synchronize Gagal Device Tidak Terhubung Internet");
            }else{
                toastMessage("Data Successfully Inserted!");
            }
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }
}