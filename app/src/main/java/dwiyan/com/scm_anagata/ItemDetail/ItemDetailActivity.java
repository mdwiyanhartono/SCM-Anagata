package dwiyan.com.scm_anagata.ItemDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.API.ApiRequestData;
import dwiyan.com.scm_anagata.API.Retroserver;
import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyItem;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.ResponseModelProduct;
import dwiyan.com.scm_anagata.ItemDetail.Adapter.AdapterListItem;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Keranjang;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItem;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelItem;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelKeranjang;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends BaseActivity implements AdapterListItem.OnItemClickListener {


    String IdMenu, IdCompany,ProductName,FileContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent i = getIntent();
        IdMenu = i.getStringExtra("IdMenu");
//        ProductName = i.getStringExtra("ProductName");
//        FileContent = i.getStringExtra("FileContent");
        setUp();
    }

    RecyclerView rcmenudetail;
//    CollapsingToolbarLayout collapsing_toolbar;
    ImageView imagebanner;
    CardView btnkeranjang;
    TextView labelbtnKeranjang,count;
    AdapterListItem adapterListMenu;
    private List<ModelItem> menulist = new ArrayList<>();
    LinearLayout ly2;
    private void setUp() {
        PdLoading();
//        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        rcmenudetail = findViewById(R.id.rc1);
        imagebanner = findViewById(R.id.imagedetail);
        btnkeranjang = findViewById(R.id.btnkeranjang);
        labelbtnKeranjang = findViewById(R.id.labelbtnKeranjang);
        count = findViewById(R.id.count);
        ly2 = findViewById(R.id.ly2);
        rcmenudetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcmenudetail.setItemAnimator(new DefaultItemAnimator());
        adapterListMenu = new AdapterListItem(this);
        adapterListMenu.setData(menulist);
        rcmenudetail.setAdapter(adapterListMenu);
        SetupData();
        cekKeranjang();
//        collapsing_toolbar.setTitle(ProductName);
//        if(!FileContent.isEmpty()){
//            byte[] decodedString1 = Base64.decode(FileContent, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1,0, decodedString1.length);
//            imagebanner.setImageBitmap(decodedByte);
//        }
        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemDetailActivity.this, Keranjang.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void SetupData() {
//        PdLoading();
        menulist.clear();
        API();
        Call<ResponsModelItem> menu = api.GetItem(new RequestBodyItem(IdMenu, GlobalVar.ID,""));
        menu.enqueue(new Callback<ResponsModelItem>() {
            @Override
            public void onResponse(Call<ResponsModelItem> call, Response<ResponsModelItem> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                menulist = response.body().getResult();
                adapterListMenu.setData(menulist);
//                rcmenudetail.setAdapter(adapterListMenu);
            }

            @Override
            public void onFailure(Call<ResponsModelItem> call, Throwable t) {
                pdLoading.dismiss();
                DialogNotifError("Error Detail Menu !", t.getMessage().toString());
            }

        });

    }

    private void cekKeranjang() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelKeranjang> keranjang = api.getkeranjang(new RequestBodyUserId(GlobalVar.ID));
        keranjang.enqueue(new Callback<ResponsModelKeranjang>() {
            @Override
            public void onResponse(Call<ResponsModelKeranjang> call, Response<ResponsModelKeranjang> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                String message = response.body() != null ? response.body().getMessage() : "Blank";
                if (kode.equals("1")) {
//                    ly2.setVisibility(View.VISIBLE);
                    String Count = response.body() != null ? response.body().getCount() : "0";
                    String Total = response.body() != null ? response.body().getTotal() : "0";
                    if (Integer.parseInt(Count) > 0) {
                        ly2.setVisibility(View.VISIBLE);
                        labelbtnKeranjang.setText("Lihat Keranjang-" + Total);
                        count.setText("Anda memilih-" + Count + " item");
                    } else {
                        ly2.setVisibility(View.GONE);
//                        btnkeranjang.setText("Back");
                    }

                } else {
                    ly2.setVisibility(View.GONE);
                    DialogNotifFailed("Failed !", message.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponsModelKeranjang> call, Throwable t) {
                DialogNotifError("Error !", t.getMessage().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetupData();
        cekKeranjang();
    }

    @Override
    public void onItemClick(String idmenu) {
        Intent i = new Intent(this, ItemDetail.class);
        i.putExtra("IDMenu" , idmenu);
        i.putExtra("ActivityID" , "2");
        startActivity(i);
//        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}