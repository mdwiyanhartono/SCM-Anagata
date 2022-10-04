package dwiyan.com.scm_anagata.ItemDetail.Keranjang.VAPayment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyExternalID;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelGetVA;
import dwiyan.com.scm_anagata.MainActivity;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VAPaymentTransaksi extends BaseActivity {

    TextView Label1, LabelTgl, External_id, transaksi_id, LabelVA, LabelAmount, currency;
    ImageView iconBank;
    EditText Tutorial, NumberVA;
    ScrollView scv1;
    LinearLayout lytop, ly1s;
    LottieAnimationView lottieview;

    String MasterTransID, ExternalID, grandTotal, BankCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_a_payment_transaksi);
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        Intent i = getIntent();
        ExternalID = i.getStringExtra("external_id");
        BankCode = i.getStringExtra("BankKode");
        grandTotal = i.getStringExtra("grandTotal");
        MasterTransID = i.getStringExtra("MasterTransId");
        SetUp();
        SetUpdata();
//        new Handler().postDelayed(new Runnable() {
//            @SuppressLint("NewApi")
//            @Override
//            public void run() {
//                final int welcomeScreenDisplay = 3000; // 3000 = 3 detik
//                int wait = 0;
//                try {
//                    while (wait < welcomeScreenDisplay) {
//                        sleep(100);
//                        wait += 100;
//                    }
//                } catch (Exception e) {
//                    System.out.println("EXc=" + e);
//                } finally {
//
//                }
//            }
//        }, 5000);
    }

    public void CopyVA(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("VANumber", NumberVA.getText().toString());
        clipboard.setPrimaryClip(clipData);

        Toast.makeText(this, "Copied.", Toast.LENGTH_SHORT).show();
    }
    private void SetUpdata() {
        API();
        Call<ResponseModelGetVA> getVA = api.GetVA(new ReqBodyExternalID(ExternalID));
        getVA.enqueue(new Callback<ResponseModelGetVA>() {
            @Override
            public void onResponse(Call<ResponseModelGetVA> call, Response<ResponseModelGetVA> response) {
                pdLoading.dismiss();
//                shimmerFrameLayout.stopShimmer();
//                shimmerFrameLayout.hideShimmer();
//                lytop.setVisibility(View.VISIBLE);
//                ly1s.setVisibility(View.VISIBLE);
                String Kode = response.body().getKode();
                if (Kode.equals("1")) {
                    External_id.setText(ExternalID);
                    transaksi_id.setText(MasterTransID);

                    NumberFormat nf = NumberFormat.getInstance(Locale.US);
                    df = (DecimalFormat) nf;
                    {
                        df.applyPattern(pattern);
                    }
                    df.setDecimalSeparatorAlwaysShown(true);
                    double grandTOTAL = Double.parseDouble(grandTotal);
                    LabelAmount.setText(String.valueOf(df.format(grandTOTAL)));
                    lottieview.playAnimation();


                    if (BankCode.equals("BNI")) {
                        iconBank.setImageResource(R.drawable.bank_bnismall);
                    } else if (BankCode.equals("BRI")) {
                        iconBank.setImageResource(R.drawable.bank_brismall);
                    } else if (BankCode.equals("MANDIRI")) {
                        iconBank.setImageResource(R.drawable.bank_mandirismall);
                    } else if (BankCode.equals("PERMATA")) {
                        iconBank.setImageResource(R.drawable.bank_permatasmall);
                    } else if (BankCode.equals("BJB")) {
                        iconBank.setImageResource(R.drawable.bank_bjbsmall);
                    }

                    String Message = response.body().getMessage();
                    String Currency = response.body().getCurrency();
                    String VANumber = response.body().getAccount_number();
                    String VAName = response.body().getName();
                    Label1.setText("Terimakasih " + VAName + ".");
                    NumberVA.setText(VANumber);
                    LabelVA.setText(VANumber);
                    currency.setText(Currency);
                } else {
                    SetUpdata();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelGetVA> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error !", t.getMessage().toString());

            }
        });

    }

    private DecimalFormat df;
    String pattern = "#,###,###,###,###.00";

    private void SetUp() {
        Label1 = findViewById(R.id.Label1);
        LabelTgl = findViewById(R.id.LabelTgl);
        LabelVA = findViewById(R.id.LabelVA);
        External_id = findViewById(R.id.External_id);
        transaksi_id = findViewById(R.id.transaksi_id);
        LabelAmount = findViewById(R.id.LabelAmount);
        iconBank = findViewById(R.id.iconbank);
        Tutorial = findViewById(R.id.Tutorial);
        NumberVA = findViewById(R.id.numberVa);
        currency = findViewById(R.id.currency);
        lottieview = findViewById(R.id.lottieview);

        timerClass = new TimerClass(1000 * 60 * 60, 1000);

        timerClass.start();
    }


    public void TutorialPembayaran(View view) {
    }

    TimerClass timerClass;

    public void Beranda(View view) {
        StartMainActivity();
    }

    public void StartMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StartMainActivity();
        finish();
    }

    //Membuat InnerClass untuk konfigurasi Countdown Time
    public class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Method ini berjalan saat waktu/timer berubah
        @Override
        public void onTick(long millisUntilFinished) {
            //Kenfigurasi Format Waktu yang digunakan
//            String waktu = String.format("%02d:%02d",
//                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
//                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
//                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            String waktu = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            //Menampilkannya pada TexView
            LabelTgl.setText(waktu);
        }

        @Override
        public void onFinish() {
           StartMainActivity();
        }
    }

}