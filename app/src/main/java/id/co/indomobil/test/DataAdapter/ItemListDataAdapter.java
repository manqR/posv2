package id.co.indomobil.test.DataAdapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;
import java.util.List;

import id.co.indomobil.test.DBEntity.mItemEntity;
import id.co.indomobil.test.Helper.TextHelper;

public class ItemListDataAdapter extends BaseAdapter implements Filterable {
    List<mItemEntity> mlist;
    public Context context;
    public ArrayList<mItemEntity> itemList;
    public ArrayList<mItemEntity> mStringFilterList;
    ValueFilter valueFilter;
    TextHelper textHelper;

    public ItemListDataAdapter(Context context, ArrayList<mItemEntity> itemList )
    {
        this.context = context;
        this.itemList = itemList;
        this.mStringFilterList = itemList;
    }

    static class LayoutHandler{
        TextView code, name , qtyend, priceamount, qty, qtytoreturn, categorycode,qtyavailtoreturn;
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mStringFilterList.indexOf(getItem(position));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mview = convertView;
        ItemListDataAdapter.LayoutHandler layoutHandler;
        textHelper = new TextHelper();
        if(mview==null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            mItemEntity m = itemList.get(position);
        }else {
            layoutHandler = (LayoutHandler) mview.getTag();
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mItemEntity dataProvider = (mItemEntity)this.getItem(position);
        if (String.valueOf(dataProvider.getCATEGORY_CODE()).equals("SV")) {
            //layoutHandler.name.setCompoundDrawablesWithIntrinsicBounds(0, 0,  R.drawable.ic_gift_18dp, 0);
//            layoutHandler.name.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this.context, R.drawable.ic_gift_18dp), null);
//            layoutHandler.priceamount.setVisibility(View.INVISIBLE);
        }else if(String.valueOf(dataProvider.getCATEGORY_CODE().substring(0,2)).equals("JS")) {
            //layoutHandler.name.setCompoundDrawablesWithIntrinsicBounds(0, 0,  R.drawable.ic_jasa_18dp, 0);
//            layoutHandler.name.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this.context, R.drawable.ic_jasa_18dp), null);
//            layoutHandler.priceamount.setVisibility(View.VISIBLE);
        }else{
//            layoutHandler.name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//            layoutHandler.priceamount.setVisibility(View.VISIBLE);
        }

//        layoutHandler.code.setText(dataProvider.getITEM_CODE());
//        layoutHandler.name.setText(dataProvider.getITEM_NAME());
        if (dataProvider.getisReturn()){
//            layoutHandler.qtytoreturn.setVisibility(View.VISIBLE);
            if (dataProvider.getCATEGORY_CODE().toUpperCase().equals("BENSIN")) {
//                layoutHandler.qty.setText(dataProvider.getQTY_END().toString());
//                layoutHandler.qtyend.setText("Qty = " + String.valueOf((float) Math.round(dataProvider.getQTY_END() * 100) / 100));
//                layoutHandler.qtytoreturn.setText("Qty yang dapat diRetur = " + dataProvider.getQTY_TO_RETURN().toString());
//                layoutHandler.qtyavailtoreturn.setText(dataProvider.getQTY_TO_RETURN().toString());
            }else {
//                layoutHandler.qty.setText(Integer.toString((int) Math.round(dataProvider.getQTY_END())));
//                layoutHandler.qtyend.setText("Qty = " + Integer.toString((int) Math.round(dataProvider.getQTY_END())));
//                layoutHandler.qtytoreturn.setText("Qty yang dapat diRetur = " + Integer.toString((int) Math.round(dataProvider.getQTY_TO_RETURN())));
//                layoutHandler.qtyavailtoreturn.setText(Integer.toString((int) Math.round(dataProvider.getQTY_TO_RETURN())));
            }
        }else{
            if (!dataProvider.getCATEGORY_CODE().toUpperCase().substring(0,2).equals("JS")) {
                if (dataProvider.getCATEGORY_CODE().toUpperCase().equals("BENSIN")) {
//                    layoutHandler.qty.setText(dataProvider.getQTY_END().toString());
//                    layoutHandler.qtyend.setText("Stock = " + String.valueOf((float) Math.round(dataProvider.getQTY_END() * 100) / 100));
                } else {
//                    layoutHandler.qty.setText(Integer.toString((int) Math.round(dataProvider.getQTY_END())));
//                    layoutHandler.qtyend.setText("Stock = " + Integer.toString((int) Math.round(dataProvider.getQTY_END())));
                }
            }else{
//                layoutHandler.qty.setText("");
//                layoutHandler.qtyend.setText("");
            }

        }

//        layoutHandler.priceamount.setText(textHelper.convertToMoneyFormat_IDR(String.valueOf(dataProvider.getPRICE_AMOUNT()),"Rp "));
      /*  if (String.valueOf(dataProvider.getPRICE_AMOUNT()).equals("0.0")){
            layoutHandler.priceamount.setText("FREE");
        }*/
//        layoutHandler.categorycode.setText(dataProvider.getCATEGORY_CODE());


        return mview;
    }

    @Override
    public Filter getFilter(){
        if (valueFilter == null){
            valueFilter = new ItemListDataAdapter.ValueFilter();
        }
        return valueFilter;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence != null && charSequence.length() > 0) {
                ArrayList<mItemEntity> filterList = new ArrayList<mItemEntity>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if (( (mStringFilterList.get(i).getITEM_NAME().toUpperCase()  )
                            .contains(charSequence.toString().toUpperCase())) ||
                            ( (mStringFilterList.get(i).getITEM_CODE().toUpperCase()  )
                                    .contains(charSequence.toString().toUpperCase())) ) {

                        mItemEntity item = new mItemEntity(
                                mStringFilterList.get(i).getITEM_CODE(),
                                mStringFilterList.get(i).getITEM_NAME(),
                                mStringFilterList.get(i).getQTY_END(),
                                mStringFilterList.get(i).getQTY_TO_RETURN(),
                                mStringFilterList.get(i).getPRICE_AMOUNT(),
                                mStringFilterList.get(i).getCATEGORY_CODE());

                        filterList.add(item);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            itemList = (ArrayList<mItemEntity>)filterResults.values;
            notifyDataSetChanged();
        }
    }
}
