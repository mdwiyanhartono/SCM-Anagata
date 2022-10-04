package dwiyan.com.scm_anagata.FragmentMainMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dwiyan.com.scm_anagata.Adapter.AdapterChat;
import dwiyan.com.scm_anagata.Base.BaseFragment;
import dwiyan.com.scm_anagata.Base.GlobalVar;
import dwiyan.com.scm_anagata.DataModel.ModelChat;
import dwiyan.com.scm_anagata.DataModel.RequestBodyChat;
import dwiyan.com.scm_anagata.DataModel.RequestBodyUserId;
import dwiyan.com.scm_anagata.DataModel.ResponseModelCategory;
import dwiyan.com.scm_anagata.DataModel.ResponseModelChat;
import dwiyan.com.scm_anagata.DataModel.ResponseModelGlobal;
import dwiyan.com.scm_anagata.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Chat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chat extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Chat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chat.
     */
    // TODO: Rename and change types and number of parameters
    public static Chat newInstance(String param1, String param2) {
        Chat fragment = new Chat();
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


    EditText chat;
    RecyclerView RcComment;
    ImageView ButtonSendComment;
    AdapterChat adapterChat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chat, container, false);

        chat = v.findViewById(R.id.chat);
        RcComment = v.findViewById(R.id.RcComment);
        RcComment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        RcComment.setItemAnimator(new DefaultItemAnimator());
        adapterChat = new AdapterChat();
        SetUpChat();
        ButtonSendComment = v.findViewById(R.id.ButtonSendComment);
        ButtonSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chat.getText().toString().isEmpty()){
                    chat.setError("harus diisi");
                } else {
                    PdLoading();
                    API();
                    Call<ResponseModelGlobal> SendChat = api.SendChat( new RequestBodyChat(GlobalVar.ID,chat.getText().toString(),"0"));
                    SendChat.enqueue(new Callback<ResponseModelGlobal>() {
                        @Override
                        public void onResponse(Call<ResponseModelGlobal> call, Response<ResponseModelGlobal> response) {
                            pdLoading.dismiss();
                            Kode = response.body().getKode();
                            Message = response.body().getMessage();
                            if(Integer.parseInt(Kode) == 1){
                                Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
//                                DialogNotifSuccessAutoClose("Berhasil",Message);
                                chat.setText("");
                                SetUpChat();
                            } else {
                                DialogNotifFailed("Gagal" , Message);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModelGlobal> call, Throwable t) {
                            pdLoading.dismiss();
                        }
                    });
                }

            }
        });

        return v;
    }

    private List<ModelChat> modelChat = new ArrayList<>();
    private void SetUpChat() {
        PdLoading();
        modelChat.clear();
        API();
        Call<ResponseModelChat> getComplaintComment = api.GetChat(new RequestBodyUserId(GlobalVar.ID));
        getComplaintComment.enqueue(new Callback<ResponseModelChat>() {
            @Override
            public void onResponse(Call<ResponseModelChat> call, Response<ResponseModelChat> response) {
                Kode = response.body().getKode();
                pdLoading.dismiss();
                if (Integer.parseInt(Kode) > 0) {
                    modelChat = response.body().getResult();
                }
                adapterChat.setData(modelChat);
                adapterChat.notifyDataSetChanged();
                RcComment.setAdapter(adapterChat);
                RcComment.smoothScrollToPosition(RcComment.getAdapter().getItemCount());
                RcComment.findViewHolderForLayoutPosition(RcComment.getAdapter().getItemCount());
            }

            @Override
            public void onFailure(Call<ResponseModelChat> call, Throwable t) {
                DialogNotifError("Error !", t.getMessage().toString());
                pdLoading.dismiss();
            }
        });
    }
}