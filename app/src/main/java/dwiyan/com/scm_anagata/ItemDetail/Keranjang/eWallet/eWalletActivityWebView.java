package dwiyan.com.scm_anagata.ItemDetail.Keranjang.eWallet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyExternalID;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eWalletActivityWebView extends BaseActivity {

    String Url, MasterTransId, GrandTotal, Ewallet, external_id;
    Button btnUrl, btnBeranda;
    TextView timer, TVMessage;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_wallet2);

        timer = findViewById(R.id.timer);
        TVMessage = findViewById(R.id.Message);
        btnBeranda = findViewById(R.id.btnBeranda);
        btnUrl = findViewById(R.id.btnUrl);
        imageView = findViewById(R.id.imageView);

        Intent i = getIntent();
        Url = i.getStringExtra("Url");
        Ewallet = i.getStringExtra("CodeEwallet");
        MasterTransId = i.getStringExtra("MasterTransId");
        MasterTransId = i.getStringExtra("external_id");
        GrandTotal = i.getStringExtra("grandTotal");
        sendUrl();
        if (Ewallet.equals("ID_LINKAJA")) {
            imageView.setImageResource(R.drawable.linkajapayment);
            TVMessage.setText("Jika tidak diteruskan ke halaman LinkAja silahkan klik tombol Muat Ulang");
        } else if (Ewallet.equals("ID_DANA")) {
            TVMessage.setText("Jika tidak diteruskan ke halaman Dana silahkan klik tombol Muat Ulang");
            imageView.setImageResource(R.drawable.danapayment2);
        } else if (Ewallet.equals("ID_SHOPEEPAY")) {
            TVMessage.setText("Jika tidak diteruskan ke halaman ShopeePay silahkan klik tombol Muat Ulang");
        }

        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl();
            }
        });

        btnBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        webview.getSettings().setLoadsImagesAutomatically(true);
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setDomStorageEnabled(true);
//
//        // Tiga baris di bawah ini agar laman yang dimuat dapat
//        // melakukan zoom.
//        webview.getSettings().setSupportZoom(true);
//        webview.getSettings().setBuiltInZoomControls(true);
//        webview.getSettings().setDisplayZoomControls(false);
//        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
//        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webview.setWebViewClient(new WebViewClient());
//        webview.loadUrl(Url);
//        setContentView(webview);
    }

    OVOPayLoadActivity.TimerClass timerClass;

    //Membuat InnerClass untuk konfigurasi Countdown Time
    public class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Method ini berjalan saat waktu/timer berubah
        @Override
        public void onTick(long millisUntilFinished) {
            //Kenfigurasi Format Waktu yang digunakan
            String waktu = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished) -
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            //Menampilkannya pada TexView
            timer.setText(waktu + " detik");
            API();
            Call<ResponseModelGlobal> cekPay = api.CekPaymentOVO(new ReqBodyExternalID(external_id));
            cekPay.enqueue(new Callback<ResponseModelGlobal>() {
                @Override
                public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                    Kode = response.body().getKode();
                    Message = response.body().getMessage();
                    if (Kode.equals("1")) {
//                        DialogBerhasilAutoClose("Berhasil !", Message);
//                        timerClass.onFinish();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                    DialogNotifError("Error !", t.getMessage().toString());
//                    timerClass.onFinish();
                }
            });

        }

        @Override
        public void onFinish() {

            ///Berjalan saat waktu telah selesai atau berhenti
//            Toast.makeText(MainActivity.this, "Waktu Telah Berakhir", Toast.LENGTH_LONG).show();
//            finish();
        }
    }

    private void sendUrl() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
        startActivity(browserIntent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, eWalletActivity.class);
        i.putExtra("MasterTransId", MasterTransId);
        i.putExtra("grandTotal", GrandTotal);
        startActivity(i);
    }
}