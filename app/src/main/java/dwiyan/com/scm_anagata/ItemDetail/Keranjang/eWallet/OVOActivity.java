package dwiyan.com.scm_anagata.ItemDetail.Keranjang.eWallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyMasterTransIdGrandTotalOVONumber;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelCreateVA;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OVOActivity extends BaseActivity {

    EditText nomorHP;
    Button kirim;
    String STransId, grandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_v_o);

        Intent i = getIntent();
        grandTotal = i.getStringExtra("grandTotal");
        STransId = i.getStringExtra("MasterTransId");

        nomorHP = findViewById(R.id.nomorHP);
        kirim = findViewById(R.id.btnkirim);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomorHP.getText().toString().isEmpty()) {
                    nomorHP.setError("Silahkan masukan nomor OVO kamu");
                } else {
                    sendeWallet(nomorHP.getText().toString(), STransId);
                }

            }
        });

        getuser();

    }


    private void sendeWallet(String NomorHp, String TransId) {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        API();
        Call<ResponseModelCreateVA> OVOres = api.OVO(new ReqBodyMasterTransIdGrandTotalOVONumber(TransId, grandTotal, GlobalVar.ID, NomorHp));
        OVOres.enqueue(new Callback<ResponseModelCreateVA>() {
            @Override
            public void onResponse(Call<ResponseModelCreateVA> call, Response<ResponseModelCreateVA> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                String external_id = response.body().getExternal_id();
                if (Kode.equals("1")) {
                    Intent i = new Intent(OVOActivity.this, OVOPayLoadActivity.class);
                    i.putExtra("external_id" , external_id);
                    startActivity(i);
                } else {
                    DialogNotifFailed("Failed !" , Message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelCreateVA> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error !" , t.getMessage().toString());
            }
        });
    }
}