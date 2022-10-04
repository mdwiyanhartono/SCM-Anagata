package dwiyan.com.scm_anagata.ItemDetail.Keranjang.VAPayment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyMasterTransIdGrandTotal;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelCreateVA;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VirtualAccountActivity extends BaseActivity {

    String grandTotal,MasterTransID,TypeTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_account);

        Intent i = getIntent();
        grandTotal = i.getStringExtra("grandTotal");
        MasterTransID = i.getStringExtra("MasterTransId");

//        Toast.makeText(this, grandTotal +"/"+MasterTransID, Toast.LENGTH_SHORT).show();
    }

    public void BJBVA(View view) {

        CreateVA("BJB");
    }

//    public void BSIVA(View view) {
//    }

    public void PermataVA(View view) {
        CreateVA("PERMATA");
    }

    public void MandiriVA(View view) {
        CreateVA("MANDIRI");
    }

    public void BRIVA(View view) {
        CreateVA("BRI");
    }

    public void BNIVA(View view) {
        CreateVA("BNI");
    }

    private void CreateVA(String BankCode){
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        API();
        Call<ResponseModelCreateVA> createVA = api.CreateVA(new ReqBodyMasterTransIdGrandTotal(MasterTransID,grandTotal, GlobalVar.ID,BankCode));
        createVA.enqueue(new Callback<ResponseModelCreateVA>() {
            @Override
            public void onResponse(Call<ResponseModelCreateVA> call, Response<ResponseModelCreateVA> response) {
                pdLoading.dismiss();
                String kode = response.body().getKode();
                String message = response.body().getMessage();
                String external_id = response.body().getExternal_id();
                String status = response.body().getStatus();
                String TransId = MasterTransID;
                if(kode.equals("1")){
                    Intent i = new Intent(VirtualAccountActivity.this, VAPaymentTransaksi.class);
                    i.putExtra("MasterTransId", TransId);
                    i.putExtra("grandTotal", grandTotal);
                    i.putExtra("BankKode", BankCode);
                    i.putExtra("external_id", external_id);
                    startActivity(i);
                } else {
                    DialogNotifFailed("Gagal",message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelCreateVA> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error !",t.getMessage().toString());
            }
        });
    }
}