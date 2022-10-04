package dwiyan.com.scm_anagata.ItemDetail.Keranjang;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.VAPayment.VirtualAccountActivity;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.eWallet.eWalletActivity;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MethodPayment extends BaseActivity {

    String MasterTransID, grandTotal;
    TextView PaymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_payment);
        Intent i = getIntent();

        MasterTransID = i.getStringExtra("MasterTransId");
        grandTotal = i.getStringExtra("grandTotal");

    }

    public void VA(View view) {
//        DialogNotifFailed("Belum Tersedia !", "Fitur Ini Belum Tersedia");
        Intent i = new Intent(this, VirtualAccountActivity.class);
        i.putExtra("MasterTransId", MasterTransID);
        i.putExtra("grandTotal", grandTotal);
        startActivity(i);
    }

    public void Ewallet(View view) {
//        DialogNotifFailed("Belum Tersedia !", "Fitur Ini Belum Tersedia");
        Intent i = new Intent(this, eWalletActivity.class);
        i.putExtra("MasterTransId", MasterTransID);
        i.putExtra("grandTotal", grandTotal);
        startActivity(i);
    }

}