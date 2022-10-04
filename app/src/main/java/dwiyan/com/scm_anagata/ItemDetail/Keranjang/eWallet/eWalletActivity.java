package dwiyan.com.scm_anagata.ItemDetail.Keranjang.eWallet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyMasterTransIdGrandTotalCodeEwallet;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModeleWallet;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eWalletActivity extends BaseActivity {

    String MasterTransId, GrandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_wallet);

        Intent i = getIntent();
        MasterTransId = i.getStringExtra("MasterTransId");
        GrandTotal = i.getStringExtra("grandTotal");
    }

    public void OVO(View view) {
        Intent i = new Intent(this, OVOActivity.class);
        i.putExtra("MasterTransId", MasterTransId);
        i.putExtra("grandTotal", GrandTotal);
        startActivity(i);
    }

    public void LinkAja(View view) {
        sendeWallet(MasterTransId, GrandTotal, "ID_LINKAJA");
    }

    public void ShopeePay(View view) {
        sendeWallet(MasterTransId, GrandTotal, "ID_SHOPEEPAY");
    }

    public void Dana(View view) {
        sendeWallet(MasterTransId, GrandTotal, "ID_DANA");
    }

    private void sendeWallet(String TransId, String grandTotal, String CodeEwallet) {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        API();
        Call<ResponseModeleWallet> eWallet = api.eWallet(new ReqBodyMasterTransIdGrandTotalCodeEwallet(TransId, grandTotal, GlobalVar.ID, CodeEwallet));
        eWallet.enqueue(new Callback<ResponseModeleWallet>() {
            @Override
            public void onResponse(Call<ResponseModeleWallet> call, Response<ResponseModeleWallet> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                String UrlDesktopWeb = response.body().getDesktop_web_checkout_url();
                String UrlMobileWeb = response.body().getMobile_web_checkout_url();
                String UrlDepLink = response.body().getMobile_deeplink_checkout_url();
                String external_id = response.body().getExternal_id();
                String QRString = response.body().getQr_checkout_string();

                if (!UrlMobileWeb.isEmpty()) {
                    Intent i = new Intent(eWalletActivity.this, eWalletActivityWebView.class);
                    i.putExtra("MasterTransId", MasterTransId);
                    i.putExtra("CodeEwallet", CodeEwallet);
                    i.putExtra("grandTotal", GrandTotal);
                    i.putExtra("external_id", external_id);
                    i.putExtra("Url", UrlMobileWeb);
                    startActivity(i);
                } else if (!UrlDesktopWeb.isEmpty()) {
                    Intent i = new Intent(eWalletActivity.this, eWalletActivityWebView.class);
                    i.putExtra("MasterTransId", MasterTransId);
                    i.putExtra("CodeEwallet", CodeEwallet);
                    i.putExtra("grandTotal", GrandTotal);
                    i.putExtra("external_id", external_id);
                    i.putExtra("Url", UrlMobileWeb);
                    startActivity(i);
                } else if (!UrlDepLink.isEmpty()) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(UrlDepLink));
                        startActivity(intent);
                    } catch (Exception ex) {
                        DialogNotifFailed("Failed !", "Aplikasi tidak ditemukan \t" + ex.getLocalizedMessage());
                    }

                } else if (!QRString.isEmpty()) {
                    Intent i = new Intent(eWalletActivity.this, eWalletActivityWebView.class);
                    i.putExtra("MasterTransId", MasterTransId);
                    i.putExtra("CodeEwallet", CodeEwallet);
                    i.putExtra("grandTotal", GrandTotal);
                    i.putExtra("external_id", external_id);
                    i.putExtra("QRString", QRString);
                } else {
                    DialogNotifFailed("Failed !", "Gagal melakukan pembayaran denga metode ini");
                }

            }

            @Override
            public void onFailure(Call<ResponseModeleWallet> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error !", t.getMessage());
            }
        });
    }
}