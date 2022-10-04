package dwiyan.com.scm_anagata.ItemDetail.Keranjang.eWallet;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyExternalID;
import dwiyan.com.scm_anagata.MainActivity;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OVOPayLoadActivity extends BaseActivity {

    TextView timer;

    String external_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_v_o_pay_load);

        Intent i = getIntent();
        external_id = i.getStringExtra("external_id");
        timer = findViewById(R.id.timer);
        //Set Waktu selama 3 detik = 60000 * 3 millis dengan interval 1 detik = 1000 millis
        timerClass = new TimerClass(1000 * 31, 1000);

        timerClass.start();
    }

    TimerClass timerClass;
    //Membuat InnerClass untuk konfigurasi Countdown Time
    public class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Method ini berjalan saat waktu/timer berubah
        @Override
        public void onTick(long millisUntilFinished) {
            //Kenfigurasi Format Waktu yang digunakan
            String waktu = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) ,
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
                    if(Kode.equals("1")){
                        timerClass.cancel();
                        timerClass.onFinish();
//                        timerClass.onFinish();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                    DialogNotifError("Error !" , t.getMessage().toString());
//                    timerClass.onFinish();
                }
            });

        }

        @Override
        public void onFinish() {
            if(Kode.equals("1")){
                DialogBerhasilAutoClose("Berhasil !" , Message);
            } else {

            }

            ///Berjalan saat waktu telah selesai atau berhenti
//            Toast.makeText(MainActivity.this, "Waktu Telah Berakhir", Toast.LENGTH_LONG).show();
//            finish();
        }
    }

    public void DialogBerhasilAutoClose(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert_auto_hide);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        anim = d.findViewById(R.id.anim);
        title.setText(Title);
        message.setText(Message);
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                final int welcomeScreenDisplay = 1000; // 3000 = 3 detik
                int wait = 0;
                try {
                    while (wait < welcomeScreenDisplay) {
                        sleep(100);
                        wait += 100;
                    }
                } catch (Exception e) {
                    System.out.println("EXc=" + e);

                } finally {
                    Intent i = new Intent(OVOPayLoadActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 2800);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }
}