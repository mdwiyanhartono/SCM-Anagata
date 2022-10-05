package dwiyan.com.scm_anagata.ItemDetail;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyIdItem;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.Model.ReqBodyKeranjang;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelItemDetail;
import dwiyan.com.scm_anagata.Order.OrderList;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetail extends BaseActivity {

    //    Button btnsubmit;
    CardView min, add;
    ImageView imagemenu;
    TextView desc, menuname, harga;
    EditText counter;
    EditText note;
    String MenuID, ActivityID, KeranjangID, CompanyID, countkeranjang = "0";
    int addcounter = 1;
    private List<ModelItemDetail> datamenu = new ArrayList<>();

    TextView labelbtn,uom;
    CardView btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_1);
        getuser();
        Intent i = getIntent();
        MenuID = i.getStringExtra("IDMenu");
        ActivityID = i.getStringExtra("ActivityID");
        Toast.makeText(this, MenuID, Toast.LENGTH_SHORT).show();
        setUp();
        setUpData();
    }

    private void setUpData() {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        API();
        Call<ResponsModelItemDetail> Menu = api.getItemDetail(new RequestBodyIdItem(MenuID, GlobalVar.ID));
        Menu.enqueue(new Callback<ResponsModelItemDetail>() {
            @Override
            public void onResponse(Call<ResponsModelItemDetail> call, Response<ResponsModelItemDetail> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                String message = response.body() != null ? response.body().getMessage() : "Blank";
                if (kode.equals("1")) {
                    datamenu = response.body().getResult();
                    for (int i = 0; i < datamenu.size(); i++) {
                        menuname.setText(datamenu.get(i).getItemname());
                        uom.setText("Dalam satuan / "+datamenu.get(i).getItemuom());
                        desc.setText(datamenu.get(i).getItemdesc());
                        harga.setText(" Rp. "+datamenu.get(i).getItempriceview());
                        price = datamenu.get(i).getItemprice();
                        counter.setText(datamenu.get(i).getCountkeranjang());
                        countkeranjang = datamenu.get(i).getCountkeranjang();
//                        note.setText(datamenu.get(i).getNote());
                        KeranjangID = datamenu.get(i).getIdtranschart();
                        if (Integer.valueOf(datamenu.get(i).getCountkeranjang()) > 0) {
                            labelbtn.setText("Pesanan " + datamenu.get(i).getCountkeranjang() + "- Rp." + datamenu.get(i).getTotal());
                        }
                        if (!datamenu.get(i).getImagefile().isEmpty()) {
                            byte[] decodedString1 = Base64.decode(datamenu.get(i).getImagefile(), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
                            imagemenu.setImageBitmap(decodedByte);
                        }

                    }
                } else {
                    DialogNotifFailed("Failed !", message);
                }
                pdLoading.dismiss();
            }

            @Override
            public void onFailure(Call<ResponsModelItemDetail> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error !", t.getMessage().toString());
            }
        });
    }


    private void setUp() {
        btnsubmit = findViewById(R.id.btnsubmit);
        min = findViewById(R.id.min);
        add = findViewById(R.id.add);
        imagemenu = findViewById(R.id.imagedetailmenu);
        desc = findViewById(R.id.desc);
        menuname = findViewById(R.id.namamenu);
        uom = findViewById(R.id.uom);
        counter = findViewById(R.id.counter);
        harga = findViewById(R.id.hargamenu);
        note = findViewById(R.id.etnote);
        labelbtn = findViewById(R.id.labelbtn);

        df = (DecimalFormat) nf;
        {
            df.applyPattern(pattern);
        }
        df.setDecimalSeparatorAlwaysShown(true);

        counter.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0 ){
                    int count1 = Integer.parseInt(counter.getText().toString());
                    if( count1 > 0){

                        df = (DecimalFormat) nf;
                        {
                            df.applyPattern(pattern);
                        }
                        df.setDecimalSeparatorAlwaysShown(true);

//                counter.setText(String.valueOf(count1));
                        String hargaa = price;
                        if (hargaa.contains(",")) {
                            hargaa = hargaa.replaceAll(",", "");
                        }
                        double price = Double.parseDouble(hargaa);
                        price = Math.ceil(price * count1);
                        if (Integer.valueOf(countkeranjang) > 0) {
//            btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1button));
                            labelbtn.setText("Update Keranjang-" + String.valueOf(df.format(price)));
                        } else {
//            btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1button));
                            labelbtn.setText("Tambahkan ke Keranjang-" + String.valueOf(df.format(price)));
                        }
                    } else {

                        if (Integer.valueOf(countkeranjang) > 0) {
                            labelbtn.setText("Hapus");
//                btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background_grad_red));
                        } else {
//                btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1));
                            labelbtn.setText("Back To Menu");
                        }
                    }
                } else {
                    counter.setText("0");
                }
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                setUpminOnclick();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpAddOnclick();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpSubmitOnclick();

            }
        });

    }

    private void setUpSubmitOnclick() {
        String Counter = counter.getText().toString();
        final ProgressDialog pdLoading = new ProgressDialog(ItemDetail.this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        if (Counter.equals("0")) {
            if (Integer.valueOf(countkeranjang) > 0) {
                RemoveItem();
            } else if (Integer.valueOf(ActivityID) == 2) {
                finish();
            } else {
                Intent i = new Intent(ItemDetail.this, ItemDetailActivity.class);
                i.putExtra("CompanyID", CompanyID);
                startActivity(i);
                finish();
            }
        } else {
            if (Integer.valueOf(countkeranjang) > 0) {
                UpdateItem();
            } else {
                AddItem();
            }

        }
        pdLoading.dismiss();
    }

    private void UpdateItem() {
        API();
        Call<ResponseModelGlobal> UpdateKeranjang = api.updatekeranjang(new ReqBodyKeranjang(GlobalVar.ID, MenuID, counter.getText().toString(), KeranjangID));
        UpdateKeranjang.enqueue(new Callback<ResponseModelGlobal>() {
            @Override
            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                String message = response.body() != null ? response.body().getMessage() : "Blank";
                setUpActivity();
            }

            @Override
            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                DialogNotifError("Error !", t.getMessage().toString());
            }
        });
    }

    private void AddItem() {
        API();
        Call<ResponseModelGlobal> InputKeranjang = api.inputkeranjang(new ReqBodyKeranjang(GlobalVar.ID, MenuID, counter.getText().toString(), ""));
        InputKeranjang.enqueue(new Callback<ResponseModelGlobal>() {
            @Override
            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                String message = response.body() != null ? response.body().getMessage() : "Blank";
                setUpActivity();
            }

            @Override
            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                DialogNotifError("Error !", t.getMessage().toString());
            }
        });
    }

    private void RemoveItem() {
        API();
        Call<ResponseModelGlobal> RemoveKeranjang = api.removekeranjang(new ReqBodyKeranjang(GlobalVar.ID, MenuID, "", KeranjangID));
        RemoveKeranjang.enqueue(new Callback<ResponseModelGlobal>() {
            @Override
            public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                String message = response.body() != null ? response.body().getMessage() : "Blank";
//                Toast.makeText(DetailMenuService.this, message, Toast.LENGTH_SHORT).show();
                setUpActivity();
            }

            @Override
            public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                DialogNotifError("Error !", t.getMessage().toString());
            }
        });
    }

    private void setUpActivity() {
        if (Integer.valueOf(ActivityID) == 0) {
            Intent i = new Intent(ItemDetail.this, ItemDetailActivity.class);
            i.putExtra("CompanyID", CompanyID);
            startActivity(i);
            finish();
        } else if (Integer.valueOf(ActivityID) == 2) {
            finish();
        } else if(Integer.valueOf(ActivityID) == 3) {
            Intent i = new Intent(ItemDetail.this, OrderList.class);
//            i.putExtra("CompanyID", CompanyID);
            startActivity(i);
            finish();
        }
    }


    @SuppressLint("NewApi")
    private void setUpAddOnclick() {
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        df = (DecimalFormat) nf;
        {
            df.applyPattern(pattern);
        }
        df.setDecimalSeparatorAlwaysShown(true);
        int count = Integer.parseInt(counter.getText().toString());
        count = count + addcounter;
        counter.setText(String.valueOf(count));
        String hargaa = price;
        if (hargaa.contains(",")) {
            hargaa = hargaa.replaceAll(",", "");
        }
        double price = Double.parseDouble(hargaa);
        price = Math.ceil(price * count);
        if (Integer.valueOf(countkeranjang) > 0) {
//            btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1button));
            labelbtn.setText("Update Keranjang-" + String.valueOf(df.format(price)));
        } else {
//            btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1button));
            labelbtn.setText("Tambahkan ke Keranjang-" + String.valueOf(df.format(price)));
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "NewApi"})
    private void setUpminOnclick() {
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        df = (DecimalFormat) nf;
        {
            df.applyPattern(pattern);
        }
        df.setDecimalSeparatorAlwaysShown(true);
        int count = Integer.parseInt(counter.getText().toString());
        count = count - addcounter;
        counter.setText(String.valueOf(count));
        String hargaa = price;
        if (hargaa.contains(",")) {
            hargaa = hargaa.replaceAll(",", "");
        }
        double price = Double.parseDouble(hargaa);
        price = Math.ceil(price * count);
        if (count < 1) {
            counter.setText("0");
            if (Integer.valueOf(countkeranjang) > 0) {
                labelbtn.setText("Hapus");
//                btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background_grad_red));
            } else {
//                btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1));
                labelbtn.setText("Back To Menu");
            }

        } else {
            if (Integer.valueOf(countkeranjang) > 0) {
//                btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1button));
                labelbtn.setText("Update Keranjang-" + String.valueOf(df.format(price)));
            } else {
//                btnsubmit.setBackground(getBaseContext().getDrawable(R.drawable.background1button));
                labelbtn.setText("Tambahkan ke Keranjang-" + String.valueOf(df.format(price)));
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setUpActivity();
    }
}