package dwiyan.com.scm_anagata.FragmentMainMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.API.ApiRequestData;
import dwiyan.com.scm_anagata.API.Retroserver;
import dwiyan.com.scm_anagata.Adapter.AdapterItemCategory;
import dwiyan.com.scm_anagata.Adapter.SliderAdapterInfo;
import dwiyan.com.scm_anagata.Base.BaseFragment;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ModelBanner;
import dwiyan.com.scm_anagata.DataModel.ModelItemCategory;
import dwiyan.com.scm_anagata.DataModel.ResponseModelBanner;
import dwiyan.com.scm_anagata.DataModel.ResponseModelCategory;
import dwiyan.com.scm_anagata.History.History;
import dwiyan.com.scm_anagata.ItemDetail.ItemDetailActivity;
import dwiyan.com.scm_anagata.Order.Order;
import dwiyan.com.scm_anagata.Order.OrderList;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends BaseFragment implements AdapterItemCategory.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    SliderView sliderView ;
    SliderAdapterInfo adapterInfo;
    TextView nama,username;
    CardView cdvPaket, cdvNonePaket,infocd,nullcd;
    RecyclerView rc1;
    AdapterItemCategory adapterItemCategory;
    CardView history,order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        PdLoading();
        history = v.findViewById(R.id.history);
        order = v.findViewById(R.id.order);
        nama = v.findViewById(R.id.nama);
        username = v.findViewById(R.id.username);
        sliderView = v.findViewById(R.id.imageSlider);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        adapterInfo = new SliderAdapterInfo(getActivity());
        sliderView.setSliderAdapter(adapterInfo);
        infocd = v.findViewById(R.id.infocd);
        nullcd = v.findViewById(R.id.nullcd);
        setupSlider();

        nama.setText(GlobalVar.NAMA);
        username.setText(GlobalVar.EMAIL);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), History.class);
                startActivity(i);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), OrderList.class);
                startActivity(i);
            }
        });

        rc1 = v.findViewById(R.id.rc1);
        rc1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rc1.setItemAnimator(new DefaultItemAnimator());
        adapterItemCategory =  new AdapterItemCategory(this);

        SetupCategory();
        return v;
    }
    private List<ModelItemCategory> ItemCategoryList = new ArrayList<>();
    private void SetupCategory() {
        ItemCategoryList.clear();
        ApiRequestData api = Retroserver.getClient(getActivity().getApplicationContext()).create(ApiRequestData.class);
        Call<ResponseModelCategory> Category = api.GetCategory();
        Category.enqueue(new Callback<ResponseModelCategory>() {
            @Override
            public void onResponse(Call<ResponseModelCategory> call, Response<ResponseModelCategory> response) {
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

            }
        });
    }

    private List<ModelBanner> bannerList = new ArrayList<>();
    private void setupSlider()  {
        bannerList.clear();
        ApiRequestData api = Retroserver.getClient(getActivity().getApplicationContext()).create(ApiRequestData.class);
        Call<ResponseModelBanner> Banner = api.GetBanner();
        Banner.enqueue(new Callback<ResponseModelBanner>() {
            @Override
            public void onResponse(Call<ResponseModelBanner> call, Response<ResponseModelBanner> response) {

                Kode = response.body().getKode();
                Message = response.body().getMessage();
                bannerList = response.body().getResult();
                if(bannerList.size() > 0){
                    infocd.setVisibility(View.VISIBLE);
                    nullcd.setVisibility(View.GONE);
                }
                adapterInfo.renewItems(bannerList);
                sliderView.setSliderAdapter(adapterInfo);
                pdLoading.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModelBanner> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }

    @Override
    public void onItemClick(String IdProduct, String ProductName, String FileContent) {
        Intent i = new Intent(getActivity(), ItemDetailActivity.class);
        i.putExtra("IdMenu", IdProduct);
        i.putExtra("ProductName", ProductName);
        i.putExtra("FileContent", FileContent);
        startActivity(i);
    }
}