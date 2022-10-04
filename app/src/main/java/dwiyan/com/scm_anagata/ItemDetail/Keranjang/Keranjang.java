package dwiyan.com.scm_anagata.ItemDetail.Keranjang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Adapter.AdapterKeranjang;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelKeranjangAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.RequestBodyInputTransaksi;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponsModelKeranjangAll;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Keranjang extends BaseActivity implements AdapterKeranjang.OnItemClickListener {

    private List<ModelKeranjangAll> listkeranjang = new ArrayList<>();
    AdapterKeranjang adapterKeranjang;
    TextView grandtotal,subtotal,ppn;
    Button btnsubmit;
    RecyclerView rc1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);

        grandtotal = findViewById(R.id.grandtotal);
        btnsubmit = findViewById(R.id.btnsubmit);
        rc1 = findViewById(R.id.rc1);
        ppn = findViewById(R.id.ppn);
        subtotal = findViewById(R.id.subtotal);
        rc1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc1.setItemAnimator(new DefaultItemAnimator());
        adapterKeranjang = new AdapterKeranjang(this);
        adapterKeranjang.setData(listkeranjang);
        rc1.setAdapter(adapterKeranjang);
        setUpdata();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitKeranjang();
            }
        });
    }

    private void SubmitKeranjang() {
        SendData();
    }

    private void SendData() {
        PdLoading();
        API();
        Call<ResponseModelGlobal> InputTrans = api.InputTransaksi(new RequestBodyInputTransaksi(GlobalVar.ID,subTotalPrice,PPN,PPNValue));
        InputTrans.enqueue(new Callback<ResponseModelGlobal>() {
            @Override
            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                if(Integer.parseInt(Kode) == 1){
                    DialogSuccessBackActivity("Berhasil",Message);
                } else {
                    DialogNotifFailed("Gagal",Message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error" , t.getMessage());
            }
        });

    }

    int totalgrand=0;
    String subTotalPrice="0",PPN="0",PPNValue="0";
    private DecimalFormat df;
    String pattern = "#,###,###,###,###.00";
    private void setUpdata() {
        listkeranjang.clear();
        PdLoading();
        API();
        Call<ResponsModelKeranjangAll> KeranjangAll = api.getkeranjangall(new RequestBodyUserId(GlobalVar.ID));
        KeranjangAll.enqueue(new Callback<ResponsModelKeranjangAll>() {
            @Override
            public void onResponse(@NonNull Call<ResponsModelKeranjangAll> call, @NonNull Response<ResponsModelKeranjangAll> response) {
                pdLoading.dismiss();
                NumberFormat nf = NumberFormat.getInstance(Locale.US);
                df = (DecimalFormat) nf;
                {
                    df.applyPattern(pattern);
                }
                df.setDecimalSeparatorAlwaysShown(true);
                Kode = response.body() != null ? response.body().getKode() : "9";
                Message = response.body() != null ? response.body().getMessage() : "Blank";
                subTotalPrice = response.body().getTotal();
                PPN = response.body().getPPN();
                Double totalsub = Double.parseDouble(response.body().getTotal());

                subtotal.setText(String.valueOf(df.format(totalsub)));
                assert response.body() != null;
                listkeranjang = response.body().getResult();
                adapterKeranjang.setData(listkeranjang);

                int ppn1 = Integer.parseInt(response.body().getPPN());
                int sbtotal = 2000;
                if (response.body().getTotal().contains(",")) {
                    sbtotal = Integer.parseInt(response.body().getTotal().replaceAll(",", ""));
                } else {
                    sbtotal = Integer.parseInt(response.body().getTotal());
                }
                PPNValue = String.valueOf(ppn1 * sbtotal / 100) ;
                double ppn10;
                    ppn10 = Math.ceil(ppn1 * sbtotal / 100);

                ppn.setText(String.valueOf(df.format(ppn10)));

                Double GrandTotal1 = Math.ceil(ppn10 + Double.parseDouble(response.body().getTotal()));
                grandtotal.setText(String.valueOf(df.format(GrandTotal1)));
            }

            @Override
            public void onFailure(@NonNull Call<ResponsModelKeranjangAll> call, @NonNull Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("", Objects.requireNonNull(t.getMessage()).toString());
            }
        });
    }

    @Override
    public void Detail(String MenuID) {

    }
}