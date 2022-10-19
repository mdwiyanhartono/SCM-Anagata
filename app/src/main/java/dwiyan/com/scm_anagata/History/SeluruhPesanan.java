package dwiyan.com.scm_anagata.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ModelAllTrans;
import dwiyan.com.scm_anagata.DataModel.ModelConfirmTrans;
import dwiyan.com.scm_anagata.DataModel.RequestBodyConfirmtrans;
import dwiyan.com.scm_anagata.DataModel.ResponseModelAllTransaksi;
import dwiyan.com.scm_anagata.DataModel.ResponseModelConfirmasiTrans;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.Transaksi.Adapter.AdapterListItemAllTransaction;
import dwiyan.com.scm_anagata.Transaksi.Adapter.AdapterListItemTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeluruhPesanan extends BaseActivity implements AdapterListItemAllTransaction.OnItemClickListener {

    private List<ModelAllTrans> assetslist = new ArrayList<>();
    String IDtrans;
    RecyclerView rcitem;
    CardView btnclose;
    AdapterListItemAllTransaction adapterListMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seluruh_pesanan);
        Intent i = getIntent();
        IDtrans = i.getStringExtra("IDTrans");
        PdLoading();
        SetUp();

    }

    private void SetUp() {
        rcitem = findViewById(R.id.rcitem);
        btnclose = findViewById(R.id.btnclose);
        adapterListMenu = new AdapterListItemAllTransaction(this);
        adapterListMenu.setData(assetslist);
        SetUpData(IDtrans);
        rcitem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcitem.setItemAnimator(new DefaultItemAnimator());
        rcitem.setAdapter(adapterListMenu);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void SetUpData(String iDtrans) {
        assetslist.clear();
        API();
        Call<ResponseModelAllTransaksi> confirmdata = api.getAllTransaction(new RequestBodyConfirmtrans(GlobalVar.ID, iDtrans));
        confirmdata.enqueue(new Callback<ResponseModelAllTransaksi>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ResponseModelAllTransaksi> call, Response<ResponseModelAllTransaksi> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                assetslist = response.body().getResult();
                adapterListMenu.setData(assetslist);
            }

            @Override
            public void onFailure(Call<ResponseModelAllTransaksi> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(String idmenu) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}