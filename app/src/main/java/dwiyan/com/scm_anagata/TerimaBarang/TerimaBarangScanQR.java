package dwiyan.com.scm_anagata.TerimaBarang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import javax.xml.transform.Result;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyConfirmtrans;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerimaBarangScanQR extends BaseActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_terima_barang_scan_qr);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    TextView transcode;
    Button closed,oke;
    ImageView close;

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        Toast.makeText(this, result.getText().toString(), Toast.LENGTH_SHORT).show();
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        String hasil = result.getText();
        String[] stringscan = hasil.split("#");
        if(stringscan.length > 1 && !stringscan[1].isEmpty()){
            Dialog d = new Dialog(this);
            d.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d.setContentView(R.layout.view_scan);
            transcode = d.findViewById(R.id.transcode);
            oke = d.findViewById(R.id.ok);
            close = d.findViewById(R.id.close);
            closed = d.findViewById(R.id.cancel);
            closed = d.findViewById(R.id.cancel);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    d.dismiss();
                }
            });
            closed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });

            oke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                    API();
                    Call<ResponseModelGlobal> ApproveTerimaBarang = api.ApproveTerimaBarang(new RequestBodyConfirmtrans(GlobalVar.ID,stringscan[0]));
                    ApproveTerimaBarang.enqueue(new Callback<ResponseModelGlobal>() {
                        @Override
                        public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                            Kode = response.body().getKode();
                            Message = response.body().getMessage();
                            DialogSuccessBackActivity("Berhasil" , Message);
                        }

                        @Override
                        public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {

                        }
                    });
                }
            });
            transcode.setText(stringscan[1]);
            Window window = d.getWindow();
            window.setGravity(Gravity.CENTER_VERTICAL);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
            d.show();
        } else {
            DialogNotifFailed("QR" , "Format QR tidak diketahui");
        }
    }
}