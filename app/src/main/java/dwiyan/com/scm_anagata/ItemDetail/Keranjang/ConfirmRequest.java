package dwiyan.com.scm_anagata.ItemDetail.Keranjang;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Adapter.AdapterRequestAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ModelRequestItem;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ReqBodyIDTransIDUser;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponsModelRequestAll;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelConfirmReq;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmRequest extends BaseActivity implements AdapterRequestAll.OnItemClickListener {

    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";
    TextView id, alamatwarung, grandtotal, tglVisit, namawarung;
    EditText etalamatauser, contact, note;
    String ID,Status = "0";
    RecyclerView rc1;

    CardView submit, cancel,close,bayar;

    AdapterRequestAll adapterRequestAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_request);
        Intent i = getIntent();

        id = findViewById(R.id.ViewID);
        alamatwarung = findViewById(R.id.alamatwarung);
        tglVisit = findViewById(R.id.tglVisit);
        grandtotal = findViewById(R.id.grandtotal);
        etalamatauser = findViewById(R.id.etalamatauser);
        contact = findViewById(R.id.contact);
        note = findViewById(R.id.note);
        namawarung = findViewById(R.id.namawarung);
        rc1 = findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc1.setItemAnimator(new DefaultItemAnimator());
        adapterRequestAll = new AdapterRequestAll(this);
        adapterRequestAll.setData(listItem);
        rc1.setAdapter(adapterRequestAll);
        ID = i.getStringExtra("ID");
        Status = i.getStringExtra("Status");


        submit = findViewById(R.id.btnsubmit);
        cancel = findViewById(R.id.btncancel);
        bayar = findViewById(R.id.btnbayar);
        close = findViewById(R.id.btnclose);
        setUP();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ConfirmRequest.this, MethodPayment.class);
                i.putExtra("MasterTransId", "TR-"+ID+"-");
                i.putExtra("grandTotal", String.valueOf(grandTotal));
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelReq();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmRequest.this, MethodPayment.class);
                i.putExtra("MasterTransId", "TR-"+ID+"-");
                i.putExtra("grandTotal", String.valueOf(grandTotal));
                startActivity(i);
//                ConfirmReq();
            }
        });
        id.setText(i.getStringExtra("ID"));

    }

    private void ConfirmReq() {
        PdLoading();
        API();
        Call<ResponseModelConfirmReq> ConfirmReq = api.ConfirmReq(new ReqBodyIDTransIDUser(ID, GlobalVar.ID));
        ConfirmReq.enqueue(new Callback<ResponseModelConfirmReq>() {
            @Override
            public void onResponse(Call<ResponseModelConfirmReq> call, Response<ResponseModelConfirmReq> response) {
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                String TypeTrans = response.body().getTypeTrans();
                pdLoading.dismiss();
                if (Kode.equals("1")) {
                    if(TypeTrans.equals("KPN")){
                        DialogNotifFinish("Berhasil", "Terimakasih, Petugas akan tiba dilokasi anda sesuai jadwal");
                    } else {
                        Intent i = new Intent(ConfirmRequest.this, MethodPayment.class);
                        i.putExtra("MasterTransId", "TR-"+ID+"-");
                        i.putExtra("grandTotal", String.valueOf(grandTotal));
                        startActivity(i);
                    }

                } else {
                    DialogNotifFailed("Failed !", Message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelConfirmReq> call, Throwable t) {
                DialogNotifError("Error !" ,t.getMessage().toString());
                pdLoading.dismiss();
            }
        });
    }


    private void CancelReq() {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        API();
        Call<ResponseModelConfirmReq> CancelReq = api.CancelReq(new ReqBodyIDTransIDUser(ID, GlobalVar.ID));
        CancelReq.enqueue(new Callback<ResponseModelConfirmReq>() {
            @Override
            public void onResponse(Call<ResponseModelConfirmReq> call, Response<ResponseModelConfirmReq> response) {
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                pdLoading.dismiss();
                if (Kode.equals("1")) {
                    DialogNotifFinish("Berhasil", Message);
                } else {
                    DialogNotifFailed("Failed !", Message);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelConfirmReq> call, Throwable t) {
                DialogNotifError("Error !" ,t.getMessage().toString());
                pdLoading.dismiss();
            }
        });
    }

    public void DialogNotifFinish(String Title, String Message) {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.alert);
        message = d.findViewById(R.id.massage);
        title = d.findViewById(R.id.title);
        oke = d.findViewById(R.id.ok);
        anim = d.findViewById(R.id.anim);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                d.dismiss();
            }
        });
        title.setText(Title);
        message.setText(Message);
        Window window = d.getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        d.show();
    }

    private List<ModelRequestItem> listItem = new ArrayList<>();

    private DecimalFormat df;
    int grandTotal = 0;
    String pattern = "#,###,###,###,###.00";

    private void setUP() {
        listItem.clear();
        PdLoading();
        API();
        Call<ResponsModelRequestAll> requestAll = api.getRequestAll(new ReqBodyIDTransIDUser(ID, GlobalVar.ID));
        requestAll.enqueue(new Callback<ResponsModelRequestAll>() {
            @Override
            public void onResponse(Call<ResponsModelRequestAll> call, Response<ResponsModelRequestAll> response) {
                pdLoading.dismiss();
                alamatwarung.setText(response.body().getAlamataCompany());
                etalamatauser.setText(response.body().getUserAddress() + "," + response.body().getUserKota() + "," + response.body().getUserProvinsi());
                tglVisit.setText(response.body().getVisitDate());
                namawarung.setText(response.body().getNamaCompany());
                contact.setText(response.body().getContactPerson());
                note.setText(response.body().getNote());
                NumberFormat nf = NumberFormat.getInstance(Locale.US);
                df = (DecimalFormat) nf;
                {
                    df.applyPattern(pattern);
                }
                df.setDecimalSeparatorAlwaysShown(true);
                grandTotal = Integer.valueOf(response.body().getAmount()) + Integer.valueOf(response.body().getOrderFee());
                double grandTOTAL = Double.parseDouble(String.valueOf(grandTotal));
                grandtotal.setText(String.valueOf(df.format(grandTOTAL)));

                listItem = response.body().getItem();
                adapterRequestAll.setData(listItem);

            }

            @Override
            public void onFailure(Call<ResponsModelRequestAll> call, Throwable t) {

                pdLoading.dismiss();
            }
        });

        if(Status.equals("00")){
            cancel.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            close.setVisibility(View.GONE);
            bayar.setVisibility(View.GONE);
        } else if(Status.equals("20")) {
            bayar.setVisibility(View.VISIBLE);
        } else {

        }

    }

    @Override
    public void Detail(String MenuID) {

    }
}