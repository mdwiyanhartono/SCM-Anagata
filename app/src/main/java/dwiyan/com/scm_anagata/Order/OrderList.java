package dwiyan.com.scm_anagata.Order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.API.ApiRequestData;
import dwiyan.com.scm_anagata.API.Retroserver;
import dwiyan.com.scm_anagata.Adapter.AdapterItemCategory;
import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ModelItemCategory;
import dwiyan.com.scm_anagata.DataModel.RequestBodyItem;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.ResponseModelCategory;
import dwiyan.com.scm_anagata.ItemDetail.Adapter.AdapterListItem;
import dwiyan.com.scm_anagata.ItemDetail.ItemDetail;
import dwiyan.com.scm_anagata.ItemDetail.ItemDetailActivity;
import dwiyan.com.scm_anagata.ItemDetail.Keranjang.Keranjang;
import dwiyan.com.scm_anagata.ItemDetail.Model.ModelItem;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelItem;
import dwiyan.com.scm_anagata.ItemDetail.ResponseModel.ResponsModelKeranjang;
import dwiyan.com.scm_anagata.Order.Adapter.AdapterCategory;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderList extends BaseActivity implements UpdateRC, AdapterListItem.OnItemClickListener {

    RecyclerView rc1,rcmenudetail;
    AdapterCategory adapterItemCategory;
    AdapterListItem adapterListMenu;
    CardView btnkeranjang;
    TextView labelbtnKeranjang,count;
    LinearLayout ly2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        rc1 = findViewById(R.id.rc1);
        rcmenudetail = findViewById(R.id.rc2);
        btnkeranjang = findViewById(R.id.btnkeranjang);
        labelbtnKeranjang = findViewById(R.id.labelbtnKeranjang);
        count = findViewById(R.id.count);
        ly2 = findViewById(R.id.ly2);
        rc1.setItemAnimator(new DefaultItemAnimator());
        rc1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterItemCategory =  new AdapterCategory(this);

        rcmenudetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcmenudetail.setItemAnimator(new DefaultItemAnimator());
        adapterListMenu = new AdapterListItem(this);
        adapterListMenu.setData(menulist);
        rcmenudetail.setAdapter(adapterListMenu);
        PdLoading();
        SetUpCategory();
        cekKeranjang();

        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrderList.this, Keranjang.class);
                startActivity(i);
//                finish();
            }
        });
    }

    private void cekKeranjang() {
        API();
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

    private List<ModelItemCategory> ItemCategoryList = new ArrayList<>();
    private void SetUpCategory() {
        ItemCategoryList.clear();
        API();
        Call<ResponseModelCategory> Category = api.GetCategoryALL();
        Category.enqueue(new Callback<ResponseModelCategory>() {
            @Override
            public void onResponse(Call<ResponseModelCategory> call, Response<ResponseModelCategory> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                if(Integer.parseInt(Kode) == 1){
                    ItemCategoryList = response.body().getResult();
                    adapterItemCategory.setData(ItemCategoryList);
                    rc1.setAdapter(adapterItemCategory);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelCategory> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }

    private List<ModelItem> menulist = new ArrayList<>();

    String Categid;
    @Override
    public void callback(String categid) {
        Categid = categid;
        GetMenu(Categid);
        cekKeranjang();
    }

    private void GetMenu(String categid) {
        PdLoading();
//        Toast.makeText(this, String.valueOf(position)+" Position", Toast.LENGTH_SHORT).show();
        menulist.clear();
        API();
        Call<ResponsModelItem> menu = api.GetItem(new RequestBodyItem(categid, GlobalVar.ID));
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

    @Override
    public void onItemClick(String idmenu) {
        Intent i = new Intent(this, ItemDetail.class);
        i.putExtra("IDMenu" , idmenu);
        i.putExtra("ActivityID" , "3");
        startActivity(i);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cekKeranjang();
//        SetUpCategory();
//        GetMenu(Categid);
    }
}