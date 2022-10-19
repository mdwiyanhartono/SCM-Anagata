package dwiyan.com.scm_anagata.FragmentMainMenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import dwiyan.com.scm_anagata.Base.BaseFragment;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.R;
import dwiyan.com.scm_anagata.UpdatePassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Account extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account.
     */
    // TODO: Rename and change types and number of parameters
    public static Account newInstance(String param1, String param2) {
        Account fragment = new Account();
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

    Button btnlogout;
    ImageView imageprofile;
    LinearLayout lyubahkatasandi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        btnlogout = v.findViewById(R.id.logout);
        lyubahkatasandi = v.findViewById(R.id.lyubahkatasandi);
        lyubahkatasandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UpdatePassword.class);
                startActivity(i);

            }
        });
        imageprofile = v.findViewById(R.id.imageprofile);
        if(!GlobalVar.Image.isEmpty()){
            byte[] decodedString1 = Base64.decode(GlobalVar.Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1,0, decodedString1.length);
            imageprofile.setImageBitmap(decodedByte);
        } else {
            imageprofile.setImageResource(R.drawable.nodata);
        }
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API();
                Call<ResponseModelGlobal> Logout = api.LogOut(new RequestBodyUserId(GlobalVar.ID));
                Logout.enqueue(new Callback<ResponseModelGlobal>() {
                    @Override
                    public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                        Kode = response.body().getKode();
                        if(Integer.parseInt(Kode) == 1){
                            logout();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {

                    }
                });
            }
        });
        return  v;
    }
}