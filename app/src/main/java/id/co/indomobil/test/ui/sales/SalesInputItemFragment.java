package id.co.indomobil.test.ui.sales;



import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mCategoryEntity;
import id.co.indomobil.test.DBEntity.mItemEntity;
import id.co.indomobil.test.DBEntity.mMixMatchEntity;
import id.co.indomobil.test.DBEntity.mPromo1Entity;
import id.co.indomobil.test.DBEntity.tSales1Entity;
import id.co.indomobil.test.DBHelper.mCategoryHelper;
import id.co.indomobil.test.DBHelper.mComGenVariableHelper;
import id.co.indomobil.test.DBHelper.mItemHelper;
import id.co.indomobil.test.DBHelper.mMixMatchHelper;
import id.co.indomobil.test.DBHelper.mPromo0Helper;
import id.co.indomobil.test.DBHelper.mPromo1Helper;
import id.co.indomobil.test.DBHelper.tSales1Helper;
import id.co.indomobil.test.DBHelper.tSalesHelper;
import id.co.indomobil.test.DBHelper.tShiftLogHelper;
import id.co.indomobil.test.DataAdapter.ItemListDataAdapter;
import id.co.indomobil.test.Helper.ApplicationController;
import id.co.indomobil.test.Helper.TextHelper;
import id.co.indomobil.test.Helper.UserSessionManager;
import id.co.indomobil.test.MainActivity;
import id.co.indomobil.test.R;

public class SalesInputItemFragment extends Fragment {


    private Context mContext = null;

    private tSalesHelper dbSales = null;
    private tSales1Helper dbSales1 = null;
    private mPromo0Helper dbPromo0 = null;
    private mPromo1Helper dbPromo1 = null;
    private mMixMatchHelper dbMixMatch = null;
    private mCategoryHelper dbCategory = null;
    private mComGenVariableHelper dbComgen = null;
    private tShiftLogHelper dbShift = null;
    private mItemHelper dbItem = null;

    private List<tSales1Entity> tSalesItemList;
    private List<mCategoryEntity> mCategoryEntityListAll;
    private List<mMixMatchEntity> mMixMatchEntityListAll;
    private List<mMixMatchEntity> mMixMatchEntityList;
    private List<mMixMatchEntity> mMixMatchDiscountIDList;
    private List<mMixMatchEntity> mMixMatchLineGroupList;
    private List<mMixMatchEntity> mMixMatchLineGroupDetailList;
    private List<mItemEntity> mItemEntityList;
    private ItemListDataAdapter mListDataAdapter;

    private tSales1Entity tSales1 = new tSales1Entity("", 0, 0, 0);

    TextView txtSalesNo, txtSalesDate, tvQtyItem, txtTotalAmount, txtKasir;

    RadioGroup rdGrpPromo, rdGrpPromoValue;
    RadioButton rdPromo1, rdPromo2, rdPromo3, rdPromo4, rdPromo5, rdPromo6;
    RadioButton rdPromo1Value, rdPromo2Value, rdPromo3Value, rdPromo4Value, rdPromo5Value, rdPromo6Value;


    Integer nextSysNo = 0;
    Integer nextLineNo = 0;
    Integer iNoItem = 0;
    String NoItem = "";
    String sUserId = "";
    String sSort = "";
    String sFilter = "";
    String ShiftNo = "";
    String scrollShiftNo = "";
    String sCompanyCode = "";
    double ItemPrice = 0;
    boolean isView = false;
    FragmentTransaction ft;
    android.app.Fragment fragment = null;

    ApplicationController globalVar;
    UserSessionManager session;


    private List<mPromo1Entity> mPromo1EntityList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sales_input_item, container, false);
        mContext = getActivity();
        Toolbar mToolBar = (Toolbar) ((MainActivity) getActivity()).findViewById(R.id.toolbar);

//        globalVar = (ApplicationController) getActivity().getApplication();
        mToolBar.setTitle("PENJUALAN");

