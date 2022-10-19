package dwiyan.com.scm_anagata.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.Order.Adapter.AdapterListOrder;
import dwiyan.com.scm_anagata.Order.Model.ModelOrder;
import dwiyan.com.scm_anagata.Order.Model.ResponsModelOrder;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.Transaksi.Konfirmasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceSingle extends BaseActivity implements AdapterListOrder.OnItemClickListener {

    RecyclerView rclist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_single);
        rclist = findViewById(R.id.rc1);
        rclist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rclist.setItemAnimator(new DefaultItemAnimator());
        adapterListOrder = new AdapterListOrder(this);
        adapterListOrder.setData(listorder);
        rclist.setAdapter(adapterListOrder);
        PdLoading();
        getData();

    }
    AdapterListOrder adapterListOrder;
    private List<ModelOrder> listorder = new ArrayList<>();
    private void getData() {

        listorder.clear();
        API();
        Call<ResponsModelOrder> getHistory = api.GetListInvoice(new RequestBodyUserId(GlobalVar.ID));
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
        Intent i = new Intent(this, DetailTransaksiPaySingle.class);
        i.putExtra("IDTrans" , idmenu);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}