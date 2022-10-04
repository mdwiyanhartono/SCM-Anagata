package dwiyan.com.scm_anagata.Order;

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
import dwiyan.com.scm_anagata.Order.Adapter.AdapterListOrder;
import dwiyan.com.scm_anagata.Order.Model.ModelOrder;
import dwiyan.com.scm_anagata.Order.Model.ResponsModelOrder;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order extends BaseActivity implements AdapterListOrder.OnItemClickListener {

    RecyclerView rc1;
    AdapterListOrder adapterListOrder;
    private List<ModelOrder> listorder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        rc1 = findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc1.setItemAnimator(new DefaultItemAnimator());
        adapterListOrder = new AdapterListOrder(this);
        adapterListOrder.setData(listorder);
        rc1.setAdapter(adapterListOrder);
        getData();
    }

    private void getData() {
        PdLoading();
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
    public void onItemClick(String idmenu , String ispaid) {
        Intent i = new Intent(this,DetailOrder.class);
        i.putExtra("IDTrans" , idmenu);
        startActivity(i);
        finish();
    }
}