package dwiyan.com.scm_anagata.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.History.Adapter.AdapterListHistory;
import dwiyan.com.scm_anagata.History.Model.ModelHistory;
import dwiyan.com.scm_anagata.History.Model.ResponsModelHistory;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Adapter.AdapterKeranjang;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelKeranjangAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponsModelKeranjangAll;
import dwiyan.com.scm_anagata.Order.Adapter.AdapterListOrder;
import dwiyan.com.scm_anagata.Order.Model.ModelOrder;
import dwiyan.com.scm_anagata.Order.Model.ResponsModelOrder;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.Transaksi.Konfirmasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends BaseActivity implements  AdapterListOrder.OnItemClickListener {

    RecyclerView rclist;
//    AdapterListHistory adapterListHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rclist = findViewById(R.id.rc1);
        rclist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rclist.setItemAnimator(new DefaultItemAnimator());
        adapterListOrder = new AdapterListOrder(this);
        adapterListOrder.setData(listorder);
        rclist.setAdapter(adapterListOrder);
        PdLoading();
        getData();


    }

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private void dialogTanggal() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                History.this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    AdapterListOrder adapterListOrder;
    private List<ModelOrder> listorder = new ArrayList<>();
    private void getData() {

        listorder.clear();
        API();
        Call<ResponsModelOrder> getHistory = api.GetListOrder(new RequestBodyUserId(GlobalVar.ID));
        getHistory.enqueue(new Callback<ResponsModelOrder>() {
            @Override
            public void onResponse(Call<ResponsModelOrder> call, Response<ResponsModelOrder> response) {
                Kode = response.body().getKode();
                pdLoading.dismiss();
                Message =response.body().getMessage();
                if(Integer.parseInt(Kode) == 1){
                    listorder = response.body().getResult();
                    adapterListOrder.setData(listorder);
                }
            }

            @Override
            public void onFailure(Call<ResponsModelOrder> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }


    @Override
    public void onItemClick(String idmenu, String ispaid) {
        Intent i = new Intent(this, Konfirmasi.class);
        i.putExtra("IDTrans" , idmenu);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
}