//        session = new UserSessionManager(mContext);
//        sCompanyCode = ((MainActivity) getActivity()).companyCode;
//        sUserId = ((MainActivity) getActivity()).employeeNo;

        dbSales = new tSalesHelper(mContext);
        dbSales1 = new tSales1Helper(mContext);
        dbPromo0 = new mPromo0Helper(mContext);
        dbPromo1 = new mPromo1Helper(mContext);
        dbMixMatch = new mMixMatchHelper(mContext);
        dbCategory = new mCategoryHelper(mContext);
        dbComgen = new mComGenVariableHelper(mContext);
        dbShift = new tShiftLogHelper(mContext);

        final ArrayList<String> listCatCode = new ArrayList<String>();
        final ArrayList<String> listCatName = new ArrayList<String>();
        mCategoryEntityListAll = dbCategory.SelectAllCategory(" WHERE CATEGORY_CODE NOT IN ('BENSIN', 'SV') AND RECORD_STATUS = 'A' ");
        listCatCode.add(0, "Pilih kategori item");
        listCatName.add(0, "Pilih kategori item");
        for (int i = 1; i <= mCategoryEntityListAll.size(); i++) {
            listCatCode.add(i, mCategoryEntityListAll.get(i - 1).getCATEGORY_CODE());
            listCatName.add(i, mCategoryEntityListAll.get(i - 1).getCATEGORY_NAME());
        }

        final Spinner ddlItemCategory_SalesInputItem = (Spinner) rootView.findViewById(R.id.ddlItemCategory_SalesInputItem);
        final SearchView search_view = (SearchView) rootView.findViewById(R.id.sv_category_search_view);
        final ListView listView = (ListView) rootView.findViewById(R.id.ListItem_SalesInputItem);

        mItemEntityList = new ArrayList<>();
        dbItem = new mItemHelper(mContext);
        mItemEntityList = dbItem.SelectAllItem(sCompanyCode, " and mi0.CATEGORY_CODE <> 'BENSIN' AND lcs.QTY_END > 0");
        mListDataAdapter = new ItemListDataAdapter(getActivity().getApplicationContext(), (ArrayList<mItemEntity>) mItemEntityList);
        listView.setAdapter(mListDataAdapter);

        txtTotalAmount = (TextView) rootView.findViewById(R.id.txtTotalAmmount_SalesInputItem);
        txtSalesDate = (TextView) rootView.findViewById(R.id.txtSalesDate_SalesInputItem);
        txtSalesNo = (TextView) rootView.findViewById(R.id.txtSalesNo_SalesInputItem);
        txtKasir = (TextView) rootView.findViewById(R.id.txtKasir_SalesInputItem);
        txtTotalAmount.setText("Rp 0,00");

        txtSalesNo.setText(TextHelper.getNextDocNoFormat(tSales1Entity.class, mContext));
        txtSalesDate.setText(String.valueOf(new SimpleDateFormat("dd MMMM yyyy").format(new java.util.Date())));

        nextSysNo = dbSales.GetNextSalesSysNO();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            nextSysNo = Integer.valueOf(bundle.getString("sysNo"));
            sSort = bundle.getString("Sort");
            sFilter = bundle.getString("Filter");
            isView = bundle.getBoolean("isView");

            double[] curRst = dbSales1.getTotalAndQty(nextSysNo);
            iNoItem = (int) curRst[1];
            txtTotalAmount.setText(TextHelper.convertToMoneyFormat_IDR(Double.toString(curRst[0]), "Rp"));
        } else {
//            dbSales1.DeleteSales1BySysNo(nextSysNo);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item);
        dataAdapter.addAll(listCatName);
        // attaching data adapter to spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddlItemCategory_SalesInputItem.setAdapter(dataAdapter);

//        ddlItemCategory_SalesInputItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                mItemEntityList = new ArrayList<>();
//                if (position > 0) {
//                    mItemEntityList = dbItem.SelectItemByCategory(sCompanyCode, listCatCode.get(position));
//                } else {
//                    mItemEntityList = dbItem.SelectAllItem(sCompanyCode, " and mi0.CATEGORY_CODE <> 'BENSIN' AND lcs.QTY_END > 0");
//                }
//                mListDataAdapter = new ItemListDataAdapter(getActivity().getApplicationContext(), (ArrayList<mItemEntity>) mItemEntityList);
//                listView.setAdapter(mListDataAdapter);
//
//                search_view.clearFocus();
//                search_view.setQuery("", false);
//                View focusedView = getActivity().getCurrentFocus();
//                if (focusedView != null) {
//                    InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    keyboard.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//
//        View view = inflater.inflate(R.layout.fragment_sales_input_item,container,false);
        return rootView;
    }

    public void showBackButton() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



}



//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//
//import id.co.indomobil.indostationposv2.R;
//
//
//
//public class SalesInputItemFragment extends Fragment implements View.OnClickListener {
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.fragment_sales_input_item, container, false);
//
//        return v;
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//}
//
////public class SalesInputItemFragment extends Fragment {
////
////    private Button btnbackup;
////    private Context mContext = null;
////
////
////    public View onCreateView(@NonNull LayoutInflater inflater,
////                             ViewGroup container, Bundle savedInstanceState) {
////        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
////
////        mContext = getActivity();
////        return rootView;
////    }
////
////}