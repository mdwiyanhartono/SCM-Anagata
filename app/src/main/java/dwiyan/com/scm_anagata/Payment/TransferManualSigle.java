package dwiyan.com.scm_anagata.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.messaging.MessagingAnalytics;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ResponseModelBanner;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferManualSigle extends BaseActivity implements AdapterView.OnItemSelectedListener {

    Spinner penerimadana,pengirimdana;
    SpinnerAdapterPenerimadana spinnerAdapterPenerimadana;
    SpinnerAdapterPengirimdana spinnerAdapterPengirimdana;
    private List<ModelBank> listbankpenerima = new ArrayList<>();
    private List<ModelBank> listbankpengirim = new ArrayList<>();
    String IDtrans;
    CardView btnbayar;
    EditText refrens,paymentamount,note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_manual_sigle);

        Intent i = getIntent();
        IDtrans = i.getStringExtra("IDTrans");

        btnbayar = findViewById(R.id.btnbayar);
        refrens = findViewById(R.id.refrens);
        paymentamount = findViewById(R.id.paymentamount);
        note = findViewById(R.id.note);

        penerimadana = findViewById(R.id.bankreceived);
        pengirimdana = findViewById(R.id.banksender);
        penerimadana.setOnItemSelectedListener(this);
        pengirimdana.setOnItemSelectedListener(this);
        setUpSpinner();

        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdLoading();
                API();
                Call<ResponseModelGlobal> inputTransfer = api.InputTransferManual(new RequestBodyInputTransferManual(GlobalVar.ID,IDtrans,bankreceivedid,banksend,
                        refrens.getText().toString(),note.getText().toString(),paymentamount.getText().toString()));
                inputTransfer.enqueue(new Callback<ResponseModelGlobal>() {
                    @Override
                    public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                        pdLoading.dismiss();
                        Kode = response.body().getKode();
                        Message = response.body().getMessage();
                        if(Integer.parseInt(Kode) == 1){
                            DialogSuccessBackActivity("Berhasil" ,Message );
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                        pdLoading.dismiss();
                        DialogNotifError("Error" , t.getMessage());
                    }
                });
             }
        });
    }

    private void setUpSpinner() {
        listbankpenerima.clear();
        listbankpengirim.clear();
        PdLoading();
        API();
        Call<ResponseModelBank> getbank = api.GetBank();
        getbank.enqueue(new Callback<ResponseModelBank>() {
            @Override
            public void onResponse(Call<ResponseModelBank> call, Response<ResponseModelBank> response) {
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                listbankpenerima = response.body().getResult();
                spinnerAdapterPenerimadana = new SpinnerAdapterPenerimadana(listbankpenerima, TransferManualSigle.this);
                penerimadana.setAdapter(spinnerAdapterPenerimadana);
            }

            @Override
            public void onFailure(Call<ResponseModelBank> call, Throwable t) {

            }
        });

        Call<ResponseModelBank> getbank2 = api.GetBank();
        getbank2.enqueue(new Callback<ResponseModelBank>() {
            @Override
            public void onResponse(Call<ResponseModelBank> call, Response<ResponseModelBank> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                listbankpengirim = response.body().getResult();
                spinnerAdapterPengirimdana = new SpinnerAdapterPengirimdana(listbankpengirim, TransferManualSigle.this);
                pengirimdana.setAdapter(spinnerAdapterPengirimdana);
            }

            @Override
            public void onFailure(Call<ResponseModelBank> call, Throwable t) {
                pdLoading.dismiss();
            }
        });

    }

    String bankreceivedid="",banksend = "";
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        pengirimdana = (Spinner) parent;
        if (pengirimdana.getId() == R.id.bankreceived) {
            ModelBank ls = listbankpenerima.get(i);
            bankreceivedid = ls.getBankaccountid();

            Toast.makeText(this, "bankpenerima-" + bankreceivedid , Toast.LENGTH_SHORT).show();
        }
        pengirimdana = (Spinner) parent;
        if (pengirimdana.getId() == R.id.banksender) {
            ModelBank ls = listbankpengirim.get(i);
            banksend = ls.getBankaccountid();

            Toast.makeText(this, "bankpengirim-" + banksend , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}