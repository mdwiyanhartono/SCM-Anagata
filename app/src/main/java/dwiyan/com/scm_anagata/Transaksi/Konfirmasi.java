package dwiyan.com.scm_anagata.Transaksi;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ModelConfirmTrans;
import dwiyan.com.scm_anagata.DataModel.RequestBodyConfirmtrans;
import dwiyan.com.scm_anagata.DataModel.RequestBodyIdItem;
import dwiyan.com.scm_anagata.DataModel.ResponseModelConfirmasiTrans;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.ItemDetail.Adapter.AdapterListItem;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Model.ResponseModelConfirmReq;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.VAPayment.VirtualAccountActivity;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelItemDetail;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.Transaksi.Adapter.AdapterListItemTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Konfirmasi extends BaseActivity implements AdapterListItemTransaction.OnItemClickListener {

    String IDtrans;
    TextView CodeTrans, statustrans, item, deliveryfee, totalprice, alamatcompany, companyname, tanggalorder, emailcompany, phonecompany;
    RecyclerView rcitem;
    ImageView up, down;
    CardView btnclose, btnsubmit, btncancel, btnbayar;
    private List<ModelConfirmTrans> assetslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        Intent i = getIntent();
        IDtrans = i.getStringExtra("IDTrans");
        SetUp();
    }

    AdapterListItemTransaction adapterListMenu;
    CardView btnpaymentgateway,btnpaymentmanual;
    ImageView close;
    private void SetUp() {
        PdLoading();
        CodeTrans = findViewById(R.id.CodeTrans);
        btnclose = findViewById(R.id.btnclose);
        btnsubmit = findViewById(R.id.btnsubmit);
        btncancel = findViewById(R.id.btncancel);
        btnbayar = findViewById(R.id.btnbayar);

        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog d = new Dialog(Konfirmasi.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.confirmasi_pembayaran);
                btnpaymentgateway.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Konfirmasi.this, VirtualAccountActivity.class);
                        i.putExtra("MasterTransId" ,IDtrans );
                    }
                });
                Window window = d.getWindow();
                window.setGravity(Gravity.CENTER_VERTICAL);
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
                d.show();
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdLoading();
                API();
                Call<ResponseModelGlobal> cancel = api.CancelTrans(new RequestBodyConfirmtrans(GlobalVar.ID, IDtrans));
                cancel.enqueue(new Callback<ResponseModelGlobal>() {
                    @Override
                    public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                        Kode = response.body().getKode();
                        Message = response.body().getMessage();
                        pdLoading.dismiss();
                        if (Integer.parseInt(Kode) == 1) {
                            DialogSuccessBackActivity("Berhasil", Message);
                        } else {
                            DialogNotifFailed("Gagal", Message);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                        pdLoading.dismiss();
                        DialogNotifError("Error", t.getMessage());
                    }
                });
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PdLoading();
                API();
                Call<ResponseModelGlobal> confirmtrans = api.ConfirmTrans(new RequestBodyConfirmtrans(GlobalVar.ID, IDtrans));
                confirmtrans.enqueue(new Callback<ResponseModelGlobal>() {
                    @Override
                    public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                        Kode = response.body().getKode();
                        Message = response.body().getMessage();
                        pdLoading.dismiss();
                        if (Integer.parseInt(Kode) == 1) {
                            DialogSuccessBackActivity("Berhasil", Message);
                        } else {
                            DialogNotifFailed("Gagal", Message);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                        pdLoading.dismiss();
                        DialogNotifError("Error", t.getMessage());
                    }
                });
            }
        });
        statustrans = findViewById(R.id.statustrans);
        item = findViewById(R.id.item);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        deliveryfee = findViewById(R.id.deliveryfee);
        totalprice = findViewById(R.id.totalprice);
        alamatcompany = findViewById(R.id.alamatcompany);
        companyname = findViewById(R.id.companyname);
        tanggalorder = findViewById(R.id.tanggalorder);
        emailcompany = findViewById(R.id.emailcompany);
        phonecompany = findViewById(R.id.phonecompany);
        rcitem = findViewById(R.id.rcitem);
        adapterListMenu = new AdapterListItemTransaction(this);
        adapterListMenu.setData(assetslist);
        SetUpData(IDtrans);
        rcitem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcitem.setItemAnimator(new DefaultItemAnimator());
        rcitem.setAdapter(adapterListMenu);

    }

    private void SetUpData(String iDtrans) {
        assetslist.clear();
        API();
        Call<ResponseModelConfirmasiTrans> confirmdata = api.GetConfirmTransaksi(new RequestBodyConfirmtrans(GlobalVar.ID, iDtrans));
        confirmdata.enqueue(new Callback<ResponseModelConfirmasiTrans>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ResponseModelConfirmasiTrans> call, Response<ResponseModelConfirmasiTrans> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                assetslist = response.body().getResult();
                adapterListMenu.setData(assetslist);
                CodeTrans.setText(response.body().getTransactioncode());
                statustrans.setText(response.body().getTransactionStatus());
                item.setText(response.body().getTransactionQnty() + " Items");
                df = (DecimalFormat) nf;
                {
                    df.applyPattern(pattern);
                }
                df.setDecimalSeparatorAlwaysShown(true);

                double delivfee = Double.parseDouble(response.body().getTransactionDeliveryFee());
                double grandtotal = Double.parseDouble(response.body().getTransactionGrandTotal());
                deliveryfee.setText(df.format(delivfee));
                totalprice.setText(df.format(grandtotal));
                alamatcompany.setText(response.body().getCompanyaddress() + " \u2022 " + response.body().getCompanycity() + " \u2022 " + response.body().getCompanyprovince() + " \u2022 " + response.body().getCompanycodepos());
                companyname.setText(response.body().getCompanyname() + "  \u2022  " + response.body().getTransactionDate());
                emailcompany.setText(response.body().getCompanyemail());
                phonecompany.setText(response.body().getCompanyphone());
                statustrans.setTextColor(R.color.yellow);

                if (Integer.parseInt(response.body().getTransactionStatusID()) == 2 && Integer.parseInt(response.body().getTransactionStatusID()) == 3) {
                    btnclose.setVisibility(View.VISIBLE);
                } else if (Integer.parseInt(response.body().getTransactionStatusID()) == 1) {
                    btnclose.setVisibility(View.GONE);
                    btnsubmit.setVisibility(View.VISIBLE);
                    btncancel.setVisibility(View.VISIBLE);
                } else {
                    btnclose.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelConfirmasiTrans> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error", t.getMessage());
            }
        });
    }

    public void viewrc(View view) {
        rcitem.setVisibility(View.VISIBLE);
        up.setVisibility(View.VISIBLE);
        down.setVisibility(View.GONE);
    }

    public void downrc(View view) {
        rcitem.setVisibility(View.GONE);
        up.setVisibility(View.GONE);
        down.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(String idmenu) {

    }
}