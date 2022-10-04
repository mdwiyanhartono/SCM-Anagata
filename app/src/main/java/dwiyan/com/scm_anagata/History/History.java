package dwiyan.com.scm_anagata.History;

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
import dwiyan.com.scm_anagata.History.Adapter.AdapterListHistory;
import dwiyan.com.scm_anagata.History.Model.ModelHistory;
import dwiyan.com.scm_anagata.History.Model.ResponsModelHistory;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Adapter.AdapterKeranjang;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelKeranjangAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponsModelKeranjangAll;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.Transaksi.Konfirmasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends BaseActivity implements AdapterListHistory.OnItemClickListener {

    RecyclerView rclist;
    AdapterListHistory adapterListHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rclist = findViewById(R.id.rc1);
        rclist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rclist.setItemAnimator(new DefaultItemAnimator());
        adapterListHistory = new AdapterListHistory(this);
        adapterListHistory.setData(listhistory);
        rclist.setAdapter(adapterListHistory);
        getData();
    }

    private List<ModelHistory> listhistory = new ArrayList<>();
    private void getData() {
        PdLoading();
        listhistory.clear();
        API();
        Call<ResponsModelHistory> getHistory = api.GetHistory(new RequestBodyUserId(GlobalVar.ID));
        getHistory.enqueue(new Callback<ResponsModelHistory>() {
            @Override
            public void onResponse(Call<ResponsModelHistory> call, Response<ResponsModelHistory> response) {
                Kode = response.body().getKode();
                pdLoading.dismiss();
                Message =response.body().getMessage();
                if(Integer.parseInt(Kode) == 1){
                    listhistory = response.body().getResult();
                    adapterListHistory.setData(listhistory);
                }
            }

            @Override
            public void onFailure(Call<ResponsModelHistory> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }


    @Override
    public void onItemClick(String idmenu) {
        Intent i = new Intent(this, Konfirmasi.class);
        i.putExtra("IDTrans" , idmenu);
        startActivity(i);

    }
}