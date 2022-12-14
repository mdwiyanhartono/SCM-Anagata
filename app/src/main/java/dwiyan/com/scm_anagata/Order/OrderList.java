package dwiyan.com.scm_anagata.Order;

import static dwiyan.com.scm_anagata.PaginationScrollListener.PAGE_START;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.API.ApiRequestData;
import dwiyan.com.scm_anagata.API.Retroserver;
import dwiyan.com.scm_anagata.Adapter.AdapterItemCategory;
import dwiyan.com.scm_anagata.Base.BaseActivity;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ModelItemCategory;
import dwiyan.com.scm_anagata.DataModel.RequestBodyItem;
import dwiyan.com.scm_anagata.DataModel.RequestBodyItemPagination;
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
import dwiyan.com.scm_anagata.Order.Model.ModelItemPagination;
import dwiyan.com.scm_anagata.PaginationScrollListener;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderList extends BaseActivity implements UpdateRC, AdapterListItem.OnItemClickListener {

    RecyclerView rc1, rcmenudetail;
    AdapterCategory adapterItemCategory;
    AdapterListItem adapterListMenu;
    CardView btnkeranjang;
    TextView labelbtnKeranjang, count;
    EditText cari;
    String sCari = null;
    LinearLayout ly2;
    NestedScrollView nsc;
    ProgressBar loadingPB;
    private boolean isLoading = false;
    int itemCount = 0;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        loadingPB = findViewById(R.id.idPBLoading);
        rc1 = findViewById(R.id.rc1);
        nsc = findViewById(R.id.nsc);
        rcmenudetail = findViewById(R.id.rc2);
        btnkeranjang = findViewById(R.id.btnkeranjang);
        labelbtnKeranjang = findViewById(R.id.labelbtnKeranjang);
        count = findViewById(R.id.count);
        cari = findViewById(R.id.cari);
        ly2 = findViewById(R.id.ly2);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rc1.setItemAnimator(new DefaultItemAnimator());
        rc1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterItemCategory = new AdapterCategory(this);
        loadingPB.setVisibility(View.GONE);
        rcmenudetail.setLayoutManager(layoutManager);
        rcmenudetail.setItemAnimator(new DefaultItemAnimator());
        nsc.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    currentPage++;
//                    PdLoading();
                    loadingPB.setVisibility(View.VISIBLE);
                    GetMenu(Categid);
//                    Toast.makeText(OrderList.this, "Load More Page" + currentPage, Toast.LENGTH_SHORT).show();
//                    loadingPB.setVisibility(View.VISIBLE);
//                    getDataFromAPI(page, limit);
                }
            }
        });
//        rcmenudetail.addOnScrollListener(new PaginationScrollListener(layoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                currentPage++;
////                GetMenu(Categid);
//                Toast.makeText(OrderList.this, "Load More Page" + currentPage , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
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

        cari.addTextChangedListener(new TextWatcher() {

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
                if (s.length() != 0) {
                    sCari = cari.getText().toString();
                    menulist.clear();
                    currentPage = PAGE_START;
                    GetMenu(Categid);
                } else {

                }
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
                if (Integer.parseInt(Kode) == 1) {
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

    private ArrayList<ModelItemPagination> menulist = new ArrayList<>();

    String Categid;

    @Override
    public void callback(String categid) {
        Categid = categid;
        cari.setText("");
        sCari = null;
        menulist.clear();
        itemCount = 0;
        currentPage = PAGE_START;
        PdLoading();
        GetMenu(Categid);
        cekKeranjang();
    }

    private void GetMenu(String categid) {
//        menulist.clear();
//        PdLoading();
        API();
        Call<ResponsModelItem> menu = api.GetItemPagination(new RequestBodyItemPagination(categid, GlobalVar.ID, sCari, String.valueOf(currentPage), String.valueOf(currentPage)));
        menu.enqueue(new Callback<ResponsModelItem>() {
            @Override
            public void onResponse(Call<ResponsModelItem> call, Response<ResponsModelItem> response) {
                pdLoading.dismiss();
                Kode = response.body().getKode();
                Message = response.body().getMessage();
                for (int i = 0; i < response.body().getResult().size(); i++) {
                    menulist.add(new ModelItemPagination(
                            response.body().getResult().get(i).getItemid(),
                            response.body().getResult().get(i).getCountChart(),
                            response.body().getResult().get(i).getItemname(),
                            response.body().getResult().get(i).getItemuom(),
                            response.body().getResult().get(i).getItemcode(),
                            response.body().getResult().get(i).getItemdesc(),
                            response.body().getResult().get(i).getItemprice(),
                            response.body().getResult().get(i).getItempriceview(),
                            response.body().getResult().get(i).getImageFileContent(),
                            response.body().getResult().get(i).getImageFileContent()
                    ));

                    itemCount++;
//                    Toast.makeText(OrderList.this, "Count " + itemCount, Toast.LENGTH_SHORT).show();
                }
//                menulist = response.body().getResult();
                adapterListMenu.setData(menulist);
                rcmenudetail.setAdapter(adapterListMenu);
                loadingPB.setVisibility(View.GONE);
//                itemCount = menulist.size() + itemCount;
//                Toast.makeText(OrderList.this, "Count " + itemCount, Toast.LENGTH_SHORT).show();
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
        i.putExtra("IDMenu", idmenu);
        i.putExtra("ActivityID", "3");
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